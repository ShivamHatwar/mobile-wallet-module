package com.capgemini.wallet.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.wallet.bean.EditUserDetails;
import com.capgemini.wallet.bean.ForgotPassRequest;
import com.capgemini.wallet.entities.*;
import com.capgemini.wallet.service.IWalletService;
import com.capgemini.wallet.utility.GlobalResources;

@RestController
@RequestMapping("/")
@CrossOrigin(origins="*")
public class WalletController {

	
	@Autowired
	private IWalletService service;
	
	

	private Logger logger=GlobalResources.getLogger(WalletController.class);
	

	@PutMapping("changePassword/{mobile}/{oldPassword}/{newPassword}")
	public ResponseEntity<Boolean> checkPassword(@PathVariable("mobile") String mobile,
			@PathVariable("oldPassword") String oldPassword, @PathVariable("newPassword") String newPassword)
			throws Exception {
		logger.info("In WalletController at function changePassword");
		return new ResponseEntity<Boolean>(service.changePassword(mobile, oldPassword, newPassword), HttpStatus.OK);

	}

	@PutMapping("/forgotPassword")
	public String forgotPassword(@RequestBody ForgotPassRequest forgotpPassRequest) throws Exception {
		logger.info("In WalletController at function forgotPassword");
		return service.forgotPassword(forgotpPassRequest.getMobile(), forgotpPassRequest.getSecurityQuestion(), forgotpPassRequest.getSecurityAnswer());
	}
	
	@PutMapping("/forgotPin/{mobile}/{password}")
	public String forgotPin(@PathVariable("mobile") String mobile
			,@PathVariable("password") String password) throws Exception {
		logger.info("In WalletController at function forgotPassword");
		return service.forgotPin(mobile,password);
	}

	@GetMapping(value = "/getUserDetails{mobile}")
	public ResponseEntity<EditUserDetails> getUserDetails(@PathVariable("mobile")String mobile) {

		return new ResponseEntity<EditUserDetails>(service.getUserDetails(mobile), HttpStatus.OK);
	}

	@PutMapping(value = "/editUser")
	public String editUser(@RequestBody UserInfo user) {
		logger.info("In WalletController at function editUser");
		return service.editUser(user);
	}
	
	
	
}
	

