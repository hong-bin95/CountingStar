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
		
		Dataset radianceDataSet = file.getDatasetByPath("/observation_data/DNB_observations");
		
		int[] redianceDim = radianceDataSet.getDimensions();
		
		float[][] radiance = (float[][]) radianceDataSet.getData();
		
		SuomiNppViirsDnbData result = new SuomiNppViirsDnbData();
		result.setDayNightFlag(dayNightFlag);
		result.setRangeEndingDate(rangeEndingDate);
		result.setRangeEndingTime(rangeEndingTime);
		result.setgRingPointLatitude(gRingPointLatitude);
		result.setgRingPointLongitude(gRingPointLongitude);
		result.setRadiance(radiance, redianceDim[0], redianceDim[1]);
		
		return result;
	}

}
