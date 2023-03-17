package com.a201.countingstar.db.repository.recommendation;

import com.a201.countingstar.db.entity.recommendation.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Integer> {

}
