package com.ssafy.countingstar.data.raw;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "item")
@XmlAccessorType (XmlAccessType.FIELD)
public class ASOSData {

    @XmlElement(name = "tm")
    private String tm;
    
    @XmlElement(name = "stnId")
    private int stnId;
    
    @XmlElement(name = "ta")
    private double ta;
    
    @XmlElement(name = "rn")
    private double rn;
    
    @XmlElement(name = "ws")
    private double ws;
    
    @XmlElement(name = "wd")
    private int wd;
    
    @XmlElement(name = "hm")
    private int hm;
    
    @XmlElement(name = "pa")
    private double pa;
    
    @XmlElement(name = "ps")
    private double ps;
    
    @XmlElement(name = "ss")
    private double ss;
    
    @XmlElement(name = "icsr")
    private double icsr;
    
    @XmlElement(name = "dsnw")
    private double dsnw;
    
    @XmlElement(name = "hr3Fhsc")
    private double hr3Fhsc;
    
    @XmlElement(name = "dc10Tca")
    private int dc10Tca;
    
    @XmlElement(name = "dc10LmcsCa")
    private int dc10LmcsCa;
    
    @XmlElement(name = "clfmAbbrCd")
    private String clfmAbbrCd;
    
    @XmlElement(name = "lcsCh")
    private int lcsCh;
    
    @XmlElement(name = "vs")
    private int vs;
    
    @XmlElement(name = "gndSttCd")
    private int gndSttCd;
    
    @XmlElement(name = "dmstMtphNo")
    private int dmstMtphNo;
    
    @XmlElement(name = "ts")
    private double ts;
    
    @XmlElement(name = "m005Te")
    private double m005Te;
    
    @XmlElement(name = "m01Te")
    private double m01Te;
    
    @XmlElement(name = "m02Te")
    private double m02Te;
    
    @XmlElement(name = "m03Te")
    private double m03Te;
    
    public ASOSData() {}

	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}

	public int getStnId() {
		return stnId;
	}

	public void setStnId(int stnId) {
		this.stnId = stnId;
	}

	public double getTa() {
		return ta;
	}

	public void setTa(double ta) {
		this.ta = ta;
	}

	public double getRn() {
		return rn;
	}

	public void setRn(double rn) {
		this.rn = rn;
	}

	public double getWs() {
		return ws;
	}

	public void setWs(double ws) {
		this.ws = ws;
	}

	public int getWd() {
		return wd;
	}

	public void setWd(int wd) {
		this.wd = wd;
	}

	public int getHm() {
		return hm;
	}

	public void setHm(int hm) {
		this.hm = hm;
	}

	public double getPa() {
		return pa;
	}

	public void setPa(double pa) {
		this.pa = pa;
	}

	public double getPs() {
		return ps;
	}

	public void setPs(double ps) {
		this.ps = ps;
	}

	public double getSs() {
		return ss;
	}

	public void setSs(double ss) {
		this.ss = ss;
	}

	public double getIcsr() {
		return icsr;
	}

	public void setIcsr(double icsr) {
		this.icsr = icsr;
	}

	public double getDsnw() {
		return dsnw;
	}

	public void setDsnw(double dsnw) {
		this.dsnw = dsnw;
	}

	public double getHr3Fhsc() {
		return hr3Fhsc;
	}

	public void setHr3Fhsc(double hr3Fhsc) {
		this.hr3Fhsc = hr3Fhsc;
	}

	public int getDc10Tca() {
		return dc10Tca;
	}

	public void setDc10Tca(int dc10Tca) {
		this.dc10Tca = dc10Tca;
	}

	public int getDc10LmcsCa() {
		return dc10LmcsCa;
	}

	public void setDc10LmcsCa(int dc10LmcsCa) {
		this.dc10LmcsCa = dc10LmcsCa;
	}

	public String getClfmAbbrCd() {
		return clfmAbbrCd;
	}

	public void setClfmAbbrCd(String clfmAbbrCd) {
		this.clfmAbbrCd = clfmAbbrCd;
	}

	public int getLcsCh() {
		return lcsCh;
	}

	public void setLcsCh(int lcsCh) {
		this.lcsCh = lcsCh;
	}

	public int getVs() {
		return vs;
	}

	public void setVs(int vs) {
		this.vs = vs;
	}

	public int getGndSttCd() {
		return gndSttCd;
	}

	public void setGndSttCd(int gndSttCd) {
		this.gndSttCd = gndSttCd;
	}

	public int getDmstMtphNo() {
		return dmstMtphNo;
	}

	public void setDmstMtphNo(int dmstMtphNo) {
		this.dmstMtphNo = dmstMtphNo;
	}

	public double getTs() {
		return ts;
	}

	public void setTs(double ts) {
		this.ts = ts;
	}

	public double getM005Te() {
		return m005Te;
	}

	public void setM005Te(double m005Te) {
		this.m005Te = m005Te;
	}

	public double getM01Te() {
		return m01Te;
	}

	public void setM01Te(double m01Te) {
		this.m01Te = m01Te;
	}

	public double getM02Te() {
		return m02Te;
	}

	public void setM02Te(double m02Te) {
		this.m02Te = m02Te;
	}

	public double getM03Te() {
		return m03Te;
	}

	public void setM03Te(double m03Te) {
		this.m03Te = m03Te;
	}
    
    
}