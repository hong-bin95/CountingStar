package com.ssafy.countingstar.service;

import java.time.LocalDate;
import java.util.List;

import com.ssafy.countingstar.data.LightPollution;

public interface LightPollutionService {
	
	public List<LightPollution> getAllLightPollution(LocalDate date);

}
