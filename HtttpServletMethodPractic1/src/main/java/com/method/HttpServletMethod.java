package com.method;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class HttpServletMethod extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HttpServletMethod() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getParameter("userName");
		String yourEmailID = request.getParameter("yourEmailID");
		String yourPassword = request.getParameter("yourPassword");
		String gender = request.getParameter("gender");
		String favoriteLanguage = request.getParameter("favoriteLanguage");
		//System.out.println("gender.." + gender);
		//System.out.println("favoriteLanguage.." + favoriteLanguage);

		if (userName != null && yourEmailID != null && yourPassword != null) {

			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("emailId", yourEmailID);
			httpSession.setAttribute("gender", gender);
			httpSession.setAttribute("favoriteLanguage", favoriteLanguage);
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		}
		//response.setHeader("java", "ram");
		
		response.setIntHeader("refresh", 5);
		
		doHead(request,response);
	}
	@Override
	protected void doHead(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		Enumeration<String> list=arg0.getHeaderNames();
		while(list.hasMoreElements()) {
			String head=list.nextElement();
			System.out.println(head +":"+arg0.getHeader(head));
		}
	}
	
}