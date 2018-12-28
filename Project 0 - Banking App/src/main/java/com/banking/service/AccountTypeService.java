package com.banking.service;

import java.util.List;

import com.banking.dao.AccountTypesDao;
import com.banking.pojos.AccountTypes;


public class AccountTypeService {
	
	static AccountTypesDao accType = new AccountTypesDao();
	
	public List<AccountTypes> findAllAccountsT() {
		
		return accType.findAll();
		
	}
	
	public AccountTypes findAccountT(int id) {
		
		return accType.findById(id);
		
	}
	
	public AccountTypes saveAccountT(AccountTypes obj) {
		
		return accType.save(obj);
		
	}
	
	public AccountTypes updateAccountT(AccountTypes obj) {
		
		return accType.update(obj);
		
	}
	

}
