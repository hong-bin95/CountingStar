package com.ssafy.countingstar.data;

import java.util.Calendar;

public class TimeData {
	
	private double equinox;
	
	private double solarTime;
	
	private Calendar cal;
	
	public TimeData(Calendar cal) {
		setCalendar(cal);
	}

	public double getEquinox() {
		return equinox;
	}

	public double getSolarTime() {
		return solarTime;
	}
	
	public void setCalendar(Calendar cal) {
		this.cal = cal;
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
	  	double hour = cal.get(Calendar.HOUR_OF_DAY);
	  	double minute = cal.get(Calendar.MINUTE);
	  	double second = cal.get(Calendar.SECOND);
	  	this.solarTime = hour + minute/60 + second/3600;
	  	
	  	int a = (14 - month) / 12;
	    int y = year + 4800 - a;
	    int m = month + 12 * a - 3;
	  	
	  	double julianDay = day + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400 - 32045;
	  	julianDay += (hour - 12) / 24.0;
	    julianDay += minute / 1440.0;
	    julianDay += second / 86400.0;
	    
	  	this.equinox = julianDay - 2451545.0;
	}
	
	public Calendar getCalendar() {
		return this.cal;
	}
	
	

}
