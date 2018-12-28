package com.banking.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.banking.dao.AccountTypesDao;
import com.banking.dao.BankAccountsDao;
import com.banking.dao.UserDao;
import com.banking.pojos.AccountTypes;
import com.banking.pojos.BankAccounts;
import com.banking.pojos.User;

public class DaoTests {
	
	public static AccountTypesDao accTyDao = new AccountTypesDao();
	public static BankAccountsDao bAccDao = new BankAccountsDao();
	public static UserDao uDao = new UserDao();

	@Test
	public void findAllAccounts() {
		
		List<AccountTypes> accounts = accTyDao.findAll();
		
		assertNotNull(accounts);
		
	}
	
	@Test
	public void findAccount() {
		
		AccountTypes account = accTyDao.findById(1);
		
		assertNotNull(account);
		
	}
	
	@Test
	public void findAllBankAccounts() {
		
		List<BankAccounts> bAcc = bAccDao.findAll();
		
		assertNotNull(bAcc);
		
	}
	
	@Test
	public void findBankAccount() {
		
		BankAccounts bAcc = bAccDao.findById(1);
		
		assertNotNull(bAcc);
		
	}
	
	@Test
	public void findAllUsers() {
		
		List<User> u = uDao.findAll();
		
		assertNotNull(u);
		
	}
	
	@Test
	public void findUser() {
		
		User u = uDao.findById(1);
		
		assertNotNull(u);
		
	}
	
	
	

}
