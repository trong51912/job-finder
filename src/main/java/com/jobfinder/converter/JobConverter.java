package com.jobfinder.converter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jobfinder.dto.JobDTO;
import com.jobfinder.entity.JobEntity;
import com.jobfinder.entity.SkillEntity;

@Component
public class JobConverter {

	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	
	public JobDTO toDto(JobEntity entity) {
		List<Long> skills = new ArrayList<>();
		if (entity.getSkills() != null) {
			for(SkillEntity skill: entity.getSkills()) {
				skills.add(skill.getId());
			}
		}
		JobDTO result = new JobDTO();
		result.setLocation(entity.getLocation());
		result.setId(entity.getId());
		result.setTitle(entity.getTitle());
		result.setType(entity.getType());
		result.setDescription(entity.getDescription());
		result.setRequirements(entity.getRequirements());
		result.setBenefit(entity.getBenefit());
		result.setDeadline(formatter.format(entity.getApplicationDeadline().getTime()));
		result.setSalary(entity.getSalary());
		result.setPosition(entity.getPosition());
		result.setType(entity.getType());
		result.setSkills(skills);
		if (entity.getCategory() != null) {
			result.setCategory_id(entity.getCategory().getId());
		}
		if (entity.getEmployer() != null) {
			result.setEmployer_id(entity.getEmployer().getId());
		}
		result.setCreateAt(formatter.format(entity.getCreate_at().getTime()));
		result.setUpdateAt(formatter.format(entity.getUpdate_at().getTime()));
		result.setCreateBy(entity.getCreate_by());
		result.setUpdateBy(entity.getUpdate_by());
		return result; 
	}
	
	public JobEntity toEntity(JobDTO dto){
		JobEntity result = new JobEntity();
		result.setTitle(dto.getTitle());
		result.setType(dto.getType());
		result.setDescription(dto.getDescription());
		result.setRequirements(dto.getRequirements());
		result.setBenefit(dto.getBenefit());
		result.setSalary(dto.getSalary());
		result.setLocation(dto.getLocation());
		result.setPosition(dto.getPosition());
		result.setType(dto.getType());
		return result;
	}
	
	public JobEntity toEntity(JobEntity result, JobDTO dto) {
		result.setTitle(dto.getTitle());
		result.setType(dto.getType());
		result.setDescription(dto.getDescription());
		result.setRequirements(dto.getRequirements());
		result.setBenefit(dto.getBenefit());
		result.setSalary(dto.getSalary());
		result.setLocation(dto.getLocation());
		result.setPosition(dto.getPosition());
		result.setType(dto.getType());
		dto.getCategory_id();
		return result;
	}
}