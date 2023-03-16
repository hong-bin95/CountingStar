package com.ssafy.countingstar.util;

import com.ssafy.countingstar.data.EquatorialCoordinateData;
import com.ssafy.countingstar.data.HorizontalCoordinateData;
import com.ssafy.countingstar.data.LocationData;
import com.ssafy.countingstar.data.TimeData;

public class CelestialUtils {
	
	// 상수 정의
	final static double R = 149597870; // 지구와 태양 사이의 평균 거리 (km)
	final static double E = 23.4392911; // 황도와 천구적도 사이의 각 (도 단위)
	
	/**
	 * 현재 시간을 받아 태양의 적도 좌표를 구한다.
	 * @param time
	 * @return 태양의 적도 좌표
	 */
	public static EquatorialCoordinateData getSunEquatorialCoordinates(TimeData time) {
		
		double equinox = time.getEquinox();
		
		// 황경과 황위를 구함
		double L = (280.460 + 0.9856474 * equinox) % 360;
	    double g = (357.528 + 0.9856003 * equinox) % 360;
	    double lambda = L + 1.915 * Math.sin(Math.toRadians(g)) + 0.020 * Math.sin(Math.toRadians(2 * g));
	    
	    double epsilon = 23.439 - 0.0000004 * equinox;
	    double ra = Math.toDegrees(Math.atan2(Math.cos(Math.toRadians(epsilon)) * Math.sin(Math.toRadians(lambda)), Math.cos(Math.toRadians(lambda))));
	    if (ra < 0) ra += 360;
	    double dec = Math.toDegrees(Math.asin(Math.sin(Math.toRadians(epsilon)) * Math.sin(Math.toRadians(lambda))));
	    return new EquatorialCoordinateData(ra,dec);
	}
	
	/**
	 * 현재 위치와 시간을 받아서 태양의 지평 좌표를 구한다.
	 * @param location
	 * @param time
	 * @return 태양의 지평 좌표
	 */
	public static HorizontalCoordinateData getSunHorizontalCoordinate(LocationData location, TimeData time) {
		return convertToHorizontalCoordinate(location, time, getSunEquatorialCoordinates(time));
	}
	
	/**
	 * 현재 날짜와 현재 시간과 천체의 적도 좌표를 받아 천체의 지평 좌표를 구한다.
	 * @param location
	 * @param time
	 * @param celestial
	 * @return 천체의 지평 좌표
	 */
	public static HorizontalCoordinateData convertToHorizontalCoordinate(LocationData location, TimeData time, EquatorialCoordinateData celestial) {
		
		double lat = location.getLat();
		double lng = location.getLng();
		
		double ra = celestial.getRa();
		double dec = celestial.getDec();
		double equinox = time.getEquinox();
		
		double theta0 = (280.46061837 + 360.98564736629 * equinox) % 360;
	    double theta = theta0 + lng;
	    double tau = theta - ra;
	    double alt = Math.toDegrees(Math.asin(Math.sin(Math.toRadians(lat)) * Math.sin(Math.toRadians(dec)) + Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(dec)) * Math.cos(Math.toRadians(tau))));
	    double az = Math.toDegrees(Math.atan2(-Math.sin(Math.toRadians(tau)), (Math.cos(Math.toRadians(lat)) * Math.tan(Math.toRadians(dec)) - Math.sin(Math.toRadians(lat)) * Math.cos(Math.toRadians(tau)))));
	    if (az < 0) az += 360;
	    
	    return new HorizontalCoordinateData(alt, az);
	}
	
	/**
	 * 태양의 지평 위치를 통해 해가 떠있는지 검사한다.
	 * @param location
	 * @param time
	 * @param sun
	 * @return 해가 떠있으면 true, 떠 있지 않으면 false
	 */
	public static boolean isSunOn(HorizontalCoordinateData coordinate) {
		// 방위각이 90도에서 태양이 뜨고, 270도이하에서 태양이 진다. 그 이외에 경우 가려져서 보이지 않는다.
		
		// 태양의 덩치와 지구 구체의 굴절율등을 고려했을때  약 -0.83도 무렵부터 태양이 보이기 시작한다. 약간의 오차가 있지만 인간이 느끼지 못할 정도로 미비한 오차이다.
		
		return coordinate.getAltitude() > -0.83 && coordinate.getAzimuth() >= 90 && coordinate.getAzimuth() < 270;
		
	}
	
	/**
	 * 천체의 지평 위치를 통해 천체가 떠 있는지 검사한다.
	 * @param celestial
	 * @return 천체가 떠 있으면 true, 떠 있지 않으면 false
	 */
	public static boolean isCelestialOn(HorizontalCoordinateData celestial) {
		return celestial.getAltitude() > 0;
	}
}
