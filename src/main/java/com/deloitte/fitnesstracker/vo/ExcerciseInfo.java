package com.deloitte.fitnesstracker.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.deloitte.fitnesstracker.constants.FitnessTrackerConstants;

@Document(collection = FitnessTrackerConstants.EXCERCISE_COLLECTION)
public class ExcerciseInfo {

	@Id
	private String id;
	private String date;
	private String userName; 
	private Excercise cardio;
	private Excercise weightLift;
	private Excercise yoga;
	private Excercise others;
	private long totalCalories;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Excercise getCardio() {
		return cardio;
	}
	public void setCardio(Excercise cardio) {
		this.cardio = cardio;
	}
	public Excercise getWeightLift() {
		return weightLift;
	}
	public void setWeightLift(Excercise weightLift) {
		this.weightLift = weightLift;
	}
	public Excercise getYoga() {
		return yoga;
	}
	public void setYoga(Excercise yoga) {
		this.yoga = yoga;
	}
	public Excercise getOthers() {
		return others;
	}
	public void setOthers(Excercise others) {
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
		builder.append("ExcerciseInfo [id=").append(id).append(", date=").append(date).append(", userName=")
				.append(userName).append(", cardio=").append(cardio).append(", weightLift=").append(weightLift)
				.append(", yogs=").append(yoga).append(", others=").append(others).append(", totalCalories=")
				.append(totalCalories).append("]");
		return builder.toString();
	}
}
