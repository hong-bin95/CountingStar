package com.ssafy.countingstar.service.processor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

public class Unit0 implements Serializable{
	float[] cornerLat;
	float[] cornerLng;
	LocalDate date;
	int hour;
	float[][] rad;
	
	public Unit0() {};
	
	public Unit0(float[] cornerLat, float[] cornerLng, LocalDate date, int hour, float[][] rad) {
		this.cornerLat = cornerLat;
		this.cornerLng = cornerLng;
		this.date = date;
		this.hour = hour;
		this.rad = rad;
	}

	public float[] getCornerLat() {
		return cornerLat;
	}

	public void setCornerLat(float[] cornerLat) {
		this.cornerLat = cornerLat;
	}

	public float[] getCornerLng() {
		return cornerLng;
	}

	public void setCornerLng(float[] cornerLng) {
		this.cornerLng = cornerLng;
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

	public float[][] getRad() {
		return rad;
	}

	public void setRad(float[][] rad) {
		this.rad = rad;
	}
	
}