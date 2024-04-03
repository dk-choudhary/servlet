package com.Listener;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Listnerservlet")
public class FirstServletForListner extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		ServletContext contxt = getServletContext();
		Connection con = (Connection) contxt.getAttribute("con");
		String  name=(String) contxt.getAttribute("name");
		String post =(String) contxt.getAttribute("post");
		contxt.setAttribute("name", "dheeraj");
		contxt.setAttribute("post", "govindgarh");
		contxt.setAttribute("post", "jaipur");
		contxt.removeAttribute("name");
		
		try {
			PreparedStatement ps = con.prepareStatement("select * from employee");
			ResultSet rs = ps.executeQuery();
			List<Emp> list = new ArrayList<Emp>();
			while (rs.next()) {
				Emp e = new Emp();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setSalary(rs.getFloat(3));
				list.add(e);
			}

			out.print("<h1>emp details" + "</h1>");
			out.print("<table border='1' cellpadding='4' width='60%'>");
			out.print("<tr><th>Id</th><th>Name</th><th>Salary</th>");
			for (Emp e : list) {
				out.print("<tr><td>" + e.getId() + "</td><td>" + e.getName() + "</td><td>" + e.getSalary()
						+ "</td></tr>");
			}
			out.print("</table>");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
