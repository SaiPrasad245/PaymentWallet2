package com.cg.sprint2.carddetails.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class CardDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardDetailsApplication.class, args);
	}

}
