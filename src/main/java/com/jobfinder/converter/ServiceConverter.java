package com.jobfinder.converter;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;
import com.jobfinder.dto.ServiceDTO;
import com.jobfinder.entity.ServiceEntity;
@Component
public class ServiceConverter {
	
	public ServiceDTO toDto(ServiceEntity entity) {

		ServiceDTO result = new ServiceDTO();
		result.setId(entity.getId());
		result.setName(entity.getName());
		result.setPrice(entity.getPrice());
		result.setJobPostNumber(entity.getJobPostNumber());
//		result.setCreateAt((Timestamp) entity.getCreate_at());
		return result;
	}
	
	public ServiceEntity toEntity(ServiceDTO dto) {
		ServiceEntity result = new ServiceEntity();
		result.setName(dto.getName());
		result.setJobPostNumber(dto.getJobPostNumber());
		result.setPrice(dto.getPrice());
		result.setStatus(dto.getStatus());
		return result;
	}
	public ServiceEntity toEntity(ServiceEntity result, ServiceDTO dto) {
		result.setName(dto.getName());
		result.setJobPostNumber(dto.getJobPostNumber());
		result.setPrice(dto.getPrice());
		result.setStatus(dto.getStatus());
		return result;
	}

}
