package com.ssafy.countingstar.service.collector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.countingstar.data.LightPollution;
import com.ssafy.countingstar.data.raw.SuomiNPPViirsDnsMetaDataGroup;
import com.ssafy.countingstar.data.raw.SuomiNppViirsDnbData;
import com.ssafy.countingstar.data.raw.SuomiNppViirsDnbMetaData;
import com.ssafy.countingstar.service.LightPollutionDownloadRecordService;
import com.ssafy.countingstar.service.LightPollutionService;
import com.ssafy.countingstar.service.data.util.SuomiNppViirsDnbDataDeserializer;
import com.ssafy.countingstar.service.processor.LightPollutionProcessorService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LightPollutionCollectorServiceImpl implements LightPollutionCollectorService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LightPollutionCollectorServiceImpl.class);
    
    private String token;
    
    private int mdpr;
    
    private static final String metaDataUrl = "https://nrt3.modaps.eosdis.nasa.gov/api/v2/content/details/allData/5200/VJ102DNB_NRT/Recent?fields=all&formats=json";
    //private static final String metaDataUrl = "https://nrt3.modaps.eosdis.nasa.gov/api/v2/content/details/allData/5200/VNP02DNB_NRT/2023/086?fields=all&formats=json";
    	
    private final HttpEntity<String> META_HEADER;
    
    private final HttpEntity<String> DATA_HEADER;
    
    private LightPollutionDownloadRecordService downloadRecord;
    
    private RestTemplate restTemplate;
    
    private LightPollutionService lightPollutionService;
    
    private LightPollutionProcessorService lightPollutionProcessor;
    
    private float maxLat;
    private float minLat;
    private float maxLng;
    private float minLng;
    
    public LightPollutionCollectorServiceImpl(
    		@Autowired LightPollutionDownloadRecordService downloadRecord, 
    		@Autowired LightPollutionService lightPollutionService, 
    		@Autowired LightPollutionProcessorService lightPollutionProcessor,
    		@Value("${max-download-per-request}") int mdpr,
    		@Value("${earthdata-token}") String token,
    		@Value("${lightpollution-limit-max-lat}") float maxLat,
    		@Value("${lightpollution-limit-min-lat}") float minLat,
    		@Value("${lightpollution-limit-max-lng}") float maxLng,
    		@Value("${lightpollution-limit-min-lng}") float minLng
    	) {
    	
    	this.downloadRecord = downloadRecord;
    	this.lightPollutionService = lightPollutionService;
    	this.lightPollutionProcessor = lightPollutionProcessor;
    	this.mdpr = mdpr;
    	this.token = token;
    	
    	this.maxLat = maxLat;
    	this.minLat = minLat;
    	this.maxLng = maxLng;
    	this.minLng = minLng;
    	
    	this.restTemplate = new RestTemplate();
    	META_HEADER = createHeader(MediaType.APPLICATION_JSON);
    	DATA_HEADER = createHeader(MediaType.APPLICATION_OCTET_STREAM);
    }
    
    private SuomiNppViirsDnbMetaData[] downloadMetadata() throws JsonMappingException, JsonProcessingException{
    	HttpEntity<String> entity = META_HEADER;
        String response = restTemplate.exchange(metaDataUrl, HttpMethod.GET, entity, String.class).getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SuomiNppViirsDnbMetaData[] result = objectMapper.readValue(response, SuomiNPPViirsDnsMetaDataGroup.class).getContent();
        return result;
    }
    
    private SuomiNppViirsDnbData downloadData(SuomiNppViirsDnbMetaData metadata) throws IOException {
    	LOGGER.info("Start To Download FileChecksum : " + metadata.getCksum());
    	ResponseEntity<byte[]> response = restTemplate.exchange(metadata.getDownloadsLink(), HttpMethod.GET, DATA_HEADER, byte[].class);
        if (response.getStatusCode() == HttpStatus.OK) {
        	SuomiNppViirsDnbData rawData = SuomiNppViirsDnbDataDeserializer.deserialize(response.getBody());
            downloadRecord.markAsDownloaded(metadata.getCksum(), metadata.getLdt());
            return rawData;
        }else {
        	throw new IOException();
        }
    }
    
    private Iterable<LightPollution> processData(List<SuomiNppViirsDnbData> processTarget) {
    	LOGGER.info("Start to Process Data");
    	return lightPollutionProcessor.process(processTarget);
    }
    
    private void preserveData(Iterable<LightPollution> lightPollutions) {
    	LOGGER.info("Start to Save Data");
    	lightPollutionService.save(lightPollutions);
    }
    
    private void collectData(SuomiNppViirsDnbMetaData[] metadatas, LocalDateTime reqTime) throws IOException {
    	int downloadCount = 0;
    	List<SuomiNppViirsDnbData> processTarget = new ArrayList<SuomiNppViirsDnbData>();
    	for (int i = metadatas.length-1; i >=0; i--) {
    		if(downloadCount >= mdpr) {
    			break;
    		}
    		// 가장 최신 데이터 부터 수집
    		SuomiNppViirsDnbMetaData metadata = metadatas[i];
    		if(!metadata.getLdt().isBefore(reqTime)) {
    			String log = String.format("[%s] Service : LightPollution, DownloadUrl : %s, IsSuccess : Skipped(afterReqTime), FileChecksum : %s, RequestTime : %s",
                        LocalDateTime.now(), metadata.getDownloadsLink(), metadata.getCksum(), reqTime);
    			LOGGER.info(log);
    			continue;
    		}
            if (downloadRecord.isDownloaded(metadata.getCksum())) {
                String log = String.format("[%s] Service : LightPollution, DownloadUrl : %s, IsSuccess : Skipped(downloaded), FileChecksum : %s, RequestTime : %s",
                        LocalDateTime.now(), metadata.getDownloadsLink(), metadata.getCksum(), reqTime);
                LOGGER.info(log);
                continue;
            }
            try {
                SuomiNppViirsDnbData rawData = downloadData(metadata);
                
                
                if(rawData.getDayNightFlag() == 1) {
                	LOGGER.info("Passed Because It was not night"); 
                	continue;
                	}
                if(
                		rawData.getNorthBoundingCoordinate() < minLat ||
                		rawData.getSouthBoundingCoordinate() > maxLat ||
                		rawData.getEastBoundingCoordinate() < minLng ||
                		rawData.getWestBoundingCoordinate() > maxLng
                		
                ) {
                	LOGGER.info("Passed Because It could not be coveraged (lat, lng)");
                	continue;
                }
                
                // float 읽기.
                rawData.loadFromDataSet();
                
                processTarget.add(rawData);
                String log = String.format("[%s] Service : LightPollution, DownloadUrl : %s, IsSuccess : Yes, FileChecksum : %s, RequestTime : %s",
                        LocalDateTime.now(), metadata.getDownloadsLink(), metadata.getCksum(), reqTime);
                LOGGER.info(log);
                downloadCount++;
            } catch (Exception e) {
                String log = String.format("[%s] Service : LightPollution, DownloadUrl : %s, IsSuccess : No, FileChecksum : %s, RequestTime : %s, ErrorMessage : %s",
                        LocalDateTime.now(), metadata.getDownloadsLink(), metadata.getCksum(), LocalDateTime.now(), e.getMessage());
                LOGGER.error(log);
                e.printStackTrace();
            }
            
        }
    	preserveData(processData(processTarget));
    	LOGGER.info("Complete All Process");
    }
    
    
    
    private HttpEntity<String> createHeader(MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(mediaType));
        headers.set("Authorization", "Bearer " + token);
        return new HttpEntity<String>(headers);
    }

	@Override
	public void collect(LocalDateTime dateTime) {
		try {
			collectData(downloadMetadata(), dateTime);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
}