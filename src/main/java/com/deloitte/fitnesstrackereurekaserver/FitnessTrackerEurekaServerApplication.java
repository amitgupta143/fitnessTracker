package com.deloitte.fitnesstrackereurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FitnessTrackerEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitnessTrackerEurekaServerApplication.class, args);
	}

}
