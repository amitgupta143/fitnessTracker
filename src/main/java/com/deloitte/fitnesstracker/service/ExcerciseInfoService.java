package com.deloitte.fitnesstracker.service;

import java.util.List;

import com.deloitte.fitnesstracker.vo.ExcerciseInfo;

public interface ExcerciseInfoService {

	public ExcerciseInfo findById(String id);
	public List<ExcerciseInfo> findByUserName(String userName);
	public void deleteById(String id);
	public ExcerciseInfo saveExcerciseInfo(ExcerciseInfo excerciseInfo);
	public ExcerciseInfo findByUserNameAndDate(String userName, String date);
}
