package com.banking.pojos;

public class AccountTypes {
	 
	private int id;
	private String accountType;
	
	public AccountTypes () {}
	
	public AccountTypes (String accountType) {
		
		super();
		this.accountType = accountType;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	

}
