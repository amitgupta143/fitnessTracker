package com.deloitte.fitnesstracker.controllers;

import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.API_CALCULATE_BMI;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.API_MAPPING;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.API_USER;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.API_USER_PROFILE_SAVE;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.EMAIL_ID;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.INCHES_TO_METER_UNIT_CONVERSION;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.MSG;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.SAVE_MSG;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.STAR;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.USER_PROFIE;
import static org.springframework.http.ResponseEntity.ok;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.fitnesstracker.dto.UserProfileDTO;
import com.deloitte.fitnesstracker.repository.UserRepository;
import com.deloitte.fitnesstracker.service.impl.UserDetailsServiceImpl;
import com.deloitte.fitnesstracker.vo.User;

@CrossOrigin(origins = STAR)
@RestController
@RequestMapping(API_MAPPING)
public class UserProfileController {
	
	private Logger logger = LoggerFactory.getLogger(UserProfileController.class);

	@Autowired
	private UserDetailsServiceImpl userService;
	
	@GetMapping(API_USER)
	public ResponseEntity<UserProfileDTO> getUserProfile(@RequestParam(value=EMAIL_ID, required = true) String emailId){
		logger.info("UserProfileController : getUserProfile : loadUserProfileByUsername start");
		UserProfileDTO userProfile = userService.loadUserProfileByUsername(emailId);
		logger.info("UserProfileController : getUserProfile : loadUserProfileByUsername userProfile {} ", userProfile);
		return new ResponseEntity<UserProfileDTO>(userProfile, HttpStatus.OK);
	}
	
	@PostMapping(API_CALCULATE_BMI)
	public ResponseEntity<UserProfileDTO> calculateBMI(@RequestBody UserProfileDTO userProfileData){
		
		logger.info("UserProfileController : calculateBMI start");

		Double heightInInches = Double.valueOf(userProfileData.getHeight());
		Double weightInKgs = Double.valueOf(userProfileData.getWeight());
		Double heightInMeters = (heightInInches/INCHES_TO_METER_UNIT_CONVERSION);
	
		heightInInches = roundDouble(heightInInches,BigDecimal.ROUND_CEILING);
		weightInKgs = roundDouble(weightInKgs,BigDecimal.ROUND_CEILING);
		heightInMeters = roundDouble(heightInMeters,BigDecimal.ROUND_CEILING);
		
		logger.info("UserProfileController : calculateBMI heightInInches: {} , weightInKgs: {}, heightInMeters: {} ", heightInInches, weightInKgs, heightInMeters);
		
		Double bmi = (weightInKgs)/(heightInMeters*heightInMeters); // BMI formula (weightInKgs)/(meters*meters)
		
		bmi = roundDouble(bmi , BigDecimal.ROUND_CEILING);
		
		logger.info("UserProfileController : calculateBMI BMI : {} ", bmi.toString());
		userProfileData.setBmi(bmi.toString());
		logger.info("UserProfileController : calculateBMI userProfileData {} ", userProfileData);

		return new ResponseEntity<UserProfileDTO>(userProfileData, HttpStatus.OK);
	}
	
	@PostMapping(API_USER_PROFILE_SAVE)
	public ResponseEntity<Map<Object, Object>> saveUserProfile(@RequestBody UserProfileDTO userProfileData){
		
		logger.info("UserProfileController : saveUserProfile  starts");
		User userExists = userService.findUserByEmail(userProfileData.getEmailId());
		
		logger.info("UserProfileController : saveUserProfile is userExists : {} ", !ObjectUtils.isEmpty(userExists));

		if (userExists == null) {
			throw new BadCredentialsException("User with username: " + userProfileData.getEmailId() + " doesn't exists");
		}
		
		BeanUtils.copyProperties(userProfileData, userExists);
		logger.info("UserProfileController : saveUserProfile saving profile");

		userService.saveUser(userExists);
		BeanUtils.copyProperties(userExists, userProfileData);
		
		logger.info("UserProfileController : saveUserProfile profile saved");
		
		Map<Object, Object> model = new HashMap<>();
		model.put(MSG, SAVE_MSG);
		model.put(USER_PROFIE, userProfileData);
		
		return ok(model);
	}
	
	/**
	 * Rounding Double value precision to 2 places
	 */
	private double roundDouble(double d, int places) {
	    BigDecimal bigDecimal = new BigDecimal(Double.toString(d));
	    bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
	    return bigDecimal.doubleValue();
	}
}
