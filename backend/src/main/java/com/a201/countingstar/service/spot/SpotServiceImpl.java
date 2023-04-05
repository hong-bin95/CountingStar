package com.a201.countingstar.service.spot;

import com.a201.countingstar.db.entity.spot.Spot;
import com.a201.countingstar.db.repository.spot.SpotRepository;
import com.a201.countingstar.db.repository.starGrade.StarGradeRepository;
import com.a201.countingstar.dto.spot.SpotResponseDto;
import com.a201.countingstar.dto.spotRanking.spotRankingResponseDto;
import org.springframework.stereotype.Service;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpotServiceImpl implements SpotService {
    private final SpotRepository spotRepository;
    private final StarGradeRepository spotGradeRepository;
    @Override
    public List<SpotResponseDto> getSpotAll(){
        List<Spot> spotEntityList = spotRepository.findAll();
        List<SpotResponseDto> spotList = new ArrayList<>();
        spotEntityList.stream().forEach(spot -> {
            spotList.add(SpotResponseDto.builder()
                    .spotId(spot.getSpotId())
                    .areaCode(spot.getAreaCode())
                    .spotName(spot.getSpotName())
                    .latitude(spot.getLatitude())
                    .longitude(spot.getLongitude()).build());
        });
        return spotList;
    }

    @Override
    public SpotResponseDto getSpotDetail(int spotId) {
        Optional<Spot> spotEntity = spotRepository.findById(spotId);
        SpotResponseDto spot = SpotResponseDto.builder()
                .spotId(spotEntity.get().getSpotId())
                .areaCode(spotEntity.get().getAreaCode())
                .spotName(spotEntity.get().getSpotName())
                .latitude(spotEntity.get().getLatitude())
                .longitude(spotEntity.get().getLongitude()).build();
        return spot;
    }

    @Override
    public List<spotRankingResponseDto> getSpotRanking(  String baseDateYear,
                                                         String baseDateMonth,
                                                         String baseDateDay,
                                                         String baseDateHour,
                                                         String baseDateMinute,
                                                         int limit){
        return spotGradeRepository.getSpotRanking(baseDateYear, baseDateMonth, baseDateDay,
                                                    baseDateHour, baseDateMinute, limit);
    }
}
