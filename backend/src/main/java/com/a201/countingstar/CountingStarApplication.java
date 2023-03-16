package com.a201.countingstar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CountingStarApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountingStarApplication.class, args);
    }

}
