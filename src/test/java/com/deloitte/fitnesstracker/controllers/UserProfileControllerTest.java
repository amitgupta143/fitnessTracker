package com.deloitte.fitnesstracker.controllers;

import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.API_MAPPING;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.API_USER;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.EMAIL_ID;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.deloitte.fitnesstracker.dto.UserProfileDTO;
import com.deloitte.fitnesstracker.repository.UserRepository;
import com.deloitte.fitnesstracker.service.impl.UserDetailsServiceImpl;
import com.deloitte.fitnesstracker.vo.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class UserProfileControllerTest {

	private MockMvc mvc;
	private ObjectMapper mapper;
	private UserProfileController controller;
	private UserDetailsServiceImpl userService;

	@Mock
	private UserRepository repository;
	
	@Before
	public void setup() throws Exception {
		initMocks(this);
		mapper = new ObjectMapper();
		controller = new UserProfileController();
		userService = new UserDetailsServiceImpl();
		ReflectionTestUtils.setField(controller, "userService", userService);
		ReflectionTestUtils.setField(userService, "userRepository", repository);
		mvc = standaloneSetup(controller).build();
		when(repository.findByEmailId(anyString())).thenReturn(new User());
	}
	
	@Test
	public void getUserProfileTest() throws Exception {
		MvcResult result = mvc.perform(get(API_MAPPING+API_USER)
				.param(EMAIL_ID,"test@deloitte.com"))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
		
		assertNotNull(result.getResponse().getContentAsString());
	}
	
	@Test
	public void calculateBMI() throws Exception {
		UserProfileDTO data = new UserProfileDTO();
		data.setHeight("62");
		data.setWeight("68");
		
		MvcResult result = mvc.perform(post(API_MAPPING+API_CALCULATE_BMI)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(data)))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
		
		assertNotNull(result.getResponse().getContentAsString());
		UserProfileDTO response = mapper.readValue(result.getResponse().getContentAsString(), UserProfileDTO.class);
		assertEquals("27.59", response.getBmi());
	}
	
}
