package com.a201.countingstar.controller;

import com.a201.countingstar.moon.MoonEnum;
import com.a201.countingstar.service.moon.MoonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Api("Moon RestController")
@RestController
@RequestMapping("/moon")
public class MoonController {

    private final MoonService moonService;

    public MoonController(MoonService moonService) {
        this.moonService = moonService;
    }

    public String callAPI() throws JsonProcessingException {
        HashMap<String, Object> result = new HashMap<String, Object>();

        String jsonInString = "";

        try {
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            RestTemplate restTemplate = new RestTemplate(factory);

            HttpHeaders header = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(header);

            // 월령 값 받아올 api url
            String url = "http://api.farmsense.net/v1/moonphases/";

            // 전달 받은 시간을 string 유닉스 타임으로 바꾸기
            long time = new SimpleDateFormat("yyyyMMdd").parse("20200101").getTime();
            String unixTime = new Long(time).toString().substring(0, 10);

            // 해당 유닉스 타임으로 uri 생성
            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url + "?" + "d=" + unixTime).build();

            System.out.println("uri :" + uri);

//            restTemplate.getInterceptors().add((request, body, execution) -> {
//                ClientHttpResponse response = execution.execute(request,body);
//                response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
//                return response;
//            });
//            final HttpHeaders headers = new HttpHeaders();
//            final HttpEntity<?> entity = new HttpEntity<>(headers);

//            BooksResponseDto body = restTemplate.exchange(OpenBookUrl_getBooks, HttpMethod.GET, entity, BooksResponseDto.class).getBody();
//            return body;

            // api 호출해서 map 타입으로 받기
            ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);
//            ResponseEntity<Map> ddd= restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);

//            result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
//            result.put("header", resultMap.getHeaders()); //헤더 정보 확인
//            result.put("body", resultMap.getBody()); //실제 데이터 정보 확인

//            System.out.println("data_1 :" + result);
//            System.out.println("data_2 :" + resultMap.getBody());
//
//            // 데이터를 제대로 전달 받았는지 string 형태로 파싱해서 확인
//            ObjectMapper mapper = new ObjectMapper();
//            jsonInString = mapper.writeValueAsString(resultMap.getBody());
//
//            System.out.println("data_3 :" + jsonInString);

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            result.put("statusCode", e.getRawStatusCode());
            result.put("body"  , e.getStatusText());
            System.out.println("dfdfdfdf");
            System.out.println(e.toString());

        } catch (Exception e) {
            result.put("statusCode", "999");
            result.put("body"  , "excpetion오류");
            System.out.println(e.toString());
        }
        return jsonInString;
    }

    @ApiOperation(value = "달 위상 사진 url", notes = "해당 시간의 달 위상 사진 url 반환")
    @GetMapping
    public MoonEnum getMoonUrl() {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;
        try {
//            MoonEnum moon = moonService.getMoonUrl(Integer.valueOf());
            callAPI();
        } catch (Exception e) {

        }
        return null;
    }

}
