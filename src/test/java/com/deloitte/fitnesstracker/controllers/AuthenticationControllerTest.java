package com.deloitte.fitnesstracker.controllers;

import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.API_LOGIN;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.API_MAPPING;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.*;

import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.TOKEN;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.USER;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.USERNAME;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.deloitte.fitnesstracker.config.JwtTokenProvider;
import com.deloitte.fitnesstracker.dto.LoginDto;
import com.deloitte.fitnesstracker.dto.UserDto;
import com.deloitte.fitnesstracker.repository.UserRepository;
import com.deloitte.fitnesstracker.service.impl.UserDetailsServiceImpl;
import com.deloitte.fitnesstracker.vo.Role;
import com.deloitte.fitnesstracker.vo.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class AuthenticationControllerTest {

	private MockMvc mvc;
	private ObjectMapper mapper;
	private AuthenticationController controller;
	private JwtTokenProvider jwtTokenProvider;
	private PasswordEncoder bCryptPasswordEncoder;
	private UserDetailsServiceImpl userService;
	
	@Mock
	AuthenticationManager authenticationManager;
	
	@Mock
	UserRepository users;
	
	@Before
	public void setup() throws Exception {
		initMocks(this);
		mapper = new ObjectMapper();
		controller = new AuthenticationController();
		jwtTokenProvider = new JwtTokenProvider();
		bCryptPasswordEncoder = new BCryptPasswordEncoder();
		userService = new UserDetailsServiceImpl();
		ReflectionTestUtils.setField(controller, "jwtTokenProvider", jwtTokenProvider);
		ReflectionTestUtils.setField(controller, "bCryptPasswordEncoder", bCryptPasswordEncoder);
		ReflectionTestUtils.setField(controller, "userService", userService);
		ReflectionTestUtils.setField(controller, "authenticationManager", authenticationManager);
		ReflectionTestUtils.setField(controller, "users", users);
		ReflectionTestUtils.setField(userService, "userRepository", users);
		ReflectionTestUtils.setField(jwtTokenProvider, "userDetailsService", userService);
		
		User user = new User();
		Role userRole = new Role(USER);
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
		
		when(users.findByEmailId(anyString())).thenReturn(user);
		
		mvc = standaloneSetup(controller).build();
	}
	
	@Test
	public void loginTest() throws Exception {
		LoginDto login = new LoginDto();
		login.setEmailId("test@deloitte.com");
		login.setPassword("test");
		
		MvcResult result = mvc.perform(post(API_MAPPING+API_LOGIN)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(login)))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
		
		assertNotNull(result.getResponse().getContentAsString());
		assertTrue(result.getResponse().getContentAsString().contains(TOKEN));
		assertTrue(result.getResponse().getContentAsString().contains(USERNAME));
	}
	
	@Test
	public void registerTest() throws Exception {
		when(users.findByEmailId(anyString())).thenReturn(null);

		UserDto user = new UserDto();
		user.setEmailId("test2@deloitte.com");
		user.setPassword("test");
		
		MvcResult result = mvc.perform(post(API_MAPPING+API_REGISTER)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(user)))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
		
		assertNotNull(result.getResponse().getContentAsString());
		assertTrue(result.getResponse().getContentAsString().contains(TOKEN));
		assertTrue(result.getResponse().getContentAsString().contains(USERNAME));
	}
}
