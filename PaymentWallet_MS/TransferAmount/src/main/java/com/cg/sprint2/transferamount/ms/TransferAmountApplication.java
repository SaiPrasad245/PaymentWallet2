package com.cg.sprint2.transferamount.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class TransferAmountApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransferAmountApplication.class, args);
	}

}
