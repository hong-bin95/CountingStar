package com.ssafy.countingstar.service.processor;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ssafy.countingstar.data.Atmosphere;
import com.ssafy.countingstar.data.Celestial;
import com.ssafy.countingstar.data.EquatorialCoordinateData;
import com.ssafy.countingstar.data.HorizontalCoordinateData;
import com.ssafy.countingstar.data.LightPollution;
import com.ssafy.countingstar.data.LocationData;
import com.ssafy.countingstar.data.ObsoleteLightPollution;
import com.ssafy.countingstar.data.TimeData;
import com.ssafy.countingstar.dto.StarGrade;
import com.ssafy.countingstar.dto.TimeWithSpot;
import com.ssafy.countingstar.service.AtmosphereService;
import com.ssafy.countingstar.service.CelestialService;
import com.ssafy.countingstar.service.ObsoleteLightPollutionService;
import com.ssafy.countingstar.service.StarGradeService;
import com.ssafy.countingstar.util.CelestialUtils;
import com.ssafy.countingstar.util.DistanceUtils;

import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

@Service
public class DataProcessorServiceImpl implements DataProcessorService, Serializable{
	
	transient JavaStreamingContext jssc;
	
	Map<String, Object> kafkaParams;
	
	Collection<String> topics = Arrays.asList("timeWithSpot");
	
	transient ObsoleteLightPollutionService ObsoleteLightPollutionService;
	
	transient AtmosphereService atmosphereService;
	
	transient CelestialService celestialService;
	
	transient StarGradeService starGradeService;
	
