package com.a201.countingstar.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class CommonEnum {
    // 추천 컨텐츠 타입
    //public enum KindOfRecommendationContentsType{
    //    // 이번 달의 별자리
    //    THIS_MONTH_CONSTELLATION,
    //    //  오늘은 어디에 별이 많이 뜰까요?
    //    TODAY_BEST_STAR
    //}

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT) // api 호출시 Enum 전체 반환
    public enum RecommendationContentsType{

        THIS_MONTH_CONSTELLATION("THIS_MONTH_CONSTELLATION" , "이번 달의 별자리", 1),
        TODAY_BEST_STAR("TODAY_BEST_STAR", "오늘은 어디에 별이 많이 뜰까요?", 2);

        private final String code;
        private final String name;
        private final int value;

        public static RecommendationContentsType getValueByName(String name){
            for(RecommendationContentsType type : values()){
                if(type.name.equals(type.getName()) ){
                    return type;
                }
            }

            return null;
        }

    }

    @AllArgsConstructor
    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT) // api 호출시 Enum 전체 반환
    public enum SearchType{
        NAME("NAME","이름"),
        SUBJECT("SUBJECT","제목"),
        CONTENTS("CONTENTS","내용"),
        ID("ID","아이디");

        private final String code;
        private final String value;

        public static boolean IsSearchTypeByCode(String code){
            for(SearchType value : values()){
                if(value.getCode().equals(code)){
                    return true;
                }
            }
            return false;
        }

    }
}
