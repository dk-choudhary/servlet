package com.cookies;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String n = request.getParameter("userName");

		Cookie ck2[] = request.getCookies();
		if (ck2 != null) {
			for (Cookie cookie : ck2) {
				if (cookie.getName().equals("uname")) {
					out.print("Hello " + cookie.getValue());
					out.print("<a href='servlet2'>Second</a>");
				}
			}
		} else {
			Cookie ck = new Cookie("uname", n);
			response.addCookie(ck);
			out.print("Hello " + n);
			out.print("<a href='servlet2'>Second</a>");
		}

	}
}