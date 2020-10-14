package com.deloitte.fitnesstracker.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.fitnesstracker.constants.FitnessTrackerConstants;
import com.deloitte.fitnesstracker.exception.RecordNotFoundException;
import com.deloitte.fitnesstracker.repository.ExcerciseRepository;
import com.deloitte.fitnesstracker.vo.ExcerciseInfo;

@Service
public class ExcerciseInfoServiceImpl implements ExcerciseInfoService {

	private Logger logger = LoggerFactory.getLogger(ExcerciseInfoServiceImpl.class);

	@Autowired
	ExcerciseRepository repository;
	
	@Override
	public ExcerciseInfo findById(String id) {
		logger.info("ExcerciseInfoServiceImpl : findById {} ", id);
		return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(FitnessTrackerConstants.RECORD_NOT_FOUND));
	}

	@Override
	public List<ExcerciseInfo> findByUserName(String userName) {
		logger.info("ExcerciseInfoServiceImpl : findByUserName {} ", userName);
		return repository.findByUserName(userName);
	}

	@Override
	public void deleteById(String id) {
		logger.info("ExcerciseInfoServiceImpl : deleteById {} ", id);
		repository.deleteById(id);
	}

	@Override
	public ExcerciseInfo saveExcerciseInfo(ExcerciseInfo excerciseInfo) {
		logger.info("ExcerciseInfoServiceImpl : saveExcerciseInfo {} ", excerciseInfo);
		return repository.save(excerciseInfo);
	}

	@Override
	public ExcerciseInfo findByUserNameAndDate(String userName, String date) {
		logger.info("ExcerciseInfoServiceImpl : findByUserNameAndDate username : {} , date : {} ", userName, date);
		return repository.findByUserNameAndDate(userName, date);
	}

}
