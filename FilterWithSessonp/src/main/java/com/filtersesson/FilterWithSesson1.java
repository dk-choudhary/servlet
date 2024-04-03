package com.filtersesson;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class FilterWithSesson1 extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
HttpSession sesson=req.getSession();
resp.setContentType("text/html");
PrintWriter out=resp.getWriter();


String name1=(String) sesson.getAttribute("name1");
String name=req.getParameter("username");
out.print("name1 :"+name1+"<br>");
out.print("name :"+name+"<br>");
}
}
