package com.a201.countingstar.service.moon;

import com.a201.countingstar.dto.moon.MoonResponseDto;
import com.a201.countingstar.moon.MoonEnum;

public interface MoonService {
    public MoonResponseDto getMoonUrl(int value);
}
