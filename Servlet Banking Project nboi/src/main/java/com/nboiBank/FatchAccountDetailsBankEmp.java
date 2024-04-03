package com.nboiBank;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/fatchDetails")
public class FatchAccountDetailsBankEmp extends HttpServlet{
 @Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   resp.setContentType("text/html");
	   HttpSession session = req.getSession(false);
	   session.setAttribute("getAcData", req.getParameter("acNumber"));
	   PrintWriter pw = resp.getWriter();  
	   String name=(String) session.getAttribute("name");
	   String action=req.getParameter("action");
	   String type=(String) session.getAttribute("reqType");
	   session.removeAttribute("reqType");
	   if(action.equals("getBasicDetails")) {
		   resp.sendRedirect("basicDetailsCheck?type="+type);
	   }else {
		   resp.sendRedirect("acTransctionCheck?page=1&type="+type);
	   }
}
}
