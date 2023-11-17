package com.jobfinder.dto;

import java.util.List;

public class UserDTO extends AbstractDTO<UserDTO>{
	private String userName;
	private String password;
	private String confirmPassword;
	private String passwordOld;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String phone;
	private Integer status;
	private List<Long> roleId;
	private	Long applicant_id;
	private	Long employer_id;
	
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
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getPasswordOld() {
		return passwordOld;
	}
	public void setPasswordOld(String passwordOld) {
		this.passwordOld = passwordOld;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public List<Long> getRoleId() {
		return roleId;
	}
	public void setRoleId(List<Long> roleId) {
		this.roleId = roleId;
	}
	public Long getApplicant_id() {
		return applicant_id;
	}
	public void setApplicant_id(Long applicant_id) {
		this.applicant_id = applicant_id;
	}
	public Long getEmployer_id() {
		return employer_id;
	}
	public void setEmployer_id(Long employer_id) {
		this.employer_id = employer_id;
	}	
	
}
