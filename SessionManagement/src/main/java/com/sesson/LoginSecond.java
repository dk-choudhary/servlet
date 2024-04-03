package com.sesson;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginSecond extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		HttpSession sesson=req.getSession();
		
		String name = (String) sesson.getAttribute("name");
		String password = (String) sesson.getAttribute("password");
		
		out.println(name + "   " + password);
		out.println("<a href='Profile'>visit3</a>");

	}
}
