package com.deloitte.fitnesstracker.dto;

public class ExcerciseDto {

	private String name;
	private String calories;
	
	public ExcerciseDto(String name, String calories) {
		super();
		this.name = name;
		this.calories = calories;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getCalories() {
		return calories;
	}
	public void setCalories(String calories) {
		this.calories = calories;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExcerciseDto [name=").append(name).append(", calories=").append(calories).append("]");
		return builder.toString();
	}
}
