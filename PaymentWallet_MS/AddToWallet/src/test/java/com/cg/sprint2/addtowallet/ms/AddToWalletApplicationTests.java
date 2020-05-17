package com.cg.sprint2.addtowallet.ms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
class AddToWalletApplicationTests {

		@Autowired
		TestRestTemplate testRestTemplate;

		public void setTestRestTemplate(TestRestTemplate testRestTemplate) {
			this.testRestTemplate = testRestTemplate;
		}
		@LocalServerPort 
		int  localServerPort;
		
		@Test
	    public void testAddMoneyToWallet_Positive() throws Exception
	    {
			String url="http://localhost:"+localServerPort+"addMoney/7799208319/7799208319123456/963/10";
			ResponseEntity<String> response =testRestTemplate.getForEntity(url,String.class);
			Assertions.assertEquals("Money Added Sucessfully", response.getBody());
			
	    }
		@Test
		 public void testAddMoneyToWallet_Negative() throws Exception
		    {
				String url="http://localhost:"+localServerPort+"addMoney/7799208319/7799208319123456/963/100000";
				ResponseEntity<String> response =testRestTemplate.getForEntity(url,String.class);
				Assertions.assertEquals("UNSUCCESSFUL TRANSACTION ERR------->Insufficient Balance", response.getBody());
				
		    }
		@Test
		 public void testAddMoneyToWallet_Negative1() throws Exception
		    {
				String url="http://localhost:"+localServerPort+"addMoney/7799208319/7799208319123456/962/10";
				ResponseEntity<String> response =testRestTemplate.getForEntity(url,String.class);
				Assertions.assertEquals("UNSUCCESSFUL TRANSACTION ERR-------> INCORRECT CVV", response.getBody());
				
		    }
 

		@Test
	    public void testAddMoneyByUpi_Positive() throws Exception
	    {
			String url="http://localhost:"+localServerPort+"addMoneyByUpi/7799208319/7799208319@upi/8521/10";
			ResponseEntity<String> response =testRestTemplate.getForEntity(url,String.class);
			Assertions.assertEquals("TRANSACTION SUCCESS ------->Money Added Sucessfully", response.getBody());
			
	    }
		@Test
		 public void testAddMoneyByUpi_Negative() throws Exception
		    {
				String url="http://localhost:"+localServerPort+"addMoneyByUpi/7799208319/7799208319@upi/963/100";
				ResponseEntity<String> response =testRestTemplate.getForEntity(url,String.class);
				Assertions.assertEquals("UNSUCCESSFUL TRANSACTION ERR-------> INCORRECT PIN", response.getBody());
				
		    }
		@Test
		 public void testAddMoneyByUpi_Negative1() throws Exception
		    {
				String url="http://localhost:"+localServerPort+"addMoneyByUpi/7799208319/77992083191@upi/8521/1000000";
				ResponseEntity<String> response =testRestTemplate.getForEntity(url,String.class);
				Assertions.assertEquals("UNSUCCESSFUL TRANSACTION ERR------->Insufficient Balance", response.getBody());
				
		    }

}
