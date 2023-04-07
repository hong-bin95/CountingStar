package com.ssafy.countingstar.service.data.serializer;

import com.ssafy.countingstar.data.Celestial;
import com.ssafy.countingstar.dto.serializer.KryoDeserializer;

public class CelestialDeserializer extends KryoDeserializer<Celestial>{

	public CelestialDeserializer() {
		super(Celestial.class);
	}

}
