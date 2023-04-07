package com.ssafy.countingstar.data;

import java.io.Serializable;

// 지평 좌표
public class HorizontalCoordinateData implements Serializable {
	
	//고도
	private double altitude;
	
	//방위각
	private double azimuth;

	public HorizontalCoordinateData(double altitude, double azimuth) {
		super();
		this.altitude = altitude;
		this.azimuth = azimuth;
	}

	public double getAltitude() {
		return altitude;
	}

	public double getAzimuth() {
		return azimuth;
	}
	

}
