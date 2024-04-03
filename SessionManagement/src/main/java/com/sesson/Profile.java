package com.sesson;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Profile extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		HttpSession sesson = req.getSession(false);
		if (sesson != null) {
			String name = (String) sesson.getAttribute("name");
			String password = (String) sesson.getAttribute("password");

			//out.println(name + "   " + password);
			sesson.invalidate();
			out.println("<h1>sucessfully logout</h>");
			RequestDispatcher rd = req.getRequestDispatcher("index.html");
			rd.include(req, resp);
			

		} else {
			out.println("<h1>please login first</h>");
		
		
			RequestDispatcher rd = req.getRequestDispatcher("index.html");
			rd.include(req, resp);

		}


		
	}
}
