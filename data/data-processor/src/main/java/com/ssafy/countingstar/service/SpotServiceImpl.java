package com.ssafy.countingstar.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.countingstar.dto.Spot;
import com.ssafy.countingstar.repository.ReactiveSpotRepository;

import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Service
public class SpotServiceImpl implements SpotService{
	
	@Autowired
	KafkaSender<String, Spot> kafkaSender;
	
	@Autowired
	ReactiveSpotRepository reactiveSpotRepository;
	
	public SpotServiceImpl(
			@Autowired KafkaSender<String, Spot> kafkaSender,
			@Autowired ReactiveSpotRepository reactiveSpotRepository
			) {
		this.kafkaSender = kafkaSender;
		this.reactiveSpotRepository = reactiveSpotRepository;
	}

	@Override
	public void publishAllSpots() {
		kafkaSender.send(reactiveSpotRepository.findAll().map(r -> SenderRecord.create(new ProducerRecord<>("spot", r),null))).subscribe();
	}

}
