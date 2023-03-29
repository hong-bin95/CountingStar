package com.ssafy.countingstar.service.processor;

import java.util.List;

import com.ssafy.countingstar.data.LightPollution;
import com.ssafy.countingstar.data.raw.SuomiNppViirsDnbData;

public interface LightPollutionProcessorService {
	
	/**
	 * 
	 * @param rawData 전처리 이전 raw데이터들.
	 * @return 완전히 처리된 LightPollution 정보.
	 */
	public Iterable<LightPollution> process(List<SuomiNppViirsDnbData> rawData);
	
	
	
}
