package com.jobfinder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobfinder.dto.CategoryDTO;
import com.jobfinder.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
//	Optional<CategoryEntity> findById(Long categoryId);
}	