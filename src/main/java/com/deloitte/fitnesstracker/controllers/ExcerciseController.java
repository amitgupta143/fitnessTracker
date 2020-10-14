package com.deloitte.fitnesstracker.controllers;

import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.*;
import static org.springframework.http.ResponseEntity.ok;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.deloitte.fitnesstracker.dto.ExcerciseDto;
import com.deloitte.fitnesstracker.dto.ExcerciseInfoDto;
import com.deloitte.fitnesstracker.service.ExcerciseInfoService;
import com.deloitte.fitnesstracker.vo.Excercise;
import com.deloitte.fitnesstracker.vo.ExcerciseInfo;

@CrossOrigin(origins = STAR)
@RestController
@RequestMapping(API_MAPPING)
public class ExcerciseController {

	private Logger logger = LoggerFactory.getLogger(ExcerciseController.class);
	
	@Autowired
	ExcerciseInfoService service;
	
	
	@PostMapping(SAVE_EXCERCISE)
	public ResponseEntity<Map<Object, Object>> saveExcerciseInfo(@RequestBody ExcerciseInfoDto data){
		logger.info("Excercise Info Recieved {} ", data.toString());
				
		if(null != data && data.getDate() == null) {
		  data.setDate(LocalDate.now());
		}
		ExcerciseInfo excerciseInfo = null;
		
		if( null != data ) {
			excerciseInfo = service.findByUserNameAndDate(data.getUserName(), data.getDate().toString());
			logger.info("Excercise Info Recieved from DB {} ", excerciseInfo);

			if(excerciseInfo == null) {
				excerciseInfo = new ExcerciseInfo();
				excerciseInfo.setDate(LocalDate.now().toString());
			}

			excerciseInfo.setUserName(data.getUserName());
			excerciseInfo.setTotalCalories(data.getTotalCalories());

			if(null != data.getCardio()) {
				excerciseInfo.setCardio(new Excercise(data.getCardio().getName(), data.getCardio().getCalories()));
			}
			if(null != data.getWeightLift()) {
				excerciseInfo.setWeightLift(new Excercise(data.getWeightLift().getName(), data.getWeightLift().getCalories()));
			}
			if(null != data.getYoga()) {
				excerciseInfo.setYoga(new Excercise(data.getYoga().getName(), data.getYoga().getCalories()));
			}
			if(null != data.getOthers()) {
				excerciseInfo.setOthers(new Excercise(data.getOthers().getName(), data.getOthers().getCalories()));
			}
		}
		
		logger.info("Excercise Info to be saved {} ", excerciseInfo.toString());
		service.saveExcerciseInfo(excerciseInfo);
		
		Map<Object, Object> model = new HashMap<>();
		model.put(MSG, SAVE_EXCERCISE_MSG);
		return ok(model);
	}
	
	@GetMapping(RETRIEVE_EXCERCISE)
	public ResponseEntity<List<ExcerciseInfoDto>> retrieveData(@RequestParam(value=EMAIL_ID, required = true) String emailId){
		logger.debug("Email Id Info Recieved {} ", emailId);
		List<ExcerciseInfo> data = service.findByUserName(emailId);
		logger.debug("List Retrieved {} ", data);
	    List<ExcerciseInfoDto> response = new ArrayList<>(); 

	    data.forEach((source) -> {
	      ExcerciseInfoDto target= new ExcerciseInfoDto();
	      
	      target.setId(source.getId());
	      target.setDate(LocalDate.parse(source.getDate()));
	      target.setUserName(source.getUserName());
	      target.setTotalCalories(source.getTotalCalories());
	      
	      if(null != data && null != source.getCardio()) {
	    	  target.setCardio(new ExcerciseDto(source.getCardio().getName(), source.getCardio().getCalories()));
	      }
	      if(null != data && null != source.getWeightLift()) {
	    	  target.setWeightLift(new ExcerciseDto(source.getWeightLift().getName(), source.getWeightLift().getCalories()));
	      }
	      if(null != data && null != source.getYoga()) {
	    	  target.setYoga(new ExcerciseDto(source.getYoga().getName(), source.getYoga().getCalories()));
	      }
	      if(null != data && null != source.getOthers()) {
	    	  target.setOthers(new ExcerciseDto(source.getOthers().getName(), source.getOthers().getCalories()));
	      }
	    
	      response.add(target);
	    });
	    return new ResponseEntity<List<ExcerciseInfoDto>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(DELETE_EXCERCISE)
	public ResponseEntity<Map<Object, Object>> deleteExcerciseInfo(@RequestParam(value=ID, required = true) String id){
		logger.info("Excercise delete record id recieved {} ", id);
		
		ExcerciseInfo info = service.findById(id);
		Map<Object, Object> model = new HashMap<>();

		if(null != info) {
		  service.deleteById(id);
		  model.put(MSG, DELETE_EXCERCISE_MSG);
		}
		return ok(model);
	}

}
