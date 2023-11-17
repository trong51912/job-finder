package com.jobfinder.converter;

import org.springframework.stereotype.Component;

import com.jobfinder.dto.RoleDTO;
import com.jobfinder.entity.RoleEntity;

@Component
public class RoleConverter {
	
	public RoleDTO toDto(RoleEntity entity) {
		System.out.println(entity.getCode());
		RoleDTO result = new RoleDTO();
		result.setId(entity.getId());
		result.setRole_name(entity.getRole_name());
		result.setCode(entity.getCode());
		return result;
	}
	
	public RoleEntity toEntity(RoleDTO dto) {
		RoleEntity result = new RoleEntity();
		result.setRole_name(dto.getRole_name());
		result.setCode(dto.getCode());
		return result;
	}
	
	public RoleEntity toEntity(RoleEntity result, RoleDTO dto) {
		result.setRole_name(dto.getRole_name());
		result.setCode(dto.getCode());
		return result;
	}
}
