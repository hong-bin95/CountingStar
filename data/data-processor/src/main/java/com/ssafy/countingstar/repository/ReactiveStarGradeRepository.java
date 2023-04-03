package com.ssafy.countingstar.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.countingstar.dto.StarGrade;

@Repository
public interface ReactiveStarGradeRepository extends ReactiveCrudRepository<StarGrade, Integer>{

}
