package com.reimburse.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.reimburse.pojos.Type;

import com.reimburse.util.ConnectionFactory;

import oracle.jdbc.OracleTypes;

public class TypeDao implements Dao<Type, Integer> {

	@Override
	public List<Type> findAll() {
		
		List<Type> types = new ArrayList<Type>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "{ call GET_ALL_ERS_TYPE(?) }";
			
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.execute();
			
			ResultSet rs = (ResultSet) cs.getObject(1);
			
			while(rs.next()) {
				
				Type temp = new Type();
				temp.setId(rs.getInt("REIMB_TYPE_ID"));
				temp.setType(rs.getString(2));
				types.add(temp);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		return types;

	}

	@Override
	public Type findById(Integer id) {
		
		Type type = null;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "SELECT * FROM ERS_REIMBURSEMENT_TYPE WHERE REIMB_TYPE_ID = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				type = new Type();
				type.setId(rs.getInt(1));
				type.setType(rs.getString(2));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return type;
		
	}

	@Override
	public Type save(Type obj) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
		conn.setAutoCommit(false);
			
		String sql = "INSERT INTO ERS_REIMBURSEMENT_TYPE (REIMB_TYPE)"
					+ " VALUES(?)";
		String[] keyNames = {"REIMB_TYPE_ID"};
			
		PreparedStatement ps = conn.prepareStatement(sql, keyNames);
		ps.setString(1, obj.getType());
			
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
	public Type update(Type obj) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "UPDATE ERS_REIMBURSEMENT_TYPE SET REIMB_TYPE = ? WHERE REIMB_TYPE_ID = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  obj.getType());
			ps.setInt(2, obj.getId());
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return null;
	
	}

	@Override
	public void delete(Type obj) {
		// TODO Auto-generated method stub
		
	}
	

}
