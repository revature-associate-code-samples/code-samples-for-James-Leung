package com.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.banking.pojos.BankAccounts;
import com.banking.util.ConnectionFactory;

public class BankAccountsDao implements Dao<BankAccounts, Integer> {

	@Override
	public List<BankAccounts> findAll() {
		
		List<BankAccounts> bank_acc = new ArrayList<BankAccounts>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String query = "SELECT * FROM BK_ACCOUNTS ORDER BY ACC_OWNER";
			
			Statement statement = conn.createStatement();
			
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				
				BankAccounts acc = new BankAccounts();
				acc.setId(rs.getInt("ACC_ID"));
				acc.setAccountType(rs.getInt(2));
				acc.setAccountOwner(rs.getInt(3));
				acc.setBalance(rs.getDouble(4));
				bank_acc.add(acc);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return bank_acc;
		
	}

	@Override
	public BankAccounts findById(Integer id) {
		
		BankAccounts bk = null;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "SELECT * FROM BK_ACCOUNTS WHERE ACC_OWNER = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				bk = new BankAccounts();
				bk.setId(rs.getInt(1));
				bk.setAccountType(rs.getInt(2));
				bk.setAccountOwner(rs.getInt(3));
				bk.setBalance(rs.getDouble(4));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return bk;		
	}

	@Override
	public BankAccounts save(BankAccounts obj) {

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO BK_ACCOUNTS (ACC_TYPE_ID, ACC_OWNER, ACC_BALANCE) VALUES(?, ?, ?)";
			String[] keyNames = {"ACC_ID"};
			
			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			ps.setInt(1, obj.getAccountType());
			ps.setInt(2, obj.getAccountOwner());
			ps.setDouble(3, obj.getBalance());
			 
			int numRows = ps.executeUpdate();
			
			if(numRows == 1) {
				
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
	public BankAccounts update(BankAccounts obj) {

		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			conn.setAutoCommit(false);
			
			String sql = "UPDATE BK_ACCOUNTS SET ACC_BALANCE = ? WHERE ACC_TYPE_ID = ? AND ACC_OWNER = ?";
			
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1,  obj.getBalance());
			ps.setInt(2, obj.getAccountType());
			ps.setInt(3, obj.getAccountOwner());
			ps.executeUpdate();

			conn.commit();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return obj;
	}

	@Override
	public void delete(BankAccounts obj) {
		// TODO Auto-generated method stub
		
	}
	
	

}
