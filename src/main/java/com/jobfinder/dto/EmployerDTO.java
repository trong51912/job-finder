package com.jobfinder.dto;

import com.jobfinder.entity.EmployerEntity;
import com.jobfinder.entity.UserEntity;

public class EmployerDTO extends AbstractDTO<UserDTO>{
	private Long id;
	private String companyName;
	private String companyAddress;
	private String companyIntroduce;
	private String position;
	private String firstName;
	private String lastName;
	private String email;
	private Integer status;
	private Long roleId;
	private String userName;
	private String password;
	private String confirmPassword;
	private String phone;
	private	Long user_id;
	private String service;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCompanyIntroduce() {
		return companyIntroduce;
	}
	public void setCompanyIntroduce(String companyIntroduce) {
		this.companyIntroduce = companyIntroduce;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	 public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public void updateEmployerInfo(UserEntity user) {
	        // Cập nhật thông tin của employer từ các thuộc tính trong UserEntity
	        this.setEmail(user.getEmail());
	        this.setFirstName(user.getFirstName());
	        this.setLastName(user.getLastName());
	        this.setPhone(user.getPhone());
	        // ...
	    }
	    
	    // Phương thức để cập nhật thông tin của employer
	    public void updateEmployerInfo(EmployerEntity employer) {
	        // Cập nhật thông tin của employer từ các thuộc tính trong EmployerDTO
	        employer.setCompanyName(this.getCompanyName());
	        employer.setCompanyAddress(this.getCompanyAddress());
	        employer.setPosition(this.getPosition());
	        // ...
	    }
}
