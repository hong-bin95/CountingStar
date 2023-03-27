package com.a201.countingstar.service.constellation;

import com.a201.countingstar.db.repository.constellation.ConstellationRepository;
import com.a201.countingstar.dto.constellation.ConstellationRankResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConstellationServiceImpl implements ConstellationService {

    private final ConstellationRepository constellationRepository;
    @Override
    public List<ConstellationRankResponseDto> getConstellationRank(String baseDateYear, String baseDateMonth, int limit) {
        return constellationRepository.getConstellationRank(baseDateYear, baseDateMonth, limit);
    }
}
