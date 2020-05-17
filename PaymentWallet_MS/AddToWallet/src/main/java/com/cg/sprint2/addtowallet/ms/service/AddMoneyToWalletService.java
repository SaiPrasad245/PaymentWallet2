package com.cg.sprint2.addtowallet.ms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.sprint2.addtowallet.ms.dao.AddMoneyToWalletDAO;
import com.cg.sprint2.addtowallet.ms.dto.CardDetails;

@Service
public class AddMoneyToWalletService {
	@Autowired

	AddMoneyToWalletDAO amdao;

	public void setAmdao(AddMoneyToWalletDAO amdao) {
		this.amdao = amdao;
	}

	// Show All Cards
	@Transactional(readOnly = true)
	public List<CardDetails> getCarddetailsByMobileno(String mobileno) {
		List<String> mb = new ArrayList<>();
		mb.add(mobileno);
		return amdao.findAllById(mb);
	}

	// Add Money To Wallet from card QUERY
	public void addMoney(double add, String mobileno) {
		amdao.addToWallet(add, mobileno);
	}

	// Deducting Amount From Card QUERY
	public void dedcutBalance(double deduct, String cdno) {
		amdao.deductBalance(deduct, cdno);
	}

	// adding money from wallet to card and deducting amount from card operation
	public String addMoneyToWalletFromCard(double amt, String cdno, String mobileno, int cvv) {
		List<CardDetails> cd = getCarddetailsByMobileno(mobileno);

		for (CardDetails cardDetails : cd) {
			if (cvv == cardDetails.getCvv()) {
				if (amt < cardDetails.getCardbalance()) {
					dedcutBalance(amt, cdno);
					addMoney(amt, mobileno);
					return  "Money Added Sucessfully";
				} else
					return "UNSUCCESSFUL TRANSACTION ERR------->Insufficient Balance";
			} else
				return  "UNSUCCESSFUL TRANSACTION ERR-------> INCORRECT CVV";
		}
		return "UNSUCCESSFUL TRANSACTION ERR-------> BAD REQUEST" ;
	}

	// Set UPI PIN
	public ResponseEntity<String> setUpi(int pin, String mobileno) {
		Optional<CardDetails> cd = amdao.findById(mobileno);
		String upiid = cd.get().getUpiid();
		amdao.setUpi(pin, upiid);
		String smg = "UPI PIN SET SUCCESSFULLY";
		return new ResponseEntity<String>(smg, HttpStatus.OK);
	}

	// Adding Money through UPI
	public String addMoneyToWalletFromUPI(double amt, String upiid, String mobileno, int pin) {
		Optional<CardDetails> cd = amdao.findById(mobileno);
		String cdno = cd.get().getCardno();
		if (pin != cd.get().getUpipin()) {
			return  "UNSUCCESSFUL TRANSACTION ERR-------> INCORRECT PIN";
		    }
		else {
			if (amt < cd.get().getCardbalance()) {
				dedcutBalance(amt, cdno);
				addMoney(amt, mobileno);
				return  "TRANSACTION SUCCESS ------->Money Added Sucessfully";
			} else {
			return  "UNSUCCESSFUL TRANSACTION ERR------->Insufficient Balance";
			}
		}

	}
}
