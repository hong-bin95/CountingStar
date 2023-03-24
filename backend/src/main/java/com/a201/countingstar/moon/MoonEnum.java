package com.a201.countingstar.moon;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 달 위상 사진 url
public class MoonEnum {

    @AllArgsConstructor
//    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @Getter
    public enum MoonPhotoUrl{
        ZERO(0, ""),
        ONE(1, ""),
        TWO(2, ""),
        THREE(3, ""),
        FOUR(4, ""),
        FIVE(5, ""),
        SIX(6, ""),
        SEVEN(7, ""),
        EIGHT(8, ""),
        NINE(9, ""),
        TEN(10, "");

        private final int value;
        private final String url;

        private static String getUrlByValue(int value) {
            for (MoonPhotoUrl url : values()) {
                if (url.value == value) {
                    return url.getUrl();
                }
            }
            return null;
        }

    }
}
