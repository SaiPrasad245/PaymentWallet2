package com.cg.sprint2.showbalance.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.cg.sprint2.showbalance.ms.service.ShowBalanceService;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ShowBalanceController {

	@Autowired
	 ShowBalanceService sservice;

	public void setSservice(ShowBalanceService sservice) {
		this.sservice = sservice;
	}
	// Show Wallet Balance of the user
	@GetMapping(value="/walletBalance/{mobileno}")
	public double showWalletBalanceOfUser(@PathVariable String mobileno)
	{
		double balance=sservice.showWalletBalanceOfUser(mobileno);
		return balance;
	}
	
}

