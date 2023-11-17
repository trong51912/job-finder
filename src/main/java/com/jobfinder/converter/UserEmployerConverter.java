//package com.jobfinder.converter;
//
//import org.springframework.stereotype.Component;
//
//import com.jobfinder.dto.UserEmployerDTO;
//import com.jobfinder.entity.EmployerEntity;
//import com.jobfinder.entity.UserEntity;
//
//@Component
//public class UserEmployerConverter{
//	
//	public UserEntity convertToUser(UserEmployerDTO userEmployerDTO) {
//		UserEntity user = new UserEntity();
//        user.setId(userEmployerDTO.getUserId());
//        user.setUserName(userEmployerDTO.getUserName());
//        user.setFirstName(userEmployerDTO.getFirstname());
//        user.setLastName(userEmployerDTO.getLastName());
//        user.setAddress(userEmployerDTO.getAddress());
//        user.setEmail(userEmployerDTO.getEmail());
//        user.setPhone(userEmployerDTO.getPhone());
//
//        if (userEmployerDTO.getEmployerId() != null) {
//            EmployerEntity employer = new EmployerEntity();
//            employer.setId(userEmployerDTO.getEmployerId());
//            employer.setCompanyName(userEmployerDTO.getCompany_name());
//            employer.setCompanyAddress(userEmployerDTO.getCompany_address());
//            employer.setPosition(userEmployerDTO.getPosition());
//            employer.setUser(user);
//            user.setEmployer(employer);
//        }
//
//        return user;
//    }
//
//    public UserEmployerDTO convertToUserEmployerDTO(UserEntity user) {
//        UserEmployerDTO userEmployerDTO = new UserEmployerDTO();
//        user.setId(userEmployerDTO.getUserId());
//        user.setUserName(userEmployerDTO.getUserName());
//        user.setFirstName(userEmployerDTO.getFirstname());
//        user.setLastName(userEmployerDTO.getLastName());
//        user.setAddress(userEmployerDTO.getAddress());
//        user.setEmail(userEmployerDTO.getEmail());
//        user.setPhone(userEmployerDTO.getPhone());
//
//        if (user.getEmployer() != null) {
//            EmployerEntity employer = user.getEmployer();
//            employer.setId(userEmployerDTO.getEmployerId());
//            employer.setCompanyName(userEmployerDTO.getCompany_name());
//            employer.setCompanyAddress(userEmployerDTO.getCompany_address());
//            employer.setPosition(userEmployerDTO.getPosition());
//            employer.setUser(user);
//        }
//
//        return userEmployerDTO;
//    }
//
//}
