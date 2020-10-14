package com.deloitte.fitnesstracker.service;

import java.util.List;

import com.deloitte.fitnesstracker.vo.DietInfo;

public interface DietInfoService {

	public DietInfo findById(String id);
	public List<DietInfo> findByUserName(String userName);
	public void deleteById(String id);
	public DietInfo saveDietInfo(DietInfo dietInfo);
	public DietInfo findByUserNameAndDate(String userName, String date);
}
