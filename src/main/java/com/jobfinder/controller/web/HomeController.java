package com.jobfinder.controller.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jobfinder.dto.ApplicantDTO;
import com.jobfinder.dto.CategoryDTO;
import com.jobfinder.dto.EmployerDTO;
import com.jobfinder.dto.JobDTO;
import com.jobfinder.dto.UserDTO;
import com.jobfinder.security.BcryptPassword;
import com.jobfinder.service.IApplicantService;
import com.jobfinder.service.ICategoryService;
import com.jobfinder.service.IEmployerService;
import com.jobfinder.service.IJobService;
import com.jobfinder.service.IUserService;
import com.jobfinder.util.SecurityUtils;
import com.jobfinder.validation.ResetPassValidation;

@Controller(value = "homeControllerOfWeb")
public class HomeController {
	
	@Autowired
	private IJobService jobService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private IEmployerService employerService;
	
	@Autowired
	private IApplicantService applicantService;
	
	/**
	 * function check user logging in is applicant
	 * 
	 * 11102023 NamHH
	 * 
	 * @author NamHH
	 */
	public boolean isApplicant() {
		boolean result = false;
		for(ApplicantDTO applicant: applicantService.findAll()) {// find user employer logging in
			if(Long.parseLong(SecurityUtils.getPrincipal().getId()) == applicant.getUser_id()) {
				result = true;
			}
		}
		return result;
	}
	
	/**
	 * function check role user logging in
	 * 
	 * 11102023 NamHH
	 * 
	 * @author NamHH
	 */
	public int roleUserLogginIn() {
		int result = 1;
		for(ApplicantDTO applicant: applicantService.findAll()) {//check user logging in is applicant
			if(Long.parseLong(SecurityUtils.getPrincipal().getId()) == applicant.getUser_id()) {
				result = 3;
			}
		}
		for(EmployerDTO employer: employerService.findAll()) {//check user logging in is employer
			if(Long.parseLong(SecurityUtils.getPrincipal().getId()) == employer.getUser_id()) {
				result = 2;
			}
		}
		return result;
	}
	
