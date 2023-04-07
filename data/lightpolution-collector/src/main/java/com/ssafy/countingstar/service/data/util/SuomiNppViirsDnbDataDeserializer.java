package com.ssafy.countingstar.service.data.util;

import com.ssafy.countingstar.data.raw.SuomiNppViirsDnbData;

import io.jhdf.HdfFile;
import io.jhdf.api.Dataset;

public class SuomiNppViirsDnbDataDeserializer {

	public static SuomiNppViirsDnbData deserialize(byte[] body) {
		HdfFile file = HdfFile.fromBytes(body);
		String dayNightFlag = (String) file.getAttribute("DayNightFlag").getData();
		String rangeEndingDate = (String) file.getAttribute("RangeEndingDate").getData();
		String rangeEndingTime = (String) file.getAttribute("RangeEndingTime").getData();
		
		float[] gRingPointLatitude 
			= (float[]) file.getAttribute("GRingPointLatitude").getData();
		float[] gRingPointLongitude
			= (float[]) file.getAttribute("GRingPointLongitude").getData();
		
		float southBoundingCoordinate = ((float[])file.getAttribute("SouthBoundingCoordinate").getData())[0];
		float northBoundingCoordinate = ((float[])file.getAttribute("NorthBoundingCoordinate").getData())[0];
		float eastBoundingCoordinate = ((float[])file.getAttribute("EastBoundingCoordinate").getData())[0];
		float westBoundingCoordinate = ((float[])file.getAttribute("WestBoundingCoordinate").getData())[0];
		
		Dataset radianceDataSet = file.getDatasetByPath("/observation_data/DNB_observations");
		
		SuomiNppViirsDnbData result = new SuomiNppViirsDnbData();
		result.setDayNightFlag(dayNightFlag);
		result.setRangeEndingDate(rangeEndingDate);
		result.setRangeEndingTime(rangeEndingTime);
		result.setgRingPointLatitude(gRingPointLatitude);
		result.setgRingPointLongitude(gRingPointLongitude);
		result.setSouthBoundingCoordinate(southBoundingCoordinate);
		result.setNorthBoundingCoordinate(northBoundingCoordinate);
		result.setEastBoundingCoordinate(eastBoundingCoordinate);
		result.setWestBoundingCoordinate(westBoundingCoordinate);
		result.setDataSet(radianceDataSet);
		
		return result;
	}

}
