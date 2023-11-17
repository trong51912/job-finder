package com.jobfinder.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jobfinder.dto.EmployerDTO;
import com.jobfinder.service.IUserService;

@Component
public class EmployerValidation implements Validator{
	@Autowired
	private IUserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return EmployerDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		EmployerDTO employer = (EmployerDTO)target;
		
		//user
		String email = employer.getEmail();
		String userName = employer.getUserName();
        String password = employer.getPassword();
        String confirmPassword = employer.getConfirmPassword();
        String conpanyIntroduce = employer.getCompanyIntroduce();
       
        //validation empty
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyName", "employer.companyName.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyAddress", "employer.companyAddress.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "conpanyIntroduce", "employer.conpanyIntroduce.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "position", "employer.position.empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "user.firstName.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "user.lastName.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "user.userName.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "user.email.empty");
        
        //Business validation
        if(!password.equals(confirmPassword)){//password khong khop
            errors.rejectValue("confirmPassword","user.confirmPassword.missMatch");
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
        if(conpanyIntroduce.length()<30 && conpanyIntroduce.length()>1){//truong conpany introduce qua ngan
            errors.rejectValue("conpanyIntroduce","employer.conpanyIntroduce.size");
        }
	}

}
