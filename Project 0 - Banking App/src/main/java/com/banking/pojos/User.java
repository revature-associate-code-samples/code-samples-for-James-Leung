package com.banking.pojos;

public class User {

	private int id;
	private String firstName; //first name
	private String lastName;  //last name
	private String username;  //username
	private String password;  //password
	
	//User u = new User()
	public User() {}
	
	//User u = new User("John", "Doe", "jdoe", "password", "john.doe@gmail.com", 913.133.21);
	public User(String firstName, String lastName, String username, String password) {
	
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	
}
