package com.deloitte.fitnesstracker.controllers;

import static org.springframework.http.ResponseEntity.ok;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.fitnesstracker.dto.DietDto;
import com.deloitte.fitnesstracker.dto.DietInfoDto;
import com.deloitte.fitnesstracker.dto.TipsDto;
import com.deloitte.fitnesstracker.service.DietInfoService;
import com.deloitte.fitnesstracker.vo.Diet;
import com.deloitte.fitnesstracker.vo.DietInfo;

@CrossOrigin(origins = STAR)
@RestController
@RequestMapping(API_MAPPING)
public class DietController {

	private Logger logger = LoggerFactory.getLogger(DietController.class);
	
	@Autowired
	DietInfoService service;
	
	@GetMapping(RETRIEVE_TIPS)
	public ResponseEntity<List<TipsDto>> getTipsInfo() {
		logger.info("Retrieving Tips");
		
		List<TipsDto> tipsList = new LinkedList<>();
		tipsList.add(new TipsDto(RECIPES, RECIPES_LINK));
		tipsList.add(new TipsDto(EXCERCISES, EXCERCISES_LINK));
		tipsList.add(new TipsDto(LIFESTYLE, LIFESTYLE_LINK));
		
		logger.info("Tips List {} ", tipsList);
		return ok(tipsList);
	}
	
	@PostMapping(SAVE_DIET)
	public ResponseEntity<Map<Object, Object>> saveDietInfo(@RequestBody DietInfoDto data){
		logger.info("Diet Info Recieved {} ", data);
				
		if(null != data && data.getDate() == null) {
		  data.setDate(LocalDate.now());
		}
		
		DietInfo dietInfo = null;
		
		if( null != data ) {
			dietInfo = service.findByUserNameAndDate(data.getUserName(), data.getDate().toString());
			logger.info("Diet Info Recieved from DB {} ", dietInfo);

			if(dietInfo == null) {
				dietInfo = new DietInfo();
				dietInfo.setDate(LocalDate.now().toString());
			}

			dietInfo.setUserName(data.getUserName());
			dietInfo.setTotalCalories(data.getTotalCalories());

			if(null != data.getBreakfast()) {
				dietInfo.setBreakfast(new Diet(data.getBreakfast().getName(), data.getBreakfast().getCalories()));
			}
			if(null != data.getLunch()) {
				dietInfo.setLunch(new Diet(data.getLunch().getName(), data.getLunch().getCalories()));
			}
			if(null != data.getSnacks()) {
				dietInfo.setSnacks(new Diet(data.getSnacks().getName(), data.getSnacks().getCalories()));
			}
			if(null != data.getDinner()) {
				dietInfo.setDinner(new Diet(data.getDinner().getName(), data.getDinner().getCalories()));
			}
		}
		
		logger.info("Diet Info to be saved {} ", dietInfo.toString());
		service.saveDietInfo(dietInfo);
		
		Map<Object, Object> model = new HashMap<>();
		model.put(MSG, SAVE_MSG);
		return ok(model);
	}
	
	@GetMapping(RETRIEVE_DIET)
	public ResponseEntity<List<DietInfoDto>> retrieveData(@RequestParam(value=EMAIL_ID, required = true) String emailId){
		logger.debug("Email Id Info Recieved {} ", emailId);
		List<DietInfo> data = service.findByUserName(emailId);
		logger.debug("List Retrieved {} ", data);
	    List<DietInfoDto> response = new ArrayList<>(); 

	    data.forEach((source) -> {
	      DietInfoDto target= new DietInfoDto();
	      
	      target.setId(source.getId());
	      target.setDate(LocalDate.parse(source.getDate()));
	      target.setUserName(source.getUserName());
	      target.setTotalCalories(source.getTotalCalories());
	      
	      if(null != data && null != source.getBreakfast()) {
	    	  target.setBreakfast(new DietDto(source.getBreakfast().getName(), source.getBreakfast().getCalories()));
	      }
	      if(null != data && null != source.getLunch()) {
	    	  target.setLunch(new DietDto(source.getLunch().getName(), source.getLunch().getCalories()));
	      }
	      if(null != data && null != source.getSnacks()) {
	    	  target.setSnacks(new DietDto(source.getSnacks().getName(), source.getSnacks().getCalories()));
	      }
	      if(null != data && null != source.getDinner()) {
	    	  target.setDinner(new DietDto(source.getDinner().getName(), source.getDinner().getCalories()));
	      }
	    
	      response.add(target);
	    });
	    return new ResponseEntity<List<DietInfoDto>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(DELETE_DIET)
	public ResponseEntity<Map<Object, Object>> deleteDietInfo(@RequestParam(value=ID, required = true) String id){
		logger.info("Diet Info delete record id recieved {} ", id);
		
		DietInfo info = service.findById(id);
		Map<Object, Object> model = new HashMap<>();

		if(null != info) {
		  service.deleteById(id);
		  model.put(MSG, DELETE_MSG);
		}
		
		return ok(model);
	}

}
