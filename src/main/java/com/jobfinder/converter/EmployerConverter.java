package com.jobfinder.converter;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.jobfinder.dto.EmployerDTO;
import com.jobfinder.entity.EmployerEntity;

@Component
public class EmployerConverter {
	public EmployerDTO toDto(EmployerEntity entity) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
		EmployerDTO result = new EmployerDTO();
		result.setId(entity.getId());
		result.setCompanyName(entity.getCompanyName());
		result.setCompanyAddress(entity.getCompanyAddress());
		result.setCompanyIntroduce(entity.getCompanyIntroduce());
		result.setPosition(entity.getPosition());
		if(entity.getServices() != null) {
			result.setService(entity.getServices().getName());
		} else {
			result.setService("Chưa Đăng Ký");
		}
		
		if (entity.getUser() != null) {
			result.setUser_id(entity.getUser().getId());
			result.setEmail(entity.getUser().getEmail());
			result.setFirstName(entity.getUser().getFirstName());
			result.setLastName(entity.getUser().getLastName());
			result.setPhone(entity.getUser().getPhone());
			result.setUserName(entity.getUser().getUserName());
			
		}
		result.setCreateAt(formatter.format(entity.getCreate_at().getTime()));
		result.setUpdateAt(formatter.format(entity.getUpdate_at().getTime()));
		result.setCreateBy(entity.getCreate_by());
		result.setUpdateBy(entity.getUpdate_by());
		return result;
	}
	
	public EmployerEntity toEntity(EmployerDTO dto) {
		EmployerEntity result = new EmployerEntity();
		result.setCompanyName(dto.getCompanyName());
		result.setCompanyAddress(dto.getCompanyAddress());
		result.setCompanyIntroduce(dto.getCompanyIntroduce());
		result.setPosition(dto.getPosition());
		return result;
	}
	
	public EmployerEntity toEntity(EmployerEntity result, EmployerDTO dto) {
		result.setCompanyName(dto.getCompanyName());
		result.setCompanyAddress(dto.getCompanyAddress());
		result.setCompanyIntroduce(dto.getCompanyIntroduce());
		result.setPosition(dto.getPosition());
		return result;
	}
}
