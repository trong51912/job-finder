package com.jobfinder.converter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jobfinder.dto.ApplicantDTO;
import com.jobfinder.entity.ApplicantEntity;
import com.jobfinder.entity.SkillEntity;

@Component
public class ApplicantConverter {
	
	public ApplicantDTO toDto(ApplicantEntity entity) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
		List<Long> skills = new ArrayList<>();
		for(SkillEntity skill: entity.getSkills()) {
			skills.add(skill.getId());
		}
		ApplicantDTO result = new ApplicantDTO();
		result.setId(entity.getId());
		result.setSkills(skills);
		result.setExperience(entity.getExperience());
		result.setEducation(entity.getEducation());
		if (entity.getUser() != null) {
			result.setUser_id(entity.getUser().getId());
			result.setEmail(entity.getUser().getEmail());
			result.setPhone(entity.getUser().getPhone());
			result.setAddress(entity.getUser().getAddress());
			result.setUserName(entity.getUser().getUserName());
			result.setFirstName(entity.getUser().getFirstName());
			result.setLastName(entity.getUser().getLastName());
			result.setStatus(entity.getUser().getStatus());
		}
		result.setCreateAt(formatter.format(entity.getCreate_at().getTime()));
		result.setUpdateAt(formatter.format(entity.getUpdate_at().getTime()));
		result.setCreateBy(entity.getCreate_by());
		result.setUpdateBy(entity.getUpdate_by());
		return result;
	}
	
	public ApplicantEntity toEntity(ApplicantDTO dto) {
		ApplicantEntity result = new ApplicantEntity();
		result.setExperience(dto.getExperience());
		result.setEducation(dto.getEducation());
		return result;
	}
	
	public ApplicantEntity toEntity(ApplicantEntity result, ApplicantDTO dto) {
		result.setExperience(dto.getExperience());
		result.setEducation(dto.getEducation());
		return result;
	}
}
