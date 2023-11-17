package com.jobfinder.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jobfinder.dto.JobDTO;

@Component
public class JobValidation implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return JobDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		JobDTO job = (JobDTO)target;
		
		//job
		String title = job.getTitle();
		String description = job.getDescription();
        List<Long> skills = job.getSkills();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date deadline = new Date();
        
        //validation empty
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "job.title.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "job.type.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location", "job.location.empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "job.description.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "requirements", "job.requirements.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "benefit", "job.benefit.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deadline", "job.deadline.empty");
       
        try {
            deadline = sdf.parse(job.getDeadline());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        //ngay hom nay
        Date toDay = new Date();
        
        if(title.length()>70){//title qua dai
            errors.rejectValue("title","job.title.maxSize");
        }
        
        if(deadline.before(toDay)){//han tuyen dung khong hop le
            errors.rejectValue("deadline","job.deadline.invalid");
        }
        
        if(skills.size()==0){//khong chon skill
            errors.rejectValue("skills","job.skills.size");
        }
        
        if(description.length()<30 && description.length()>1){//truong description qua ngan
            errors.rejectValue("description","job.description.size");
        }
	}
	
}
