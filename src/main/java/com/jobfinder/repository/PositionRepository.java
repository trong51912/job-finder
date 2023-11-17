package com.jobfinder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobfinder.entity.PositionEntity;


public interface PositionRepository extends JpaRepository<PositionEntity, Long>{
	List<PositionEntity> findByCategoryId(Long categoryId);
}