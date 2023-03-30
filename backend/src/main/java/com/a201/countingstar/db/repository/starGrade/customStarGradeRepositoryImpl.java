package com.a201.countingstar.db.repository.starGrade;

import com.a201.countingstar.common.CommonEnum;
import com.a201.countingstar.db.entity.spot.QSpot;
import com.a201.countingstar.db.entity.star.QStarGrade;
import com.a201.countingstar.dto.grade.GradeRequestDto;
import com.a201.countingstar.dto.grade.GradeResponseDto;
import com.a201.countingstar.dto.grade.SpotDto;
import com.a201.countingstar.dto.spotRanking.spotRankingResponseDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.a201.countingstar.db.entity.star.QStarGrade;
import com.a201.countingstar.dto.spotRanking.spotRankingResponseDto;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class customStarGradeRepositoryImpl implements customStarGradeRepository {
    private final JPAQueryFactory queryFactory;
    public customStarGradeRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    private QStarGrade starGrade = QStarGrade.starGrade;
    private QSpot spot = QSpot.spot;

    @Override
    public List<spotRankingResponseDto> getSpotRanking(String baseDateYear,
                                                       String baseDateMonth,
                                                       String baseDateDay,
                                                       String baseDateHour,
                                                       String baseDateMinute,
                                                       int limit) {
        List<spotRankingResponseDto> responseList = new ArrayList<>();

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(starGrade.basicDateYear.eq(baseDateYear));
        builder.and(starGrade.basicDateMonth.eq(baseDateMonth));
        builder.and(starGrade.basicDateDay.eq(baseDateDay));
        builder.and(starGrade.basicDateHour.eq(baseDateHour));
        builder.and(starGrade.basicDateMinute.eq(baseDateMinute));

        List<Tuple> starGradeList =
                queryFactory.select(spot.spotId,
                        spot.spotName,
                                        starGrade.grade1.sum(),
                                        starGrade.spot.count())
                        .from(starGrade)
                        .where(builder)
                        .leftJoin(starGrade.spot, spot)
                        .groupBy(starGrade.spot)
                        .orderBy(
                                starGrade.grade1.sum().desc()
                        )
                        .limit(limit)
                        .fetch();


        starGradeList.forEach(starG -> {
            responseList.add(new spotRankingResponseDto(
                    starG.get(spot.spotId),
                    starG.get(spot.spotName),
                    (int) Math.round(starG.get(starGrade.starGrade.grade1.sum())/2*10/starG.get(starGrade.spot.count()))
//                    (int)(starG.get(starGrade.starGrade.grade1.sum())/starG.get(starGrade.spot.count()))
            ));
        });

        return responseList;

    }

    @Override
    public List<GradeResponseDto> getGradeList(GradeRequestDto request) {
        List<GradeResponseDto> responseList = new ArrayList<>();

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(starGrade.basicDateYear.eq(request.getBaseDateYear()));
        builder.and(starGrade.basicDateMonth.eq(request.getBaseDateMonth()));
        builder.and(starGrade.basicDateDay.eq(request.getBaseDateDay()));
        builder.and(starGrade.basicDateHour.eq(request.getBaseDateHour()));
        builder.and(starGrade.basicDateMinute.eq(request.getBaseDateMinute()));

        System.out.println("1 : " + CommonEnum.SearchType.valueOf(request.getSearchType()));
        System.out.println("2 : " + CommonEnum.SearchType.NAME.getCode());
        System.out.println("3 : " + CommonEnum.SearchType.ID.getCode());

        System.out.println("CommonEnum.SearchType.valueOf(request.getSearchType()).equals(CommonEnum.SearchType.NAME.getCode()) : " + CommonEnum.SearchType.valueOf(request.getSearchType()).equals(CommonEnum.SearchType.NAME.getCode()));
        System.out.println("CommonEnum.SearchType.valueOf(request.getSearchType()).equals(CommonEnum.SearchType.ID.getCode()) : " + CommonEnum.SearchType.valueOf(request.getSearchType()).equals(CommonEnum.SearchType.ID.getCode()));

        if(request.getSearchType().equals(CommonEnum.SearchType.NAME.getCode())){
            // 스팟 이름으로 검색
            builder.and(starGrade.spot.spotName.contains(request.getKeyword() == null ? "" : request.getKeyword()));
        }
        else if(request.getSearchType().equals(CommonEnum.SearchType.ID.getCode() )){
            // 스팟 아이디로 검색
            if(request.getKeyword() == null){
                return null;
            }
            builder.and(starGrade.spot.spotId.eq(Integer.parseInt(request.getKeyword())));
        }


        List<Tuple> starGradeList =
                queryFactory.select( spot.spotId,
                                spot.spotName,
                                spot.latitude,
                                spot.longitude,
                                spot.areaCode,
                                starGrade.grade1.sum(),
                                starGrade.spot.count())
                        .from(starGrade)
                        .where(builder)
                        .leftJoin(starGrade.spot, spot)
                        .groupBy(starGrade.spot)
                        .orderBy(
                                starGrade.grade1.sum().desc()
                        )
                        .limit(request.getLimit())
                        .fetch();


        starGradeList.forEach(starG -> {
            responseList.add(new GradeResponseDto(
                    new SpotDto(starG.get(spot.spotId),
                            starG.get(spot.spotName),
                            starG.get(spot.latitude),
                            starG.get(spot.longitude),
                            starG.get(spot.areaCode)),
                    (int) Math.round(starG.get(starGrade.starGrade.grade1.sum())/2*10/starG.get(starGrade.spot.count()))
//                    (int)(starG.get(starGrade.starGrade.grade1.sum())/starG.get(starGrade.spot.count()))
            ));
        });

        return responseList;
    }
}
