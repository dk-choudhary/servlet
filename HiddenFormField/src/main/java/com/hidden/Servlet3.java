package com.hidden;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Servlet3 extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		out.println("<form action='Servlet44' method='post'>");
		out.println("<input type='hidden' name='name' value='" + name + "'/>");
		out.println("<input type='hidden' name='password' value='" + password + "'/>");
		out.println(name+"   "+password);
		out.println("<input type='submit' value='forth'/>");

		out.println("</form>");
		
		
		
		MysqlConnection msql=new MysqlConnection();
		try {
			msql.insertUserData(name, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
