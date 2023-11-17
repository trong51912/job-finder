package com.jobfinder.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jobfinder.dto.ApplicantDTO;
import com.jobfinder.service.IUserService;

@Component
public class ApplicantValidation implements Validator{
	
	@Autowired
	private IUserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return ApplicantDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ApplicantDTO applicant = (ApplicantDTO)target;
		
		//applicant
		String experience = applicant.getExperience();
		
		//user
		String email = applicant.getEmail();
		String userName = applicant.getUserName();
        String password = applicant.getPassword();
        String confirmPassword = applicant.getConfirmPassword();
        List<Long> skills = applicant.getSkills();
       
        //validation empty
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "education", "applicant.education.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "experience", "applicant.experience.empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "user.firstName.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "user.lastName.empty");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "user.userName.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "user.email.empty");
        
        //Business validation
        if(!password.equals(confirmPassword)){//password khong khop
            errors.rejectValue("confirmPassword","user.confirmPassword.missMatch");
        }
        if(skills.size()==0){//khong chon skill
            errors.rejectValue("skills","applicant.skills.size");
        }
        
        if(password.length()<8 || password.length()>15){//password qua ngan hoac qua dai
            errors.rejectValue("password","user.password.size");
        }
        
        if(userService.findOneByUserNameAndStatus(userName, 1)!=null&&userName.length()>1){//username da ton tai
            errors.rejectValue("userName","user.userName.exist");
        }
        
        if(userService.findOneByEmailAndStatus(email, 1)!=null&&email.length()>1){//email da ton tai
            errors.rejectValue("email","user.email.exist");
        }
        
        if(experience.length()<30 && experience.length()>1){//truong experience qua ngan
            errors.rejectValue("experience","applicant.experience.size");
        }
	}
	
}
