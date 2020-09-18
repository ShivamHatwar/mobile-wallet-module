package com.capgemini.wallet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.wallet.entities.Cards;

public interface CardsDAO extends JpaRepository<Cards,Long>{
	

}
