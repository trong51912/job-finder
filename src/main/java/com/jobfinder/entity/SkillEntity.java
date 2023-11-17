package com.jobfinder.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "skill")
public class SkillEntity extends BaseEntity{
	
	@Column(name = "name")
	private String name;
	
	@ManyToMany(mappedBy = "applicantSkills")
    private List<ApplicantEntity> applicants = new ArrayList<>();
	
	@ManyToMany(mappedBy = "jobSkills")
    private List<JobEntity> jobs = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private CategoryEntity category;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ApplicantEntity> getApplicants() {
		return applicants;
	}

	public void setApplicants(List<ApplicantEntity> applicants) {
		this.applicants = applicants;
	}

	public List<JobEntity> getJobs() {
		return jobs;
	}

	public void setJobs(List<JobEntity> jobs) {
		this.jobs = jobs;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}
	
}
