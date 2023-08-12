package com.staroot.hc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HcApplication {

	public static void main(String[] args) {
		SpringApplication.run(HcApplication.class, args);
	}

}
