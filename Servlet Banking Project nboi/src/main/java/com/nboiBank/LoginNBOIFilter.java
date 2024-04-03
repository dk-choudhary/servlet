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

@WebFilter("/login")
public class LoginNBOIFilter extends HttpFilter {
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		if (req.getSession(false) == null) {
			res.setContentType("text/html");
			Connection connection = (Connection) getServletContext().getAttribute("connection");
			try {
				if (req.getParameter("usertype") != null) {
					PreparedStatement ps = null;
					if (req.getParameter("usertype").equals("Customer")) {

						ps = connection.prepareStatement("select * from Customer_basic_deatils where user_id=?;");
					} else {
						ps = connection.prepareStatement("select * from bank_emp where emp_id=?;");
					}
					ps.setString(1, req.getParameter("user"));
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						if (rs.getString("password").equals(req.getParameter("password"))) {
							HttpSession session = req.getSession();
							session.invalidate();
							session = req.getSession();
							if (req.getParameter("usertype").equals("Customer")) {
								session.setAttribute("PanCard", rs.getString("pan_card"));
								session.setAttribute("name", rs.getString("name"));
								session.setAttribute("ac_number", rs.getLong("ac_number"));
								
							} else {
								session.setAttribute("name", rs.getString("Name"));
								session.setAttribute("emp_id", rs.getString("emp_id"));
								session.setAttribute("empLevel", rs.getInt("post"));
								
							}
							chain.doFilter(req, res);
						} else {
							res.sendRedirect("wrongPassForLoginPopup.html");
						}
					} else {
						if (req.getParameter("usertype").equals("Customer")) {
							res.sendRedirect("coustomerNotHaveAccountPopup.html");
						} else {
							res.sendRedirect("notBankEmployeePopup.html");
						}

					}
				} else {
					res.sendRedirect("selectCustomerTypeLoginPopup.html");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			chain.doFilter(req, res);
		}
	}
}
