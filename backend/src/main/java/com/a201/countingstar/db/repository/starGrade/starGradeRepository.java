package com.a201.countingstar.db.repository.starGrade;

import com.a201.countingstar.db.entity.spot.Spot;
import com.a201.countingstar.db.entity.star.StarGrade;
import com.a201.countingstar.dto.spotRanking.spotRankingResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface starGradeRepository  extends JpaRepository<StarGrade, Integer>, customStarGradeRepository {
}
