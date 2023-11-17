package com.jobfinder.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobfinder.converter.UserConverter;
import com.jobfinder.dto.UserDTO;
import com.jobfinder.entity.RoleEntity;
import com.jobfinder.entity.UserEntity;
import com.jobfinder.repository.RoleRepository;
import com.jobfinder.repository.UserRepository;
import com.jobfinder.security.BcryptPassword;
import com.jobfinder.service.IUserService;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	private RoleRepository roleRepository;
	
	

	@Override
	public List<UserDTO> findAll() {
		List<UserDTO> models = new ArrayList<>();
		List<UserEntity> entities = userRepository.findAll();
		for (UserEntity item : entities) {
			UserDTO userModel = userConverter.toDto(item);
			models.add(userModel);
		}

		return models;
	}


	@Override
	public UserDTO findById(long id) {
		UserEntity entity = userRepository.findOne(id);
		return userConverter.toDto(entity);
	}
	
	@Override
	public UserDTO findOneByUserNameAndStatus(String userName, int status) {
		UserEntity entity = userRepository.findOneByUserNameAndStatus(userName, 1);
		if(entity!=null) {
			return userConverter.toDto(entity);
		}else {
			return null;
		}
	}
	
	@Override
	public UserDTO findOneByEmailAndStatus(String email, int status) {
		UserEntity entity = userRepository.findOneByEmailAndStatus(email, 1);
		if(entity!=null) {
			return userConverter.toDto(entity);
		}else {
			return null;
		}
	}

	@Override
	@Transactional
	public UserDTO save(UserDTO dto) {
		UserEntity userEntity = new UserEntity();
		if (dto.getId() != null) {
			UserEntity oldUser = userRepository.findOne(dto.getId());
			userEntity = userConverter.toEntity(oldUser, dto);
		} else {
			userEntity = userConverter.toEntity(dto);
			
			List<RoleEntity> roles = new ArrayList<>();
			for(Long id: dto.getRoleId()) {
				roles.add(roleRepository.findOne(id));
			}
			userEntity.setRoles(roles);
			
			userEntity.setStatus(1);
		}
		return userConverter.toDto(userRepository.save(userEntity));
	}
	
	@Override
	@Transactional
	public void resetPassword(UserDTO dto) {
		BcryptPassword bcryptPassword = new BcryptPassword();
		UserEntity userEntity = userRepository.findOne(dto.getId());
		userEntity.setPassword(bcryptPassword.BcryptPass(dto.getPassword()));
		userRepository.save(userEntity);
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (long id : ids) {
			userRepository.delete(id);
		}
	}
	
	@Override
	@Transactional
    public UserEntity blockUser(Long userId) {
		UserEntity entity = userRepository.findOne(userId);
        if (entity != null) {
        	entity.setStatus(0);
            return userRepository.save(entity);
        }
        return null;
    }
	
	@Override
	@Transactional
    public UserEntity unblockUser(Long userId) {
		UserEntity entity = userRepository.findOne(userId);
        if (entity != null) {
        	entity.setStatus(1);
            return userRepository.save(entity);
        }
        return null;
    }

	@Override
	public UserDTO findByUserName(String userName) {
		UserEntity userEntity = userRepository.findByUserName(userName);
		return userConverter.toDto(userEntity);
	}
	
	@Override
	public void updateUser(UserDTO userDTO) {
		UserEntity userEntity = userRepository.findByUserName(userDTO.getUserName());
//		if (userEntity != null) {
//	        userEntity.setFirstName(userDTO.getFirstName());
//	        userRepository.save(userEntity);
//	    } 
		userEntity.setFirstName(userDTO.getFirstName());
		userEntity.setLastName(userDTO.getLastName());
		userEntity.setPhone(userDTO.getPhone());
		userEntity.setAddress(userDTO.getAddress());
		userEntity.setEmail(userDTO.getEmail());
		userRepository.save(userEntity);
	}



}
