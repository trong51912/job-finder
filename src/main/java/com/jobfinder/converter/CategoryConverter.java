package com.jobfinder.converter;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.jobfinder.dto.CategoryDTO;
import com.jobfinder.entity.CategoryEntity;

@Component
public class CategoryConverter {
	
	public CategoryDTO toDto(CategoryEntity entity) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
		CategoryDTO result = new CategoryDTO();
		result.setId(entity.getId());
		result.setName(entity.getName());
		result.setCode(entity.getCode());
		result.setCreateAt(formatter.format(entity.getCreate_at().getTime()));
		result.setUpdateAt(formatter.format(entity.getUpdate_at().getTime()));
		result.setCreateBy(entity.getCreate_by());
		result.setUpdateBy(entity.getUpdate_by());
		return result; 
	}
	
	public CategoryEntity toEntity(CategoryDTO dto) {
		CategoryEntity result = new CategoryEntity();
		result.setName(dto.getName());
		result.setCode(dto.getCode());
		return result; 
	}
	
	public CategoryEntity toEntity(CategoryEntity result, CategoryDTO dto) {
		result.setName(dto.getName());
		result.setCode(dto.getCode());
		return result; 
	}
}
