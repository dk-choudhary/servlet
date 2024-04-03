package com.search;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SendredirectMethod extends HttpServlet {  
	  
	  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{  
	   
	  
	      
	    ;  
	          
	    String search=request.getParameter("userName");  
	  response.sendRedirect("https://www.google.com/search?q="+search);
	   
	  
	  }
	}  