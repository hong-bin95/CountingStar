package com.ssafy.countingstar.service;

import com.ssafy.countingstar.data.LightPollution;

public interface LightPolutionService {
	
	/**
	 * 처리된 빛공해정보를 데이터 저장소에 저장한다.
	 * @param lightPollution
	 */
	public void save(LightPollution lightPollution);

}
