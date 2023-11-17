package com.jobfinder.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.jobfinder.dto.RoleDTO;
import com.jobfinder.entity.RoleEntity;

public interface IRoleService {
	List<RoleDTO> findAll(Pageable pageable);
	int getTotalItem();
	RoleDTO findById(long id);
	List<RoleEntity> findByIds(List<Long> ids);
	RoleDTO save(RoleDTO dto);
	void delete(long[] ids);
	void updateRole(Long userId, Long roleId);
}