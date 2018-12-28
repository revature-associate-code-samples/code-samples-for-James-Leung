package com.reimburse.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.reimburse.dao.ReimburseDao;
import com.reimburse.pojos.Reimbursement;
import com.reimburse.servlets.loadViewsServlet;

public class ReimburseService {
	
	static ReimburseDao reimD = new ReimburseDao();
	private static Logger log = Logger.getLogger(loadViewsServlet.class);
	
	public static List<Reimbursement> getAllReimbs() {
		
		List<Reimbursement> reim = reimD.findAll();
		
		return reim;
		
	}

	public static void createReim() {
		
		List<Reimbursement> reims = reimD.findAll();

	
	}
	
	public static Reimbursement updateReim() {
		
		Reimbursement r = reimD.findById(2);
		reimD.update(r);
		
		return r;
	}

	//test save
	
//	public static Reimbursement saveReim() {
//		
//		Date date = new Date();
//		Timestamp time = new Timestamp(date.getTime());
//		Reimbursement r = new Reimbursement(300, "test", time, time, 1, 2, 2, 3);
//		reimD.save(r);
//		
//		return r;
//	}
//	
	public static void updateStatus(int rid, int rstat) {
		
		Reimbursement r = reimD.findById(rid);
		
		r.setStatus_id(rstat);
		
		reimD.update(r);
		
	}
}
