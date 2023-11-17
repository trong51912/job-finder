package com.jobfinder.converter;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.jobfinder.dto.UserDTO;
import com.jobfinder.entity.UserEntity;
import com.jobfinder.security.BcryptPassword;

@Component
public class UserConverter {
	
	public UserDTO toDto(UserEntity entity) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
		UserDTO result = new UserDTO();
		result.setId(entity.getId());
		result.setUserName(entity.getUserName());
		result.setPassword(entity.getPassword());
		result.setFirstName(entity.getFirstName());
		result.setLastName(entity.getLastName());
		result.setEmail(entity.getEmail());
		result.setAddress(entity.getAddress());
		result.setPhone(entity.getPhone());
		result.setCreateAt(formatter.format(entity.getCreate_at().getTime()));
		result.setUpdateAt(formatter.format(entity.getUpdate_at().getTime()));
		result.setEmployer_id(entity.getId());
		result.setCreateBy(entity.getCreate_by());
		result.setUpdateBy(entity.getUpdate_by());
		return result;
	}
	
	public UserEntity toEntity(UserDTO dto) {
		BcryptPassword bcryptPassword = new BcryptPassword();
		UserEntity result = new UserEntity();
		result.setUserName(dto.getUserName());
		result.setPassword(bcryptPassword.BcryptPass(dto.getPassword()));
		result.setFirstName(dto.getFirstName());
		result.setLastName(dto.getLastName());
		result.setEmail(dto.getEmail());
		result.setAddress(dto.getAddress());
		result.setPhone(dto.getPhone());
		result.setId(dto.getEmployer_id());;
		return result;
	}
	
	public UserEntity toEntity(UserEntity result, UserDTO dto) {
		result.setFirstName(dto.getFirstName());
		result.setLastName(dto.getLastName());
		result.setAddress(dto.getAddress());
		result.setPhone(dto.getPhone());
		return result;
	}
}
