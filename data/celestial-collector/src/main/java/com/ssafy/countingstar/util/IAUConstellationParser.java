package com.ssafy.countingstar.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.countingstar.data.raw.IAUConstellation;

public class IAUConstellationParser {
	public static List<IAUConstellation> parseCSV(String fileName) {
        List<IAUConstellation> constellations = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] values = parseLine(line);
                Integer sno = Integer.parseInt(values[0]);
                String constellation = values[1];
                String iauAbbreviation = values[2];
                String otherAbbreviation = values[3];
                String genitive = values[4];
                String family = values[5];
                String origin = values[6];
                String meaning = values[7];
                String brightestStar = values[8];

                IAUConstellation constellationObj =
                        new IAUConstellation(sno,
                                             constellation,
                                             iauAbbreviation,
                                             otherAbbreviation,
                                             genitive,
                                             family,
                                             origin,
                                             meaning,
                                             brightestStar);
                constellations.add(constellationObj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return constellations;
    }

    private static String[] parseLine(String line) {
        List<String> result = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (inQuotes) {
                if (c == '\"') inQuotes=false;
                 else sb.append(c);
            } else {
                 if (c == '\"') inQuotes=true; 
                 else if (c == ',') { 
                     result.add(sb.toString());
                     sb.setLength(0); 
                 } else sb.append(c); 
             }
         }
         result.add(sb.toString());
         return result.toArray(new String[result.size()]);
     }
    
    public static void main(String[] args) {
        List<IAUConstellation> constellations = IAUConstellationParser.parseCSV("C:/ssafy/list-constellations-677j.csv");
    }
}