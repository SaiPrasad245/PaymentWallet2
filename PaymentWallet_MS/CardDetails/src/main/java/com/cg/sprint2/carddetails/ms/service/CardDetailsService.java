package com.cg.sprint2.carddetails.ms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.sprint2.carddetails.ms.dao.CardDetailsDAO;
import com.cg.sprint2.carddetails.ms.dto.CardDetails;

@Service
public class CardDetailsService {

	@Autowired
	CardDetailsDAO cdao;

	public void setCdao(CardDetailsDAO cdao) {
		this.cdao = cdao;
	}

	// Adding Card
	public CardDetails addcard(CardDetails card) {
		String upi="@upi";
		card.setUpiid(card.getUpiid().concat(upi));
		return cdao.save(card);
	}
	// Adding  Existing Card
		public CardDetails addecard(CardDetails card) {
			return cdao.save(card);
		}
	
	// Show All Cards
	@Transactional(readOnly = true)
	public List<CardDetails> getCarddetails() {
		return cdao.findAll();
		
			
	}

	// Show All Cards By Mobileno
	@Transactional(readOnly = true)
	public Optional<CardDetails> getCarddetailsByMobileno(String mobileno) {
		List<String> mb = new ArrayList<>();
		mb.add(mobileno);
		return cdao.findById(mobileno);
		
	}

	// Adding Card To The User
	public String addNewCard(CardDetails cdetails, String mobileno) {
		cdetails.setMobile_no(mobileno);
		List<CardDetails> cd=getCarddetails();
		try
		{
		for (CardDetails cdet : cd) 
		{
			if((cdet.getCardno()==cdetails.getCardno()))
			{
				cdetails.setUpiid(cdet.getUpiid());
				cdetails.setCardbalance(cdet.getCardbalance());
				cdetails.setUpipin(cdet.getUpipin());
				addecard(cdetails);
				return  "REQUEST ACCEPTED----->Card Added";
				
			}
		}
		addcard(cdetails);
		return "REQUEST ACCEPTED----->Card Added";
	}
	catch(Exception ex)
	{
		 return "REQUEST FAILED-------> Crad Not Added";
	}
	}
	// Show Card Balance
	public ResponseEntity<String> showAccountBalanceOfUser(String mobileno)
	{
		try{
			CardDetails u =cdao.findById(mobileno).get();
		return new ResponseEntity<String>("ACCOUNT BALANCE-----"+u.getCardbalance()+"    ₹",HttpStatus.OK);
		}
		catch(Exception ex)
		{
			return new ResponseEntity<String>("---Please Add a Card---- ",HttpStatus.OK);
		}
		
	}
	// Get UPI ID
	public StringBuilder geUpi(String mobileno)
	{
		try {
		CardDetails u =cdao.findById(mobileno).get();
	   StringBuilder c = new StringBuilder(u.getUpiid());
	    return c ;
		     
	}
		catch(Exception ex)
		{
			String s="";
			StringBuilder c=new StringBuilder(s);
			return c;}
		}
	
}