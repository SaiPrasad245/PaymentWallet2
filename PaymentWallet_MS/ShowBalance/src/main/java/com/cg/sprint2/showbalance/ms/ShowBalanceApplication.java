package com.cg.sprint2.showbalance.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class ShowBalanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShowBalanceApplication.class, args);
	}

}
