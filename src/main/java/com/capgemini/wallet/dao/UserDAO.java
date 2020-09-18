package com.capgemini.wallet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.wallet.entities.UserInfo;

public interface UserDAO extends JpaRepository<UserInfo,String>{
	
	UserInfo findByUsername(String username);
	
	UserInfo findByMobile(String mobile);

	boolean existsByUsername(String username);
	boolean existsByAadhar(long aadhar);
	boolean existsByMobile(String mobile);
}
