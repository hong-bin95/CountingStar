package com.a201.countingstar.db.repository.starGrade;

import com.a201.countingstar.db.entity.spot.QSpot;
import com.a201.countingstar.db.entity.star.QStarGrade;
import com.a201.countingstar.dto.spotRanking.spotRankingResponseDto;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.a201.countingstar.db.entity.star.QStarGrade;
import com.a201.countingstar.dto.spotRanking.spotRankingResponseDto;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
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
                                                       int number) {
        List<spotRankingResponseDto> responseList = new ArrayList<>();
        List<Tuple> starGradeList =
                queryFactory.select(spot.spotName,
                                        starGrade.grade1.sum())
                        .from(starGrade)
                        .leftJoin(starGrade.spot, spot)
                        .groupBy(starGrade.spot)
                        .orderBy(
                                starGrade.grade1.sum().desc()
                        )
                        .fetch();


        starGradeList.forEach(starG -> {
            responseList.add(new spotRankingResponseDto(
                    starG.get(spot.spotName),
                    starG.get(starGrade.starGrade.grade1.sum())
            ));
        });

        return responseList;

    }
}
