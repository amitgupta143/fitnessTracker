package com.deloitte.fitnesstracker.dto;

import java.time.LocalDate;

public class DietInfoDto {
	
	private String id;
	private LocalDate date;
	private String userName; 
	private DietDto breakfast;
	private DietDto lunch;
	private DietDto snacks;
	private DietDto dinner;
	private long totalCalories;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public DietDto getBreakfast() {
		return breakfast;
	}
	public void setBreakfast(DietDto breakfast) {
		this.breakfast = breakfast;
	}
	public DietDto getLunch() {
		return lunch;
	}
	public void setLunch(DietDto lunch) {
		this.lunch = lunch;
	}
	public DietDto getSnacks() {
		return snacks;
	}
	public void setSnacks(DietDto snacks) {
		this.snacks = snacks;
	}
	public DietDto getDinner() {
		return dinner;
	}
	public void setDinner(DietDto dinner) {
		this.dinner = dinner;
	}
	public long getTotalCalories() {
		return totalCalories;
	}
	public void setTotalCalories(long totalCalories) {
		this.totalCalories = totalCalories;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DietInfoDto [id=").append(id).append(", date=").append(date).append(", userName=")
				.append(userName).append(", breakfast=").append(breakfast).append(", lunch=").append(lunch)
				.append(", snacks=").append(snacks).append(", dinner=").append(dinner).append(", totalCalories=")
				.append(totalCalories).append("]");
		return builder.toString();
	}
	
	
	
}
