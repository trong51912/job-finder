package com.jobfinder.service;

import java.util.List;

import com.jobfinder.dto.UserDTO;
import com.jobfinder.entity.UserEntity;

public interface IUserService {
	UserDTO findById(long id);
	List<UserDTO> findAll();
	UserDTO findOneByUserNameAndStatus(String userName, int status);
	UserDTO findOneByEmailAndStatus(String email, int status);
	UserDTO save(UserDTO dto);
	void resetPassword(UserDTO dto);
	void delete(long[] ids);
	UserEntity blockUser(Long userId);
	UserEntity unblockUser(Long userId);
	UserDTO findByUserName(String userName);
	void updateUser(UserDTO userDTO);
}
