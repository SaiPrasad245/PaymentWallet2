package com.cg.sprint2.showbalance.ms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sprint2.showbalance.ms.dao.ShowBalanceDAO;
import com.cg.sprint2.showbalance.ms.dto.User;


@Service
public class ShowBalanceService {
	
	@Autowired
	ShowBalanceDAO sdao;

	public void setSdao(ShowBalanceDAO sdao) {
		this.sdao = sdao;
	}
	
	// Show Wallet Balance
	
	public double showWalletBalanceOfUser(String mobileno)
	{
		User u =sdao.findById(mobileno).get();
		return u.getWalletbalance();
		
	}

	
	
	
	

}
