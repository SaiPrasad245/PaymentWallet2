package com.cg.sprint2.transactionhistory.ms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.cg.sprint2.transactionhistory.ms.dto.TransactionHistory;
import com.cg.sprint2.transactionhistory.ms.service.TransactionHistoryService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionHistoryController {
	
	@Autowired
	TransactionHistoryService thservice;
	public void setThservice(TransactionHistoryService thservice) {
		this.thservice = thservice;
	}
	// All Transactions Between the Dates
	@GetMapping(value="/showAllTransactions/{fromdate}/{todate}/{smobileno}",produces="application/json")
	public ResponseEntity<List<TransactionHistory>> showAllTransactionBetweenDates(@PathVariable String fromdate,@PathVariable String todate,@PathVariable String smobileno) throws ParseException
	{
	
		Date fdate = new SimpleDateFormat("yyyy-MM-dd").parse(fromdate);
		Date tdate = new SimpleDateFormat("yyyy-MM-dd").parse(todate);
		List<TransactionHistory> transactions=thservice.showAllTransactionsBetweenDates(fdate, tdate, smobileno,smobileno);
		return new ResponseEntity<List<TransactionHistory>>(transactions,HttpStatus.OK);
	}
	// Sent Transactions
	@GetMapping(value="/showSentTransactions/{smobileno}",produces="application/json")
	public ResponseEntity<List<TransactionHistory>> showSentTransactions(@PathVariable String smobileno)
	{
		List<TransactionHistory> transactions=thservice.showSentTransaction(smobileno);
		return new ResponseEntity<List<TransactionHistory>>(transactions,HttpStatus.OK);
	}
	// Received Transactions
		@GetMapping(value="/showRecievedTransactions/{rmobileno}",produces="application/json")
		public ResponseEntity<List<TransactionHistory>> showRecievedTransactions(@PathVariable String rmobileno)
		{
			List<TransactionHistory> transactions=thservice.showRecievedTransaction(rmobileno);
			return new ResponseEntity<List<TransactionHistory>>(transactions,HttpStatus.OK);
		}
}