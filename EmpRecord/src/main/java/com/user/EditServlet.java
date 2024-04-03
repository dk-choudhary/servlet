package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class EditServlet extends HttpServlet{
	
	 private static Connection connection;

	    @Override
	    public void init() {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            String jdbcUrl = "jdbc:mysql://localhost:3306/servlet";
	            String username = "root";
	            String dbPassword = "123456";
	            connection = DriverManager.getConnection(jdbcUrl, username, dbPassword);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		 	
		//set content
		response.setContentType("text/html");
	     PrintWriter out = response.getWriter();
	     
	   //geting Cookie array
		Cookie ck[] = request.getCookies();	
			
		//iterate cookie
		String old_name=null;
		for(int i=0;i<ck.length;i++) {
			old_name=ck[0].getValue();						
		}
		

	        try {
	            String sql_Update_query = "UPDATE emp SET name=?, email=?,password=?, gender=? ,weight=?, height=? WHERE id=?";
	            PreparedStatement ps =connection.prepareStatement(sql_Update_query);

	            
	            int newId = Integer.valueOf(request.getParameter("id"));                   
	       	 	String newName = request.getParameter("name");	            
	            String newEmail =  request.getParameter("email");
	           
	            String newpassword = request.getParameter("password");
	            String newGender =  request.getParameter("gender");
	            int newWeight = Integer.valueOf(request.getParameter("weight"));
	            int newHeight = Integer.valueOf(request.getParameter("height"));
	             

	            ps.setString(1, newName);
	            ps.setString(2, newEmail);	           
	            ps.setString(3, newpassword);
	            ps.setString(4, newGender);
	            ps.setInt(5, newWeight);
	            ps.setInt(6, newHeight);
	            ps.setInt(7, newId);
	          

	            // Execute the update
	            ps.executeUpdate();
	            out.print("<h1>User Details Updated Successfully</h1>");
	           
	         
	        } catch (Exception e) {
	            e.printStackTrace();
	            out.print("<h1>Error occurred: " + e.getMessage() + "</h1>");
	        }

	        out.print("<a href=\"index.html\">Home Page</a>");

		
	}

}
