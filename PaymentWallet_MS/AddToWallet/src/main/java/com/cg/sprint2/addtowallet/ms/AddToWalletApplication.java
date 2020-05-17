package com.cg.sprint2.addtowallet.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class AddToWalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddToWalletApplication.class, args);
	}

}
