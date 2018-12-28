package com.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.banking.pojos.AccountTypes;
import com.banking.util.ConnectionFactory;

public class AccountTypesDao implements Dao<AccountTypes, Integer> {

	@Override
	public List<AccountTypes> findAll() {
		
		List<AccountTypes> acc_types = new ArrayList<AccountTypes>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String query = "SELECT * FROM BK_ACCOUNT_TYPES ORDER BY TYPE_ID";
			
			Statement statement = conn.createStatement();
			
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				
				AccountTypes accT = new AccountTypes();
				accT.setId(rs.getInt("TYPE_ID"));
				accT.setAccountType(rs.getString(2));
				acc_types.add(accT);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return acc_types;
	}

	@Override
	public AccountTypes findById(Integer id) {
		
		AccountTypes at = null;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "SELECT * FROM BK_ACCOUNT_TYPES WHERE TYPE_ID = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				at = new AccountTypes();
				at.setId(rs.getInt(1));
				at.setAccountType(rs.getString(2));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return at;	
	}

	@Override
	public AccountTypes save(AccountTypes obj) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO BK_ACCOUNT_TYPES (ACC_TYPE) VALUES(?)";
			String[] keyNames = {""};
			
			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			ps.setString(1, obj.getAccountType());
			
			int numRows = ps.executeUpdate();
			if(numRows > 0) {
				
				ResultSet rs = ps.getGeneratedKeys();
				
				while(rs.next()) {
					
					obj.setId(rs.getInt(1));
					
				}
				
				conn.commit();
				
			}	
			
		} catch (SQLException e) {

			e.printStackTrace();
		
		}
		
		return obj;
	}

	@Override
	public AccountTypes update(AccountTypes obj) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "UPDATE BK_ACCOUNT_TYPES SET ACC_TYPE = ? WHERE TYPE_ID = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  obj.getAccountType());
			ps.setInt(2, obj.getId());
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		return null;
	}

	@Override
	public void delete(AccountTypes obj) {
		// TODO Auto-generated method stub
		
	}
	
	

}
