package com.a201.countingstar.db.repository.constellation;

import com.a201.countingstar.db.entity.constellation.QConstellation;
import com.a201.countingstar.db.entity.star.QStar;
import com.a201.countingstar.db.entity.star.QStarGrade;
import com.a201.countingstar.db.entity.star.StarGrade;
import com.a201.countingstar.dto.constellation.ConstellationRankResponseDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class CustomConstellationRepositoryImpl implements CustomConstellationRepository {

    private final JPAQueryFactory queryFactory;
    public CustomConstellationRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    private QConstellation constellation = QConstellation.constellation;
    private QStar star = QStar.star;
    private QStarGrade starGrade = QStarGrade.starGrade;

    @Override
    public List<ConstellationRankResponseDto> getConstellationRank(String baseDateYear, String baseDateMonth, int limit) {

        List<ConstellationRankResponseDto> responseList = new ArrayList<>();

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(constellation.observeMonth.contains(baseDateMonth));

        List<Tuple> constellationList =
                queryFactory
                        .select(constellation.korName, constellation.constellationId)
                            .from(constellation)
                                .where(builder)
                                        .limit(limit)
                                                .fetch();

        constellationList.forEach(conste -> {
            responseList.add(new ConstellationRankResponseDto(
               conste.get(constellation.constellationId),
                    conste.get(constellation.korName)
            ));
        });

        /*
        select con.name, count(con.name)  , sum(sg.grade_1)/count(con.name)
        from constellation con
            inner join star st
                on con.constellation_id = st.constellation_id
            inner join star_grade sg
                on st.star_id = sg.star_id
        where sg.basic_date_year = "2023"
                and sg.basic_date_month = "03"
        group by con.constellation_id;
        * */


        return responseList;
    }
}
