package com.jobfinder.dto;

public class RoleDTO extends AbstractDTO<RoleDTO>{

	private String role_name;
	private String code;
	
	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
