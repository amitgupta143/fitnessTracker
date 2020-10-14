package com.deloitte.fitnesstracker.constants;

public interface FitnessTrackerConstants {

	public static final String SECRET_VALUE = "${security.jwt.token.secret-key:secret}";
	public static final String SECRET ="secret";
	public static final String EXPIRY = "${security.jwt.token.expire-length:7200000}";
	public static final String ROLES = "roles";
	public static final String AUTHORIZATION = "Authorization";
	public static final String BEARER = "Bearer ";
	public static final String INVALID_TOKEN = "Expired or invalid JWT token";
	public static final String EMPTY = "";
	public static final String STAR = "*";
	public static final String LOGIN = "/api/auth/login";
	public static final String REGISTER = "/api/auth/register";
	public static final String ACTUATOR = "/actuator/**";
	public static final String USER_AUTH = "/api/auth/user/**";
	public static final String USER = "USER";
	public static final String UNAUTHORIZED = "Unauthorized";
	public static final String RESOURCE = "/resources/**";
	public static final String STATIC = "/static/**";
	public static final String CSS = "/css/**";
	public static final String JS = "/js/**";
	public static final String IMAGES = "/images/**";
	public static final String API_MAPPING = "/api/auth";
	public static final String API_LOGIN = "/login";
	public static final String API_REGISTER = "/register";
	public static final String API_USER = "/user";
	public static final String API_USER_PROFILE_SAVE = "/user/saveProfile";
	public static final String API_CALCULATE_BMI = "/user/calculateBMI";
	public static final String INVALID_MSG = "Invalid email id or password";
	public static final String USERNAME = "username";
	public static final String TOKEN = "token";
	public static final String EMAIL_ID = "emailId";
	public static final String MSG = "msg";
	public static final String SAVE_MSG = "Member Details updated successfully";
	public static final String USERNAME_NOT_FOUND = "username not found";
	public static final String USER_PROFIE = "userProfile";
	public static final Double INCHES_TO_METER_UNIT_CONVERSION = 39.37;


}
