package com.jobfinder.dto;

import org.springframework.stereotype.Component;

@Component
public class CategoryDTO extends AbstractDTO<CategoryDTO>{

	private String name;
	private String code;
	private String positon;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPositon() {
		return positon;
	}

	public void setPositon(String positon) {
		this.positon = positon;
	}
	
	
}