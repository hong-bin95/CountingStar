package com.ssafy.countingstar.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class TimeWithSpot implements Serializable {
	
	private int spotId;
	
	private float latitude;
	
	private float longitude;
	
	private LocalDate date;
	
	private int hour;
	
	public TimeWithSpot() {
	}

	public TimeWithSpot(int spotId, float latitude, float longitude, LocalDate date, int hour) {
		this.spotId = spotId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.date = date;
		this.hour = hour;
	}

	public int getSpotId() {
		return spotId;
	}

	public void setSpotId(int spotId) {
		this.spotId = spotId;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}
	
}