	/**
	 * method get mapping return home page
	 * @author namHH
	 * 
	 * @return view jsp
	 */
	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage() {
		List<JobDTO> listJob = jobService.findAll();//get all job
		List<JobDTO> jobs = new ArrayList<>();
		
		List<CategoryDTO> ListCategory = categoryService.findAll();//get all category
		List<CategoryDTO> categories = new ArrayList<>();
		
		if(listJob.size()>4) {//get 4 job from list job
			for(int i=0 ; i<4 ; i++) {
				jobs.add(listJob.get(i));
			}
		}else {			
			jobs=listJob;
		}
		
		if(ListCategory.size()>4) {//get 4 category from list category
			for(int i=0 ; i<4 ; i++) {
				categories.add(ListCategory.get(i));
			}
		}else {			
			categories=ListCategory;
		}
		ModelAndView mav = new ModelAndView("web/home");//create model view jsp
		
		//kiem tra nguoi dung da dang nhap
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!authentication.getName().equals("anonymousUser")) {
			if(roleUserLogginIn()==2){//nguoi dung la employer
				mav.addObject("role", 2);
			}else if(roleUserLogginIn()==3){//nguoi dung la applicant
				mav.addObject("role", 3);
			}else {//nguoi dung la admin
				mav.addObject("role", 1);
			}
		}
		mav.addObject("jobs", jobs);// push jobs to view
		mav.addObject("categories", categories);// push categories to view
		mav.addObject("users", userService.findAll());// push users to view
		mav.addObject("employers", employerService.findAll());// push employers to view
		mav.addObject("applicants", applicantService.findAll());// push employers to view
		return mav;
	}
	
	@RequestMapping(value = "/thong-tin-ca-nhan", method = RequestMethod.GET)
	public String showEditForm(@RequestParam(value = "id") Long id, Model model,
			RedirectAttributes redirectAttributes) {
		// Kiểm tra xem người dùng đã đăng nhập hay chưa
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!authentication.getName().equals("anonymousUser")) {
			if(isApplicant()){//kiem tra nguoi dung co duoc quyen thay doi thong tin khong
				model.addAttribute("user", userService.findById(id));
				model.addAttribute("users", userService.findAll());// push users to view
				model.addAttribute("applicant", applicantService.findByUserId(id));
				return "web/user-profile";
			}
			redirectAttributes.addFlashAttribute("message", "Tài khoản của bạn không có quyền truy cập!");//truyen message thanh cong toi trang dang nhap
			redirectAttributes.addFlashAttribute("alert", "danger");//truyen type message toi trang dang nhap
			return "redirect:/dang-nhap";
		}
		redirectAttributes.addFlashAttribute("message", "Bạn phải đăng nhập");//truyen message thanh cong toi trang dang nhap
		redirectAttributes.addFlashAttribute("alert", "danger");//truyen type message toi trang dang nhap
		return "redirect:/dang-nhap";
	}

	@RequestMapping(value = "/thong-tin-ca-nhan", method = RequestMethod.POST)
	public String updateUser(@RequestParam(value = "id") Long userId, UserDTO userDTO
			, RedirectAttributes redirectAttributes) {
		userService.save(userDTO);
		redirectAttributes.addFlashAttribute("message", "Đổi thông tin thành công");//truyen message thanh cong toi trang dang nhap
		redirectAttributes.addFlashAttribute("alert", "success");//truyen type message toi trang dang nhap
		return "redirect:/thong-tin-ca-nhan?id=" + userId;
	}
	
	@RequestMapping(value = "/quan-ly-tai-khoan", method = RequestMethod.GET)
	public String accountManager(@RequestParam(value = "id") Long id, Model model,
			RedirectAttributes redirectAttributes) {
		// Kiểm tra xem người dùng đã đăng nhập hay chưa
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!authentication.getName().equals("anonymousUser")) {
			if(isApplicant()){//kiem tra nguoi dung co duoc quyen thay doi thong tin khong
				model.addAttribute("user", userService.findById(id));
				model.addAttribute("userDTO", new UserDTO());// push users to view
				model.addAttribute("users", userService.findAll());// push users to view
				model.addAttribute("applicant", applicantService.findByUserId(id));
				return "web/account";
			}
			redirectAttributes.addFlashAttribute("message", "Tài khoản của bạn không có quyền truy cập!");//truyen message thanh cong toi trang dang nhap
			redirectAttributes.addFlashAttribute("alert", "danger");//truyen type message toi trang dang nhap
			return "redirect:/dang-nhap";
		}
		redirectAttributes.addFlashAttribute("message", "Bạn phải đăng nhập");//truyen message thanh cong toi trang dang nhap
		redirectAttributes.addFlashAttribute("alert", "danger");//truyen type message toi trang dang nhap
		return "redirect:/dang-nhap";
	}

	@RequestMapping(value = "/quan-ly-tai-khoan", method = RequestMethod.POST)
	public String accountManager(@RequestParam(value = "id") Long id, @Valid UserDTO userDTO
			, RedirectAttributes redirectAttributes, BindingResult bindingResult, Model model) {
		ResetPassValidation passValidation = new ResetPassValidation();
		BcryptPassword bcryptPassword = new BcryptPassword();
		
		passValidation.validate(userDTO, bindingResult);
		if (!bcryptPassword.checkPassowrd(userService.findById(userDTO.getId()).getPassword(), userDTO.getPasswordOld())||
				bindingResult.hasErrors()) {
			//neu co loi return ve lai form va hien thi loi
			model.addAttribute("user", userService.findById(id));
			model.addAttribute("users", userService.findAll());// push users to view
			model.addAttribute("applicant", applicantService.findByUserId(id));
			if(!bcryptPassword.checkPassowrd(userService.findById(userDTO.getId()).getPassword(), userDTO.getPasswordOld())) {
				model.addAttribute("notmatch", "Mật khẩu không đúng!");
			}
			return "web/account";
		}
		
		userService.resetPassword(userDTO);
		redirectAttributes.addFlashAttribute("message", "Đổi mật khẩu thành công");//truyen message thanh cong toi trang dang nhap
		redirectAttributes.addFlashAttribute("alert", "success");//truyen type message toi trang dang nhap
		return "redirect:/quan-ly-tai-khoan?id=" + id;
	}
	
	@RequestMapping(value = "/tim-kiem", method = RequestMethod.GET)
	public String searchByTitle(@RequestParam("keyword") String keyword, 
			@RequestParam(name = "page") int page,
			@RequestParam(name = "limit") int limit,
			Model model) {
		JobDTO jobDTO = new JobDTO();
		jobDTO.setPage(page);
		jobDTO.setLimit(limit);
		Pageable pageable = new PageRequest(page - 1, limit);
		jobDTO.setListResult(jobService.findByTitle(pageable, keyword));//get all job
		jobDTO.setTotalItem(jobService.getTotalItem());
		jobDTO.setTotalPage((int) Math.ceil((double) jobDTO.getTotalItem()/jobDTO.getLimit()));
		
		model.addAttribute("jobs", jobDTO);//push jobs to view
		model.addAttribute("categories", categoryService.findAll());//push categories to view
		model.addAttribute("employers", employerService.findAll());//push employers to view
		model.addAttribute("users", userService.findAll());// push users to view
		return "web/list-job";
	}
	
	@RequestMapping(value = "/cong-ty", method = RequestMethod.GET)
	public String showCompanyList(Model model) {
	    List<EmployerDTO> companies = employerService.findAll(); // Fetch the list of companies
	    model.addAttribute("companies", companies); // Add the list to the model
	    model.addAttribute("users", userService.findAll());// push users to view
	    return "web/company-list";
	}
}
