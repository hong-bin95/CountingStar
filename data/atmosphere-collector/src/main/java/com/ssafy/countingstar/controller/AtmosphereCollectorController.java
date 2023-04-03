package com.ssafy.countingstar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.countingstar.dto.AtmosphereCollectorRequestDTO;
import com.ssafy.countingstar.service.collector.AtmosphereCollectorService;

@Controller
@RequestMapping("/")
public class AtmosphereCollectorController {
	@Autowired
	AtmosphereCollectorService collector;
	
	@PostMapping("/")
	public ResponseEntity<?> requestCollect(AtmosphereCollectorRequestDTO requestBody) {
		collector.collect(requestBody.getDate());
		return ResponseEntity.ok().build();
	}
}
