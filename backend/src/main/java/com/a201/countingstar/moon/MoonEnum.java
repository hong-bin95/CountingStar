package com.a201.countingstar.moon;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

// 달 위상 사진 url
public class MoonEnum {

    @AllArgsConstructor
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @Getter
    public enum MoonPhotoUrl{

        // 삭 -> 초승달 -> 상현달 -> 차가는달 -> 보름달 -> 기울어가는달 -> 하현달 -> 그믐달
        DARK_MOON(1, "https://counting-star.com/images/%EC%82%AD.png"),
        WAX_CRE(2, "https://counting-star.com/images/%EC%B4%88%EC%8A%B9%EB%8B%AC.png"),
        FIRST_QUARTER(3, "https://counting-star.com/images/%EC%83%81%ED%98%84%EB%8B%AC.png"),
        WAX_GIB(4, "https://counting-star.com/images/%EC%B0%A8%EA%B0%80%EB%8A%94%EB%8B%AC.png"),
        FULL_MOON(5, "https://counting-star.com/images/%EB%B3%B4%EB%A6%84%EB%8B%AC.png"),
        WAN_GIB(6, "https://counting-star.com/images/%EA%B8%B0%EC%9A%B8%EC%96%B4%EA%B0%80%EB%8A%94%EB%8B%AC.png"),
        THIRD_QUARTER(7, "https://counting-star.com/images/%ED%95%98%ED%98%84%EB%8B%AC.png"),
        WAN_CRE(8, "https://counting-star.com/images/%EA%B7%B8%EB%AF%90%EB%8B%AC.png");

        private final int value;
        private final String url;

        public static String getUrlByValue(int value) {
            for (MoonPhotoUrl url : values()) {
                if (url.value == value) {
                    return url.getUrl();
                }
            }
            return null;
        }

    }
}
