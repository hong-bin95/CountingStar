package com.ssafy.countingstar.data.raw;

public class YBSCStar {
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
}