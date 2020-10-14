package com.deloitte.fitnesstracker.dto;

public class TipsDto {
	
	private String type;
	private String resourceLink;
	
	public TipsDto(String type, String resourceLink) {
		super();
		this.type = type;
		this.resourceLink = resourceLink;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getResourceLink() {
		return resourceLink;
	}
	public void setResourceLink(String resourceLink) {
		this.resourceLink = resourceLink;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TipsDto [type=").append(type).append(", resourceLink=").append(resourceLink).append("]");
		return builder.toString();
	}

}
