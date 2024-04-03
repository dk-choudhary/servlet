package com.hidden;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Servlet1 extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		out.println("<form action='Servlet22' method='post'>");
		out.println("<input type='hidden' name='name' value='" + name + "'/>");
		out.println("<input type='hidden' name='password' value='" + password + "'/>");
		out.println(name+"   "+password);
		out.println("<input type='submit' value='second'/>");

		out.println("</form>");
	}

}
