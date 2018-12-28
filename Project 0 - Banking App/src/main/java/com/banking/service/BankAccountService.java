package com.banking.service;

import java.util.List;

import com.banking.dao.BankAccountsDao;
import com.banking.pojos.BankAccounts;

public class BankAccountService {
	
	static BankAccountsDao bankAcc = new BankAccountsDao();
	
	public List<BankAccounts> findAllBankAcc() {
		
		return bankAcc.findAll();
		
	}
	
	public BankAccounts findBankAcc(int id) {
		
		return bankAcc.findById(id);
		
	}
	
	public BankAccounts saveBankAcc(BankAccounts obj) {
		
		return bankAcc.save(obj);
		
	}
	
	public BankAccounts updateBankAcc(BankAccounts obj) {

		return bankAcc.update(obj);
		
	}

}
