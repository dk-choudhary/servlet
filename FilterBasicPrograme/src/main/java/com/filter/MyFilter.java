package com.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyFilter implements Filter {
	private static final String USERNAME_PATTERN = "[a-z0-9]";
	private static final String PASSWORD_PATTERN = "[a-z0-9]";



	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		res.setContentType("text/html");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String username = request.getParameter("name");
		String password = request.getParameter("password");

		if (isValidUser(username, password)) {

			chain.doFilter(request, response);
		} else {

		
			response.getWriter().println("Authentication failed. Please provide valid credentials.");
			req.getRequestDispatcher("index.html").include(request, response);
		}
	}

	private boolean isValidUser(String username, String password) {

		if ((username != null && password != null) && (username.contains("@") && password.contains("1")))
				 {

			return true;
		}
				 else {
		return false;}
	}

}
