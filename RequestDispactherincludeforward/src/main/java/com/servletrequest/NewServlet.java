package com.servletrequest;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NewServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
	PrintWriter out = resp.getWriter();
		
		String name = req.getParameter("username");
		String pass = req.getParameter("password");
		out.print("<h2>My name is Servlet2</h2>");
		out.print(name +" "+pass);
		
	}

}
