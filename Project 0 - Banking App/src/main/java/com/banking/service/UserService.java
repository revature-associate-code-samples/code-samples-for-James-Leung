package com.banking.service;

import java.util.List;

import com.banking.dao.UserDao;
import com.banking.pojos.User;

public class UserService {
	
	static UserDao user = new UserDao();
	
	public List<User> findAllUsers() {
		
		return user.findAll();
		
	}
	
	public User findUser(int id) {
		
		return user.findById(id);
		
	}
	
	public User saveUser(User obj) {
		
		return user.save(obj);
		
	}
	
	public User updateUser(User obj) {
		
		return user.update(obj);
	
	}
	

}
