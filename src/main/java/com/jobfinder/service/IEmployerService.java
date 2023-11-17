package com.jobfinder.service;

import java.util.List;

import com.jobfinder.dto.EmployerDTO;

public interface IEmployerService {
	EmployerDTO findById(long id);
	Long getUserIdByUsername(String username);
	List<EmployerDTO> findAll();
	EmployerDTO save(EmployerDTO dto);
	void delete(long[] ids);
	void updatePackageService(Long emId , Long serId );
//	void updateEmployer(EmployerDTO dto);
	EmployerDTO getEmployerProfile(Long id);
	void updateEmployerInfo(Long id, Long employerId, EmployerDTO employerDTO);
}
