package com.nboiBank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/update_user")
public class UpdateUserPassword extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("text/html");
	HttpSession session = req.getSession(false);
	Connection connection = (Connection) getServletContext().getAttribute("connection");
	try {
		PreparedStatement ps = connection.prepareStatement("update customer_basic_deatils set user_id=?, password=?, task_panding=-1 where temp_ac_num=?;");
		ps.setString(1, req.getParameter("newUsername"));
		ps.setString(2, req.getParameter("newPassword"));
		ps.setString(3, (String) session.getAttribute("temp_ac_num"));
		ps.executeUpdate();
		session.invalidate();

		resp.sendRedirect("userPasswordUpdatedPopup.html");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


}
}
