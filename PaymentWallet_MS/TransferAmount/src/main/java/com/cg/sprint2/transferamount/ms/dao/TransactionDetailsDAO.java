package com.cg.sprint2.transferamount.ms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.sprint2.transferamount.ms.dto.TransactionDetails;

public interface TransactionDetailsDAO extends JpaRepository<TransactionDetails,Integer> {

}
