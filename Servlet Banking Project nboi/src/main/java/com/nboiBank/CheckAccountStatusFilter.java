package com.nboiBank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/checkRequestStatus")
public class CheckAccountStatusFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		res.setContentType("text/html");
		String tempAcNum = req.getParameter("tempAcNumber");
		Connection connection = (Connection) getServletContext().getAttribute("connection");
		PrintWriter pw = res.getWriter();
		try {
			PreparedStatement ps = connection
					.prepareStatement("select * from Customer_basic_deatils where temp_ac_num=?;");
			ps.setString(1, req.getParameter("tempAcNumber"));
			ResultSet rs = ps.executeQuery();
      
			if (rs.next()) {
				HttpSession session = req.getSession();
				session.setAttribute("resultset", rs);
				session.setAttribute("customer", rs.getString("name"));
				session.setAttribute("temp_ac_num", rs.getString("temp_ac_num"));
				if (rs.getLong("temp_ac_num") == Long.parseLong(req.getParameter("tempAcNumber"))) {
					if (rs.getString("dob").equals(req.getParameter("dob"))) {
						if (rs.getString("password").equals(req.getParameter("password"))) {
							chain.doFilter(req, res);
						} else {
							res.sendRedirect("wrongPassPopup.html");
						}
					} else {
						res.sendRedirect("WrongDobPopup.html");
					}
				} else {
					res.sendRedirect("wrongTempAcNumPopup.html");
				}

			} else {
				res.sendRedirect("notCustomerPopup.html");
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
