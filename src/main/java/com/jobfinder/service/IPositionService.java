package com.jobfinder.service;

import java.util.List;

import com.jobfinder.dto.PositionDTO;

public interface IPositionService {
	List<PositionDTO> findAll();
	List<PositionDTO> findByCategoryId(Long categoryId);
}
