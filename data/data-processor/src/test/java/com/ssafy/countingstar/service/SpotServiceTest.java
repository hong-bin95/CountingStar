package com.ssafy.countingstar.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.spark.SparkConf;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import org.apache.spark.streaming.*;
import org.apache.spark.streaming.api.java.*;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;

import com.ssafy.countingstar.DataProcessorApplication;
import com.ssafy.countingstar.dto.TimeWithSpot;

@SpringBootTest(classes = DataProcessorApplication.class)
public class SpotServiceTest {
	@Autowired
	SpotService spotService;
	
	JavaStreamingContext jssc;
	
	Map<String, Object> kafkaParams;
	
	Collection<String> topics = Arrays.asList("timeWithSpot");
	
	public SpotServiceTest(
			@Value("${spark.master.host}") String sparkMaster, 
			@Value("${spring.kafka.consumer.bootstrap-servers}") String bootstrapServers
			) {
		SparkConf conf = new SparkConf().setAppName("CountingStarDataProcessor").setMaster(sparkMaster);
		jssc = new JavaStreamingContext(conf, Durations.seconds(1));
        kafkaParams = new HashMap<>();
        kafkaParams.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        kafkaParams.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaParams.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "com.ssafy.countingstar.dto.serializer.TimeWithSpotDeserializer");
        kafkaParams.put(ConsumerConfig.GROUP_ID_CONFIG, "countingstar");
        kafkaParams.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        kafkaParams.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
	}
	
	@Test
	void testPubSub() throws InterruptedException {
		spotService.publishAllSpots(LocalDate.of(2023, 3, 30),1);
		
		JavaInputDStream<ConsumerRecord<String, TimeWithSpot>> stream =
                KafkaUtils.createDirectStream(
                        jssc,
                        LocationStrategies.PreferConsistent(),
                        ConsumerStrategies.<String, TimeWithSpot>Subscribe(topics, kafkaParams)
                );
		
		stream.foreachRDD(rdd->{
			System.out.println("===========RDDSTART===========");
			rdd.foreach(x->{
				assertNotNull(x.value());
				System.out.println("Date : " + x.value().getDate());
			});
			System.out.println("===========RDDEND===========");
			
		});
		
	    jssc.start();
	    jssc.awaitTermination();
		
	}
}
