package com.jobfinder.dto;

import java.util.List;

public class SkillDTO extends AbstractDTO<SkillDTO>{
	private String name;
	private Long category_id;
	private List<Long> applicants;
	private List<Long> jobs;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}
	public List<Long> getApplicants() {
		return applicants;
	}
	public void setApplicants(List<Long> applicants) {
		this.applicants = applicants;
	}
	public List<Long> getJobs() {
		return jobs;
	}
	public void setJobs(List<Long> jobs) {
		this.jobs = jobs;
	}
	
	
}
