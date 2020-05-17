package com.cg.sprint2.transactionhistory.ms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TransactionHistoryApplicationTests {
	@Autowired
	TestRestTemplate testRestTemplate;

	public void setTestRestTemplate(TestRestTemplate testRestTemplate) {
		this.testRestTemplate = testRestTemplate;
	}
	@LocalServerPort 
	int  localServerPort;
	
	@Test
    public void testSentTransactions() throws Exception
    {
		String url="http://localhost:"+localServerPort+"showSentTransactions/93998165142";
		ResponseEntity<String> response =testRestTemplate.getForEntity(url,String.class);
		Assertions.assertEquals(200, response.getStatusCodeValue());
		
    }
	@Test
	 public void testRecievedTransactions() throws Exception
	    {
			String url="http://localhost:"+localServerPort+"showRecievedTransactions/93998165142";
			ResponseEntity<String> response =testRestTemplate.getForEntity(url,String.class);
			Assertions.assertEquals(200, response.getStatusCodeValue());
			
	    }
	@Test
	 public void testAllTransactions() throws Exception
	    {
			String url="http://localhost:"+localServerPort+"showAllTransactions/05-05-2020/20-05-2020/93998165142";
			ResponseEntity<String> response =testRestTemplate.getForEntity(url,String.class);
			Assertions.assertEquals(200, response.getStatusCodeValue());
			
	    }
}
