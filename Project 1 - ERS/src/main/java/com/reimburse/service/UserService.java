package com.reimburse.service;

import org.apache.log4j.Logger;

import com.reimburse.dao.UserDao;
import com.reimburse.pojos.User;

public class UserService {
	
	static UserDao userD = new UserDao();
	
	static Logger log = Logger.getLogger(UserService.class);
	
	public User validateUser(String user, String password) {

		User u = userD.findByUser(user);
		
		if(u.getPassword().equals(password)) {
			
			return u;
			
		}
	
		return null;
		
	}

}
