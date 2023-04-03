package com.ssafy.countingstar.service.process;

import java.util.List;

import com.ssafy.countingstar.data.raw.ASOSData;

public class Unit2 {
	
	float lat;
	float lng;
	List<ASOSData> listOfASOS;
	
	public Unit2() {}
	
	public Unit2(float lat, float lng, List<ASOSData> listOfASOS) {
		super();
		this.lat = lat;
		this.lng = lng;
		this.listOfASOS = listOfASOS;
	}

	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}
	public List<ASOSData> getListOfASOS() {
		return listOfASOS;
	}
	public void setListOfASOS(List<ASOSData> listOfASOS) {
		this.listOfASOS = listOfASOS;
	}
	
	

}
