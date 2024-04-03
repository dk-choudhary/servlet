package com.userin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Extract form data
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String dob = request.getParameter("dob");
		String fathername = request.getParameter("fathername");
		String address = request.getParameter("address");
		String mobile = request.getParameter("mobile");
		String pan = request.getParameter("pan");
		String aadhar = request.getParameter("aadhar");

		// Get database connection from servlet context
		ServletContext context = getServletContext();
		Connection connection = (Connection) context.getAttribute("con");

		try {
			// SQL query to insert user data
			String sql = "INSERT INTO register (username, password, dob, fathername, address, mobile, pan, aadhar) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, username);
			statement.setString(2, password);
			statement.setString(3, dob);
			statement.setString(4, fathername);
			statement.setString(5, address);
			statement.setString(6, mobile);
			statement.setString(7, pan);
			statement.setString(8, aadhar);

			// Execute the query
			int rowsInserted = statement.executeUpdate();

			if (rowsInserted > 0) {
				response.setContentType("text/html");
				response.getWriter().println("<html><body><h1>Registration Successful!</h1></body></html>");
			} else {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to register user.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
		}
	}
}
