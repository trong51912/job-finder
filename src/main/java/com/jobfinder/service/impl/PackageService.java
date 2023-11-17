package com.jobfinder.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.jobfinder.converter.ServiceConverter;
import com.jobfinder.dto.ServiceDTO;
import com.jobfinder.entity.ServiceEntity;
import com.jobfinder.repository.EmployerRepository;
import com.jobfinder.repository.PackageRepository;
import com.jobfinder.service.IPackageService;

@Service
public class PackageService implements IPackageService {
	@Autowired
	private PackageRepository packageRepository;
	
	@Autowired
	private ServiceConverter serviceConverter;
	
	@Autowired
	private EmployerRepository employerRepository;

	@Override
	@Transactional
	public ServiceDTO findById(long id) {
		// TODO Auto-generated method stub
		ServiceEntity entity = packageRepository.findOne(id); 
		return serviceConverter.toDto(entity);
	}

	@Override
	@Transactional
	public List<ServiceDTO> finAll() {
		// TODO Auto-generated method stub
		List<ServiceDTO> models = new  ArrayList<>();
		List<ServiceEntity> entities = packageRepository.findAll();
		for(ServiceEntity item : entities) {
			ServiceDTO serviceModel = serviceConverter.toDto(item);
			models.add(serviceModel);
		}
		return models;
	}
	
	
}
