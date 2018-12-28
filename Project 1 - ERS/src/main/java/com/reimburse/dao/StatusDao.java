package com.reimburse.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.reimburse.pojos.Status;
import com.reimburse.util.ConnectionFactory;

import oracle.jdbc.OracleTypes;

public class StatusDao implements Dao<Status, Integer> {

	@Override
	public List<Status> findAll() {
		
		List<Status> Statuses = new ArrayList<Status>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "{ call GET_ALL_ERS_STATUS(?) }";
			
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.execute();
			
			ResultSet rs = (ResultSet) cs.getObject(1);
			
			while(rs.next()) {
				
				Status stat = new Status();
				stat.setId(rs.getInt("REIMB_STATUS_ID"));
				stat.setStatus(rs.getString(2));
				Statuses.add(stat);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		return Statuses;
		
	}

	@Override
	public Status findById(Integer id) {
		
		Status stat = null;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "SELECT * FROM ERS_REIMBURSEMENT_TYPE WHERE REIMB_TYPE_ID = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				stat = new Status();
				stat.setId(rs.getInt(1));
				stat.setStatus(rs.getString(2));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return stat;

	}

	@Override
	public Status save(Status obj) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
		conn.setAutoCommit(false);
				
		String sql = "INSERT INTO ERS_REIMBURSEMENT_STATUS (REIMB_STATUS)"
						+ " VALUES(?)";
		
		String[] keyNames = {"REIMB_STATUS_ID"};
		PreparedStatement ps = conn.prepareStatement(sql, keyNames);
		ps.setString(1, obj.getStatus());
				
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
	public Status update(Status obj) {

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
		String sql = "UPDATE ERS_REIMBURSEMENT_TYPE SET REIMB_STATUS = ? WHERE REIMB_STATUS_ID = ?";
			
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,  obj.getStatus());
		ps.setInt(2, obj.getId());
		ps.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return null;
		
	}

	@Override
	public void delete(Status obj) {
		// TODO Auto-generated method stub
		
	}

}
