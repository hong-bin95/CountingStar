package com.ssafy.countingstar.service.processor;

import java.io.Serializable;
import java.sql.Timestamp;

public class Unit0 implements Serializable{
	float[] cornerLat;
	float[] cornerLng;
	Timestamp timestamp;
	float[][] rad;
	
	public Unit0() {};
	
	public Unit0(float[] cornerLat, float[] cornerLng, Timestamp timestamp, float[][] rad) {
		this.cornerLat = cornerLat;
		this.cornerLng = cornerLng;
		this.timestamp = timestamp;
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

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public float[][] getRad() {
		return rad;
	}

	public void setRad(float[][] rad) {
		this.rad = rad;
	}
	
	
}