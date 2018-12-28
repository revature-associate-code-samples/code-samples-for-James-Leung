package com.reimburse.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.reimburse.dao.UserDao;
import com.reimburse.pojos.Reimbursement;
import com.reimburse.pojos.User;
import com.reimburse.service.ReimburseService;
import com.reimburse.service.UserService;

@WebServlet("/login")
public class loadLoginServlet extends HttpServlet{
	
	private static Logger log = Logger.getLogger(loadLoginServlet.class);
	static UserService userServ = new UserService();
	static ReimburseService reimServ = new ReimburseService();
	static ReimburseDao rDao = new ReimburseDao();
	static UserDao uDao = new UserDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("partials/login.html").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		User u = mapper.readValue(req.getInputStream(), User.class);
		u = userServ.validateUser(u.getUsername(), u.getPassword());
		
		if(u == null) {
			
			req.getRequestDispatcher("partials/error-login.html").forward(req, resp);
			
		}
		
		else {
			
			HttpSession session = req.getSession();
			session.setAttribute("user", u);
			log.trace(u);
			if(u.getRole_id() == 1) {
				
				resp.sendRedirect("partials/employee.html");
				
			}
			else {
				
				resp.sendRedirect("partials/manager.html");
				
			}
			
			
		}
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		ObjectMapper mapper = new ObjectMapper();
	
		Reimbursement r = mapper.readValue(req.getInputStream(), Reimbursement.class);
		
		reimServ.updateStatus(r.getId(), r.getStatus_id());

		
	}

}
