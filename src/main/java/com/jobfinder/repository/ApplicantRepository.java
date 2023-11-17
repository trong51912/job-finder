package com.jobfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobfinder.entity.ApplicantEntity;

@Repository
public interface ApplicantRepository extends JpaRepository<ApplicantEntity, Long>{
	ApplicantEntity findByUserId(Long id);
}
