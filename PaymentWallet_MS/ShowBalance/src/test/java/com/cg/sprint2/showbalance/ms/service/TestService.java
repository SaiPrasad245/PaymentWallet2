package com.cg.sprint2.showbalance.ms.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TestService {

	@Autowired
	ShowBalanceService sservice;
	
	@Test
	public void testShowBalance_Positive() throws Exception
	{
		double balance=sservice.showWalletBalanceOfUser("9398165142");
		Assertions.assertEquals(4475,balance);
	}
}
