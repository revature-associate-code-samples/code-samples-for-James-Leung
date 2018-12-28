package com.banking.pojos;

public class BankAccounts {
	 
	 private int id;
	 private double balance;
	 private int accountTypeId;
	 private int accountOwner;
	 
	 public BankAccounts() {}
	 
	 public BankAccounts(int accountTypeId, int accountOwner, double balance ) {
		 
		 super();
		 this.accountTypeId = accountTypeId;
		 this.accountOwner = accountOwner;
		 this.balance = balance;
		 
	 }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccountType() {
		return accountTypeId;
	}

	public void setAccountType(int accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public int getAccountOwner() {
		return accountOwner;
	}

	public void setAccountOwner(int accountOwner) {
		this.accountOwner = accountOwner;
	}
	 

}
