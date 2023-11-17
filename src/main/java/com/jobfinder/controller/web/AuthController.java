package com.jobfinder.controller.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jobfinder.dto.ApplicantDTO;
import com.jobfinder.dto.EmployerDTO;
import com.jobfinder.dto.UserDTO;
import com.jobfinder.entity.CodeMessage;
import com.jobfinder.service.IApplicantService;
import com.jobfinder.service.ICategoryService;
import com.jobfinder.service.IEmployerService;
import com.jobfinder.service.ISkillService;
import com.jobfinder.service.IUserService;
import com.jobfinder.validation.ApplicantValidation;
import com.jobfinder.validation.EmployerValidation;
import com.jobfinder.validation.UserValidation;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Controller
public class AuthController {
	
	@Autowired
	private ApplicantValidation applicantValidation;
	
	@Autowired
	private EmployerValidation employerValidation;
	
	@Autowired
	private IUserService userService;

	@Autowired
	private IApplicantService applicantService;
	
	@Autowired
	private IEmployerService employerService;
	
	@Autowired
	private ISkillService skillService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Configuration viewResolver;
	
	
	/**
	 * View login page
	 * 
	 * 10242023 NamHH
	 * 
	 * @author NamHH
	 *
	 */
	@RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
	public ModelAndView loginPage(@RequestParam(value = "message", required = false) String message) {
		ModelAndView mav = new ModelAndView("auth/login/login");
		if (message != null) {
			mav.addObject("message", message);
			mav.addObject("alert", "success");
		}
		return mav;
	}
	
	/**
	 * View forget password page
	 * 
	 * 10242023 NamHH
	 * 
	 * @author NamHH
	 *
	 */
	@RequestMapping(value = "/quen-mat-khau", method = RequestMethod.GET)
	public ModelAndView forgotPassword(@RequestParam(value = "message", required = false) String message) {
		ModelAndView mav = new ModelAndView("auth/login/forgot-password");
		return mav;
	}
	
	/**
	 * post mapping confirm email
	 * 
	 * 10242023 NamHH
	 * 
	 * @author NamHH
	 * @throws TemplateException 
	 * @throws IOException 
	 * @throws MessagingException 
	 *
	 */
	@RequestMapping(value = "/quen-mat-khau", method = RequestMethod.POST)
	public String postForgotPassword(@RequestParam(name="email") String email,
			RedirectAttributes redirectAttributes) throws MessagingException, IOException, TemplateException {
		//send email
		UserDTO user = userService.findOneByEmailAndStatus(email, 1);
		if(user==null) {
			redirectAttributes.addFlashAttribute("message", "Email Không hợp lệ hoặc chưa đăng ký");//truyen message loi toi view
			redirectAttributes.addFlashAttribute("alert", "danger");//truyen type message toi trang dang nhap
			return "redirect:/quen-mat-khau";
		}
		Random random = new Random();
        int code = random.nextInt(999999 - 100000 + 1) + 100000;//random code
        CodeMessage message = new CodeMessage(user.getLastName() + " " + user.getFirstName(), code);//set content message to send email
        
        sendEmail(email, "Thay đổi mật khẩu Jobfinder", message);
        redirectAttributes.addFlashAttribute("code", code);//truyen code toi view
        redirectAttributes.addFlashAttribute("user", user);//truyen user toi view
        redirectAttributes.addFlashAttribute("email", email);//truyen email toi view
        redirectAttributes.addFlashAttribute("message", "Đã gửi code. Vui lòng kiểm tra email và nhập mã");//truyen message thanh cong toi view
		redirectAttributes.addFlashAttribute("alert", "success");//truyen type message toi trang dang nhap
		return "redirect:/quen-mat-khau/xac-nhan-ma?email="+email;
	}
	
