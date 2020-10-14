package com.deloitte.fitnesstracker.controllers;

import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.API_MAPPING;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.DELETE_EXCERCISE;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.DELETE_EXCERCISE_MSG;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.EMAIL_ID;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.ID;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.RETRIEVE_EXCERCISE;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.SAVE_EXCERCISE;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.SAVE_EXCERCISE_MSG;
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
import java.util.List;
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

import com.deloitte.fitnesstracker.controllers.ExcerciseController;
import com.deloitte.fitnesstracker.dto.DietInfoDto;
import com.deloitte.fitnesstracker.repository.ExcerciseRepository;
import com.deloitte.fitnesstracker.service.ExcerciseInfoService;
import com.deloitte.fitnesstracker.service.ExcerciseInfoServiceImpl;
import com.deloitte.fitnesstracker.vo.DietInfo;
import com.deloitte.fitnesstracker.vo.ExcerciseInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ExcerciseControllerTest {

	private MockMvc mvc;

	ExcerciseController controller;

	ExcerciseInfoService service;
	
	ObjectMapper mapper;
	
	@Mock
	ExcerciseRepository repository;

	@Before
	public void setup() throws Exception {
		initMocks(this);
		mapper = new ObjectMapper();
		controller = new ExcerciseController();
		service = new ExcerciseInfoServiceImpl();
		ReflectionTestUtils.setField(controller, "service", service);
		ReflectionTestUtils.setField(service, "repository", repository);

		
		mvc = standaloneSetup(controller).build();
		List<ExcerciseInfo> list = new ArrayList<>();
		ExcerciseInfo info = new ExcerciseInfo();
		info.setDate("2020-09-25");
		list.add(info);
		
		when(repository.findByUserNameAndDate(anyString(),anyString())).thenReturn(new ExcerciseInfo());
		when(repository.findByUserName(anyString())).thenReturn(list);
		when(repository.findById(anyString())).thenReturn(Optional.of(new ExcerciseInfo()));
	}
	
	@Test
	public void saveExcerciseTest() throws Exception {
		DietInfoDto data = new DietInfoDto();
		data.setUserName("test");

		MvcResult result = mvc.perform(post(API_MAPPING+SAVE_EXCERCISE)
				.contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(data)))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
		
		assertTrue(result.getResponse().getContentAsString().contains(SAVE_EXCERCISE_MSG));
	}
	
	@Test
	public void deleteExcerciseTest() throws Exception {
		
		MvcResult result = mvc.perform(delete(API_MAPPING+DELETE_EXCERCISE)
                .param(ID,"test"))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
		
		assertTrue(result.getResponse().getContentAsString().contains(DELETE_EXCERCISE_MSG));
	}
	
	@Test
	public void retrieveExcerciseTest() throws Exception {
		
		MvcResult result = mvc.perform(get(API_MAPPING+RETRIEVE_EXCERCISE).param(EMAIL_ID, "test@email.com"))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
		
		List<DietInfoDto> response = mapper.readValue(result.getResponse().getContentAsString(), List.class);
		assertNotNull(response);
		assertEquals(1, response.size());
	}

}
