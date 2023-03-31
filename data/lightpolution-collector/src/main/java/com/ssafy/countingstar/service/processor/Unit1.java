package com.ssafy.countingstar.service.processor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

public class Unit1 implements Serializable{
	float[] cornerLat;
	float[] cornerLng;
	LocalDate date;
	int hour;
	int i;
	int j;
	int r;
	int c;
	float rad;
	
	public Unit1() {}
	
	public Unit1(float[] cornerLat, float[] cornerLng, LocalDate date, int hour, int i, int j, int r, int c, float rad) {
		this.cornerLat = cornerLat;
		this.cornerLng = cornerLng;
		this.date = date;
		this.hour = hour;
		this.i = i;
		this.j = j;
		this.r = r;
		this.c = c;
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

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public float getRad() {
		return rad;
	}

	public void setRad(float rad) {
		this.rad = rad;
	}
	
}
