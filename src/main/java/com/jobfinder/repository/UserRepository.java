package com.jobfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobfinder.entity.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Long>{
	UserEntity findOneByUserNameAndStatus(String name, int status);
	UserEntity findOneByEmailAndStatus(String email, int status);
	UserEntity findByUserName(String userName);
	UserEntity findOneByUserName(String username);
}