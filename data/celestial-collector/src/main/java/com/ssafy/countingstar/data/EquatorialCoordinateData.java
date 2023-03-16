package com.ssafy.countingstar.data;

// 적도 좌표
public class EquatorialCoordinateData {
	
	// 적경
	private double ra;
	
	// 적위
	private double dec;
	
	public EquatorialCoordinateData(double ra, double dec) {
		this.ra = ra;
		this.dec = dec;
	}
	
	public double getRa() {
		return ra;
	}

	public double getDec() {
		return dec;
	}

	

}
