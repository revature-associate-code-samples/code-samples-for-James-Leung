package com.reimburse.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reimburse.dao.ReimburseDao;
import com.reimburse.pojos.Reimbursement;
import com.reimburse.pojos.User;


@WebServlet("/manager")
public class loadManagerServlet extends HttpServlet {
	
	private static Logger log = Logger.getLogger(loadManagerServlet.class);
	
	static ReimburseDao rDao = new ReimburseDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		log.trace("accessing doGet LoadManagerServlet");
		
		List<Reimbursement> reim = rDao.findAll();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(reim);
		
		log.trace("All Reimbursements: " + json);

		PrintWriter writer = resp.getWriter();
		resp.setContentType("application/json");
		writer.write(json);
		
	}


}
