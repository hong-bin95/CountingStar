package com.ssafy.countingstar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.countingstar.repository.LightPollutionRepository;
import com.ssafy.countingstar.data.LightPollution;

@Service
public class LightPollutionServiceImpl implements LightPollutionService{

	@Autowired
	LightPollutionRepository lightPollutionRepository;

	@Override
	public void save(Iterable<LightPollution> lightPollutions) {
		lightPollutionRepository.insert(lightPollutions);
	}

}
