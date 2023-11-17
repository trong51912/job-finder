package com.jobfinder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobfinder.entity.ServiceEntity;

public interface PackageRepository extends JpaRepository<ServiceEntity, Long>{
    List<ServiceEntity> findAllById(List<Long> ids);
}
