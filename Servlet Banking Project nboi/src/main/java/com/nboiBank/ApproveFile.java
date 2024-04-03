package com.nboiBank;

import java.io.IOException;
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

@WebServlet("/approveFile")
public class ApproveFile extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		ServletContext context = req.getServletContext();
		String panndingType = req.getParameter("panndingType");
		int pandingLevel = (int) session.getAttribute("empLevel");
		try {
			PreparedStatement ps;
			Connection con = (Connection) context.getAttribute("connection");
			long temp_ac_number = Long.valueOf(req.getParameter("temp_ac_number"));
			long ac_number = Long.parseLong(req.getParameter("ac_number"));
			if (panndingType.equals("accuont")) {

				if (pandingLevel == 1) {
					ps = con.prepareStatement(
							"select ac_number from customer_basic_deatils where ac_number is not null order by ac_number desc limit 1;");
					ResultSet rs = ps.executeQuery();
					rs.next();
					// Genrate and update ac_number in customer_basic_deatils table
					long last_ac_number = rs.getLong(1);
					ps = con.prepareStatement(
							"update customer_basic_deatils set task_panding=?, ac_number=?,current_balance=10000 where temp_ac_num=?");
					ps.setInt(1, 0);
					ps.setLong(2, last_ac_number + 1);
					ps.setLong(3, temp_ac_number);
					ps.executeUpdate();
					// update ac_number in customer_address table
					ps = con.prepareStatement("update customer_address set ac_number=? where temp_ac_num=?");
					ps.setLong(1, last_ac_number + 1);
					ps.setLong(2, temp_ac_number);
					ps.executeUpdate();
					// update ac_number in customer_document table
					ps = con.prepareStatement("update customer_document set ac_number=? where temp_ac_num=?");
					ps.setLong(1, last_ac_number + 1);
					ps.setLong(2, temp_ac_number);
					ps.executeUpdate();

				} else {
					ps = con.prepareStatement("update customer_basic_deatils set task_panding=? where temp_ac_num=?");
					ps.setInt(1, pandingLevel - 1);
					ps.setLong(2, temp_ac_number);
					ps.executeUpdate();

				}

			} else {
				if (pandingLevel == 1) {
					long my_current_balance = 0;
					int loan_amount = 0;
					ps = con.prepareStatement("select current_balance from customer_basic_deatils where ac_number=?;");
					ps.setLong(1, ac_number);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						my_current_balance = rs.getLong("current_balance");
					}
					ps = con.prepareStatement("select loan_amount from customer_loan where loan_ac_number=?;");
					ps.setLong(1, temp_ac_number);
					rs = ps.executeQuery();
					while (rs.next()) {
						loan_amount = rs.getInt("loan_amount");
					}
					// approve loan of customer
					ps = con.prepareStatement(
							"update customer_loan set pannding_level=?,stutas=? where loan_ac_number=?");
					ps.setInt(1, pandingLevel - 1);
					ps.setString(2, "active");
					ps.setLong(3, temp_ac_number);
					ps.executeUpdate();

					my_current_balance = my_current_balance + loan_amount;
					ps = con.prepareStatement("update customer_basic_deatils set current_balance=? where ac_number=?;");
					ps.setLong(1, my_current_balance);
					ps.setLong(2, ac_number);
					ps.executeUpdate();

					// transction table create transction
					ps = con.prepareStatement(
							"insert into transction (from_ac,credit,ac_number,balance) values(?,?,?,?);");
					ps.setLong(1, ac_number);
					ps.setLong(2, loan_amount);
					ps.setLong(3, temp_ac_number);
					ps.setLong(4, my_current_balance);
					ps.executeUpdate();

					ps = con.prepareStatement(
							"insert into transction (from_ac,credit,ac_number,balance) values(?,?,?,?);");
					ps.setLong(1, temp_ac_number);
					ps.setLong(2, loan_amount);
					ps.setLong(3, ac_number);
					ps.setLong(4, loan_amount);
					ps.executeUpdate();

				} else {
					ps = con.prepareStatement("update customer_loan set pannding_level=? where loan_ac_number=?");
					ps.setInt(1, pandingLevel - 1);
					ps.setLong(2, temp_ac_number);
					ps.executeUpdate();

				}
			}
			session.removeAttribute("panndingType");
			int pandingTask=(int)session.getAttribute("totalPandingTask");
			session.setAttribute("totalPandingTask", pandingTask-1);
			resp.sendRedirect("panndigTask?page=1");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
