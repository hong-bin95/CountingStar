package com.ssafy.countingstar.service;

import java.util.List;

import com.ssafy.countingstar.data.Observatory;

public interface ObservatoryService {
	
	/**
	 * 모든 관측소의 정보를 가져온다.
	 * @return 모든 관측소 정보
	 */
	public List<Observatory> readAll();

}
