package com.jobfinder.controller.employer;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.jobfinder.service.ICategoryService;
import com.jobfinder.service.IEmployerService;
import com.jobfinder.service.IJobService;
import com.jobfinder.service.IPositionService;
import com.jobfinder.service.ISkillService;
import com.jobfinder.service.IUserService;
import com.jobfinder.util.SecurityUtils;
import com.jobfinder.validation.JobValidation;

@Controller(value = "homeControllerOfEmployer")
@RequestMapping("/nha-tuyen-dung")
public class HomeController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IJobService jobService;
	
	@Autowired
	private IEmployerService employerService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private IPositionService positionService;
	
	@Autowired
	private ISkillService skillService;
	
	@Autowired
	private JobValidation jobValidation;
	
	/**
	 * function Get current employer logging in
	 * 
	 * 11102023 NamHH
	 * 
	 * @author NamHH
	 */
	public EmployerDTO getCurrentEmployer() {
		EmployerDTO result = null;
		for(EmployerDTO empl: employerService.findAll()) {// find user employer logging in
			if(Long.parseLong(SecurityUtils.getPrincipal().getId()) == empl.getUser_id()) {
				result = empl;
			}
		}
		return result;
	}
	
	/**
	 * function Get mapping return home page
	 * 
	 * 11102023 NamHH
	 * 
	 * @author NamHH
	 */
	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage() {
		
		List<UserDTO> users = userService.findAll();//get all user
		
		EmployerDTO employer = getCurrentEmployer();//get current employer

		List<JobDTO> jobs = jobService.findByEmployerId(employer.getId());//get job by employer id
		
		ModelAndView mav = new ModelAndView("employer/home");
		mav.addObject("title", "Trang chủ");//push title to view
		mav.addObject("users", users);//push users to view
		mav.addObject("jobs", jobs);//push jobs to view
		return mav;
	}
	
	
	/**
	 * function Get mapping return job page
	 * 
	 *  11102023 NamHH
	 * 
	 * @author NamHH
	 */
	@RequestMapping(value = "/viec-lam", method = RequestMethod.GET)
	public ModelAndView jobPage() {
		List<UserDTO> users = userService.findAll();//get all user
		List<CategoryDTO> categories = categoryService.findAll();//get all category
		List<JobDTO> jobs = jobService.findAll();//get all job
		
		ModelAndView mav = new ModelAndView("employer/jobs");//create a Model view return jsp
		
		EmployerDTO employer = getCurrentEmployer();//get current employer
		mav.addObject("categories", categories);//push categories to view
		mav.addObject("employer", employer);//push employer to view
		mav.addObject("users", users);//push users to view
		mav.addObject("jobs", jobs);//push jobs to view
		mav.addObject("title", "Danh sách việc làm");//push title to view
		
		return mav;
	}
	
	/**
	 * function Get mapping return create job page
	 * 
	 *  11102023 NamHH
	 * 
	 * @author NamHH
	 */
	@RequestMapping(value = "/tao-viec-lam", method = RequestMethod.GET)
	public String createJob(Model model) {
		//create list Type
		List<String> types = new ArrayList<String>();
		types.add("Full time");
		types.add("Part time");
		types.add("Remote");
		types.add("Freelance");
		
		EmployerDTO employer = getCurrentEmployer();//get current employer
		
		model.addAttribute("categories", categoryService.findAll());//push categories to view
		model.addAttribute("positions", positionService.findAll());//push positions to view
		model.addAttribute("types", types);//push types to view
		model.addAttribute("skills", skillService.findAll());//push skills to view
		model.addAttribute("employer", employer);//push employer to view
		model.addAttribute("jobDTO", new JobDTO());//push modelAtribute to form
		model.addAttribute("users", userService.findAll());//push users to view
		model.addAttribute("title", "Tạo việc làm");//push title to view
		return "employer/create-job";
	}
	
	/**
	 * function POST mapping to create new job
	 * 
	 *  11102023 NamHH
	 * 
	 * @author NamHH
	 */
	@RequestMapping(value = "/tao-viec-lam", method = RequestMethod.POST)
	public String createJob(@Valid JobDTO jobDTO, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		
		//validation cho form dang ky nguoi tim viec
		jobValidation.validate(jobDTO, bindingResult);
		
		EmployerDTO employer = getCurrentEmployer();//get current employer
		
		//create list Type
		List<String> types = new ArrayList<String>();
		types.add("Full time");
		types.add("Part time");
		types.add("Remote");
		types.add("Freelance");
		
		if (bindingResult.hasErrors()) {
			//neu co loi return ve lai form va hien thi loi
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("employer", employer);
			model.addAttribute("positions", positionService.findAll());
			model.addAttribute("skills", skillService.findAll());
			model.addAttribute("types", types);
			return "employer/create-job";
		}
		
		jobService.save(jobDTO);//save to databse
		redirectAttributes.addFlashAttribute("message", "Register successfully");//truyen message thanh cong toi trang dang nhap
		redirectAttributes.addFlashAttribute("alert", "success");//truyen type message toi trang dang nhap
		return "redirect:/nha-tuyen-dung/viec-lam";
	}
	
	/**
	 * function GET mapping to show profile page
	 * 
	 *  11102023 NhanNN
	 * 
	 * @author NhanNN
	 */
	@RequestMapping(value = "/thong-tin-ca-nhan", method = RequestMethod.GET)
	public String getEmployerProfile(@RequestParam(value = "id") Long id, Model model) {
		EmployerDTO employerDTO = employerService.getEmployerProfile(id);
		model.addAttribute("employer", employerDTO);
		UserDTO userDTO = userService.findById(id);
		model.addAttribute("users", userService.findAll());//push users to view
		model.addAttribute("user", userDTO);
		model.addAttribute("title", "Thông tin cá nhân");//push title to view
		return "employer/profile";
	}
	
	/**
	 * function GET mapping to show update profile page
	 * 
	 *  11102023 NhanNN
	 * 
	 * @author NhanNN
	 */
	@RequestMapping(value = "/cap-nhat-thong-tin", method = RequestMethod.GET)	
	public String showFormUpdateProfile(@RequestParam(value = "id") Long id, Model model) {
		 EmployerDTO employer = employerService.getEmployerProfile(id);
		 UserDTO user = userService.findById(id);
		if (user != null) {
            EmployerDTO employerDTO = new EmployerDTO();
            employerDTO.setUserName(user.getUserName());
            employerDTO.setEmail(user.getEmail());
            employerDTO.setFirstName(user.getFirstName());
            employerDTO.setLastName(user.getLastName());
            employerDTO.setPhone(user.getPhone());
            
            employerDTO.setCompanyAddress(employer.getCompanyAddress());
            employerDTO.setCompanyName(employer.getCompanyName());
            employerDTO.setPosition(employer.getPosition());
            
            
            model.addAttribute("title", "Cập nhật thông tin");//push title to view
            model.addAttribute("employer", employerDTO);
            model.addAttribute("user", user);
            model.addAttribute("users", userService.findAll());//push users to view
            return "employer/update-profile";
        } else {
            return null;
        }
	}
	
	/**
	 * function POST mapping to update profile
	 * 
	 *  11102023 NhanNN
	 * 
	 * @author NhanNN
	 */
	@RequestMapping(value = "/cap-nhat-thong-tin", method = RequestMethod.POST)
	public String  updateEmployerProfile(@RequestParam(value = "id") Long id, 
			@ModelAttribute("employer") EmployerDTO employerDTO
			, RedirectAttributes redirectAttributes) {
		employerService.updateEmployerInfo(id, employerDTO.getId(), employerDTO);
		redirectAttributes.addFlashAttribute("message", "Đổi thông tin thành công");//truyen message thanh cong toi trang dang nhap
		redirectAttributes.addFlashAttribute("alert", "success");//truyen type message toi trang dang nhap
		return "redirect:/nha-tuyen-dung/thong-tin-ca-nhan?id=" + id;
	}
	
	@RequestMapping(value = "/showApplicants", method = RequestMethod.GET)
	public String showApplicants(@RequestParam Long jobId, Model model) {
	    JobDTO job = jobService.findById(jobId);
	    List<ApplicantDTO> applicants = jobService.findApplicantsForJob(jobId);

	    model.addAttribute("job", job);
	    model.addAttribute("applicants", applicants);

	    return "employer/applicants-view";
	}
}
