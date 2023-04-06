package com.ssafy.countingstar.data;

import java.io.Serializable;

public class LocationData implements Serializable {
	
	//위도
	double lat;
	
	//경도
	double lng;

	public LocationData(double lat, double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}
	
	

}
