package com.capgemini.wallet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.wallet.entities.Transaction;

public interface TransactionsDAO extends JpaRepository<Transaction,Integer>{
	

}
