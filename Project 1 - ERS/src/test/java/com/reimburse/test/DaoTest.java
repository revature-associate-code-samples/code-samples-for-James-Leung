package com.reimburse.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.reimburse.dao.ReimburseDao;
import com.reimburse.dao.UserDao;
import com.reimburse.pojos.Reimbursement;
import com.reimburse.pojos.User;

public class DaoTest {
	
	public static ReimburseDao rDao = new ReimburseDao();
	public static UserDao uDao = new UserDao();
	
	@Test
	public void findAllUsers() {
		
		List<User> u = uDao.findAll();
		
		assertNotNull(u);
		
	}
	
	@Test
	public void findAUser() {
		
		User u = uDao.findById(1);
		assertNotNull(u);
		
	}
	
	@Test
	public void findOrderedReims() {
		
		List<Reimbursement> r = rDao.findReims(1);
		assertNotNull(r);
		
	}
	
	@Test
	public void findAReim() {
		
		Reimbursement r = rDao.findById(91);
		assertNotNull(r);
		
	}
	
	@Test
	public void findUser() {
		
		User u = uDao.findByUser("johndoe1");
		assertNotNull(u);
		
	}
	

}
