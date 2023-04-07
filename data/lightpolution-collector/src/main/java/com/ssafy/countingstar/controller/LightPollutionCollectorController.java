package com.ssafy.countingstar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.countingstar.dto.LightPollutionCollectorRequestDTO;
import com.ssafy.countingstar.service.collector.LightPollutionCollectorService;

@Controller
@RequestMapping("/api")
public class LightPollutionCollectorController {
	
	@Autowired
	LightPollutionCollectorService collector;
	
	@PostMapping("/eollect")
	public ResponseEntity<?> requestCollect(LightPollutionCollectorRequestDTO requestBody) {
		collector.collect(requestBody.getDate());
		return ResponseEntity.ok().build();
	}

}
