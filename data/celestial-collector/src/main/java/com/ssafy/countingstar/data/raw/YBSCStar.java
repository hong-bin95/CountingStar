package com.ssafy.countingstar.data.raw;

import java.io.Serializable;

public class YBSCStar implements Serializable {
	Integer HR;
    String name;
    String DM;
    Long HD;
    Long SAO;
    Integer FK5;
    Byte IRflag;
    Byte r_IRflag;
    Byte multiple;
    String ADS;
    String ADScomp;
    String VarID;
    Short RAh1900;
    Short RAm1900;
    Double RAs1900;
    Byte DE_1900;
    Short DEd1900;
    Short DEm1900;
    Short DEs1900;
    Short RAh;
    Short RAm;
    Double RAs;
    Byte DE_;
    Short DEd;
    Short DEm;
    Short DEs;
    Double GLON;
    Double GLAT;
    Double Vmag;
    Byte n_Vmag;
    Byte u_Vmag;
    Double B_V;
    Byte u_B_V;
    Double U_B;
    Byte u_U_B;
    Double R_I;
    Byte n_R_I;
    String SpType;
    Byte n_SpType;
    Double pmRA;
    Double pmDE;
    Byte n_Parallax;
    Double Parallax;
    Integer RadVel;
    String n_RadVel;
    String l_RotVel;
    Integer RotVel;
    Byte u_RotVel;
    Double Dmag;
    Double Sep;
    String MultID;
    Short MultCnt;
    Byte NoteFlag;
    
    public YBSCStar() {}
    
