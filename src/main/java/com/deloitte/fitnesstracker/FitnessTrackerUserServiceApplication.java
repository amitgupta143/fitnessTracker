package com.deloitte.fitnesstracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FitnessTrackerUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitnessTrackerUserServiceApplication.class, args);
	}

}
