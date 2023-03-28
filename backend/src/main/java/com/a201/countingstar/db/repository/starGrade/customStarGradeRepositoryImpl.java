package com.a201.countingstar.db.repository.starGrade;

import com.a201.countingstar.db.entity.spot.QSpot;
import com.a201.countingstar.db.entity.star.QStarGrade;
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
                queryFactory.select(spot.spotName,
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
                    starG.get(spot.spotName),
                    (int) Math.round(starG.get(starGrade.starGrade.grade1.sum())/starG.get(starGrade.spot.count()))
//                    (int)(starG.get(starGrade.starGrade.grade1.sum())/starG.get(starGrade.spot.count()))
            ));
        });

        return responseList;

    }
}
