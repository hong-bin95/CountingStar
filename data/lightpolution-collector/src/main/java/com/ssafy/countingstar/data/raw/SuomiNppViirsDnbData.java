package com.ssafy.countingstar.data.raw;

import io.jhdf.api.Dataset;

public class SuomiNppViirsDnbData {
	int dayNightFlag;
	String rangeEndingDate;
	String rangeEndingTime;
	
	float[] gRingPointLatitude;
	float[] gRingPointLongitude;
	
	int r;
	int c;
	
	float[][] radiance;
	
	float southBoundingCoordinate;
	float northBoundingCoordinate;
	float eastBoundingCoordinate;
	float westBoundingCoordinate;
	
	Dataset radianceDataSet;
	
	public void setDataSet(Dataset radianceDataSet) {
		this.radianceDataSet = radianceDataSet;
	}
	
	public void loadFromDataSet() {
		int[] redianceDim = radianceDataSet.getDimensions();
		float[][] radiance = (float[][]) radianceDataSet.getData();
		setRadiance(radiance, redianceDim[0], redianceDim[1]);
	}

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

	private void setRadiance(float[][] radiance, int r, int c) {
		this.radiance = radiance;
		this.r = r;
		this.c = c;
	}

	public float getSouthBoundingCoordinate() {
		return southBoundingCoordinate;
	}

	public void setSouthBoundingCoordinate(float southBoundingCoordinate) {
		this.southBoundingCoordinate = southBoundingCoordinate;
	}

	public float getNorthBoundingCoordinate() {
		return northBoundingCoordinate;
	}

	public void setNorthBoundingCoordinate(float northBoundingCoordinate) {
		this.northBoundingCoordinate = northBoundingCoordinate;
	}

	public float getEastBoundingCoordinate() {
		return eastBoundingCoordinate;
	}

	public void setEastBoundingCoordinate(float eastBoundingCoordinate) {
		this.eastBoundingCoordinate = eastBoundingCoordinate;
	}

	public float getWestBoundingCoordinate() {
		return westBoundingCoordinate;
	}

	public void setWestBoundingCoordinate(float westBoundingCoordinate) {
		this.westBoundingCoordinate = westBoundingCoordinate;
	}
	
}
