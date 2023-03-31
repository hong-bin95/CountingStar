package com.ssafy.countingstar.service.collector;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssafy.countingstar.data.Atmosphere;
import com.ssafy.countingstar.data.Observatory;
import com.ssafy.countingstar.data.raw.ASOSData;
import com.ssafy.countingstar.data.raw.ASOSResponse;
import com.ssafy.countingstar.service.AtmosphereService;
import com.ssafy.countingstar.service.ObservatoryService;
import com.ssafy.countingstar.service.process.AtmosphereProcessorService;

@Service
public class AtmosphereCollectorServiceImpl implements AtmosphereCollectorService {

    private static final String URL = "http://apis.data.go.kr/1360000/AsosHourlyInfoService/getWthrDataList";
    private final RestTemplate restTemplate;
    private final String apiKey;
    
    public static final int PAGE_NO = 1;
    public static final int NUM_OF_ROWS = 16;
    public static final String DATA_TYPE = "XML";
    public static final String DATA_CD = "ASOS";
    public static final String DATE_CD = "HR";
    
    private ObservatoryService observatoryService;
    private AtmosphereProcessorService atmosphereProcessorService;
    private AtmosphereService atmosphereService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AtmosphereCollectorServiceImpl.class);

    public AtmosphereCollectorServiceImpl(
    		@Value("${api-key}") String apiKey,
    		@Autowired ObservatoryService observatoryService,
    		@Autowired AtmosphereProcessorService atmosphereProcessorService,
    		@Autowired AtmosphereService atmosphereService
    		) {
        this.restTemplate = new RestTemplate();
        this.apiKey = apiKey;
       	this.observatoryService = observatoryService;
       	this.atmosphereProcessorService = atmosphereProcessorService;
       	this.atmosphereService = atmosphereService;
    }
    
    public Map<Integer, List<ASOSData>> downloadDataFromObservatories(LocalDateTime dateTime, List<Observatory> observatories) {
    	LOGGER.info("Start To Download");
        Map<Integer, List<ASOSData>> dataMap = new HashMap<>();

        String date = dateTime.minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String hour = dateTime.format(DateTimeFormatter.ofPattern("HH0000"));

        for (Observatory observatory : observatories) {
            List<ASOSData> dataList = downloadData(observatory.getCode(), date, hour);
            dataMap.put(observatory.getCode(), dataList);
        }

        return dataMap;
    }
    
    public List<ASOSData> downloadData(int stnIds, String date, String hour) {
    	LOGGER.info("Start To Download of code : " + stnIds);
    	
    	int tolerance = 0;
    	
    	while(tolerance++ < 3) {
    		
    		URI uri = UriComponentsBuilder.fromHttpUrl(URL)
                    .queryParam("serviceKey", apiKey)
                    .queryParam("numOfRows", NUM_OF_ROWS)
                    .queryParam("dataType", "XML")
                    .queryParam("dataCd", "ASOS")
                    .queryParam("dateCd", "HR")
                    .queryParam("startDt", date)
                    .queryParam("startHh", hour)
                    .queryParam("endDt", date)
                    .queryParam("endHh", hour)
                    .queryParam("stnIds", stnIds)
                    .build()
                    .toUri();

    		
    		try {
    			ResponseEntity<ASOSResponse> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, ASOSResponse.class);

                ASOSResponse response = responseEntity.getBody();
                if (response == null || response.getBody() == null || response.getBody().getItems() == null) {
                    continue;
                }
                return response.getBody().getItems().getItem();
    		}catch(RuntimeException e) {
    			
    		}
    		LOGGER.info("Retry");
    	}
    	return Collections.emptyList();
        
    }

    @Override
    public void collect(LocalDateTime dateTime) {
    	List<Observatory> observatories = observatoryService.readAll();
    	Map<Integer, List<ASOSData>> rawData = downloadDataFromObservatories(dateTime, observatories);
    	LOGGER.info("Start To Process");
    	List<Atmosphere> list = atmosphereProcessorService.process(rawData, observatories);
    	LOGGER.info("Start To Save");
    	atmosphereService.saveAll(list);
    }
}