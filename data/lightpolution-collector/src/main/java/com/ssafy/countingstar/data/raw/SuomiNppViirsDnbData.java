package com.ssafy.countingstar.data.raw;

public class SuomiNppViirsDnbData {
	int dayNightFlag;
	String rangeEndingDate;
	String rangeEndingTime;
	
	float[] gRingPointLatitude;
	float[] gRingPointLongitude;
	
	int r;
	int c;
	
	float[][] radiance;

	public int getDayNightFlag() {
		return dayNightFlag;
	}

	public void setDayNightFlag(String dayNightFlag) {
		if(dayNightFlag.equals("Day")) {
			this.dayNightFlag = 1;
		}else {
			this.dayNightFlag = 0;
		}
	}

	public String getRangeEndingDate() {
		return rangeEndingDate;
	}

	public void setRangeEndingDate(String rangeEndingDate) {
		this.rangeEndingDate = rangeEndingDate;
	}

	public String getRangeEndingTime() {
		return rangeEndingTime;
	}

	public void setRangeEndingTime(String rangeEndingTime) {
		this.rangeEndingTime = rangeEndingTime;
	}

	public float[] getgRingPointLatitude() {
		return gRingPointLatitude;
	}

	public void setgRingPointLatitude(float[] gRingPointLatitude) {
		this.gRingPointLatitude = gRingPointLatitude;
	}

	public float[] getgRingPointLongitude() {
		return gRingPointLongitude;
	}

	public void setgRingPointLongitude(float[] gRingPointLongitude) {
		this.gRingPointLongitude = gRingPointLongitude;
	}

	public int getR() {
		return r;
	}

	public int getC() {
		return c;
	}
	
	public float[][] getRadiance() {
		return radiance;
	}

	public void setRadiance(float[][] radiance, int r, int c) {
		this.radiance = radiance;
		this.r = r;
		this.c = c;
	}
	
	
	
}
