package com.jobfinder.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jobfinder.dto.SkillDTO;
import com.jobfinder.entity.ApplicantEntity;
import com.jobfinder.entity.CategoryEntity;
import com.jobfinder.entity.JobEntity;
import com.jobfinder.entity.SkillEntity;
import com.jobfinder.repository.CategoryRepository;

@Component
public class SkillConverter {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public SkillDTO toDto(SkillEntity entity) {
		SkillDTO result = new SkillDTO();
		List<Long> applicants = new ArrayList<>();
		List<Long> jobs = new ArrayList<>();
		if(entity.getApplicants()!=null) {
			for(ApplicantEntity applicant: entity.getApplicants()) {
				applicants.add(applicant.getId());
			}
		}
		if(entity.getJobs()!=null) {
			for(JobEntity job: entity.getJobs()) {
				jobs.add(job.getId());
			}
		}
		
		result.setId(entity.getId());
		result.setApplicants(applicants);
		result.setJobs(jobs);
		result.setName(entity.getName());
		if (entity.getCategory() != null) {
			result.setCategory_id(entity.getCategory().getId());
		}
		return result; 
	}
	
	public SkillEntity toEntity(SkillDTO dto) {
		SkillEntity result = new SkillEntity();
		CategoryEntity category = categoryRepository.findOne(dto.getCategory_id());
		result.setCategory(category);
		result.setName(dto.getName());
		return result;
	}
	
	public SkillEntity toEntity(SkillEntity result, SkillDTO dto) {
		CategoryEntity category = categoryRepository.findOne(dto.getCategory_id());
		result.setCategory(category);
		result.setName(dto.getName());
		return result;
	}
}
