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

@WebServlet("/fundTransferAction")
public class FundTransferAction extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");

		ServletContext context = req.getServletContext();
		HttpSession session = req.getSession(false);
		PrintWriter pw = resp.getWriter();

		long transferAmount = Long.valueOf(req.getParameter("amount"));
		long ac_number = (long) session.getAttribute("ac_number");
		long my_current_balance = 0;
		long to_current_balance = 0;
		long to_ac_number = Long.valueOf(req.getParameter("to_account"));
		try {
			Connection con = (Connection) context.getAttribute("connection");
			PreparedStatement ps = con.prepareStatement(
					"select current_balance,ac_number from customer_basic_deatils where ac_number=? or ac_number=?;");
			ps.setLong(1, ac_number);
			ps.setLong(2, to_ac_number);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getLong("ac_number") == ac_number) {
					my_current_balance = rs.getLong("current_balance");
				} else {
					to_current_balance = rs.getLong("current_balance");
				}
			}
			if (my_current_balance >= transferAmount) {
				my_current_balance = my_current_balance - transferAmount;
				ps = con.prepareStatement("update customer_basic_deatils set current_balance=? where ac_number=?;");
				ps.setLong(1, my_current_balance);
				ps.setLong(2, ac_number);
				ps.executeUpdate();
				ps = con.prepareStatement(
						"insert into transction (ac_number,debit,from_ac,balance) values(?,?,?,?);");
				ps.setLong(1, ac_number);
				ps.setLong(2, transferAmount);
				ps.setString(3, req.getParameter("to_account"));
				ps.setLong(4, my_current_balance);
				ps.executeUpdate();

				int remining_loan = 0;
				int emi = 0;
				ps = con.prepareStatement("select remining_amount,emi from customer_loan where loan_ac_number=?;");
				ps.setLong(1, to_ac_number);
				rs = ps.executeQuery();
				while (rs.next()) {
					remining_loan = rs.getInt("remining_amount");
					emi = rs.getInt("emi");
				}

				if (remining_loan == 0) {
					to_current_balance = to_current_balance + transferAmount;
					ps = con.prepareStatement("update customer_basic_deatils set current_balance=? where ac_number=?;");
					ps.setLong(1, to_current_balance);
					ps.setLong(2, to_ac_number);
					ps.executeUpdate();
				} else {
					remining_loan = (int) (remining_loan - transferAmount);
					emi = (int) Math.ceil(remining_loan / emi);
					if (emi > 0) {
						ps = con.prepareStatement(
								"update customer_loan set remining_amount=?,remining_emi=? where loan_ac_number=?;");
						ps.setLong(1, remining_loan);
						ps.setInt(2, emi);
						ps.setLong(3, to_ac_number);
						ps.executeUpdate();
					} else {
						ps = con.prepareStatement(
								"update customer_loan set remining_amount=?,remining_emi=?,stutas=? where loan_ac_number=?;");
						ps.setLong(1, remining_loan);
						ps.setInt(2, 0);
						ps.setString(3, "Closed");
						ps.setLong(4, to_ac_number);
						ps.executeUpdate();
					}
				}
				ps = con.prepareStatement(
						"insert into transction (from_ac,credit,ac_number,balance) values(?,?,?,?);");
				ps.setLong(1, ac_number);
				ps.setLong(2, transferAmount);
				ps.setLong(3, to_ac_number);
				ps.setLong(4, to_current_balance);
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
