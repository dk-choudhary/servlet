package com.cookies;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Servlet3")
public class Servlet3 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		boolean b = false;

		Cookie ck2[] = request.getCookies();
		if (ck2 != null) {
			for (Cookie cookie : ck2) {
				if (cookie.getName().equals("uname")) {
					System.out.println(cookie.getValue());
					out.print("Hello " + cookie.getValue());
					b = true;
				}
			}
		}

		if (b) {
			out.print("<a href='servlet1'>First</a>");
		}

	}
}
