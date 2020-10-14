package com.deloitte.fitnesstracker.constants;

public interface FitnessTrackerConstants {

	public static final String SESSION_ERROR = "Session Expired. Please re-login";
	public static final String EXCETION_MESSAGE = "Internal Server Error";
	public static final String RECORD_NOT_FOUND = "Record not found";
	public static final String EXCEPTION_MSG = "Handling Exception with JWT Token";
	public static final String SECRET_VALUE = "${security.jwt.token.secret-key:secret}";
	public static final String SECRET = "secret";
	public static final String AUTHORIZATION = "Authorization";
	public static final String BEARER = "Bearer ";
	public static final String INVALID_TOKEN = "Expired or invalid JWT token";
	public static final String STAR = "*";
	public static final String MSG = "msg";
	public static final String SAVE_MSG = "Diet Info saved successfully";
	public static final String DELETE_MSG = "Diet Info deleted successfully";
	public static final String SAVE_EXCERCISE_MSG = "Excercise Info saved successfully";
	public static final String DELETE_EXCERCISE_MSG = "Excercise Info deleted successfully";
	public static final String API_MAPPING = "/api/fitnessTracker";
	public static final String RETRIEVE_TIPS = "/retrieveTips";
	public static final String SAVE_DIET = "/diet/save";
	public static final String RETRIEVE_DIET = "/diet/retrieve";
	public static final String DELETE_DIET = "/diet/delete";
	public static final String SAVE_EXCERCISE = "/excercise/save";
	public static final String RETRIEVE_EXCERCISE = "/excercise/retrieve";
	public static final String DELETE_EXCERCISE = "/excercise/delete";
	public static final String EXCERCISE_COLLECTION = "excerciseinfo";
	public static final String DIET_COLLECTION = "dietinfo";
	public static final String EMAIL_ID = "emailId";
	public static final String ID = "id";
	public static final String RECIPES = "Recipes";
	public static final String EXCERCISES = "Excercises";
	public static final String LIFESTYLE = "LifeStyle";
	public static final String RECIPES_LINK = "https://food.ndtv.com/lists/10-best-diet-recipes-1210735";
	public static final String EXCERCISES_LINK = "https://www.shape.com/fitness/workouts/best-weight-loss-exercises";
	public static final String LIFESTYLE_LINK = "https://www.prevention.com/weight-loss/a20505548/small-changes-that-take-off-big-pounds/";

}
