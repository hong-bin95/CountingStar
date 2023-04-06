package com.ssafy.countingstar.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.countingstar.dto.StarGrade;
import com.ssafy.countingstar.repository.ReactiveStarGradeRepository;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
public class StarGradeServiceImpl implements StarGradeService, Serializable {
	
	@Autowired
	ReactiveStarGradeRepository reactiveStarGradeRepository;

	@Override
	public void insertAllStarGrade(Iterable<StarGrade> starGrades) {
		Flux.fromIterable(starGrades)
		.buffer(1024)
		.flatMap(batch -> reactiveStarGradeRepository.saveAll(batch).subscribeOn(Schedulers.parallel()), 1)
		.blockLast();
	}

}
