package com.reimburse.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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

@WebServlet("/eCreate")
public class loadCreateServlet extends HttpServlet{
	
	static ReimburseDao rDao = new ReimburseDao();
	private static Logger log = Logger.getLogger(loadCreateServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		log.trace("inside doGet eCreate");
		
		//return list of reimbursements found for specific author
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("user");
		
		
		//load reimbursements into json file
		List<Reimbursement> reim = rDao.findReims(u.getId());
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(reim);
		
		log.trace("All Reimbursements: " + json);

		PrintWriter writer = resp.getWriter();
		resp.setContentType("application/json");
		writer.write(json);

		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Create a new reimbursement for the user in current session
		
		ObjectMapper mapper = new ObjectMapper();
		Reimbursement r = mapper.readValue(req.getInputStream(), Reimbursement.class);
		
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("user");
		
		log.trace(u.getId());
		r.setAuthor(u.getId());
		
		r = rDao.save(r);
	}

}
