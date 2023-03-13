package com.a201.countingstar.repository;

import com.a201.countingstar.entity.ApiTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiTestRepository extends JpaRepository<ApiTest, Integer> {
//    List<ApiTest> findFirst100();
}
