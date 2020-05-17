package com.cg.sprint2.carddetails.ms;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.cg.sprint2.carddetails.ms.dto.CardDetails;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CardDetailsApplicationTests {
	@Autowired
	TestRestTemplate testRestTemplate;

	public void setTestRestTemplate(TestRestTemplate testRestTemplate) {
		this.testRestTemplate = testRestTemplate;
	}
	@LocalServerPort 
	int  localServerPort;
	@Test
    public void testAddCard_Positive() throws Exception
    {
		String url="http://localhost:"+localServerPort+"addcard/8074666140";
		CardDetails cd = new CardDetails("Babu","8074666140","8074666140123456","Master",852,LocalDate.now(),50000,"8074666140@upi",8741);
		ResponseEntity<String> response =testRestTemplate.postForEntity(url,cd,String.class);
		Assertions.assertEquals("REQUEST ACCEPTED----->Card Added", response.getBody());
		
    }
	@Test
	 public void testAddCard_Negative() throws Exception
	    {
			String url="http://localhost:"+localServerPort+"addcard/8074666140";
			CardDetails cd = new CardDetails("Babu","8074666140","","Master",852,LocalDate.now(),50000,"8074666140@upi",8741);
			ResponseEntity<String> response =testRestTemplate.postForEntity(url,cd,String.class);
			Assertions.assertEquals("REQUEST FAILED-------> Crad Not Added", response.getBody());
			
	    }
	@Test
	 public void testGetCardNo_Positive() throws Exception
	    {
			String url="http://localhost:"+localServerPort+"showAllCards/7799208319";
			ResponseEntity<String> response =testRestTemplate.getForEntity(url,String.class);
			Assertions.assertEquals("7799208319123456", response.getBody());
			
	    }
	@Test
	 public void testGetCardNo_Negative() throws Exception
	    {
			String url="http://localhost:"+localServerPort+"showAllCards/7799208329";
			ResponseEntity<String> response =testRestTemplate.getForEntity(url,String.class);
			Assertions.assertEquals( "NO CARD AVAILABLE --- ADD A CARD", response.getBody());
			
	    }
	@Test
	 public void testShowAccBalance_Positive() throws Exception
	    {
			String url="http://localhost:"+localServerPort+"showAccBalance/7799208319";
			CardDetails u = new CardDetails();
	       u.setCardbalance(51927);
			ResponseEntity<String> response =testRestTemplate.getForEntity(url,String.class);
			Assertions.assertEquals("ACCOUNT BALANCE-----"+u.getCardbalance()+"    ₹" , response.getBody());
			
	    }
	@Test
	 public void testShowAccBalance_Negative() throws Exception
	    {
			String url="http://localhost:"+localServerPort+"showAccBalance/7799208369";
			ResponseEntity<String> response =testRestTemplate.getForEntity(url,String.class);
			Assertions.assertEquals("---Please Add a Card---- ", response.getBody());
			
	    }
}