    public YBSCStar(Integer HR, String name, String DM, Long HD, Long SAO, Integer FK5, Byte IRflag, Byte r_IRflag,
            Byte multiple, String ADS, String ADScomp, String VarID, Short RAh1900, Short RAm1900, Double RAs1900,
            Byte DE_1900, Short DEd1900, Short DEm1900, Short DEs1900, Short RAh, Short RAm, Double RAs, Byte DE_,
            Short DEd, Short DEm, Short DEs, Double GLON, Double GLAT, Double Vmag, Byte n_Vmag, Byte u_Vmag,
            Double B_V, Byte u_B_V, Double U_B, Byte u_U_B, Double R_I, Byte n_R_I, String SpType, Byte n_SpType,
            Double pmRA, Double pmDE, Byte n_Parallax, Double Parallax, Integer RadVel, String n_RadVel,
            String l_RotVel, Integer RotVel, Byte u_RotVel, Double Dmag, Double Sep, String MultID, Short MultCnt,
            Byte NoteFlag) {
        super();
        this.HR = HR;
        this.name = name;
        this.DM = DM;
        if(DM == null )throw new RuntimeException();
        this.HD = HD;
        this.SAO = SAO;
        this.FK5 = FK5;
        this.IRflag = IRflag;
        this.r_IRflag = r_IRflag;
        this.multiple = multiple;
        this.ADS = ADS;
        this.ADScomp = ADScomp;
        this.VarID = VarID;
        this.RAh1900 = RAh1900;
        this.RAm1900 = RAm1900;
        this.RAs1900 = RAs1900;
        this.DE_1900 = DE_1900;
        this.DEd1900 = DEd1900;
        this.DEm1900 = DEm1900;
        this.DEs1900 = DEs1900;
        this.RAh = RAh;
        this.RAm = RAm;
        this.RAs = RAs;
        this.DE_ = DE_;
        this.DEd = DEd;
        this.DEm = DEm;
        this.DEs = DEs;
        this.GLON = GLON;
        this.GLAT = GLAT;
        this.Vmag = Vmag;
        this.n_Vmag = n_Vmag;
        this.u_Vmag = u_Vmag;
        this.B_V = B_V;
        this.u_B_V = u_B_V;
        this.U_B = U_B;
        this.u_U_B = u_U_B;
        this.R_I = R_I;
        this.n_R_I = n_R_I;
        this.SpType = SpType;
        this.n_SpType = n_SpType;
        this.pmRA = pmRA;
        this.pmDE = pmDE;
        this.n_Parallax = n_Parallax;
        this.Parallax = Parallax;
        this.RadVel = RadVel;
        this.n_RadVel = n_RadVel;
        this.l_RotVel = l_RotVel;
        this.RotVel = RotVel;
        this.u_RotVel = u_RotVel;
        this.Dmag = Dmag;
        this.Sep = Sep;
        this.MultID = MultID;
        this.MultCnt = MultCnt;
        this.NoteFlag = NoteFlag;
    }
	public Integer getHR() {
		return HR;
	}
	public void setHR(Integer hR) {
		HR = hR;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDM() {
		return DM;
	}
	public void setDM(String dM) {
		DM = dM;
	}
	public Long getHD() {
		return HD;
	}
	public void setHD(Long hD) {
		HD = hD;
	}
	public Long getSAO() {
		return SAO;
	}
	public void setSAO(Long sAO) {
		SAO = sAO;
	}
	public Integer getFK5() {
		return FK5;
	}
	public void setFK5(Integer fK5) {
		FK5 = fK5;
	}
	public Byte getIRflag() {
		return IRflag;
	}
	public void setIRflag(Byte iRflag) {
		IRflag = iRflag;
	}
	public Byte getR_IRflag() {
		return r_IRflag;
	}
	public void setR_IRflag(Byte r_IRflag) {
		this.r_IRflag = r_IRflag;
	}
	public Byte getMultiple() {
		return multiple;
	}
	public void setMultiple(Byte multiple) {
		this.multiple = multiple;
	}
	public String getADS() {
		return ADS;
	}
	public void setADS(String aDS) {
		ADS = aDS;
	}
	public String getADScomp() {
		return ADScomp;
	}
	public void setADScomp(String aDScomp) {
		ADScomp = aDScomp;
	}
	public String getVarID() {
		return VarID;
	}
	public void setVarID(String varID) {
		VarID = varID;
	}
	public Short getRAh1900() {
		return RAh1900;
	}
	public void setRAh1900(Short rAh1900) {
		RAh1900 = rAh1900;
	}
	public Short getRAm1900() {
		return RAm1900;
	}
	public void setRAm1900(Short rAm1900) {
		RAm1900 = rAm1900;
	}
	public Double getRAs1900() {
		return RAs1900;
	}
	public void setRAs1900(Double rAs1900) {
		RAs1900 = rAs1900;
	}
	public Byte getDE_1900() {
		return DE_1900;
	}
	public void setDE_1900(Byte dE_1900) {
		DE_1900 = dE_1900;
	}
	public Short getDEd1900() {
		return DEd1900;
	}
	public void setDEd1900(Short dEd1900) {
		DEd1900 = dEd1900;
	}
	public Short getDEm1900() {
		return DEm1900;
	}
	public void setDEm1900(Short dEm1900) {
		DEm1900 = dEm1900;
	}
	public Short getDEs1900() {
		return DEs1900;
	}
	public void setDEs1900(Short dEs1900) {
		DEs1900 = dEs1900;
	}
	public Short getRAh() {
		return RAh;
	}
	public void setRAh(Short rAh) {
		RAh = rAh;
	}
	public Short getRAm() {
		return RAm;
	}
	public void setRAm(Short rAm) {
		RAm = rAm;
	}
	public Double getRAs() {
		return RAs;
	}
	public void setRAs(Double rAs) {
		RAs = rAs;
	}
	public Byte getDE_() {
		return DE_;
	}
	public void setDE_(Byte dE_) {
		DE_ = dE_;
	}
	public Short getDEd() {
		return DEd;
	}
	public void setDEd(Short dEd) {
		DEd = dEd;
	}
	public Short getDEm() {
		return DEm;
	}
	public void setDEm(Short dEm) {
		DEm = dEm;
	}
	public Short getDEs() {
		return DEs;
	}
	public void setDEs(Short dEs) {
		DEs = dEs;
	}
	public Double getGLON() {
		return GLON;
	}
	public void setGLON(Double gLON) {
		GLON = gLON;
	}
	public Double getGLAT() {
		return GLAT;
	}
	public void setGLAT(Double gLAT) {
		GLAT = gLAT;
	}
	public Double getVmag() {
		return Vmag;
	}
	public void setVmag(Double vmag) {
		Vmag = vmag;
	}
	public Byte getN_Vmag() {
		return n_Vmag;
	}
	public void setN_Vmag(Byte n_Vmag) {
		this.n_Vmag = n_Vmag;
	}
	public Byte getU_Vmag() {
		return u_Vmag;
	}
	public void setU_Vmag(Byte u_Vmag) {
		this.u_Vmag = u_Vmag;
	}
	public Double getB_V() {
		return B_V;
	}
	public void setB_V(Double b_V) {
		B_V = b_V;
	}
	public Byte getU_B_V() {
		return u_B_V;
	}
	public void setU_B_V(Byte u_B_V) {
		this.u_B_V = u_B_V;
	}
	public Double getU_B() {
		return U_B;
	}
	public void setU_B(Double u_B) {
		U_B = u_B;
	}
	public Byte getU_U_B() {
		return u_U_B;
	}
	public void setU_U_B(Byte u_U_B) {
		this.u_U_B = u_U_B;
	}
	public Double getR_I() {
		return R_I;
	}
	public void setR_I(Double r_I) {
		R_I = r_I;
	}
	public Byte getN_R_I() {
		return n_R_I;
	}
	public void setN_R_I(Byte n_R_I) {
		this.n_R_I = n_R_I;
	}
	public String getSpType() {
		return SpType;
	}
	public void setSpType(String spType) {
		SpType = spType;
	}
	public Byte getN_SpType() {
		return n_SpType;
	}
	public void setN_SpType(Byte n_SpType) {
		this.n_SpType = n_SpType;
	}
	public Double getPmRA() {
		return pmRA;
	}
	public void setPmRA(Double pmRA) {
		this.pmRA = pmRA;
	}
	public Double getPmDE() {
		return pmDE;
	}
	public void setPmDE(Double pmDE) {
		this.pmDE = pmDE;
	}
	public Byte getN_Parallax() {
		return n_Parallax;
	}
	public void setN_Parallax(Byte n_Parallax) {
		this.n_Parallax = n_Parallax;
	}
	public Double getParallax() {
		return Parallax;
	}
	public void setParallax(Double parallax) {
		Parallax = parallax;
	}
	public Integer getRadVel() {
		return RadVel;
	}
	public void setRadVel(Integer radVel) {
		RadVel = radVel;
	}
	public String getN_RadVel() {
		return n_RadVel;
	}
	public void setN_RadVel(String n_RadVel) {
		this.n_RadVel = n_RadVel;
	}
	public String getL_RotVel() {
		return l_RotVel;
	}
	public void setL_RotVel(String l_RotVel) {
		this.l_RotVel = l_RotVel;
	}
	public Integer getRotVel() {
		return RotVel;
	}
	public void setRotVel(Integer rotVel) {
		RotVel = rotVel;
	}
	public Byte getU_RotVel() {
		return u_RotVel;
	}
	public void setU_RotVel(Byte u_RotVel) {
		this.u_RotVel = u_RotVel;
	}
	public Double getDmag() {
		return Dmag;
	}
	public void setDmag(Double dmag) {
		Dmag = dmag;
	}
	public Double getSep() {
		return Sep;
	}
	public void setSep(Double sep) {
		Sep = sep;
	}
	public String getMultID() {
		return MultID;
	}
	public void setMultID(String multID) {
		MultID = multID;
	}
	public Short getMultCnt() {
		return MultCnt;
	}
	public void setMultCnt(Short multCnt) {
		MultCnt = multCnt;
	}
	public Byte getNoteFlag() {
		return NoteFlag;
	}
	public void setNoteFlag(Byte noteFlag) {
		NoteFlag = noteFlag;
	}
    
    
}