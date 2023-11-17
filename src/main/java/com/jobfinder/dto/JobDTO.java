package com.jobfinder.dto;

import java.util.List;

public class JobDTO extends AbstractDTO<JobDTO>{
	
	private String title;
	
	private String type;
	
	private String description;
	
	private String requirements;
	
	private String benefit;
	
	private String deadline;
	
	private int salary;
	
	private String location;
	
	private String position;
	
	private List<Long> skills;
	
	private Long category_id;
	
	private Long employer_id;

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public String getBenefit() {
		return benefit;
	}

	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	public List<Long> getSkills() {
		return skills;
	}

	public void setSkills(List<Long> skills) {
		this.skills = skills;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public Long getEmployer_id() {
		return employer_id;
	}

	public void setEmployer_id(Long employer_id) {
		this.employer_id = employer_id;
	}
	
}