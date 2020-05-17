package com.cg.sprint2.transferamount.ms.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.sprint2.transferamount.ms.dao.CardDetailsDAO;
import com.cg.sprint2.transferamount.ms.dao.TransactionDetailsDAO;
import com.cg.sprint2.transferamount.ms.dao.TransferAmountDAO;
import com.cg.sprint2.transferamount.ms.dto.CardDetails;
import com.cg.sprint2.transferamount.ms.dto.TransactionDetails;
import com.cg.sprint2.transferamount.ms.dto.User;

@Service
public class TransferAmountService {

	@Autowired
	TransferAmountDAO tadao;
	
	@Autowired
	TransactionDetailsDAO thdao;
	@Autowired
	CardDetailsDAO cdao;

	public void setTadao(TransferAmountDAO tadao) {
		this.tadao = tadao;
	}
          // Transfer of Wallet Amount From one User To Another User
	public String transferAmountToAnotherWalletUser(String smobileno, String rmobileno, double amt) 
	{
		String status="Transaction Done Through Wallet";
		String msg="";
			Optional<User> u = tadao.findById(smobileno);
			try {
			if(tadao.findById(rmobileno).isPresent())
			{
				if(amt<u.get().getWalletbalance())
				{
					msg="Transaction Sucess---->Amount Transferred";
					tadao.deductFromSenderWallet(amt, smobileno);
					tadao.addToReciverWallet(amt, rmobileno);
					transactionDetails(smobileno, rmobileno, amt,status);
					return msg;
				}
				else {
				   msg="Transaction Failed : Err --- Insufficient Balance";
				   return msg;
			}
			}
			else
				msg="Transaction Failed : Err ---  The Number that the money is to be sent has to be Registerd on the Application";
			return msg;
	}
			catch(Exception ex)
			{
				 return "Transaction Failed:Err--------->The Number that the money is to be sent has to be Registerd on the Application";
			}}
	    // Saving the transaction in database
	public void transactionDetails(String smobileno,String rmobileno,double amt,String status)
	{
		
		
		Optional<User> sentuser = tadao.findById(smobileno);
		Optional<User> recieveduser = tadao.findById(rmobileno);
		 TransactionDetails th= new TransactionDetails();
		 th.setSender(sentuser.get().getName());
		 th.setReciever(recieveduser.get().getName());
		 th.setAmount(amt);
		 th.setSmobileno(smobileno);
		 th.setRmobileno(rmobileno);
		 LocalDate date = LocalDate.now();
	    	Date transdate = Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	    	th.setDateoftrans(transdate);
	     th.setStatus(status);
	    thdao.save(th);
	}
	// transfer Through UPI
	public String upiTransfer(String mobileno, String upimobileno, double amt,int pin) 
	{
		String status="Transaction Done Through UPI";
		String msg="";
		try {
			Optional<CardDetails> u = cdao.findById(mobileno);
			Optional<CardDetails> r = cdao.findById(upimobileno);
			String id =u.get().getUpiid();
			String id2 = r.get().getUpiid();
			if(cdao.findById(upimobileno).isPresent())
			{
			  if(pin==u.get().getUpipin()) 
			  {	
				if(amt<u.get().getCardbalance())
				{
					msg="Transaction Sucess:Amount Transferred";
					cdao.deductFromSenderUpi(amt, id);
				    cdao.addToReciverUpi(amt, id2);
					transactionDetails(mobileno,upimobileno, amt,status);
					return msg;
				}
				else {
				   msg="Transaction Failed : Err --->Insufficient Balance";
				   return msg;
			    }
			}
			else
			{
				msg="Transaction Failed : Err ---> Incorrect PIN";
			  return msg;}}
			else {
				msg="Transaction Failed : Err ---> The Recipients Doesn't Have a UPI FACILITY";
			return msg;
			}}
			catch(Exception ex)
			{
			 return "Transaction Failed : Err --->The Number that the money is to be sent has to be Registerd on the Application";
			}
	}	
	
}