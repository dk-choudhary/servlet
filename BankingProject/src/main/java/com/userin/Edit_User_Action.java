package com.userin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Edit_User_Action")
public class Edit_User_Action extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		HttpSession sesson=request.getSession(false);
		String username=(String) sesson.getAttribute("username");

		String updatedUsername =username;
		String updatedPassword = request.getParameter("password");
		String updatedDob = request.getParameter("dob");
		String updatedFathername = request.getParameter("fathername");
		String updatedAddress = request.getParameter("address");
		String updatedMobile = request.getParameter("mobile");
		String updatedPan = request.getParameter("pan");
		String updatedAadhar = request.getParameter("aadhar");

		ServletContext context = getServletContext();
		Connection connection = (Connection) context.getAttribute("con");

		try {
			// SQL query to insert user data
			String updateQuery = "UPDATE register SET username=?, password=?, dob=?, fathername=?, address=?, mobile=?, pan=?, aadhar=? WHERE username=?";
			PreparedStatement updateStmt = connection.prepareStatement(updateQuery);

			// Set parameters for the update statement
			updateStmt.setString(1, updatedUsername);
			updateStmt.setString(2, updatedPassword);
			updateStmt.setString(3, updatedDob);
			updateStmt.setString(4, updatedFathername);
			updateStmt.setString(5, updatedAddress);
			updateStmt.setString(6, updatedMobile);
			updateStmt.setString(7, updatedPan);
			updateStmt.setString(8, updatedAadhar);
			updateStmt.setString(9, updatedUsername);

			int rowsUpdated = updateStmt.executeUpdate();

			if (rowsUpdated > 0) {
				response.setContentType("text/html");
				
				  response.getWriter().print("<h3 style=\"position: fixed; top: 0; left: 0; width: 100%; background-color: #ffffff; padding: 10px; text-align: center; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); z-index: 999;\">Updated  Successful!</h3>");

				 // RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.html");
				//	requestDispatcher.include(request, response);

			} else {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update user.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
		}
	}
}
