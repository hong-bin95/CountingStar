package com.ssafy.countingstar.util;

import java.time.LocalDate;

public class DistanceUtils {
	
	private static final int EARTH_RADIUS = 6371;

	/**
	 * 지리적 거리를 계산한다. 단위(킬로미터) 단 고도는 고려하지 않는다.
	 * @param lat0
	 * @param lng0
	 * @param lat1
	 * @param lng1
	 * @return
	 */
    public static double calculateDistance(float lat0, float lng0, float lat1, float lng1) {
        double dLat = Math.toRadians(lat1 - lat0);
        double dLng = Math.toRadians(lng1 - lng0);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat0)) * Math.cos(Math.toRadians(lat1))
                * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;
        return distance;
    }
    
    /**
     * 시간 차이를 계산한다. 시간차이 및 날짜 차이는 동등하게 가중치를 가지며, 대략적으로 계산한다.
     * @param ld0
     * @param hour0
     * @param ld1
     * @param hour1
     * @return
     */
    public static double calculateTimeDistance(LocalDate ld0, int hour0, LocalDate ld1, int hour1) {
    	return Math.abs(ld0.toEpochDay() - ld1.toEpochDay()) + getHourDiff(hour0, hour1);
    	
    }
    
    public static int getHourDiff(int h0, int h1) {
        int diff = Math.abs(h0 - h1);
        if (diff > 12) {
            diff = 24 - diff;
        }
        return diff;
    }
}
