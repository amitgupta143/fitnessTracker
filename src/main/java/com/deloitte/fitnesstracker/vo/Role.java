package com.deloitte.fitnesstracker.vo;

public class Role {

	private String role;
	
	public Role(String role) {
		super();
		this.role = role;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Role [role=").append(role).append("]");
		return builder.toString();
	}
	
	
}