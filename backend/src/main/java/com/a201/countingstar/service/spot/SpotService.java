package com.a201.countingstar.service.spot;

import com.a201.countingstar.dto.spot.SpotResponseDto;
import java.util.List;

public interface SpotService {
    public List<SpotResponseDto> getSpotAll();
    public SpotResponseDto getSpotDetail(int spotId);
}
