package com.jobfinder.service;

import java.util.List;

import com.jobfinder.dto.SkillDTO;

public interface ISkillService {
	List<SkillDTO> findAll();
	SkillDTO findById(long id);
}
