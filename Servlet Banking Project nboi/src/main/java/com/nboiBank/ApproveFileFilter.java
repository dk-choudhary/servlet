package com.nboiBank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/approveFile")
public class ApproveFileFilter extends HttpFilter {
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		long ac_number = Long.parseLong(req.getParameter("temp_ac_number"));
		ServletContext context = req.getServletContext();
		Connection con = (Connection) context.getAttribute("connection");
		PreparedStatement ps;
		int loan_max_limit = 1000000;
		try {
			if (req.getParameter("panndingType") != null) {
				ps = con.prepareStatement(
						"select sum(remining_amount) as remining_amount from customer_loan where ac_number=?;");
				ps.setLong(1, ac_number);
				ResultSet rs = ps.executeQuery();
				rs.next();
				if (rs.getInt("remining_amount") >= loan_max_limit) {
					res.sendRedirect("NotElagilableForLoanPopup.html");
				}else {
					req.setAttribute("pandingType", "loan");
					chain.doFilter(req, res);
				}
			} else {
				chain.doFilter(req, res);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
