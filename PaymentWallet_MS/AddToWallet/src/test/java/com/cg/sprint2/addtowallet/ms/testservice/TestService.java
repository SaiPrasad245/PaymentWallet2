package com.cg.sprint2.addtowallet.ms.testservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.cg.sprint2.addtowallet.ms.service.AddMoneyToWalletService;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TestService {
	@Autowired
	AddMoneyToWalletService adservice;
	@Test
    public void testAddMoneyToWallet_Positive() throws Exception
    {
		String response = adservice.addMoneyToWalletFromCard(10, "7799208319123456", "7799208319", 963);
		Assertions.assertEquals("Money Added Sucessfully", response);
		
    }
	@Test
	 public void testAddMoneyToWallet_Negative() throws Exception
	    {
		String response = adservice.addMoneyToWalletFromCard(100000000, "7799208319123456", "7799208319", 963);
			Assertions.assertEquals("UNSUCCESSFUL TRANSACTION ERR------->Insufficient Balance", response);
			
	    }
	@Test
	 public void testAddMoneyToWallet_Negative1() throws Exception
	    {
		String response = adservice.addMoneyToWalletFromCard(10, "7799208319123456", "7799208319", 962);
			Assertions.assertEquals("UNSUCCESSFUL TRANSACTION ERR-------> INCORRECT CVV", response  );
			
	    }

	@Test
    public void testAddMoneyByUpi_Positive() throws Exception
    {
		String response =adservice.addMoneyToWalletFromUPI(10, "7799208319@upi", "7799208319",8521);
		Assertions.assertEquals("TRANSACTION SUCCESS ------->Money Added Sucessfully", response);
		
    }
	@Test
	 public void testAddMoneyByUpi_Negative() throws Exception
	    {
		String response =adservice.addMoneyToWalletFromUPI(10, "7799208319@upi", "7799208319",8523);
			Assertions.assertEquals("UNSUCCESSFUL TRANSACTION ERR-------> INCORRECT PIN", response);
			
	    }
	@Test
	 public void testAddMoneyByUpi_Negative1() throws Exception
	    {
		String response =adservice.addMoneyToWalletFromUPI(1000000, "7799208319@upi", "7799208319",8521);
			Assertions.assertEquals("UNSUCCESSFUL TRANSACTION ERR------->Insufficient Balance", response);
			
	    }

}
