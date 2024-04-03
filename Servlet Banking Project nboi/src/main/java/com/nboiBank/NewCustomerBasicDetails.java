package com.nboiBank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/basicDeatils")
public class NewCustomerBasicDetails extends HttpServlet{
   @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("text/html");
	String name=req.getParameter("firstName")+" "+req.getParameter("lastName");
	String dob=req.getParameter("dob");
	String password= name.substring(0, 4).toUpperCase()+dob.substring(0,4);
	System.out.println(password);
	try {
	Connection connection=(Connection)getServletContext().getAttribute("connection");
	String queary="INSERT INTO Customer_basic_deatils (name, father_name, mother_name, dob, email, mobile, aadhar, pan_card, ac_type,stutas,password,task_panding)\r\n"
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
	
	PreparedStatement ps=connection.prepareStatement(queary);
	ps.setString(1,name);
	ps.setString(2,req.getParameter("fatherName"));
	ps.setString(3,req.getParameter("motherName"));
	ps.setString(4,dob);
	ps.setString(5,req.getParameter("email"));
	ps.setString(6,req.getParameter("mobile"));
	ps.setString(7,req.getParameter("aadhar"));
	ps.setString(8,req.getParameter("pan"));
	ps.setString(9, req.getParameter("accountType"));
	ps.setBoolean(10, false);
	ps.setString(11, password);
	ps.setInt(12, 2);
	ps.execute();
	
	HttpSession session=req.getSession();
	session.setAttribute("password", password);
	ps=connection.prepareStatement("SELECT * FROM Customer_basic_deatils ORDER BY temp_ac_num DESC LIMIT 1;");
	ResultSet rs=ps.executeQuery();
	while(rs.next()) {
		long temp_ac_num=rs.getLong(1);
		session.setAttribute("temp_ac_num", temp_ac_num);
		session.setAttribute("customer",req.getParameter("firstName")+req.getParameter("lastName"));
	}
	resp.sendRedirect("NewAccountCustAddress.html");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
