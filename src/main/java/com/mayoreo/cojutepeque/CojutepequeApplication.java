package com.mayoreo.cojutepeque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class CojutepequeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CojutepequeApplication.class, args);
	}

}