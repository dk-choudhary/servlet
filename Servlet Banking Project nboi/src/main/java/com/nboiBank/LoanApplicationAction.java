package com.nboiBank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loanApplicationAction")
public class LoanApplicationAction extends HttpServlet{
 @Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 resp.setContentType("text/html");
		HttpSession session=req.getSession(false);
		long ac_number=(long)session.getAttribute("ac_number");
		try {
		Connection connection=(Connection)getServletContext().getAttribute("connection");
		int loan_amount=Integer.valueOf(req.getParameter("loanAmount"));
		int duration=Integer.valueOf(req.getParameter("duration"));
		int intrest=(loan_amount*12*duration)/100;
		int emi=(loan_amount+intrest)/(12*duration);
		int total_payable_amount=loan_amount+intrest;
		String queary="INSERT INTO customer_loan (ac_number, loan_amount, duration, emi, remining_amount, remining_emi, total_payable_amount, stutas, pannding_level)\r\n"
				+ "VALUES (?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps=connection.prepareStatement(queary);
		ps.setLong(1,ac_number);
		ps.setInt(2,loan_amount);
		ps.setInt(3,duration);
		ps.setInt(4,emi);
		ps.setInt(5,total_payable_amount);
		ps.setInt(6,12*duration);
		ps.setInt(7,total_payable_amount);
		ps.setString(8,"under Process");
		ps.setInt(9,3);
		ps.execute();
		
		resp.sendRedirect("LoanApplicationSubmitedPopup.html");
		
		}catch(SQLException e) {
			
		}
}
}
