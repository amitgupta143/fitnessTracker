package com.deloitte.fitnesstracker.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.fitnesstracker.constants.FitnessTrackerConstants;
import com.deloitte.fitnesstracker.exception.RecordNotFoundException;
import com.deloitte.fitnesstracker.repository.DietRepository;
import com.deloitte.fitnesstracker.vo.DietInfo;

@Service
public class DietInfoServiceImpl implements DietInfoService {

	private Logger logger = LoggerFactory.getLogger(DietInfoServiceImpl.class);
	
	@Autowired
	DietRepository repository;
	
	@Override
	public DietInfo findById(String id) {
		logger.info("DietInfoServiceImpl : findById {} ", id);
		return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(FitnessTrackerConstants.RECORD_NOT_FOUND));
	}

	@Override
	public List<DietInfo> findByUserName(String userName) {
		logger.info("DietInfoServiceImpl : findByUserName {} ", userName);
		return repository.findByUserName(userName);
	}

	@Override
	public void deleteById(String id) {
		logger.info("DietInfoServiceImpl : deleteById {} ", id);
		repository.deleteById(id);
	}

	@Override
	public DietInfo saveDietInfo(DietInfo dietInfo) {
		logger.info("DietInfoServiceImpl : saveDietInfo {} ", dietInfo);
		return repository.save(dietInfo);
	}

	@Override
	public DietInfo findByUserNameAndDate(String userName, String date) {
		logger.info("DietInfoServiceImpl : findByUserNameAndDate : username : {} ,  date : {}", userName, date);
		return repository.findByUserNameAndDate(userName, date);
	}

}
