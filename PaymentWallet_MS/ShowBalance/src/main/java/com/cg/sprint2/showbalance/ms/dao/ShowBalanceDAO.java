package com.cg.sprint2.showbalance.ms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.sprint2.showbalance.ms.dto.User;

@Repository
public interface ShowBalanceDAO extends JpaRepository<User, String> {

}
