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

@WebServlet("/atmwithdrawaction")
public class AtmWithdrawlAction extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");

		ServletContext context = req.getServletContext();
		HttpSession session = req.getSession(false);
		PrintWriter pw = resp.getWriter();

		long transferAmount = Long.valueOf(req.getParameter("amount"));
		long ac_number = (long) session.getAttribute("ac_number");
		long my_current_balance = 0;
		try {
			Connection con = (Connection) context.getAttribute("connection");
			PreparedStatement ps = con.prepareStatement(
					"select current_balance,ac_number from customer_basic_deatils where ac_number=?;");
			ps.setLong(1, ac_number);
			ResultSet rs = ps.executeQuery();
			rs.next();
			my_current_balance = rs.getLong("current_balance");

			if (my_current_balance >= transferAmount) {
				my_current_balance = my_current_balance - transferAmount;
				ps = con.prepareStatement("update customer_basic_deatils set current_balance=? where ac_number=?;");
				ps.setLong(1, my_current_balance);
				ps.setLong(2, ac_number);
				ps.executeUpdate();
				System.out.println("first");
				ps = con.prepareStatement(
						"insert into transction (ac_number,debit,from_ac,balance) values(?,?,?,?);");
				ps.setLong(1, ac_number);
				ps.setLong(2, transferAmount);
				ps.setString(3, "Cash");
				ps.setLong(4, my_current_balance);
				ps.executeUpdate();

				resp.sendRedirect("login");
			} else {
				resp.sendRedirect("InsufficientBalancePopup.html");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
