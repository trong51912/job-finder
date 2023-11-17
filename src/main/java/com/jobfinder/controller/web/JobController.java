package com.jobfinder.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jobfinder.dto.ApplicantDTO;
import com.jobfinder.dto.JobDTO;
import com.jobfinder.service.IApplicantService;
import com.jobfinder.service.ICategoryService;
import com.jobfinder.service.IEmployerService;
import com.jobfinder.service.IJobService;
import com.jobfinder.service.ISkillService;
import com.jobfinder.service.IUserService;

@Controller
@RequestMapping("/viec-lam")
public class JobController {

	@Autowired
	private IJobService jobService;

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IEmployerService employerService;
	
	@Autowired
	private IApplicantService applicantService;
	
	@Autowired
	private ISkillService skillService;
	

	/**
	 * method get mapping to return list job
	 * @author nhannn
	 * 
	 * @param model
	 * @param categoryId
	 * @param type
	 * @param salary
	 * @param location
	 * @return view jsp
	 */
	@RequestMapping(value = "/danh-sach", method = RequestMethod.GET)
	public String jobsList(Model model, @RequestParam(name = "page") int page,
			@RequestParam(name = "limit") int limit,
			@RequestParam(name = "category", required=false) Long categoryId,
			@RequestParam(name = "type", required=false) String type,
			@RequestParam(name = "salary", required=false) Integer salary,
			@RequestParam(name = "location", required=false) String location) {
		JobDTO jobDTO = new JobDTO();
		jobDTO.setPage(page);
		jobDTO.setLimit(limit);
		Pageable pageable = new PageRequest(page - 1, limit);
		jobDTO.setListResult(jobService.findAll(pageable));//get all job
		jobDTO.setTotalItem(jobService.getTotalItem());
		jobDTO.setTotalPage((int) Math.ceil((double) jobDTO.getTotalItem()/jobDTO.getLimit()));
		
		if(categoryId!= null || type!=null || salary!=null || location!=null) {
			//filter job by categoryId, type, salary, location
			jobDTO.setListResult(jobService.filter(pageable, categoryId, type, salary, location));
		}
		model.addAttribute("jobs", jobDTO);//push jobs to view
		model.addAttribute("categories", categoryService.findAll());//push categories to view
		model.addAttribute("employers", employerService.findAll());//push employers to view
		model.addAttribute("applicants", applicantService.findAll());// push employers to view
		model.addAttribute("users", userService.findAll());// push users to view
		return "web/list-job";
	}

	/**
	 * method get mapping to return detail job page
	 * @author nhannn
	 * 
	 * @param jobId
	 * @param model
	 * @return view jsp
	 */
	@RequestMapping(value = "/chi-tiet-viec-lam/{id}", method = RequestMethod.GET)
	public String showJobDetail(@PathVariable("id") Long jobId, Model model) {
		JobDTO job = jobService.findById(jobId);//get job by id
		
		model.addAttribute("categories", categoryService.findAll());//push categories to view
		model.addAttribute("skills", skillService.findAll());//push skills to view
		model.addAttribute("users", userService.findAll());//push users to view
		model.addAttribute("job", job);//push job to view
		model.addAttribute("employers", employerService.findAll());//push employers to view
		model.addAttribute("applicants", applicantService.findAll());// push employers to view
		return "web/job-detail";
	}
	
	@RequestMapping(value = "/ung-tuyen-cong-viec/{id}", method = RequestMethod.GET)
	public String showApplyForm(@PathVariable("id") Long jobId, Model model) {
		JobDTO job = jobService.findById(jobId);
		model.addAttribute("job", job);
		return "web/apply-form";
	}
	
	@RequestMapping(value = "/ung-tuyen-cong-viec", method = RequestMethod.GET)
	public String showAppliedJobs(Model model, Authentication authentication) {
	    if (authentication != null && authentication.isAuthenticated()) {
	        String username = authentication.getName();
	        ApplicantDTO applicant = applicantService.findByUsername(username);

	        if (applicant != null) {
	            List<JobDTO> appliedJobs = applicantService.findAppliedJobs(applicant.getId());
	            model.addAttribute("appliedJobs", appliedJobs);
	            model.addAttribute("users", userService.findAll());// push users to view
	            return "web/applied-jobs";
	        }
	    }

	    return "redirect:/dang-nhap";
	}

	
	@RequestMapping(value = "/nop-ho-so-ung-tuyen/{jobId}", method = RequestMethod.POST)
	public String applyForJob(@PathVariable("jobId") Long jobId) {
	    try {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
	            String username = authentication.getName();
	            ApplicantDTO applicant = applicantService.findByUsername(username);
	            JobDTO job = jobService.findById(jobId);
	            boolean isApplied = applicantService.applyForJob(applicant, job);
	            if (isApplied) {
	                return "redirect:/viec-lam/ung-tuyen-cong-viec";
	            } else {
	                return "redirect:/viec-lam/chi-tiet-bai-viet/" + jobId;
	            }
	        } else {
	            return "redirect:/dang-nhap";
	        }
	    } catch (Exception e) {
	        return "redirect:/viec-lam/chi-tiet-bai-viet/" + jobId;
	    }
	}
}