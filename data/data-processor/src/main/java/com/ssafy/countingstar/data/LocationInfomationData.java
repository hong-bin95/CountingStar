package com.ssafy.countingstar.data;

import java.io.Serializable;

public class LocationInfomationData implements Serializable {
	
	LocationData locationData;
	
	TimeData timeData;
	
	// 빛공해수준 (단위 : mcd/m^2)
	double lp;
	
	// 시정 (단위 : m)
	double vs;

}
