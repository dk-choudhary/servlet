package com.nboiBank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updatePersonalDetails")
public class UpdatePersonalDetails extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		ServletContext context = req.getServletContext();
		HttpSession session = req.getSession(false);
		PrintWriter pw = resp.getWriter();

		String pan_card = (String) session.getAttribute("PanCard");
		String name = (String) session.getAttribute("name");
		try {
			Connection con = (Connection) context.getAttribute("connection");
			// persent address update
			PreparedStatement ps = con
					.prepareStatement("update customer_address set Persent_address=? where ac_number=?;");
			ps.setString(1, req.getParameter("address"));
			ps.setLong(2, (long) session.getAttribute("ac_number"));
			ps.executeUpdate();

			// basic details update

			ps = con.prepareStatement(
					"update customer_basic_deatils set father_name=?,mother_name=?,email=?,mobile=? where ac_number=?;");
			ps.setString(1, req.getParameter("father_name"));
			ps.setString(2, req.getParameter("mother_name"));
			ps.setString(3, req.getParameter("email"));
			ps.setString(4, req.getParameter("mobile"));
			ps.setLong(5, (long) session.getAttribute("ac_number"));
			ps.executeUpdate();

			resp.sendRedirect("personalDataUpdatePopup.html");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
