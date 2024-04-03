package com.sesson;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class FirstLogin extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
	
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		HttpSession sesson = req.getSession();
		sesson.setAttribute("name", name);
		sesson.setAttribute("password", password);
		out.print(name + "  " + password);

		out.println("<a href='Servlet22'>visit2</a>");

	}

}
