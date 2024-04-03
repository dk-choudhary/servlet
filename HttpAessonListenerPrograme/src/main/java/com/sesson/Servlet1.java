package com.sesson;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/sessonlistener")
public class Servlet1 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	HttpSession sesson =req.getSession();
	PrintWriter out=resp.getWriter();
	out.print(sesson);
	sesson.setAttribute("name", "dheeraj");
	sesson.setAttribute("post", "sitarampura");
	sesson.removeAttribute("post");
	sesson.setAttribute("name", "rajesh");
	req.changeSessionId();
	out.print(sesson);
	
	
	}

}
