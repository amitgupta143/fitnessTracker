package com.deloitte.fitnesstracker.dto;

import java.time.LocalDate;

public class ExcerciseInfoDto {

	private String id;
	private LocalDate date;
	private String userName; 
	private ExcerciseDto cardio;
	private ExcerciseDto weightLift;
	private ExcerciseDto yoga;
	private ExcerciseDto others;
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
	public ExcerciseDto getCardio() {
		return cardio;
	}
	public void setCardio(ExcerciseDto cardio) {
		this.cardio = cardio;
	}
	public ExcerciseDto getWeightLift() {
		return weightLift;
	}
	public void setWeightLift(ExcerciseDto weightLift) {
		this.weightLift = weightLift;
	}
	public ExcerciseDto getYoga() {
		return yoga;
	}
	public void setYoga(ExcerciseDto yoga) {
		this.yoga = yoga;
	}
	public ExcerciseDto getOthers() {
		return others;
	}
	public void setOthers(ExcerciseDto others) {
		this.others = others;
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
		builder.append("ExcerciseInfoDto [id=").append(id).append(", date=").append(date).append(", userName=")
				.append(userName).append(", cardio=").append(cardio).append(", weightLift=").append(weightLift)
				.append(", yogs=").append(yoga).append(", others=").append(others).append(", totalCalories=")
				.append(totalCalories).append("]");
		return builder.toString();
	}
}
