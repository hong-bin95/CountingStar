package com.a201.countingstar.db.repository;

import com.a201.countingstar.db.entity.ApiTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiTestRepository extends JpaRepository<ApiTest, Integer> {
//    List<ApiTest> findFirst100();
}
