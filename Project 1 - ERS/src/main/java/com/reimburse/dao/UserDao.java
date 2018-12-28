package com.reimburse.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.reimburse.pojos.User;
import com.reimburse.util.ConnectionFactory;

import oracle.jdbc.OracleTypes;

public class UserDao implements Dao<User, Integer>{
	
	@Override
	public List<User> findAll() {

		List<User> users = new ArrayList<User>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "{ call GET_ALL_ERS_USERS(?) }";
			
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.execute();
			
			ResultSet rs = (ResultSet) cs.getObject(1);
			
			while(rs.next()) {
				
				User temp = new User();
				temp.setId(rs.getInt("ERS_USERS_ID"));
				temp.setUsername(rs.getString(2));
				temp.setPassword(rs.getString(3));
				temp.setFirstName(rs.getString(4));
				temp.setLastName(rs.getString(5));
				temp.setEmail(rs.getString(6));
				temp.setRole_id(rs.getInt(7));
				users.add(temp);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		return users;

	}

	@Override
	public User findById(Integer id) {
		
		User user = null;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERS_ID = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				user = new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setFirstName(rs.getString(4));
				user.setLastName(rs.getString(5));
				user.setEmail(rs.getString(6));
				user.setRole_id(rs.getInt(7));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return user;
	}
	
	public User findByUser(String usr) {
		
		User user = null;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERNAME = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  usr);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				user = new User();
				user.setId(rs.getInt(1));
				user.setUsername(usr);
				user.setPassword(rs.getString(3));
				user.setFirstName(rs.getString(4));
				user.setLastName(rs.getString(5));	
				user.setEmail(rs.getString(6));
				user.setRole_id(rs.getInt(7));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return user;
	}

	@Override
	public User save(User obj) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO ERS_USERS (ERS_USERNAME, ERS_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_ROLE_ID)"
					+ " VALUES(?, ?, ?, ?, ?, ?)";
			
			String[] keyNames = {"USER_ID"};
			
			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			ps.setString(1, obj.getUsername());
			ps.setString(2, obj.getPassword());
			ps.setString(3, obj.getFirstName());
			ps.setString(4, obj.getLastName());
			ps.setString(5, obj.getEmail());
			ps.setInt(6,  obj.getRole_id());
			
			
			int numRows = ps.executeUpdate();
			if(numRows > 0) {
				
				ResultSet pk = ps.getGeneratedKeys();
				
				while(pk.next()) {
					
					obj.setId(pk.getInt(1));
					
				}
				
				conn.commit();
				
			}	
			
		} catch (SQLException e) {

			e.printStackTrace();
		
		}
		
		return obj;

	}

	@Override
	public User update(User obj) {
	
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "UPDATE ERS_USERS SET ERS_USERNAME = ?, SET ERS_PASSWORD = ?, SET USER_FIRST_NAME = ?, SET USER_LAST_NAME = ?, SET USER_EMAIL = ?, SET USER_ROLE_ID = ? WHERE ERS_USERS_ID = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  obj.getUsername());
			ps.setString(2, obj.getPassword());
			ps.setString(3,  obj.getFirstName());
			ps.setString(4, obj.getLastName());
			ps.setInt(5, obj.getId());
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return null;
	}

	@Override
	public void delete(User obj) {
		// TODO Auto-generated method stub
		
	}
    
}
