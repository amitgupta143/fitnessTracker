package com.deloitte.fitnesstracker.controllers;

import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.API_LOGIN;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.API_MAPPING;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.API_REGISTER;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.INVALID_MSG;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.STAR;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.TOKEN;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.USERNAME;
import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.fitnesstracker.config.JwtTokenProvider;
import com.deloitte.fitnesstracker.dto.LoginDto;
import com.deloitte.fitnesstracker.dto.UserDto;
import com.deloitte.fitnesstracker.repository.UserRepository;
import com.deloitte.fitnesstracker.service.impl.UserDetailsServiceImpl;
import com.deloitte.fitnesstracker.vo.Role;
import com.deloitte.fitnesstracker.vo.User;

@CrossOrigin(origins = STAR)
@RestController
@RequestMapping(API_MAPPING)
public class AuthenticationController {

	private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
    private PasswordEncoder bCryptPasswordEncoder;

	@Autowired
	UserRepository users;

	@Autowired
	private UserDetailsServiceImpl userService;

	@PostMapping(API_LOGIN)
	public ResponseEntity<Map<Object, Object>> login(@RequestBody LoginDto data) {
		try {
			logger.info("AuthenticationController : Login :  Login Data {} ", data);
			String username = data.getEmailId();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
			User user = this.users.findByEmailId(username);
			Set<Role> roles = null;		
			if(null != user) {
				roles = user.getRoles();
			}
			String token = jwtTokenProvider.createToken(username, roles);
			logger.info("AuthenticationController : Login : Created Token {} ", token);
			Map<Object, Object> model = new HashMap<>();
			model.put(USERNAME, username);
			model.put(TOKEN, token);
			return ok(model);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException(INVALID_MSG);
		}
	}

	@PostMapping(API_REGISTER)
	public ResponseEntity<Map<Object, Object>> register(@RequestBody UserDto userData) {
		logger.info("AuthenticationController : registration started"); 
		User userExists = userService.findUserByEmail(userData.getEmailId());
		
		logger.info("AuthenticationController : is Existed {} ", !ObjectUtils.isEmpty(userExists)); 

		if (userExists != null) {
			throw new BadCredentialsException("User with username: " + userData.getEmailId() + " already exists");
		}
		
		User user = new User();
		BeanUtils.copyProperties(userData, user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        
		logger.info("AuthenticationController : saving user"); 

		userService.saveUser(user);
		
		logger.info("AuthenticationController : registration Completed"); 
		
		LoginDto loginData = new LoginDto();
		loginData.setEmailId(userData.getEmailId());
		loginData.setPassword(userData.getPassword());
		
		logger.info("AuthenticationController : returning to login page after successful registration");
		return login(loginData);
	}
}
