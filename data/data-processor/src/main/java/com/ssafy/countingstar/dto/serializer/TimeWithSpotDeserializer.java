package com.ssafy.countingstar.dto.serializer;

import com.ssafy.countingstar.dto.TimeWithSpot;

public class TimeWithSpotDeserializer extends KryoDeserializer<TimeWithSpot>{

	public TimeWithSpotDeserializer() {
		super(TimeWithSpot.class);
	}

}
