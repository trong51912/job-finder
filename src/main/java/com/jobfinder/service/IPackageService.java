package com.jobfinder.service;

import java.util.List;

import com.jobfinder.dto.ServiceDTO;

public interface IPackageService {
	ServiceDTO findById(long id);
	List<ServiceDTO> finAll();
}
