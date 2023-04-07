package com.ssafy.countingstar.data;

import java.io.Serializable;

public class ObservatoryData extends LocationData implements Serializable {
	
	private String observertoryName;

	public ObservatoryData(String observertoryName, double lat, double lng) {
		super(lat, lng);
		// TODO Auto-generated constructor stub
	}

}
