package com.ssafy.countingstar.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.countingstar.repository.LightPollutionCksumRepository;
import com.ssafy.countingstar.data.LightPollutionCksum;

@Service
public class LightPollutionDownloadRecordServiceImpl implements LightPollutionDownloadRecordService{
	
	@Autowired
	LightPollutionCksumRepository lightPollutionCksumDAO;

	@Override
	public boolean isDownloaded(long cksum) {
		return lightPollutionCksumDAO.findById(cksum).isPresent();
	}

	@Override
	public void markAsDownloaded(long cksum, LocalDateTime date) {
		lightPollutionCksumDAO.save(new LightPollutionCksum(cksum,date));
		
	}

}
