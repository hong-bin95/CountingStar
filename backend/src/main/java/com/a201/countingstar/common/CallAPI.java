package com.a201.countingstar.common;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.net.ssl.HttpsURLConnection;

@RequiredArgsConstructor
@Service
public class CallAPI {

    /*
    * GET api 호출
    * 예시
    * Map<String, String> map = new HashMap<>();

            map.put("Content-type","application/json");

            String result = call.GET("http://apis.data.go.kr/1360000/SfcMtlyInfoService/getNote?serviceKey=GzDETjLSQ1oE1xRDaRI700sakvjmUb04u0UbvpSYxb3O5PwVCYf9XtrapUAT5TeeZxpE2a5zulyMbNlEgDeRsA%3D%3D&numOfRows=100&pageNo=1&dataType=JSON&year=2016&month=09"
                    ,map);

            System.out.println("api 호출 결과 : " + result);
    * 결과는 String 으로 리턴. 받아서 원하는대로 컨버팅 해서 사용한다.
    * */
    public String GET(String url, Map<String,String> params) throws Exception  {

        System.out.println("call url : " + url);
        StringBuilder urlBuilder = new StringBuilder(url);
        URL _url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
        conn.setRequestMethod("GET");

        if(params != null){
            // 파라미터
            for( Map.Entry<String, String> param : params.entrySet() ){
                String strKey = param.getKey();
                String strValue = param.getValue();
                System.out.println( strKey +":"+ strValue );
                conn.setRequestProperty(strKey, strValue);
            }
        }

        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        return sb.toString();
    }
}
