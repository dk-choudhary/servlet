package com.nboiBank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/rejectFile")
public class RejectFile extends HttpServlet{
  @Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  HttpSession session = req.getSession(false);
		ServletContext context=req.getServletContext();
		String panndingType =req.getParameter("panndingType");
		int pandingLevel=(int)session.getAttribute("empLevel");
		Connection con=(Connection) context.getAttribute("connection");
		long temp_ac_number=Long.valueOf(req.getParameter("temp_ac_number"));
		try {
			PreparedStatement ps;
			if(panndingType.equals("accuont")) {
                ps=con.prepareStatement("update customer_basic_deatils set task_panding=? where temp_ac_num=?");
			     ps.setInt(1,100);
			     ps.setLong(2, temp_ac_number);
			
			     ps.executeUpdate();
  		   
		} else {
			ps=con.prepareStatement("update customer_loan set pannding_level=?,stutas=? where loan_ac_number=?");
		     ps.setInt(1,100);
		     ps.setString(2, "Rejected");
		     ps.setLong(3, temp_ac_number);
		
		     ps.executeUpdate();
		}
			int pandingTask=(int)session.getAttribute("totalPandingTask");
			session.setAttribute("totalPandingTask", pandingTask-1);
		resp.sendRedirect("panndigTask?page=1");
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
}
