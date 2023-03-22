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
import com.ssafy.countingstar.data.raw.SuomiNppViirsDnbMetaData;
import com.ssafy.countingstar.service.DownloadRecordService;
import com.ssafy.countingstar.service.LightPolutionService;
import com.ssafy.countingstar.service.data.util.LightPolutionDeserializer;
import com.ssafy.countingstar.service.processor.LightPolutionProcessorService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class LightPolutionCollectorServiceImpl implements LightPolutionCollectorService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LightPolutionCollectorServiceImpl.class);
	
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
    
    private DownloadRecordService downloadRecord;
    
    private RestTemplate restTemplate;
    
    private LightPolutionService lightPolutionService;
    
    public LightPolutionCollectorServiceImpl(
    		@Autowired DownloadRecordService downloadRecord, 
    		@Autowired LightPolutionService lightPolutionService, 
    		@Autowired LightPolutionProcessorService lightPolutionProcessor,
    		@Autowired RestTemplate restTemplate
    	) {
    	this.downloadRecord = downloadRecord;
    	this.restTemplate = restTemplate;
    	this.lightPolutionService = lightPolutionService;
    }
    
    private SuomiNppViirsDnbMetaData[] downloadMetadata() throws JsonMappingException, JsonProcessingException{
    	HttpEntity<String> entity = META_HEADER;
        String response = restTemplate.exchange(metaDataUrl, HttpMethod.GET, entity, String.class).getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        SuomiNppViirsDnbMetaData[] result = objectMapper.readValue(response, SuomiNppViirsDnbMetaData[].class);
        return result;
    }
    
    private void collectData(SuomiNppViirsDnbMetaData[] metadatas, LocalDateTime reqTime) throws IOException {
    	for (SuomiNppViirsDnbMetaData metadata : metadatas) {
            int downloadCount = 0;
            while (downloadCount < mdpr) {
                if (downloadRecord.isDownloaded(metadata.getCksum())) {
                    String log = String.format("[%s] Service : LightPolution, DownloadUrl : %s, IsSuccess : Yes, FileChecksum : %s, RequestTime : %s",
                            LocalDateTime.now(), metadata.getDownloadsLink(), metadata.getCksum(), reqTime);
                    LOGGER.info(log);
                    break;
                }

                try {
                    ResponseEntity<byte[]> response = restTemplate.exchange(metadata.getDownloadsLink(), HttpMethod.GET, DATA_HEADER, byte[].class);
                    if (response.getStatusCode() == HttpStatus.OK) {
                    	lightPolutionService.save(LightPolutionDeserializer.deserialize(response.getBody()));
                    	
                        downloadRecord.markAsDownloaded(metadata.getCksum());
                        String log = String.format("[%s] Service : LightPolution, DownloadUrl : %s, IsSuccess : Yes, FileChecksum : %s, RequestTime : %s",
                                LocalDateTime.now(), metadata.getDownloadsLink(), metadata.getCksum(), reqTime);
                        LOGGER.info(log);
                        break;
                    }
                    downloadCount++;
                } catch (Exception e) {
                    String log = String.format("[%s] Service : LightPolution, DownloadUrl : %s, IsSuccess : No, FileChecksum : %s, RequestTime : %s, ErrorMessage : %s",
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