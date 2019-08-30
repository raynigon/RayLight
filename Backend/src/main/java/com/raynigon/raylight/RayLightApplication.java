package com.raynigon.raylight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RayLightApplication {

	public static void main(String[] args) {
		SpringApplication.run(RayLightApplication.class, args);
	}

}
