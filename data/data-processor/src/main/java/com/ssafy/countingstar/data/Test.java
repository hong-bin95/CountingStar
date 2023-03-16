package com.ssafy.countingstar.data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import com.ssafy.countingstar.util.CelestialUtils;

public class Test {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
		
		
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.set(2023, Calendar.MARCH, 10, 9 - 9, 0, 0); // 날짜와 시간 설정
		
		LocationData location = new LocationData(37.5665, 126.9780);
		TimeData time = new TimeData(calendar);
		
		HorizontalCoordinateData hc = CelestialUtils.getSunHorizontalCoordinate(location, time);
		
		System.out.println("날짜 : " + sdf.format(calendar.getTime()));
		
		System.out.println("고도 : " + hc.getAltitude());
		
		System.out.println("방위각 : " + hc.getAzimuth());
		
		
	}

}
