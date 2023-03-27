package com.a201.countingstar.db.repository.constellation;

import com.a201.countingstar.db.entity.constellation.Constellation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConstellationRepository extends JpaRepository<Constellation, Integer>, CustomConstellationRepository {
}
