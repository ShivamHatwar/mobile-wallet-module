package com.capgemini.wallet.entities;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

@Entity
@Table(name="Details")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class UserInfo implements Serializable{


	private static final long serialVersionUID = 1L;
	@Id
	private String mobile;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String role;
	private String status;
	private double balance;
	private long aadhar;
	private String securityQ;
	private String securityA;
	private String pin;
	
	
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}

	@JsonBackReference(value="Transaction")
	@OneToMany(mappedBy="userInfo",cascade=CascadeType.ALL)
	private List<Transaction> transactions=new ArrayList<>();
	
	 @JsonBackReference(value="Cards")
	 @JsonIgnoreProperties
	@OneToMany(mappedBy="userInfo",cascade=CascadeType.ALL)
	private List<Cards> cards=new ArrayList<>();
	
	
	
	
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public List<Cards> getCards() {
		return cards;
	}
	public void setCards(List<Cards> cards) {
		this.cards = cards;
	}

	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public long getAadhar() {
		return aadhar;
	}
	public void setAadhar(long aadhar) {
		this.aadhar = aadhar;
	}
	public String getSecurityQ() {
		return securityQ;
	}
	public void setSecurityQ(String securityQ) {
		this.securityQ = securityQ;
	}
	public String getSecurityA() {
		return securityA;
	}
	public void setSecurityA(String securityA) {
		this.securityA = securityA;
	}
	public UserInfo()
	{
		super();
	}
	
	public UserInfo(String mobile, String firstName, String lastName, String username, String password, String role,
			String status,String pin, double balance, long aadhar, String securityQ, String securityA,
			List<Transaction> transactions, List<Cards> cards) {
		super();
		this.mobile = mobile;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.role = role;
		this.status = status;
		this.balance = balance;
		this.aadhar = aadhar;
		this.securityQ = securityQ;
		this.securityA = securityA;
		this.transactions = transactions;
		this.cards = cards;
		this.pin=pin;
	}
	
	public void addTransaction(Transaction transaction, String type,String statement,double amount) 
	{
		transaction.setUserInfo(this);
		transaction.setTimeOfTransaction((Calendar.getInstance().getTime()));
		transaction.setTransactionType(type);
		transaction.setStatement(statement);
		transaction.setAmount(amount);
		this.getTransactions().add(transaction);
	System.out.println("userinfo");
	}
	
}
