package com.deloitte.fitnesstracker.dto;

import java.util.Date;

public class UserProfileDTO {

	private String emailId;
    private String firstName;
    private String lastName;
    private long phoneNumber;
    private Date dob;
    private String height;
    private String weight;
    private String bmi;
    private String gender;
    
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getBmi() {
		return bmi;
	}
	public void setBmi(String bmi) {
		this.bmi = bmi;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserProfileDTO [emailId=").append(emailId).append(", firstName=").append(firstName)
				.append(", lastName=").append(lastName).append(", phoneNumber=").append(phoneNumber).append(", dob=")
				.append(dob).append(", height=").append(height).append(", weight=").append(weight).append(", bmi=")
				.append(bmi).append(", gender=").append(gender).append("]");
		return builder.toString();
	}
	
	
}
