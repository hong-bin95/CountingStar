package com.ssafy.countingstar.service.collector;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssafy.countingstar.data.Observatory;
import com.ssafy.countingstar.data.raw.ASOSData;
import com.ssafy.countingstar.data.raw.ASOSResponse;
import com.ssafy.countingstar.service.ObservatoryService;

@Service
public class AtmosphereCollectorServiceImpl implements AtmosphereCollectorService {

    private static final String URL = "http://apis.data.go.kr/1360000/AsosHourlyInfoService";
    private final RestTemplate restTemplate;
    private final String apiKey;
    
    public static final int PAGE_NO = 1;
    public static final int NUM_OF_ROWS = 16;
    public static final String DATA_TYPE = "XML";
    public static final String DATA_CD = "ASOS";
    public static final String DATE_CD = "HR";
    
    private ObservatoryService observatoryService;

    public AtmosphereCollectorServiceImpl(
    		@Value("${api-key}") String apiKey,
    		@Autowired ObservatoryService observatoryService
    		) {
        this.restTemplate = new RestTemplate();
        this.apiKey = apiKey;
       	this.observatoryService = observatoryService;
    }
    
    public Map<Integer, List<ASOSData>> downloadDataFromAllObservatory(LocalDateTime dateTime) {
        Map<Integer, List<ASOSData>> dataMap = new HashMap<>();
        List<Observatory> observatories = observatoryService.readAll();

        for (Observatory observatory : observatories) {
            List<ASOSData> dataList = downloadData(observatory.getCode(), dateTime);
            dataMap.put(observatory.getCode(), dataList);
        }

        return dataMap;
    }
    
    public List<ASOSData> downloadData(int stnIds, LocalDateTime dateTime) {
        String date = dateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String hour = dateTime.format(DateTimeFormatter.ofPattern("HH0000"));

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

        ResponseEntity<ASOSResponse> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, ASOSResponse.class);

        ASOSResponse response = responseEntity.getBody();
        if (response == null || response.getBody() == null || response.getBody().getItems() == null) {
            return Collections.emptyList();
        }

        return response.getBody().getItems().getItem();
    }

    @Override
    public void collect(LocalDateTime dateTime) {
        // 구현 내용
    }
}