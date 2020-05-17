package com.cg.sprint2.carddetails.ms.testservice;

import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;

import com.cg.sprint2.carddetails.ms.dto.CardDetails;
import com.cg.sprint2.carddetails.ms.service.CardDetailsService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestService {
	@Autowired
	CardDetailsService cdservice;

	@Test
	public void testAddCard_Positive() throws Exception {
		CardDetails cd = new CardDetails("Babu", "8074666140", "8074666140123456", "Master", 852, LocalDate.now(),
				50000, "8074666140@upi", 8741);
		CardDetails u = cdservice.addcard(cd);
		Assertions.assertEquals(u.getCardholdername(), cd.getCardholdername());

	}
	
	  @Test public void testGetCardNo_Positive() throws Exception {
	  Optional<CardDetails> response  =cdservice.getCarddetailsByMobileno("7799208319");
	  Assertions.assertEquals("7799208319123456", response.get().getCardno());
	  
	  }
	  
	  @Test public void testShowAccBalance_Positive() throws Exception { 
	  CardDetails u = new CardDetails(); u.setCardbalance(51927);
	  ResponseEntity<String> response =cdservice.showAccountBalanceOfUser("7799208319");
	  Assertions.assertEquals("ACCOUNT BALANCE-----"+u.getCardbalance()+"    ₹" , response.getBody());
	  
	  }
	  
	  @Test public void testShowAccBalance_Negative() throws Exception { 
		  ResponseEntity<String> response =cdservice.showAccountBalanceOfUser("7799228319");
	  Assertions.assertEquals("---Please Add a Card---- ", response.getBody());
	  
	  }
	  
	 

}
