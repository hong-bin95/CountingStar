package com.ssafy.countingstar.service;

import java.io.Serializable;
import java.time.LocalDate;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.countingstar.component.KafkaBuilder;
import com.ssafy.countingstar.dto.TimeWithSpot;
import com.ssafy.countingstar.repository.ReactiveSpotRepository;

import reactor.core.publisher.Flux;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Service
public class SpotServiceImpl implements SpotService, Serializable{
	
	KafkaSender<String, TimeWithSpot> kafkaSender;
	
	ReactiveSpotRepository reactiveSpotRepository;
	
	public SpotServiceImpl(
			@Autowired KafkaBuilder KafkaBuilder,
			@Autowired ReactiveSpotRepository reactiveSpotRepository
			) {
		this.kafkaSender = KafkaBuilder.kafkaSender("com.ssafy.countingstar.dto.serializer.TimeWithSpotSerializer");
		this.reactiveSpotRepository = reactiveSpotRepository;
	}

	@Override
	public void publishAllSpots(LocalDate date, int hour) {
		Flux<SenderRecord<String, TimeWithSpot, Void>> flux = 
				reactiveSpotRepository.findAll().map(
					r -> SenderRecord.create(new ProducerRecord<String,TimeWithSpot>(
							"timeWithSpot", 
							new TimeWithSpot(
									r.getSpotId(), 
									Float.parseFloat(r.getLatitude()), 
									Float.parseFloat(r.getLongitude()),
									date,
									hour
									)),null)
				);
		kafkaSender.send(flux).subscribe();
	}

}
