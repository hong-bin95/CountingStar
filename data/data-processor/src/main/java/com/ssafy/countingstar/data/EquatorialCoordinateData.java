package com.ssafy.countingstar.data;

import java.io.Serializable;

// 적도 좌표
public class EquatorialCoordinateData implements Serializable {
	
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
