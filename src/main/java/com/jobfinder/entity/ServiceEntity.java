package com.jobfinder.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "services")
public class ServiceEntity extends BaseEntity{
	@Column(name = "service_name")
	private String name;
	@Column(name = "price")
	private int  price ;
	@Column(name = "jobPostNumber")
	private int jobPostNumber; 
	@Column(name = "status")
	private int status;
	@OneToMany(mappedBy="service")
	private List<EmployerEntity> employerEntities;

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
	public List<EmployerEntity> getEmployerEntities() {
		return employerEntities;
	}
	public void setEmployerEntities(List<EmployerEntity> employerEntities) {
		this.employerEntities = employerEntities;
	}
	
	

}
