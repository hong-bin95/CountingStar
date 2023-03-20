package com.a201.countingstar.common;

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
    public enum RecommendationContentsType{

        THIS_MONTH_CONSTELLATION("이번 달의 별자리", 1),
        TODAY_BEST_STAR("오늘은 어디에 별이 많이 뜰까요?", 2);

//        private static final Map<Integer, RecommendationContentsType> RECOMMENDATION_LIST = new HashMap<>();
//
//        static {
//            for (RecommendationContentsType type: values()) {
//                RECOMMENDATION_LIST.put(type.getValue(), type);
//            }
//        }

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
}
