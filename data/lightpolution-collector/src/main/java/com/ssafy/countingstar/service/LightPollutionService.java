package com.ssafy.countingstar.service;

import java.util.List;

import com.ssafy.countingstar.data.LightPollution;

public interface LightPollutionService {
	
	/**
	 * 처리된 빛공해정보를 데이터 저장소에 저장한다.
	 * @param lightPollutions
	 */
	public void save(Iterable<LightPollution> lightPollutions);

}
