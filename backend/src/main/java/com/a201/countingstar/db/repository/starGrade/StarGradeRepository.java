package com.a201.countingstar.db.repository.starGrade;

import com.a201.countingstar.db.entity.star.StarGrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarGradeRepository extends JpaRepository<StarGrade, Integer>, customStarGradeRepository {
}
