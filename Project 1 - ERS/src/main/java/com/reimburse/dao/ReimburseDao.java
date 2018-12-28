package com.reimburse.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reimburse.pojos.Reimbursement;
import com.reimburse.pojos.User;
import com.reimburse.servlets.loadViewsServlet;
import com.reimburse.util.ConnectionFactory;

import oracle.jdbc.OracleTypes;

public class ReimburseDao implements Dao<Reimbursement, Integer>{
	
	private static Logger log = Logger.getLogger(ReimburseDao.class);
	@Override
	public List<Reimbursement> findAll() {
		
		List<Reimbursement> reimb = new ArrayList<Reimbursement>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "{ call GET_ALL_ERS_REIMBURSEMENT(?) }";
			
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.execute();
			
			ResultSet rs = (ResultSet) cs.getObject(1);
			
			while(rs.next()) {
				
				Reimbursement temp = new Reimbursement();
				temp.setId(rs.getInt("REIMB_ID"));
				temp.setAmount(rs.getInt(2));
				temp.setSubmitted(rs.getTimestamp(3));
				temp.setResolved(rs.getTimestamp(4));
				temp.setDescription(rs.getString(5));
				temp.setAuthor(rs.getInt(6));
				temp.setResolver(rs.getInt(7));
				temp.setStatus_id(rs.getInt(8));
				temp.setType_id(rs.getInt(9));
				reimb.add(temp);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		return reimb;
	}

	@Override
	public Reimbursement findById(Integer id) {
		
		Reimbursement reimb = null;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_ID = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {

				reimb = new Reimbursement();
				reimb.setId(rs.getInt(1));
				reimb.setAmount(rs.getInt(2));
				reimb.setSubmitted(rs.getTimestamp(3));
				reimb.setResolved(rs.getTimestamp(4));
				reimb.setDescription(rs.getString(5));
				reimb.setAuthor(rs.getInt(6));
				reimb.setResolver(rs.getInt(7));
				reimb.setStatus_id(rs.getInt(8));
				reimb.setType_id(rs.getInt(9));

			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return reimb;
	}

	public List<Reimbursement> findReims(Integer id) {
		
		List<Reimbursement> reims = new ArrayList<Reimbursement>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_AUTHOR = ? ORDER BY REIMB_ID";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {

				Reimbursement r = new Reimbursement();
				r.setId(rs.getInt(1));
				r.setAmount(rs.getInt(2));
				r.setSubmitted(rs.getTimestamp(3));
				r.setResolved(rs.getTimestamp(4));
				r.setDescription(rs.getString(5));
				r.setAuthor(rs.getInt(6));
				r.setResolver(rs.getInt(7));
				r.setStatus_id(rs.getInt(8));
				r.setType_id(rs.getInt(9));
				reims.add(r);

			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return reims;
		
		
	}
	
	@Override
	public Reimbursement save(Reimbursement obj) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO ERS_REIMBURSEMENT (REIMB_AMOUNT, REIMB_DESCRIPTION, REIMB_SUBMITTED, REIMB_RESOLVED, REIMB_AUTHOR, REIMB_RESOLVER, REIMB_STATUS_ID, REIMB_TYPE_ID)"
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			
			String[] keyNames = {"REIMB_ID"};
			
			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			ps.setInt(1, obj.getAmount());
			ps.setString(2, obj.getDescription());
			ps.setTimestamp(3, obj.getSubmitted());
			ps.setTimestamp(4, obj.getResolved());
			ps.setInt(5, obj.getAuthor());
			ps.setInt(6,  obj.getResolver());
			ps.setInt(7, obj.getStatus_id());
			ps.setInt(8, obj.getType_id());
			
			
			
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
	public Reimbursement update(Reimbursement obj) {
		
		log.trace("inside update");
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "UPDATE ERS_REIMBURSEMENT SET REIMB_STATUS_ID = ? WHERE REIMB_ID = ?";
			
			log.trace("inside update, before prepared statement");
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, obj.getStatus_id());
			ps.setInt(2, obj.getId());
			ps.executeUpdate();
		
			log.trace("update executed");
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return null;
	}

	@Override
	public void delete(Reimbursement obj) {
		// TODO Auto-generated method stub
		
	}	

}
