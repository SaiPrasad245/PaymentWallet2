package com.cg.sprint2.addtowallet.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cg.sprint2.addtowallet.ms.service.AddMoneyToWalletService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AddMoneyToWalletController {

	@Autowired
	AddMoneyToWalletService amservice;

	public void setAmservice(AddMoneyToWalletService amservice) {
		this.amservice = amservice;
	}

	// Add Money To Wallet
	@GetMapping(value = "/addMoney/{mobileno}/{cdno}/{cvv}/{amt}", produces = "application/json")
	public String addMoneyToWallet(@PathVariable double amt, @PathVariable String cdno, @PathVariable String mobileno, @PathVariable int cvv) {
		return amservice.addMoneyToWalletFromCard(amt, cdno, mobileno,cvv);
		
	}
	// Set UPI PIn
	@GetMapping(value="/setUpi/{mobileno}/{pin}",produces="application/json")
		public ResponseEntity<String> setUpi(@PathVariable String mobileno,@PathVariable int pin)
		{
		return amservice.setUpi(pin, mobileno);
	     }
	// Add Money To Wallet through UPI
		@GetMapping(value = "/addMoneyByUpi/{mobileno}/{upiid}/{pin}/{amt}", produces = "application/json")
		public String addMoneyToWalletByUpi(@PathVariable double amt, @PathVariable String upiid, @PathVariable String mobileno, @PathVariable int pin) {
			return amservice.addMoneyToWalletFromUPI(amt, upiid, mobileno, pin);
			
		}
}
