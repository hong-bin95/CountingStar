package com.ssafy.countingstar.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.countingstar.data.raw.YBSCStar;

public class YBSCStarParser {
	

	public static List<YBSCStar> parseCatalog(String filePath) throws IOException {
	    List<YBSCStar> stars = new ArrayList<>();

	    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            Integer HR = null, FK5 = null, RadVel = null, RotVel = null;
	            Long HD = null, SAO = null;
	            Short RAh1900 = null, RAm1900 = null, DEd1900 = null, DEm1900 = null, DEs1900 = null, RAh = null, RAm = null, DEd = null, DEm = null, DEs = null, MultCnt = null;
	            Double RAs1900 = null, RAs = null, GLON = null, GLAT = null, Vmag = null, B_V = null, U_B = null, R_I = null, pmRA = null, pmDE = null, Parallax = null, Dmag = null, Sep = null;
	            String name = null, DM = null, ADS = null, ADScomp = null, VarID = null, SpType = null, n_RadVel = null, l_RotVel = null, MultID = null;
	            Byte IRflag = null, r_IRflag = null, multiple = null, DE_1900 = null, DE_ = null, n_Vmag = null, u_Vmag = null, u_B_V = null, u_U_B = null, n_R_I = null, n_SpType = null, n_Parallax = null, u_RotVel = null, NoteFlag = null;
	            
	            try {
		            HR = parseIntOrDefault(line.substring(0, 4).trim(), -1);
		            name = line.substring(4, 14).trim();
		            DM = line.substring(14, 25).trim();
		            HD = parseLongOrDefault(line.substring(25, 31).trim(), -1);
		            SAO = parseLongOrDefault(line.substring(31, 37).trim(), -1);
		            FK5 = parseIntOrDefault(line.substring(37, 41).trim(), -1);
		            IRflag = line.substring(41, 42).getBytes()[0];
		            r_IRflag = line.substring(42, 43).getBytes()[0];
		            multiple = line.substring(43, 44).getBytes()[0];
		            ADS = line.substring(44, 49).trim();
		            ADScomp = line.substring(49, 51).trim();
		            VarID = line.substring(51, 60).trim();

		            RAh1900 = parseShortOrDefault(line.substring(60, 62).trim(), (short) -1);
		            RAm1900 = parseShortOrDefault(line.substring(62, 64).trim(), (short) -1);
		            RAs1900 = parseDoubleOrDefault(line.substring(64, 68).trim(), -1);
		            DE_1900 = line.substring(68, 69).getBytes()[0];
		            DEd1900 = parseShortOrDefault(line.substring(69, 71).trim(), (short) -1);
		            DEm1900 = parseShortOrDefault(line.substring(71, 73).trim(), (short) -1);
		            DEs1900 = parseShortOrDefault(line.substring(73, 75).trim(), (short) -1);
		            RAh = parseShortOrDefault(line.substring(75, 77).trim(), (short) -1);
		            RAm = parseShortOrDefault(line.substring(77, 79).trim(), (short) -1);
		            RAs = parseDoubleOrDefault(line.substring(79, 83).trim(), -1);
		            DE_ = line.substring(83, 84).getBytes()[0];
		            DEd = parseShortOrDefault(line.substring(84, 86).trim(), (short) -1);
		            DEm = parseShortOrDefault(line.substring(86, 88).trim(), (short) -1);
		            DEs = parseShortOrDefault(line.substring(88, 90).trim(), (short) -1);
		            GLON = parseDoubleOrDefault(line.substring(90, 96).trim(), -1);
		            GLAT = parseDoubleOrDefault(line.substring(96, 102).trim(), -1);
		            Vmag = parseDoubleOrDefault(line.substring(102, 107).trim(), -1);
		            n_Vmag = line.substring(107, 108).getBytes()[0];
		            u_Vmag = line.substring(108, 109).getBytes()[0];
		            B_V = parseDoubleOrDefault(line.substring(109, 114).trim(), -1);
		            u_B_V = line.substring(114, 115).getBytes()[0];
		            U_B = parseDoubleOrDefault(line.substring(115, 120).trim(), -1);
		            u_U_B = line.substring(120, 121).getBytes()[0];
		            R_I = parseDoubleOrDefault(line.substring(121, 126).trim(), -1);
		            n_R_I = line.substring(126, 127).getBytes()[0];
		            SpType = line.substring(127, 147).trim();
		            n_SpType = line.substring(147, 148).getBytes()[0];
		            pmRA = parseDoubleOrDefault(line.substring(148, 154).trim(), -1);
		            pmDE = parseDoubleOrDefault(line.substring(154, 160).trim(), -1);
		            n_Parallax = line.substring(160, 161).getBytes()[0];
		            Parallax = parseDoubleOrDefault(line.substring(161, 166).trim(), -1);
		            RadVel = parseIntOrDefault(line.substring(166, 170).trim(), -1);
		            n_RadVel = line.substring(170, 174).trim();
		            l_RotVel = line.substring(174, 176).trim();
		            RotVel = parseIntOrDefault(line.substring(176, 179).trim(), -1);
		            u_RotVel = line.substring(179, 180).getBytes()[0];
		            Dmag = parseDoubleOrDefault(line.substring(180, 184).trim(), -1);
		            Sep = parseDoubleOrDefault(line.substring(184, 190).trim(), -1);
		            MultID = line.substring(190, 194).trim();
		            MultCnt = parseShortOrDefault(line.substring(194, 196).trim(), (short) -1);
		            NoteFlag = line.substring(196).getBytes()[0];
	            }catch(ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
	            }
	            stars.add(new YBSCStar(HR, name, DM, HD, SAO, FK5, IRflag, r_IRflag, multiple, ADS, ADScomp, VarID, RAh1900, RAm1900, RAs1900, DE_1900, DEd1900, DEm1900, DEs1900, RAh, RAm, RAs, DE_, DEd, DEm, DEs, GLON, GLAT, Vmag, n_Vmag, u_Vmag, B_V, u_B_V, U_B, u_U_B, R_I, n_R_I, SpType, n_SpType, pmRA, pmDE, n_Parallax, Parallax, RadVel, n_RadVel, l_RotVel, RotVel, u_RotVel, Dmag, Sep, MultID, MultCnt, NoteFlag));
	        }
	    }

	    return stars;
	}

	private static int parseIntOrDefault(String input, int defaultValue) {
	    try {
	        return Integer.parseInt(input);
	    } catch (NumberFormatException e) {
	        return defaultValue;
	    }
	}

	private static long parseLongOrDefault(String input, long defaultValue) {
	    try {
	        return Long.parseLong(input);
	    } catch (NumberFormatException e) {
	        return defaultValue;
	    }
	}

	private static short parseShortOrDefault(String input, short defaultValue) {
	    try {
	        return Short.parseShort(input);
	    } catch (NumberFormatException e) {
	        return defaultValue;
	    }
	}
	
	private static double parseDoubleOrDefault(String input, double defaultValue) {
	    try {
	        return Double.parseDouble(input);
	    } catch (NumberFormatException e) {
	        return defaultValue;
	    }
	}

    public static void main(String[] args) {
        String filePath = "C:/ssafy/bsc5.dat";

        try {
            List<YBSCStar> stars = parseCatalog(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


