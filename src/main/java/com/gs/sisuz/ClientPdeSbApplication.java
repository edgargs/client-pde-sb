package com.gs.sisuz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClientPdeSbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientPdeSbApplication.class, args);
	}

}
