package com.jobfinder.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jobfinder.dto.UserDTO;

@Component
public class ResetPassValidation implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserDTO user = (UserDTO)target;
		
        String password = user.getPassword();
        String confirmPassword = user.getConfirmPassword();

        if(!password.equals(confirmPassword)){//password khong khop
            errors.rejectValue("confirmPassword","user.confirmPassword.missMatch");
        }
        
        if(password.length()<8 || password.length()>15){//password qua ngan hoac qua dai
            errors.rejectValue("password","user.password.size");
        }
	}
}
