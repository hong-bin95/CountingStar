package com.ssafy.countingstar.service;

public interface LightPollutionDownloadRecordService {
	
	/**
	 * @param cksum
	 * @return 해당 체크섬 데이터가 다운로드 되었는지 확인한다.
	 */
    boolean isDownloaded(String cksum);
    
    /**
     * 체크섬을 받아. 해당 체크섬이 다운로드 되었고, 처리되었음을 표시한다.
     * @param cksum
     * @param downloadedTime
     */
    void markAsDownloaded(String cksum);

}
