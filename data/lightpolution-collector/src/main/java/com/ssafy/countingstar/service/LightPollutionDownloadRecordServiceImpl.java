package com.ssafy.countingstar.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.countingstar.dao.LightPollutionCksumDAO;
import com.ssafy.countingstar.data.LightPollutionCksum;

@Service
public class LightPollutionDownloadRecordServiceImpl implements LightPollutionDownloadRecordService{
	
	@Autowired
	LightPollutionCksumDAO lightPollutionCksumDAO;

	@Override
	public boolean isDownloaded(int cksum) {
		return lightPollutionCksumDAO.findById(cksum) != null;
	}

	@Override
	public void markAsDownloaded(int cksum, LocalDateTime date) {
		lightPollutionCksumDAO.save(new LightPollutionCksum(cksum,date));
		
	}

}
