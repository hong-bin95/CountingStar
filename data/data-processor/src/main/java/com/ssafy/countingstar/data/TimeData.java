package com.ssafy.countingstar.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;

public class TimeData implements Serializable {
	
	private double equinox;
	
	private double solarTime;
	
	private LocalDate date;
	
	private int hour;
	
	public TimeData(LocalDate date, int hour) {
//		hour = hour - 9;
//		if(hour < 0) {
//			hour += 24;
//			date = date.minusDays(1);
//		}
		setTime(date, hour);
	}

	public double getEquinox() {
		return equinox;
	}

	public double getSolarTime() {
		return solarTime;
	}
	
	public void setTime(LocalDate date, int hour) {
//		hour = hour - 9;
//		if(hour < 0) {
//			hour += 24;
//			date = date.minusDays(1);
//		}
		this.date = date;
		this.hour = hour;
		
		int year = date.getYear();
		int month = date.getMonthValue();
		int day = date.getDayOfMonth();
	  	double hourDouble = hour;
//	  	double minute = cal.get(Calendar.MINUTE);
//	  	double second = cal.get(Calendar.SECOND);
	  	this.solarTime = hourDouble;// + minute/60 + second/3600;
	  	
	  	int a = (14 - month) / 12;
	    int y = year + 4800 - a;
	    int m = month + 12 * a - 3;
	  	
	  	double julianDay = day + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400 - 32045;
	  	julianDay += (hour - 12) / 24.0;
//	    julianDay += minute / 1440.0;
//	    julianDay += second / 86400.0;
	    
	  	this.equinox = julianDay - 2451545.0;
	}
	
	

}
