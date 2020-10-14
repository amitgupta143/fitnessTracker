package com.deloitte.fitnesstracker.controllers;

import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.API_MAPPING;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.DELETE_DIET;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.ID;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.EMAIL_ID;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.RETRIEVE_TIPS;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.SAVE_DIET;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.SAVE_MSG;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.deloitte.fitnesstracker.controllers.DietController;
import com.deloitte.fitnesstracker.dto.DietInfoDto;
import com.deloitte.fitnesstracker.dto.TipsDto;
import com.deloitte.fitnesstracker.repository.DietRepository;
import com.deloitte.fitnesstracker.service.DietInfoService;
import com.deloitte.fitnesstracker.service.DietInfoServiceImpl;
import com.deloitte.fitnesstracker.vo.DietInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class DietControllerTest {

	private MockMvc mvc;

	DietController controller;

	DietInfoService service;
	
	ObjectMapper mapper;
	
	@Mock
	DietRepository repository;

	@Before
	public void setup() throws Exception {
		initMocks(this);
		mapper = new ObjectMapper();
		controller = new DietController();
		service = new DietInfoServiceImpl();
		ReflectionTestUtils.setField(controller, "service", service);
		ReflectionTestUtils.setField(service, "repository", repository);

		
		mvc = standaloneSetup(controller).build();
		List<DietInfo> list = new ArrayList<>();
		DietInfo info = new DietInfo();
		info.setDate("2020-09-25");
		list.add(info);
		
		when(repository.findByUserNameAndDate(anyString(),anyString())).thenReturn(new DietInfo());
		when(repository.findById(anyString())).thenReturn(Optional.of(new DietInfo()));
		when(repository.findByUserName(anyString())).thenReturn(list);
	}

	@Test
	public void retrieveTipsTest() throws Exception {
		MvcResult result = mvc.perform(get(API_MAPPING+RETRIEVE_TIPS))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
		List<TipsDto> response = mapper.readValue(result.getResponse().getContentAsString(), List.class);
		assertNotNull(response);
		assertEquals(3, response.size());
	}
	
	@Test
	public void saveDietTest() throws Exception {
		DietInfoDto data = new DietInfoDto();
		data.setUserName("test");

		MvcResult result = mvc.perform(post(API_MAPPING+SAVE_DIET)
				.contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(data)))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
		
		assertTrue(result.getResponse().getContentAsString().contains(SAVE_MSG));
	}
	
	@Test
	public void deleteDietTest() throws Exception {
		
		MvcResult result = mvc.perform(delete(API_MAPPING+DELETE_DIET)
                .param(ID,"test"))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
		
		assertTrue(result.getResponse().getContentAsString().contains(DELETE_MSG));
	}
	
	@Test
	public void retrieveDietTest() throws Exception {
		
		MvcResult result = mvc.perform(get(API_MAPPING+RETRIEVE_DIET).param(EMAIL_ID, "test@email.com"))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
		
		List<DietInfoDto> response = mapper.readValue(result.getResponse().getContentAsString(), List.class);
		assertNotNull(response);
		assertEquals(1, response.size());
	}

}
