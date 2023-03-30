package com.ssafy.countingstar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import com.ssafy.countingstar.data.Observatory;
import com.ssafy.countingstar.repository.ObservatoryRepository;

public class ObservatoryServiceImpl implements ObservatoryService {
	
	@Autowired
	ObservatoryRepository observatoryRepository;

	@Override
	@Cacheable("observatoryList")
	public List<Observatory> readAll() {
		return observatoryRepository.findAll();
	}

}
