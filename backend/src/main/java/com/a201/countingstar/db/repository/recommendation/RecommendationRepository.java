package com.a201.countingstar.db.repository.recommendation;

import com.a201.countingstar.db.entity.recommendation.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Integer> {

}
