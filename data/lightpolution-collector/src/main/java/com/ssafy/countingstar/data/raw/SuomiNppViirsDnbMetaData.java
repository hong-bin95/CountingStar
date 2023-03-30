package com.ssafy.countingstar.data.raw;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SuomiNppViirsDnbMetaData {
    private int archiveSets;
    private long cksum;
    private String dataDay;
    private String downloadsLink;
    private int fileId;
    private String md5sum;
    private int mtime;
    private String name;
    private String products;
    private String resourceType;
    private String self;
    private int size;
    
    private LocalDate ld;
    private LocalDateTime ldt;

    public LocalDate getLd() {
		return ld;
	}

	public LocalDateTime getLdt() {
		return ldt;
	}

	public int getArchiveSets() {
        return archiveSets;
    }

    public void setArchiveSets(int archiveSets) {
        this.archiveSets = archiveSets;
    }

    public long getCksum() {
        return cksum;
    }

    public void setCksum(long cksum) {
        this.cksum = cksum;
    }

    public String getDataDay() {
        return dataDay;
    }

    public void setDataDay(String dataDay) {
    	String[] parts = dataDay.split("=");
        String dateStr = parts[1].trim();
        this.ld = LocalDate.parse(dateStr);
        this.dataDay = dataDay;
    }

    public String getDownloadsLink() {
        return downloadsLink;
    }

    public void setDownloadsLink(String downloadsLink) {
        this.downloadsLink = downloadsLink;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getMd5sum() {
        return md5sum;
    }

    public void setMd5sum(String md5sum) {
        this.md5sum = md5sum;
    }

    public int getMtime() {
        return mtime;
    }

    public void setMtime(int mtime) {
    	LocalDateTime epochStart = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
    	this.ldt = epochStart.plusSeconds(mtime);
        this.mtime = mtime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}