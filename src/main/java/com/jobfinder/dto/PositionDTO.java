package com.jobfinder.dto;

public class PositionDTO extends AbstractDTO<JobDTO>{
	private String name;
	
	private Long category_id;

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
}
