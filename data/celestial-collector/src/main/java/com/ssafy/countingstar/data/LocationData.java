package com.ssafy.countingstar.data;

public class LocationData {
	
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
