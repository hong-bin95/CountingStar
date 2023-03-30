package com.a201.countingstar.db.repository.spot;

import com.a201.countingstar.db.entity.spot.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpotRepository extends JpaRepository<Spot, Integer> {
    List<Spot> findBySpotNameContaining(String spotName);
    Spot findBySpotId(int spotId);
}
