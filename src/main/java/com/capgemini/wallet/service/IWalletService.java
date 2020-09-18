package com.capgemini.wallet.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.capgemini.wallet.bean.EditUserDetails;
import com.capgemini.wallet.entities.*;


public interface IWalletService {
	

	public Boolean changePassword(String mobile, String oldPassword, String newPassword);

	public String forgotPassword(String mobile, String securityQ, String securityA);
	
	public String forgotPin(String mobile, String Password);

	public EditUserDetails getUserDetails(String mobile);

	public String editUser(UserInfo user);

	
	
	
}
