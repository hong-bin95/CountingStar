package com.ssafy.countingstar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import com.ssafy.countingstar.service.processor.DataProcessorService;

@SpringBootApplication
@EnableReactiveCassandraRepositories
@EnableR2dbcRepositories
public class DataProcessorApplication implements CommandLineRunner {
	
	@Autowired
	DataProcessorService processor;

	public static void main(String[] args) {
		SpringApplication.run(DataProcessorApplication.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
		processor.runProcess();
    }

}
