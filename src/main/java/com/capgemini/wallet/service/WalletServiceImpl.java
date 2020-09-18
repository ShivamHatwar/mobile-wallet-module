package com.capgemini.wallet.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.capgemini.wallet.dao.*;
import com.capgemini.wallet.bean.EditUserDetails;
import com.capgemini.wallet.entities.*;
import com.capgemini.wallet.utility.GlobalResources;

@Service
public class WalletServiceImpl implements IWalletService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private CardsDAO cardsDAO;

	private Logger logger = GlobalResources.getLogger(WalletServiceImpl.class);

	UserInfo userInfo;


	@Override
	public Boolean changePassword(String mobile, String oldPassword, String newPassword) {
		try {

			System.out.println("try");
			UserInfo user = userDAO.findByMobile(mobile);
			System.out.println(user);
			System.out.println();
			if (user.getMobile().equals(mobile)) {
				if (BCrypt.checkpw(oldPassword, user.getPassword())) {
					System.out.println(user.getPassword());

					// if(user.getPassword().equals(oldPassword))

					String hashNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
					user.setPassword(hashNewPassword);
					userDAO.save(user);
					logger.info("Password changed for " + user.getUsername());
					return true;

				}
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage());
			throw exception;
		}
		logger.error("Incorrect current Password");
		return false;
	}

	@Override
	public String forgotPassword(String mobile, String securityQ, String securityA) {
		try {
			UserInfo user = userDAO.findByMobile(mobile);
			if (user.getSecurityQ().equals(securityQ) && user.getSecurityA().equals(securityA)) {

				String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

				StringBuilder sb = new StringBuilder(8);

				for (int i = 0; i <= 8; i++) {

					int index = (int) (AlphaNumericString.length() * Math.random());

					// add Character one by one in end of sb
					sb.append(AlphaNumericString.charAt(index));
				}

				String randomPass = sb.toString();
				user.setPassword(BCrypt.hashpw(randomPass, BCrypt.gensalt(12)));
				userDAO.save(user);
				logger.info("Random Password generated");
				return randomPass;
			} else {
				logger.error("Invalid securityQuestion/Answer");
				return "invalid";
			}
		} catch (Exception exception) {
			logger.error("User doesn't exist");
			throw exception;
		}
	}

	@Override
	public String forgotPin(String mobile, String Password) {
		UserInfo user = userDAO.findByMobile(mobile);

		if (BCrypt.checkpw(Password, user.getPassword())) {
			int randomPIN = (int) (Math.random() * 9000) + 1000;
			String val = "" + randomPIN;

			user.setPin(BCrypt.hashpw(val, BCrypt.gensalt(12)));

			return "Your new Pin is " + val;
		} else {
			return "Enter Correct Password";
		}
	}

	@Override
	public EditUserDetails getUserDetails(String mobile) {
		logger.info("In WalletServiceImpl at function getUserDetails");

		if (userDAO.existsByMobile(mobile)) {
			EditUserDetails editUserDetails = new EditUserDetails();
			UserInfo user = userDAO.findByMobile(mobile);
			logger.info("User details fetched of " + user.getFirstName());
			
			editUserDetails.setFirstName(user.getFirstName());
			editUserDetails.setLastName(user.getLastName());
			editUserDetails.setSecurityA(user.getSecurityA());
			editUserDetails.setSecurityQ(user.getSecurityQ());
			editUserDetails.setUsername(user.getUsername());
			return editUserDetails;
		}
		logger.error("User doesn't exist");
		return null;
	}

	@Override
	public String editUser(UserInfo user) {
		logger.info("In WalletServiceImpl at function editUser");
		userDAO.save(user);
		logger.info("User Updated - " + user.getFirstName());
		return "updated successfully";
	}

	

}