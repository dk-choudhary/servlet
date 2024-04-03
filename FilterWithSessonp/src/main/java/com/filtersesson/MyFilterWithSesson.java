package com.filtersesson;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MyFilterWithSesson extends HttpFilter{
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		
		
		HttpSession sesson=req.getSession();
		out.print(sesson.getCreationTime()+"<br>");
		out.print(sesson.getLastAccessedTime()+"<br>");
		sesson.setAttribute("name1", "ram");
		chain.doFilter(req, res);
		HttpSession sesson1=req.getSession();
		out.print(sesson1.getCreationTime()+"<br>");
		out.print(sesson1.getLastAccessedTime()+"<br>");
		
		
	}

}
