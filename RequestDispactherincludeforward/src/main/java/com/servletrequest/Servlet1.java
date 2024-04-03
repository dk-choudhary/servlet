package com.servletrequest;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Servlet1  extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("username");
		String pass = request.getParameter("password");
		
		RequestDispatcher rd = null;
		
		if(name.equals(pass)) {
			out.print("<h1>please enter different username and password</h>" );
			
			rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);
			
		}
		else {
			rd = request.getRequestDispatcher("second2");
			rd.forward(request, response);
		}
		
	}

}
