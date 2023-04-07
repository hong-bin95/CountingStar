package com.ssafy.countingstar.service.collector;

import java.time.LocalDateTime;

public interface AtmosphereCollectorService {
	
	/**
	 * date을 넘지 않는 가장 최근의 데이터를 기준으로 대기 정보를 수집한다.
	 * @param dateTime
	 */
	public void collect(LocalDateTime dateTime);

}
