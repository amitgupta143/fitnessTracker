package com.deloitte.fitnesstracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.deloitte.fitnesstracker.vo.ExcerciseInfo;

public interface ExcerciseRepository extends MongoRepository<ExcerciseInfo, String> {
	
	public Optional<ExcerciseInfo> findById(String id);
	public List<ExcerciseInfo> findByUserName(String userName);
	public ExcerciseInfo findByUserNameAndDate(String userName, String date);
	public void deleteById(String id);
}
