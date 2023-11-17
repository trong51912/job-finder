package com.jobfinder.dto;

import com.google.protobuf.Timestamp;

public class ServiceDTO extends AbstractDTO<ServiceDTO>{
	private String name ;
	private int price;
	private int jobPostNumber; 
	private  Integer status;
	private Timestamp createAt;
	private Timestamp expiredAt;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getJobPostNumber() {
		return jobPostNumber;
	}
	public void setJobPostNumber(int jobPostNumber) {
		this.jobPostNumber = jobPostNumber;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
