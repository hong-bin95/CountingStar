package com.ssafy.countingstar.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.ssafy.countingstar.data.raw.IAUStar;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class IAUStarParser {
    public static List<IAUStar> parse(String filePath) throws IOException {
        List<IAUStar> stars = new ArrayList<>();
        byte[] jsonData = Files.readAllBytes(Paths.get(filePath));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonData);
        for (JsonNode node : rootNode) {
            String nameAscii = node.path("Name/ASCII").asText();
            if (nameAscii.equals("_")) nameAscii = null;
            String nameDiacritics = node.path("Name/Diacritics").asText();
            if (nameDiacritics.equals("_")) nameDiacritics = null;
            String designation = node.path("Designation").asText();
            if (designation.equals("_")) designation = null;
            String id = node.path("ID").asText();
            if (id.equals("_")) id = null;
            String idDiacritics = node.path("ID/Diacritics").asText();
            if (idDiacritics.equals("_")) idDiacritics = null;
            String con = node.path("Con").asText();
            if (con.equals("_")) con = null;
            String wds = node.path("#").asText();
            if (wds.equals("_")) wds = null;
            String wdsJ = node.path("WDS_J").asText();
            if (wdsJ.equals("_")) wdsJ = null;
            Double mag = node.path("mag").asDouble();
            if (node.path("mag").asText().equals("_")) mag = null;
            String bnd = node.path("bnd").asText();
            if (bnd.equals("_")) bnd = null;
            Long hip = node.path("HIP").asLong();
            if (node.path("HIP").asText().equals("_")) hip = null;
            Long hd = node.path("HD").asLong();
            if (node.path("HD").asText().equals("_")) hd = null;
            Double raJ2000 = node.path("RA(J2000)").asDouble();
            if (node.path("RA(J2000)").asText().equals("_")) raJ2000 = null;
            Double decJ2000 = node.path("Dec(J2000)").asDouble();
            if (node.path("Dec(J2000)").asText().equals("_")) decJ2000 = null;
            String date = node.path("Date").asText();
            if (date.equals("_")) date = null;
            String notes = node.path("notes").asText();
            if (notes.equals("_")) notes = null;
            IAUStar star = new IAUStar(nameAscii,nameDiacritics,designation,id,idDiacritics,
                                 con,wds,wdsJ,mag,bnd,hip,hd,raJ2000,
                                 decJ2000,date,notes);
            
            stars.add(star);
        }
        return stars;	
    }
    
    public static void main(String[] args) throws IOException {
    	IAUStarParser parser = new IAUStarParser();
    	parser.parse("C:/ssafy/IAU-CSN.json");
    }
    
}
            