package com.deloitte.fitnesstracker.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.deloitte.fitnesstracker.constants.FitnessTrackerConstants;

@Document(collection = FitnessTrackerConstants.DIET_COLLECTION)
public class DietInfo {

	@Id
	private String id;
	private String date;
	private String userName; 
	private Diet breakfast;
	private Diet lunch;
	private Diet snacks;
	private Diet dinner;
	private long totalCalories;
	
	public String getId() {
		return id;
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
	public Diet getBreakfast() {
		return breakfast;
	}
	public void setBreakfast(Diet breakfast) {
		this.breakfast = breakfast;
	}
	public Diet getLunch() {
		return lunch;
	}
	public void setLunch(Diet lunch) {
		this.lunch = lunch;
	}
	public Diet getSnacks() {
		return snacks;
	}
	public void setSnacks(Diet snacks) {
		this.snacks = snacks;
	}
	public Diet getDinner() {
		return dinner;
	}
	public void setDinner(Diet dinner) {
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
		builder.append("DietInfo [id=").append(id).append(", date=").append(date).append(", userName=").append(userName)
				.append(", breakfast=").append(breakfast).append(", lunch=").append(lunch).append(", snacks=")
				.append(snacks).append(", dinner=").append(dinner).append(", totalCalories=").append(totalCalories)
				.append("]");
		return builder.toString();
	}
}
