package com.jobfinder.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobfinder.converter.PositionConverter;
import com.jobfinder.dto.PositionDTO;
import com.jobfinder.entity.PositionEntity;
import com.jobfinder.repository.PositionRepository;
import com.jobfinder.service.IPositionService;

@Service
public class PositionService implements IPositionService{
	@Autowired
	private PositionRepository positionRepository;
	
	@Autowired
	private PositionConverter positionConverter;
	
	@Override
	public List<PositionDTO> findAll() {
		List<PositionDTO> models = new ArrayList<>();
		List<PositionEntity> entities = positionRepository.findAll();
		for (PositionEntity item : entities) {
			PositionDTO userModel = positionConverter.toDto(item);
			models.add(userModel);
		}
		return models;
	}

	@Override
	public List<PositionDTO> findByCategoryId(Long categoryId) {
		List<PositionDTO> models = new ArrayList<>();
		List<PositionEntity> entities = positionRepository.findByCategoryId(categoryId);
		for (PositionEntity item : entities) {
			PositionDTO userModel = positionConverter.toDto(item);
			models.add(userModel);
		}
		return models;
	}

}
