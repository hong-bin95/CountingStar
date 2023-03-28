package com.a201.countingstar.db.repository.grade;

import com.a201.countingstar.db.entity.star.StarGrade;
import com.a201.countingstar.dto.grade.GradeRequestDto;
import com.a201.countingstar.dto.grade.GradeResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<StarGrade, Integer>, CustomGradeRepository {
}
