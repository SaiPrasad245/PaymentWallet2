package com.cg.sprint2.showbalance.ms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ShowBalanceApplicationTests {

	
	@Autowired
	TestRestTemplate testRestTemplate;

	public void setTestRestTemplate(TestRestTemplate testRestTemplate) {
		this.testRestTemplate = testRestTemplate;
	}
	@LocalServerPort 
	int  localServerPort;
	
	@Test
    public void testShowBalance_Positive() throws Exception
    {
		String url="http://localhost:"+localServerPort+"walletBalance/9398165142";
		ResponseEntity<Double> response =testRestTemplate.getForEntity(url,Double.class);
		System.out.println(response);
		Assertions.assertEquals(4475.0, response.getBody());
		
    }
	@Test
    public void testShowBalance_Negative() throws Exception
    {
		String url="http://localhost:"+localServerPort+"walletBalance/7799208319";
		ResponseEntity<Double> response =testRestTemplate.getForEntity(url,Double.class);
		System.out.println(response);
		Assertions.assertNotEquals(0, response.getBody());
		
    }
}
