package com.a201.countingstar.service.moon;

import com.a201.countingstar.common.CallAPI;
import com.a201.countingstar.dto.moon.MoonApiResponseDto;
import com.a201.countingstar.moon.MoonEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
//@AllArgsConstructor
public class MoonServiceImpl implements MoonService {
    private final CallAPI callAPI;

    public MoonServiceImpl(CallAPI callAPI) {
        this.callAPI = callAPI;
    }

    @Override
    public String getMoonUrl(String select_date) throws Exception {

//        private Object MoonResponseDto;

        Map<String, String> map = new HashMap<>();
        map.put("Content-type", "application/json");

        // 월령 값 받아올 api url
        String url = "http://api.farmsense.net/v1/moonphases/";

        // 전달 받은 시간을 string 유닉스 타임으로 바꾸기
        long time = new SimpleDateFormat("yyyyMMdd").parse(select_date).getTime();
        String unixTime = new Long(time).toString().substring(0, 10);

        // 해당 유닉스 타임으로 uri 생성
        String uri = url + "?" + "d=" + unixTime;

        // api 호출 -> string 형식의 Json 문자열
        String result = callAPI.GET(uri, map);

        // 확인
        System.out.println("uri :" + uri);
        System.out.println("api 호출 결과 : " + result);

        // 받은 데이터 역직렬화한 결과
        List<MoonApiResponseDto> moonData = MoonApiResponseDto(result);

        String phase = null;
        Float illumination = null;
        int index = 0;

        // 그 중에서 원하는 값만 꺼내기
        for (MoonApiResponseDto dto : moonData) {
            phase = dto.getPhase();
            illumination = dto.getIllumination();
            index = dto.getIndex();
        }
        System.out.println("phase->" + phase);
        System.out.println("illumination->" + illumination);
        System.out.println("index->" + index);


        int moon_value = 0;

        // 삭, 상현, 보름, 하현
        if ((phase.equals("Dark Moon") || phase.equals("New Moon")) && (index == 0 || index == 28)) {
            moon_value = 1;
            System.out.println("현재 달 ---> 삭 !" + moon_value);

        } else if (phase.equals("1st Quater") || index == 7) {
            moon_value = 3;
            System.out.println("현재 달 ---> 상현달 !" + moon_value);

        } else if (phase.equals("Full Moon") || index == 14) {
            moon_value = 5;
            System.out.println("현재 달 ---> 보름달 !" + moon_value);

        } else if (phase.equals("3rd Quater") || index == 21) {
            moon_value = 7;
            System.out.println("현재 달 ---> 하현달 !" + moon_value);

        } else {

            if (phase.equals("Waxing Crescent")) {
            System.out.println("현재 달 ---> 초승달");
            moon_value = 2;

            } else if (phase.equals("Waxing Gibbous")) {
                System.out.println("현재 달 ---> 차가는달");
                moon_value = 4;

            } else if (phase.equals("Waning Gibbous")) {
                System.out.println("현재 달 ---> 기울어가는달");
                moon_value = 6;

            } else if (phase.equals("Waning Crescent")) {
                System.out.println("현재 달 ---> 그믐달");
                moon_value = 8;
            }
        }

        String imgUrl;
        imgUrl = MoonEnum.MoonPhotoUrl.getUrlByValue(moon_value);

        return imgUrl;

    }

    // 받은 데이터 역직렬화 -> MoonApiResponseDto에 field에 맞게 넣어줌.
    // 받은 데이터와 형태, key 이름 동일해야 함. (대소문자 주의)
    // 첫 번째는 데이터 형태, 두 번째는 함수 이름
    List<MoonApiResponseDto> MoonApiResponseDto(String result) throws JsonProcessingException {
        // mapper -> null 값으로 두면 nullpointexception 발생
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new ParameterNamesModule());

        // 만드는 dto 형식을 맞춰야 함 ->
//        List<MoonApiResponseDto> dto = mapper.readValue(result.toString(),new ArrayList<MoonApiResponseDto>().getClass());
        List<MoonApiResponseDto> dto = mapper.readValue(result.toString(),new TypeReference<List<MoonApiResponseDto>>() {});
        return dto;
    }
}
