package com.reimburse.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.reimburse.pojos.Roles;
import com.reimburse.util.ConnectionFactory;

import oracle.jdbc.OracleTypes;

public class RoleDao implements Dao<Roles, Integer> {

	@Override
	public List<Roles> findAll() {
		
		List<Roles> roles = new ArrayList<Roles>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "{ call GET_ALL_ERS_ROLES(?) }";
			
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.execute();
			
			ResultSet rs = (ResultSet) cs.getObject(1);
			
			while(rs.next()) {
				
				Roles role = new Roles();
				role.setId(rs.getInt("ERS_USER_ROLE_ID"));
				role.setRole(rs.getString(2));
				roles.add(role);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		return roles;		
		
		
	}

	@Override
	public Roles findById(Integer id) {
		
		Roles role = null;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "SELECT * FROM ERS_REIMBURSEMENT_TYPE WHERE REIMB_TYPE_ID = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				role = new Roles();
				role.setId(rs.getInt(1));
				role.setRole(rs.getString(2));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return role;		
		
		
	}

	@Override
	public Roles save(Roles obj) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
		conn.setAutoCommit(false);
				
		String sql = "INSERT INTO ERS_USER_ROLES (USER_ROLE)"
						+ " VALUES(?)";		
		String[] keyNames = {"ERS_USER_ROLE_ID"};
				
		PreparedStatement ps = conn.prepareStatement(sql, keyNames);
		ps.setString(1, obj.getRole());
				
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
	public Roles update(Roles obj) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "UPDATE ERS_USER_ROLES SET USER_ROLE = ? WHERE ERS_USER_ROLE_ID = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  obj.getRole());
			ps.setInt(2, obj.getId());
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return null;	
	
	
	}

	@Override
	public void delete(Roles obj) {
		// TODO Auto-generated method stub
		
	}
	
}
