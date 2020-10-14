package com.deloitte.fitnesstracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.deloitte.fitnesstracker.vo.DietInfo;

public interface DietRepository extends MongoRepository<DietInfo, String> {

	public Optional<DietInfo> findById(String id);
	public List<DietInfo> findByUserName(String userName);
	public DietInfo findByUserNameAndDate(String userName, String date);
	public void deleteById(String id);
}
