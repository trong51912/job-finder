package com.jobfinder.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobfinder.converter.SkillConverter;
import com.jobfinder.dto.SkillDTO;
import com.jobfinder.entity.SkillEntity;
import com.jobfinder.repository.SkillRepository;
import com.jobfinder.service.ISkillService;

@Service
public class SkillService implements ISkillService{
	
	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private SkillConverter skillConverter;
	
	@Override
	public List<SkillDTO> findAll() {
		List<SkillDTO> models = new ArrayList<>();
		List<SkillEntity> entities = skillRepository.findAll();
		for (SkillEntity item : entities) {
			SkillDTO userModel = skillConverter.toDto(item);
			models.add(userModel);
		}
		return models;
	}

	@Override
	public SkillDTO findById(long id) {
		return skillConverter.toDto(skillRepository.findOne(id));
	}

}
