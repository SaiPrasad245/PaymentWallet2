package com.cg.sprint2.transferamount.ms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT )
class TransferAmountApplicationTests {
	@Autowired
	TestRestTemplate testRestTemplate;

	public void setTestRestTemplate(TestRestTemplate testRestTemplate) {
		this.testRestTemplate = testRestTemplate;
	}
	@LocalServerPort 
	int  localServerPort;
	
	@Test
    public void testTransferAmountToAnotherWallet_Positive() throws Exception
    {
		String url="http://localhost:"+localServerPort+"transferAmount/9398165142/7799208319/100";
		ResponseEntity<String> response =testRestTemplate.getForEntity(url,String.class);
		Assertions.assertEquals("Transaction Sucess---->Amount Transferred", response.getBody());
		
    }
	@Test
    public void testTransferAmountToAnotherWallet_Negative() throws Exception
    {
		String url="http://localhost:"+localServerPort+"transferAmount/9398165142/7799208319/10000";
		ResponseEntity<String> response =testRestTemplate.getForEntity(url,String.class);
		Assertions.assertEquals("Transaction Failed : Err --- Insufficient Balance", response.getBody());
		
    }
	@Test
    public void testTransferAmountToAnotherWallet_Negative1() throws Exception
    {
		String url="http://localhost:"+localServerPort+"transferAmount/9398165142/7799208329/100";
		ResponseEntity<String> response =testRestTemplate.getForEntity(url,String.class);
		Assertions.assertEquals("Transaction Failed : Err ---  The Number that the money is to be sent has to be Registerd on the Application", response.getBody());
		
    }
	@Test
    public void testUpiTransfer_Positive() throws Exception
    {
		String url="http://localhost:"+localServerPort+"upiTransfer/9398165142/7799208319/8521/100";
		ResponseEntity<String> response =testRestTemplate.getForEntity(url,String.class);
		Assertions.assertEquals("Transaction Sucess:Amount Transferred", response.getBody());
		
    }
	@Test
    public void testUpiTransfer_Negative() throws Exception
    {
		String url="http://localhost:"+localServerPort+"upiTransfer/9398165142/7799208319/8523/100";
		ResponseEntity<String> response =testRestTemplate.getForEntity(url,String.class);
		Assertions.assertEquals("Transaction Failed : Err ---> Incorrect PIN", response.getBody());
		
    }
	@Test
    public void testUpiTransfer_Negative2() throws Exception
    {
		String url="http://localhost:"+localServerPort+"upiTransfer/9398165142/7799208319/8521/1000000";
		ResponseEntity<String> response =testRestTemplate.getForEntity(url,String.class);
		Assertions.assertEquals("Transaction Failed : Err --->Insufficient Balance", response.getBody());
		
    }
       @Test
    public void testUpiTransfer_Negative3() throws Exception
    {
		String url="http://localhost:"+localServerPort+"upiTransfer/9398165142/7799208329/8521/100";
		ResponseEntity<String> response =testRestTemplate.getForEntity(url,String.class);
		Assertions.assertEquals("Transaction Failed : Err --->The Number that the money is to be sent has to be Registerd on the Application", response.getBody());
		
    }

}