	public DataProcessorServiceImpl(
			@Value("${spark.master.host}") String sparkMaster, 
			@Value("${spring.kafka.consumer.bootstrap-servers}") String bootstrapServers,
			@Autowired ObsoleteLightPollutionService ObsoleteLightPollutionService,
			@Autowired AtmosphereService atmosphereService,
			@Autowired CelestialService celestialService,
			@Autowired StarGradeService starGradeService
			) {
		
		SparkConf conf = new SparkConf().setAppName("CountingStarDataProcessor").setMaster(sparkMaster);
		jssc = new JavaStreamingContext(conf, Durations.seconds(1));
		this.ObsoleteLightPollutionService = ObsoleteLightPollutionService;
		this.atmosphereService = atmosphereService;
		this.celestialService = celestialService;
        kafkaParams = new HashMap<>();
        kafkaParams.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        kafkaParams.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaParams.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "com.ssafy.countingstar.dto.serializer.TimeWithSpotDeserializer");
        kafkaParams.put(ConsumerConfig.GROUP_ID_CONFIG, "countingstar");
        kafkaParams.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        kafkaParams.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
	}
	
	@Override
	public void runProcess() {
		
	    try {
	    	JavaInputDStream<ConsumerRecord<String, TimeWithSpot>> stream =
	                KafkaUtils.createDirectStream(
	                        jssc,
	                        LocationStrategies.PreferConsistent(),
	                        ConsumerStrategies.<String, TimeWithSpot>Subscribe(topics, kafkaParams)
	                );
	    	stream.foreachRDD(rdd->{
				rdd.foreach(x->{
					processTimeSpot(x.value());
				});
			});
			
		    jssc.start();
			jssc.awaitTermination();
	    	
			
		} catch(RuntimeException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void processTimeSpot(TimeWithSpot tws) {
		// TODO Auto-generated method stub
		LocalDate date = tws.getDate();
		int hour = tws.getHour();
		Flux<ObsoleteLightPollution> lightPollutionFlux = ObsoleteLightPollutionService.getAllLightPollution(date);
		Flux<Atmosphere> atmosphereFlux = atmosphereService.getAllAtmosphere(date, hour);
		List<Celestial> celestialFlux = celestialService.getAllCelestial();
		Iterable<StarGrade> result = processTimeWithSpot(tws,lightPollutionFlux, atmosphereFlux, celestialFlux);
		for(StarGrade sg : result) {
			System.out.println(sg);
		}
		
		//starGradeService.insertAllStarGrade(result);
	}

	private static Iterable<StarGrade> processTimeWithSpot(TimeWithSpot tws, Flux<ObsoleteLightPollution> lightPollutionFlux,
			Flux<Atmosphere> atmosphereFlux, List<Celestial> celestials) {
		final double radMultifier = 1_000_000_000_000.0;
		List<StarGrade> starGrades = new ArrayList<>(); // 순서 크게 중요하지 않음.
		LocationData location = new LocationData(tws.getLatitude(),tws.getLongitude());
		TimeData time = new TimeData(tws.getDate(), tws.getHour());
		EquatorialCoordinateData ecd = CelestialUtils.getSunEquatorialCoordinates(time);
		HorizontalCoordinateData shcd = CelestialUtils.getSunHorizontalCoordinate(location, time);
		if(CelestialUtils.isSunOn(shcd)) {
			celestials.forEach(x->{
				StarGrade starGrade = new StarGrade();
				starGrade.setBasicDateYear(String.valueOf(tws.getDate().getDayOfYear()));
				starGrade.setBasicDateMonth(String.valueOf(tws.getDate().getMonthValue()));
				starGrade.setBasicDateDay(String.valueOf(tws.getDate().getDayOfMonth()));
				starGrade.setBasicDateHour(String.valueOf(tws.getHour()));
				starGrade.setBasicDateMinute("0");
				starGrade.setSpot_id(tws.getSpotId());
				starGrade.setStar_id(x.getStarId());
				starGrade.setGrade1(0.0);
				starGrade.setGrade2(0);
				starGrades.add(starGrade);
			});
		}else {
			celestials.forEach(x->{
				StarGrade starGrade = new StarGrade();
				starGrade.setBasicDateYear(String.valueOf(tws.getDate().getDayOfYear()));
				starGrade.setBasicDateMonth(String.valueOf(tws.getDate().getMonthValue()));
				starGrade.setBasicDateDay(String.valueOf(tws.getDate().getDayOfMonth()));
				starGrade.setBasicDateHour(String.valueOf(tws.getHour()));
				starGrade.setSpot_id(tws.getSpotId());
				starGrade.setStar_id(x.getStarId());
				
				HorizontalCoordinateData hcd = CelestialUtils.convertToHorizontalCoordinate(location, time, ecd);
				
				if(CelestialUtils.isCelestialOn(hcd)){
					starGrade.setGrade1(0.0);
					starGrade.setGrade2(0);
				}else {
					// IDW 보간법 이용.
					double distSum = 0.0;
					double valSum = 0.0;
					
					
					while(lightPollutionFlux.hasElements().block()) {
						ObsoleteLightPollution lp = lightPollutionFlux.blockFirst(Duration.ofMillis(1000));
						double dist = DistanceUtils.calculateDistance(tws.getLatitude(), tws.getLongitude(), lp.getKey().getLat(), lp.getKey().getLng());
						double timeDist = DistanceUtils.calculateTimeDistance(tws.getDate(), tws.getHour(), lp.getKey().getDate(), lp.getKey().getHour());
						double distModifier = dist / 1000.0 + timeDist / 100.0;
						double d = (1.0/(distModifier*distModifier));
						
						// p = 2.
						distSum += d;
						valSum += d*lp.getRadiance();
					}
					
					double rad = (float) (valSum / distSum);
					
					distSum = 0.0;
					double valSumVs = 0.0;
					double valSumSs = 0.0;
					
					while(atmosphereFlux.hasElements().block()) {
						Atmosphere at = atmosphereFlux.blockFirst(Duration.ofMillis(1000));
						double dist = DistanceUtils.calculateDistance(tws.getLatitude(), tws.getLongitude(), at.getKey().getLat(), at.getKey().getLng());
						double timeDist = DistanceUtils.calculateTimeDistance(tws.getDate(), tws.getHour(), at.getKey().getDate(), at.getKey().getHour());
						double distModifier = dist / 1000.0 + timeDist / 100.0;
						double d = (1.0/(distModifier*distModifier));
						
						distSum += d;
						valSumVs += d*at.getVs();
						valSumSs += d*at.getSs();
						
					}
					
					double vs = valSumVs / distSum;
					double ss = valSumSs / distSum;
					
					double score = getScore(vs,ss,radMultifier * rad, x.getVisualMagnitude());
					starGrade.setGrade1(score);
					starGrade.setGrade2((int)(radMultifier * rad));
			
				}
				starGrades.add(starGrade);
			});
		}
		return starGrades;
	}

	private static double getScore(double vs, double ss, double rad, double vMag) {
		double x = 7 - (vMag + 5 * Math.log10((1+rad)*5000/vs)) + 100.0*Math.log10(ss*100.0);
		
		if(x < 0) {
			return 1.0;
		}
		else if(x > 7) {
			return 0.0;
		}
		return x;
	}
	

}
