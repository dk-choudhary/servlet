package com.servletcofigex;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ThirdServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {

	}

	protected void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		//servlet config
		String name=getServletConfig().getInitParameter("name");
		PrintWriter out=response.getWriter();
		out.print(name+"   ");

		//servlet context
		String newName1 = getServletContext().getInitParameter("name1");
		String newName2 = getServletContext().getInitParameter("name2");
		String vill=(String) getServletContext().getAttribute("village");
		String post=(String)getServletContext().getAttribute("post");
		String ps=(String)getServletContext().getAttribute("ps");
		String teh=(String)getServletContext().getAttribute("teh");
		
		out.print("Context name1="+newName1+"  context name2= "+newName2);
		out.print("Context  vill= "+vill+"  context post= "+post);
		out.print("Context ps= "+ps+"  context  teh= "+teh);
		
	
	}

}