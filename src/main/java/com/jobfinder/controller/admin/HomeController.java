package com.jobfinder.controller.admin;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jobfinder.dto.ApplicantDTO;
import com.jobfinder.dto.CategoryDTO;
import com.jobfinder.dto.EmployerDTO;
import com.jobfinder.dto.JobDTO;
import com.jobfinder.dto.UserDTO;
import com.jobfinder.service.IApplicantService;
import com.jobfinder.service.ICategoryService;
import com.jobfinder.service.IEmployerService;
import com.jobfinder.service.IJobService;
import com.jobfinder.service.ISkillService;
import com.jobfinder.service.IUserService;


@Controller(value = "homeControllerOfAdmin")
public class HomeController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IJobService jobService ;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired 
	private IEmployerService employerService ;
	
	@Autowired 
	private ISkillService skillService ;
	
	@Autowired
	private IApplicantService applicantService;

	// get data  for dashboard 
	@RequestMapping(value = "/quan-tri/trang-chu", method = RequestMethod.GET)
	public String homePage(Model model) {
		List<UserDTO> users = userService.findAll();
		int countUser = users.size();
		List<JobDTO> jobs = jobService.findAll();
		int countJob = jobs.size();
		List<EmployerDTO> employer = employerService.findAll();
		int countEmployer = employer.size();

		// model view 
		model.addAttribute("users", users);
		model.addAttribute("countUser", countUser);
		model.addAttribute("jobs", jobs);
		model.addAttribute("countJob", countJob);
		model.addAttribute("employer", employer);
		model.addAttribute("countEmployer", countEmployer);
		return "admin/home";
	}
	@RequestMapping(value = "/quan-tri/nguoi-tim-viec", method = RequestMethod.GET)
	public String ManageUsers(Model model) {
		List<ApplicantDTO> applicant = applicantService.findAll();
		int countUser = applicant.size();
		model.addAttribute("users", applicant);
		model.addAttribute("countUser", countUser);
		return "admin/users_table";
	}
	
	@RequestMapping(value = "/quan-tri/nguoi-tim-viec/{id}", method = RequestMethod.GET)
	public String detailProfileApplicant(@PathVariable Long id, Model model) {
		model.addAttribute("skills", skillService.findAll());//push skills to view
	    ApplicantDTO applicant = applicantService.findById(id);
	    model.addAttribute("applicant", applicant);
		model.addAttribute("skills", skillService.findAll());//push skills to view
	    return "admin/detail_profile_applicant";
	}

	@RequestMapping(value = "/quan-tri/cong-ty", method = RequestMethod.GET)
	public String ManageCompanies(Model model) {
		List<EmployerDTO> employerDTOs = employerService.findAll();
		int countEmployer = employerDTOs.size();
		model.addAttribute("employers" , employerDTOs);
		model.addAttribute("countEmployer" , countEmployer);
		return "admin/employers_table"; 
	}
	@RequestMapping(value="/quan-tri/cong-viec", method = RequestMethod.GET)
	public String ManageJobs (Model model) {
		LocalDate currentDate = LocalDate.now();
		List<JobDTO> jobDTO = jobService.findAll();
		int countJobs = jobDTO.size();
		 Map<Long, CategoryDTO> categoryMap = new HashMap<>();
		    Map<Long, EmployerDTO> employerMap = new HashMap<>();
		    Map<Long, EmployerDTO> remaining = new HashMap<>();
		    for (JobDTO job : jobDTO) {
		    	LocalDate applicationDeadline = ForMatter(job.getDeadline());
		    	// Tính khoảng cách giữa thời gian hiện tại và thời gian hết hạn
		    	Duration duration = Duration.between(currentDate.atStartOfDay(), applicationDeadline.atStartOfDay());
		    	String daysRemainingString = Long.toString(duration.toDays());	
//		    	System.out.println(daysRemainingString);
		    	job.setDeadline(daysRemainingString);
//		    	System.out.println(job.getDeadline());
		        CategoryDTO category = categoryService.findById(job.getCategory_id());
		        EmployerDTO employer = employerService.findById(job.getEmployer_id());
		        
		        categoryMap.put(job.getId(), category);
		        employerMap.put(job.getId(), employer);
		        
		    }
		model.addAttribute("jobs" , jobDTO);
		model.addAttribute("countJobs" , countJobs);
		model.addAttribute("categoryMap" ,categoryMap);
		model.addAttribute("employerMap" ,employerMap);
		return "admin/jobs_table"; 
	}
	@PostMapping("/quan-tri/cong-viec")
    public String deleteJobs(@RequestParam("jobIds") List<Long> jobIds) {
        jobService.deleteJobs(jobIds);
        return "redirect:/quan-tri/cong-viec"; // Redirect back to the job list page
    }
	@RequestMapping(value = "/quan-tri/{userName}", method = RequestMethod.GET)
	public String getUserProfile(@PathVariable String userName, Model model) {
		UserDTO user = userService.findByUserName(userName);
		model.addAttribute("user", user);
		return "admin/profile";
	}
	
	@RequestMapping(value = "/quan-tri/edit/{userName}", method = RequestMethod.GET)
	public String showEditForm(@PathVariable("userName") String userName, Model model) {
		UserDTO user = userService.findByUserName(userName);
		model.addAttribute("user", user);
		return "admin/edit-form";
	}
	
	@RequestMapping(value = "/quan-tri/edit/{userName}", method = RequestMethod.POST)
	public String updateUser(@PathVariable String userName,
			@ModelAttribute("user") UserDTO userDTO) {
		userService.updateUser(userDTO);
		return "redirect:/quan-tri/" + userName;
	}
	// formatt ngày thang nam tu String sang ngay thang
	public LocalDate ForMatter (String date) {
		LocalDate parsedDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		try {
		    parsedDate = LocalDate.parse(date, formatter);
		    // parsedDate bây giờ chứa ngày tháng được chuyển đổi từ chuỗi
//		    System.out.println("LocalDate: " + parsedDate);
		} catch (DateTimeParseException e) {
		    // Xử lý khi có lỗi định dạng
		    System.out.println("Lỗi định dạng: " + e.getMessage());
		}
		return parsedDate ;
	}
}
