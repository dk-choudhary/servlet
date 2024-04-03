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

@WebServlet("/addEmp")
public class AddEmpAction extends HttpServlet{
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("text/html");
	
	try {
	Connection connection=(Connection)getServletContext().getAttribute("connection");
	String queary="INSERT INTO bank_emp (emp_id, Name, gender, mobile, email, password, post)\r\n"
			+ "VALUES (?,?,?,?,?,?,?);";
	PreparedStatement ps=connection.prepareStatement(queary);
	ps.setString(1,req.getParameter("empId"));
	ps.setString(2,req.getParameter("empName"));
	ps.setString(3,req.getParameter("gender"));
	ps.setString(4,req.getParameter("mobile"));
	ps.setString(5,req.getParameter("empEmail"));
	ps.setString(6,req.getParameter("password"));
	ps.setInt(7,Integer.valueOf(req.getParameter("post")));
	ps.execute();
	resp.sendRedirect("EmployeeAddedPopup.html");
	}catch(SQLException e) {
		e.printStackTrace();
	}
}
}
