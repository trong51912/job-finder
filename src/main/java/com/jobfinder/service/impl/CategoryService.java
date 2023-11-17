package com.jobfinder.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobfinder.converter.CategoryConverter;
import com.jobfinder.dto.CategoryDTO;
import com.jobfinder.entity.CategoryEntity;
import com.jobfinder.repository.CategoryRepository;
import com.jobfinder.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryConverter categoryConverter;

	@Override
	public List<CategoryDTO> findAll() {
		List<CategoryDTO> models = new ArrayList<>();
		List<CategoryEntity> entities = categoryRepository.findAll();
		for (CategoryEntity item : entities) {
			CategoryDTO userModel = categoryConverter.toDto(item);
			models.add(userModel);
		}
		return models;
	}

	@Override
	public CategoryDTO findById(Long id) {
		CategoryEntity entity = categoryRepository.findOne(id);
		return categoryConverter.toDto(entity);
	}
}