	/**
	 * View confirm code page
	 * 
	 * 10242023 NamHH
	 * 
	 * @author NamHH
	 *
	 */
	@RequestMapping(value = "/quen-mat-khau/xac-nhan-ma", method = RequestMethod.GET)
	public String confirmcode(@RequestParam(value = "message", required = false) String message,
			@RequestParam(value = "email", required = false) String email,
			HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		
		if(email==null) {//kiem tra neu email null thi bat buoc nhap email
			redirectAttributes.addFlashAttribute("message", "Hãy nhập email!");//truyen message loi toi view
			redirectAttributes.addFlashAttribute("alert", "danger");//truyen type message toi trang dang nhap
			return "redirect:/quen-mat-khau";
		}
		if (message != null) {
			model.addAttribute("message", message);//truyen message thanh cong toi view
			model.addAttribute("alert", "success");//truyen type message toi trang dang nhap
		}
		return "auth/login/confirm-code";
	}
	
	/**
	 * post mapping confirm code
	 * 
	 * 10242023 NamHH
	 * 
	 * @author NamHH
	 *
	 */
	@RequestMapping(value = "/quen-mat-khau/xac-nhan-ma", method = RequestMethod.POST)
	public String postConfirmCode(@RequestParam(value = "code") int code, 
			@RequestParam(value = "code_defaut") int codeDefault,
			@RequestParam(value = "email") String email,
			RedirectAttributes redirectAttributes) {
		
		if(code==codeDefault) {//kiem tra neu code hop le thi cho phep doi mat khau
			redirectAttributes.addFlashAttribute("email", email);
			return "redirect:/quen-mat-khau/doi-mat-khau?email="+email;
		}else {//neu code khong hop le thi thong bao loi va cho nhap lai
			redirectAttributes.addFlashAttribute("code", codeDefault);
			redirectAttributes.addFlashAttribute("email", email);
			redirectAttributes.addFlashAttribute("message", "Mã không đúng. Vui lòng nhập lại!");//truyen message thong bao loi
			redirectAttributes.addFlashAttribute("alert", "danger");//truyen type message toi trang dang nhap
			return "redirect:/quen-mat-khau/xac-nhan-ma?email="+email;
		}
	}
	
	/**
	 * View reset password page
	 * 
	 * 10242023 NamHH
	 * 
	 * @author NamHH
	 *
	 */
	@RequestMapping(value = "/quen-mat-khau/doi-mat-khau", method = RequestMethod.GET)
	public String resetPassword(@RequestParam(value = "email", required=false) String email,
			RedirectAttributes redirectAttributes, Model model) {
		if(email==null) {
			redirectAttributes.addFlashAttribute("message", "Hãy nhập email!");//truyen message loi toi view
			redirectAttributes.addFlashAttribute("alert", "danger");//truyen type message toi trang dang nhap
			return "redirect:/quen-mat-khau";
		}
		UserDTO user = userService.findOneByEmailAndStatus(email, 1);
		model.addAttribute("userDTO", new UserDTO());
		model.addAttribute("user", user);
		return "auth/login/reset-password";
	}
	
	/**
	 * post mapping reset password
	 * 
	 * 10242023 NamHH
	 * 
	 * @author NamHH
	 *
	 */
	@RequestMapping(value = "/quen-mat-khau/doi-mat-khau", method = RequestMethod.POST)
	public String postResetPassword(@Valid UserDTO userDTO, BindingResult bindingResult, 
			RedirectAttributes redirectAttributes, Model model) {
		//validation cho form doi mat khau
		UserValidation userValidation = new UserValidation();
		userValidation.validate(userDTO, bindingResult);
		if (bindingResult.hasErrors()) {
			//neu co loi return ve lai form va hien thi loi
			UserDTO user = userService.findOneByEmailAndStatus(userDTO.getEmail(), 1);
			model.addAttribute("user", user);
			return "auth/login/reset-password";
		}
		userService.resetPassword(userDTO);
		redirectAttributes.addFlashAttribute("message", "Đổi mật khẩu thành công");//truyen message loi toi view
		redirectAttributes.addFlashAttribute("alert", "success");//truyen type message toi trang dang nhap
		return "redirect:/dang-nhap";
	}
	
	/**
	 * Method Logout
	 * 
	 * 10242023 NamHH
	 * 
	 * @author NamHH
	 *
	 */
	@RequestMapping(value = "/thoat", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/trang-chu");
	}
	
