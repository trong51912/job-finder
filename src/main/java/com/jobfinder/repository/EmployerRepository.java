package com.jobfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jobfinder.entity.EmployerEntity;

@Repository
public interface EmployerRepository extends JpaRepository<EmployerEntity, Long> {

  EmployerEntity findByUserId(Long userId); 
  

}
