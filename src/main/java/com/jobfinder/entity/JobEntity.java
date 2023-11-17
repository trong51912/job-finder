package com.jobfinder	.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jobs")
public class JobEntity extends BaseEntity{
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	@Column(name = "requirements", columnDefinition = "TEXT")
	private String requirements;
	
	@Column(name = "benefit", columnDefinition = "TEXT")
	private String benefit;
	
	@Column(name = "application_deadline")
	private Date applicationDeadline;
	
	@Column(name = "salary")
	private int salary;
	
	@Column(name = "location")
	private String location;
	

	@Column(name = "position")
	private String position;
	
	@ManyToOne
	@JoinColumn(name = "employer_id")
	private EmployerEntity employer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private CategoryEntity category;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "job_skill", joinColumns = @JoinColumn(name = "job_id"), 
								  inverseJoinColumns = @JoinColumn(name = "skill_id"))
	private List<SkillEntity> jobSkills = new ArrayList<>();
	
	@ManyToMany(mappedBy = "appliedJobs")
	private List<ApplicantEntity> applicants = new ArrayList<>();
	
	public List<ApplicantEntity> getApplicants() {
	    return applicants;
	}

	public void setApplicants(List<ApplicantEntity> applicants) {
	    this.applicants = applicants;
	}

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

	public Date getApplicationDeadline() {
		return applicationDeadline;
	}

	public void setApplicationDeadline(Date applicationDeadline) {
		this.applicationDeadline = applicationDeadline;
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

	public EmployerEntity getEmployer() {
		return employer;
	}

	public void setEmployer(EmployerEntity employer) {
		this.employer = employer;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public List<SkillEntity> getSkills() {
		return jobSkills;
	}

	public void setSkills(List<SkillEntity> jobSkills) {
		this.jobSkills = jobSkills;
	}
	
}