	/**
	 * View register page
	 * 
	 * 10242023 NamHH
	 * 
	 * @author NamHH
	 *
	 */
	@RequestMapping(value = "/dang-ky", method = RequestMethod.GET)
	public String registerPage(Model model) {
		return "auth/register/register";
	}

	@RequestMapping(value = "/dang-ky-nguoi-tim-viec", method = RequestMethod.GET)
	public String registerApplicant(Model model) {
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("skills", skillService.findAll());
		model.addAttribute("applicantDTO", new ApplicantDTO());
		return "auth/register/applicant-register";
	}

	@RequestMapping(value = "/dang-ky-nha-tuyen-dung", method = RequestMethod.GET)
	public String registerEmployer(Model model) {
		model.addAttribute("employerDTO", new EmployerDTO());
		return "auth/register/employer-register";
	}
	
	/**
	 * Method post applicant
	 * 
	 * 10242023 NamHH
	 * 
	 * @author NamHH
	 *
	 */
	@RequestMapping(value = "/dang-ky-nguoi-tim-viec", method = RequestMethod.POST)
	public String registerApplicant(@Valid ApplicantDTO applicantDTO, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		applicantValidation.validate(applicantDTO, bindingResult);//validation cho form dang ky nguoi tim viec
		if (bindingResult.hasErrors()) {
			//neu co loi return ve lai form va hien thi loi
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("skills", skillService.findAll());
			return "auth/register/applicant-register";
		}
		applicantService.save(applicantDTO);//luu vao database
		redirectAttributes.addFlashAttribute("message", "Register successfully");//truyen message thanh cong toi trang dang nhap
		redirectAttributes.addFlashAttribute("alert", "success");//truyen type message toi trang dang nhap
		return "redirect:/dang-nhap";
	}
	
	public ApplicantValidation getApplicantValidator() {
        return applicantValidation;
    }
 
    public void setApplicantValidator(ApplicantValidation applicantValidation) {
        this.applicantValidation = applicantValidation;
    }
    
    /**
	 * Method post employer
	 * 
	 * 10242023 NamHH
	 * 
	 * @author NamHH
	 *
	 */
	@RequestMapping(value = "/dang-ky-nha-tuyen-dung", method = RequestMethod.POST)
	public String registerEmployer(@Valid EmployerDTO employerDTO, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		employerValidation.validate(employerDTO, bindingResult);//validation cho form dang ky nha tuyen dung
		if (bindingResult.hasErrors()) {
			//neu co loi return ve lai form va hien thi loi
			return "auth/register/employer-register";
		}
		employerService.save(employerDTO);
		redirectAttributes.addFlashAttribute("message", "Register successfully");//truyen message thanh cong toi trang dang nhap
		redirectAttributes.addFlashAttribute("alert", "success");//truyen type message toi trang dang nhap
		return "redirect:/dang-nhap";
	}
	
	public EmployerValidation getEmployerValidation() {
        return employerValidation;
    }
 
    public void setEmployerValidation(EmployerValidation employerValidation) {
        this.employerValidation = employerValidation;
    }
    
    
    /**
	 * Method denied login
	 * 
	 * 10242023 NamHH
	 * 
	 * @author NamHH
	 *
	 */
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/dang-nhap?accessDenied");
	}
	
	public void sendEmail(String emailTo, String subject, CodeMessage codeMessage) throws MessagingException, IOException, TemplateException {
	    mailSender.send(new MimeMessagePreparator() {
	        @Override
	        public void prepare(MimeMessage mimeMessage) throws Exception {
	            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
	            messageHelper.setTo(emailTo);
	            messageHelper.setSubject(subject);

	            // Use a method to generate email content from JSP template
	            String emailContent = generateEmailContent(codeMessage);

	            messageHelper.setText(emailContent, true);
	         
	        }
	    });
	}
	private String generateEmailContent(CodeMessage codeMessage) throws TemplateException, IOException {
	    Template template = viewResolver.getTemplate("auth/login/sendCodeEmail.jsp");
	    Map<String, Object> model = new HashMap<>();
	    model.put("name", codeMessage.getName());
	    model.put("code", codeMessage.getCode());
	    return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
	}
}
