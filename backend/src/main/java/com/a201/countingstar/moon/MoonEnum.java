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

        DARK_MOON(0, "https://counting-star.com/images/%EC%82%AD.png"),
        WAX_CRE_1(1, "https://counting-star.com/images/1%20%EC%B4%88%EC%8A%B9%EB%8B%AC.png"),
        WAX_CRE_2(2, "https://counting-star.com/images/2%20%EC%B4%88%EC%8A%B9%EB%8B%AC.png"),
        FIRST_QUARTER(3, "https://counting-star.com/images/%EC%83%81%ED%98%84%EB%8B%AC.png"),
        WAX_GIB_1(4, "https://counting-star.com/images/3%20%EC%98%A4%EB%A5%B8%EC%AA%BD%20%ED%81%B0%20%EB%8B%AC.png"),
        WAX_GIB_2(5, "https://counting-star.com/images/4%20%EC%98%A4%EB%A5%B8%EC%AA%BD%20%EB%8D%94%20%ED%81%B0%20%EB%8B%AC.png"),
        FULL_MOON(6, "https://counting-star.com/images/%EB%B3%B4%EB%A6%84%EB%8B%AC.png"),
        WAN_GIB_1(7, "https://counting-star.com/images/3%20%EC%99%BC%EC%AA%BD%20%ED%81%B0%20%EB%8B%AC.png"),
        WAN_GIB_2(8, "https://counting-star.com/images/4%20%EC%99%BC%EC%AA%BD%20%EB%8D%94%20%ED%81%B0%20%EB%8B%AC.png"),
        THIRD_QUARTER(9, "https://counting-star.com/images/%ED%95%98%ED%98%84%EB%8B%AC.png"),
        WAN_CRE_1(10, "https://counting-star.com/images/1%20%EA%B7%B8%EB%AF%90%EB%8B%AC.png"),
        WAN_CRE_2(11, "https://counting-star.com/images/2%20%EA%B7%B8%EB%AF%90%EB%8B%AC.png");

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
