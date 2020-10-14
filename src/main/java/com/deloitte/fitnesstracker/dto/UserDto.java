package com.deloitte.fitnesstracker.dto;

public class UserDto extends UserProfileDTO {

    private String password;
    private String cnfPassword;
   
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCnfPassword() {
		return cnfPassword;
	}
	public void setCnfPassword(String cnfPassword) {
		this.cnfPassword = cnfPassword;
	}
    
}
