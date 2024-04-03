package com.servletcofigex;


import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class ServletConfig extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

	protected void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		//servlet config
		String name=getInitParameter("name");
		PrintWriter out=response.getWriter();
		out.print(name+"   ");
		
		
		//servlet context
		String newName1 = getServletContext().getInitParameter("name1");
		String newName2 = getServletContext().getInitParameter("name2");
		
		out.print("Context name1="+newName1+"  context name2= "+newName2);
		getServletContext().setAttribute("village", "sitarampura");
		getServletContext().setAttribute("post", "malikpur");
		getServletContext().setAttribute("ps", "govindgarh");
		getServletContext().setAttribute("teh", "jaipur");

	}



}
