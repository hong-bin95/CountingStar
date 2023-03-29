package com.ssafy.countingstar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.countingstar.dao.LightPollutionDAO;
import com.ssafy.countingstar.data.LightPollution;

@Service
public class LightPollutionServiceImpl implements LightPollutionService{
	
	@Autowired
	LightPollutionDAO lightPollutionDAO;

	@Override
	public void save(LightPollution lightPollution) {
		lightPollutionDAO.save(lightPollution);
	}

}
