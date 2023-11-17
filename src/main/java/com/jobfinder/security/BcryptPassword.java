package com.jobfinder.security;

import org.mindrot.jbcrypt.BCrypt;


public class BcryptPassword {
	
	public String BcryptPass(String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashedPassword;
	}
	
	public boolean checkPassowrd(String oldPass, String pass) {
		boolean result = false;
		if(BCrypt.checkpw(pass, oldPass)) {
			result = true;
		}
		return result;
	}
}
