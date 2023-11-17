package com.jobfinder.converter;

import org.springframework.stereotype.Component;

import com.jobfinder.dto.PositionDTO;
import com.jobfinder.entity.PositionEntity;

@Component
public class PositionConverter {
	
	public PositionDTO toDto(PositionEntity entity) {
		PositionDTO result = new PositionDTO();
		result.setId(entity.getId());
		result.setName(entity.getName());
		if (entity.getCategory() != null) {
			result.setCategory_id(entity.getCategory().getId());
		}
		return result; 
	}
	
	public PositionEntity toEntity(PositionDTO dto) {
		PositionEntity result = new PositionEntity();
		result.setName(dto.getName());
		return result;
	}
	
	public PositionEntity toEntity(PositionEntity result, PositionDTO dto) {
		result.setName(dto.getName());
		return result;
	}
}
