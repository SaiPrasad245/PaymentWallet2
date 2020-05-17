package com.cg.sprint2.carddetails.ms.controller;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cg.sprint2.carddetails.ms.dto.CardDetails;
import com.cg.sprint2.carddetails.ms.service.CardDetailsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CardDetailsController {

	@Autowired

	CardDetailsService cservice;

	// Add Card to the User
	@PostMapping(value = "/addcard/{mobileno}", consumes = "application/json")
	public String addNewCard(@RequestBody CardDetails cdetails, @PathVariable String mobileno) {
		return cservice.addNewCard(cdetails, mobileno);
	}

	// Get All Added Cards By Mobileno
	@GetMapping(value = "/showAllCards/{mobileno}", produces = "application/json")
		public String showAllCardsByMobileno(@PathVariable String mobileno)
	{
			try
			{
				Optional<CardDetails> cadDetailsList = cservice.getCarddetailsByMobileno(mobileno);
			return cadDetailsList.get().getCardno();
			}
			catch(Exception ex)
			{
				return  "NO CARD AVAILABLE --- ADD A CARD";
			}
    }
	// Show All Cards
	/*
	 * @GetMapping(value="/showAllCards/", produces = "application/json") public
	 * List<CardDetails> showAllCards() { List<CardDetails> cadDetailsList
	 * =cservice.getCarddetails(); return cadDetailsList; }
	 */
	// Show Card Balance
	@GetMapping(value="/showAccBalance/{mobileno}", produces="application/json")
	public ResponseEntity<String> showAccountBalanceOfUser(@PathVariable String mobileno)
	{
		return  cservice.showAccountBalanceOfUser(mobileno);
		
		
	}
	// GeUpi
	@GetMapping(value="/getUpi/{mobileno}",produces="application/json")
	public  StringBuilder getUpi(@PathVariable String mobileno)
	{
		StringBuilder s = cservice.geUpi(mobileno);
		StringBuilder c = s.delete(10, 14);
		return c;
		
	}
	
}
