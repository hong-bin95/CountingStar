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
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.countingstar.data.LightPollution;
import com.ssafy.countingstar.data.raw.SuomiNppViirsDnbData;
import com.ssafy.countingstar.data.raw.SuomiNppViirsDnbMetaData;
import com.ssafy.countingstar.service.LightPollutionDownloadRecordService;
import com.ssafy.countingstar.service.LightPollutionService;
import com.ssafy.countingstar.service.data.util.SuomiNppViirsDnbDataDeserializer;
import com.ssafy.countingstar.service.processor.LightPollutionProcessorService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class LightPollutionCollectorServiceImpl implements LightPollutionCollectorService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LightPollutionCollectorServiceImpl.class);
	
    @Value("${earthdata-token}")
    private static String token;
    
    @Value("${max-download-per-request}")
    private static int mdpr;
    
    private static final String metaDataUrl = "https://nrt3.modaps.eosdis.nasa.gov/api/v2/content/details/allData/5200/VJ102DNB_NRT/Recent?fields=all&formats=json";
    
    private static final HttpEntity<String> META_HEADER;
    
    private static final HttpEntity<String> DATA_HEADER;
    
    static {
    	META_HEADER = createHeader(MediaType.APPLICATION_JSON);
    	DATA_HEADER = createHeader(MediaType.APPLICATION_OCTET_STREAM);
    }
    
    private LightPollutionDownloadRecordService downloadRecord;
    
    private RestTemplate restTemplate;
    
    private LightPollutionService lightPollutionService;
    
    private LightPollutionProcessorService lightPollutionProcessor;
    
    public LightPollutionCollectorServiceImpl(
    		@Autowired LightPollutionDownloadRecordService downloadRecord, 
    		@Autowired LightPollutionService lightPollutionService, 
    		@Autowired LightPollutionProcessorService lightPollutionProcessor,
    		@Autowired RestTemplate restTemplate
    	) {
    	this.downloadRecord = downloadRecord;
    	this.restTemplate = restTemplate;
    	this.lightPollutionService = lightPollutionService;
    	this.lightPollutionProcessor = lightPollutionProcessor;
    }
    
    private SuomiNppViirsDnbMetaData[] downloadMetadata() throws JsonMappingException, JsonProcessingException{
    	HttpEntity<String> entity = META_HEADER;
        String response = restTemplate.exchange(metaDataUrl, HttpMethod.GET, entity, String.class).getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        SuomiNppViirsDnbMetaData[] result = objectMapper.readValue(response, SuomiNppViirsDnbMetaData[].class);
        return result;
    }
    
    private void collectData(SuomiNppViirsDnbMetaData[] metadatas, LocalDateTime reqTime) throws IOException {
    	for (int i = metadatas.length-1; i >=0; i--) {
    		// 가장 최신 데이터 부터 수집
    		SuomiNppViirsDnbMetaData metadata = metadatas[i];
    		if(!metadata.getLdt().isBefore(reqTime)) {
    			// 요청 시각, 이후 자료는 처리하지 않는다.
    			continue;
    		}
    		
    		// 요청을 넘지않는 자료중 최신 자료부터 
            int downloadCount = 0;
            while (downloadCount < mdpr) {
                if (downloadRecord.isDownloaded(metadata.getCksum())) {
                    String log = String.format("[%s] Service : LightPollution, DownloadUrl : %s, IsSuccess : Yes, FileChecksum : %s, RequestTime : %s",
                            LocalDateTime.now(), metadata.getDownloadsLink(), metadata.getCksum(), reqTime);
                    LOGGER.info(log);
                    break;
                }

                try {
                    ResponseEntity<byte[]> response = restTemplate.exchange(metadata.getDownloadsLink(), HttpMethod.GET, DATA_HEADER, byte[].class);
                    if (response.getStatusCode() == HttpStatus.OK) {
                    	SuomiNppViirsDnbData rawData = SuomiNppViirsDnbDataDeserializer.deserialize(response.getBody());
                    	for(LightPollution lightPollution : lightPollutionProcessor.process(rawData)) {
                    		lightPollutionService.save(lightPollution);
                    	}
                        downloadRecord.markAsDownloaded(metadata.getCksum(), metadata.getLdt());
                        String log = String.format("[%s] Service : LightPollution, DownloadUrl : %s, IsSuccess : Yes, FileChecksum : %s, RequestTime : %s",
                                LocalDateTime.now(), metadata.getDownloadsLink(), metadata.getCksum(), reqTime);
                        LOGGER.info(log);
                        break;
                    }
                    downloadCount++;
                    
                } catch (Exception e) {
                    String log = String.format("[%s] Service : LightPollution, DownloadUrl : %s, IsSuccess : No, FileChecksum : %s, RequestTime : %s, ErrorMessage : %s",
                            LocalDateTime.now(), metadata.getDownloadsLink(), metadata.getCksum(), LocalDateTime.now(), e.getMessage());
                    LOGGER.error(log);
                }
            }
        }
    }
    
    private static HttpEntity<String> createHeader(MediaType mediaType) {
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