package com.userin;

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

@WebServlet("/Personal_Details")
public class Personal_Details extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		ServletContext context = getServletContext();
		Connection con = (Connection) context.getAttribute("con");
		HttpSession session = req.getSession(false);
		String username = (String) session.getAttribute("username");

		String query = "SELECT * FROM register WHERE username = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String username1 = rs.getString("username");
				String password = rs.getString("password");
				String dob = rs.getString("dob");
				String fathername = rs.getString("fathername");
				String address = rs.getString("address");
				String mobile = rs.getString("mobile");
				String pan = rs.getString("pan");
				String aadhar = rs.getString("aadhar");

				// Generating HTML content dynamically
				out.println("<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "<head>\r\n"
						+ "    <meta charset=\"UTF-8\">\r\n"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
						+ "    <title>Personal Details</title>\r\n" + "    <style>\r\n"
						+ "        /* CSS styles for improved appearance */\r\n" + "        body {\r\n"
						+ "            font-family: Arial, sans-serif;\r\n"
						+ "            background-color: #f8f8f8;\r\n" + "            margin: 0;\r\n"
						+ "            padding: 0;\r\n" + "        }\r\n" + "        .container {\r\n"
						+ "            max-width: 800px;\r\n" + "            margin: 20px auto;\r\n"
						+ "            background-color: #fff;\r\n" + "            border-radius: 10px;\r\n"
						+ "            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);\r\n"
						+ "            padding: 20px;\r\n" + "        }\r\n" + "        h2 {\r\n"
						+ "            color: #333;\r\n" + "            text-align: center;\r\n"
						+ "            margin-bottom: 20px;\r\n" + "        }\r\n" + "        table {\r\n"
						+ "            width: 100%;\r\n" + "            border-collapse: collapse;\r\n"
						+ "            margin-top: 20px;\r\n" + "        }\r\n" + "        th, td {\r\n"
						+ "            padding: 10px;\r\n" + "            border-bottom: 1px solid #ddd;\r\n"
						+ "            text-align: left;\r\n" + "        }\r\n" + "        th {\r\n"
						+ "            background-color: #f2f2f2;\r\n" + "            color: #222;\r\n"
						+ "        }\r\n" + "        td {\r\n" + "            color: #000;\r\n" + "        }\r\n"
						+ "    </style>\r\n" + "</head>\r\n" + "<body>\r\n" + "    <div class=\"container\">\r\n"
						+ "        <h2>Personal Details</h2>\r\n" + "        <table>\r\n" + "            <tr>\r\n"
						+ "                <th>Field</th>\r\n" + "                <th>Value</th>\r\n"
						+ "            </tr>\r\n" + "            <tr>\r\n" + "                <td>Username</td>\r\n"
						+ "                <td >" + username + "</td>\r\n"

						+ "            </tr>\r\n" + "            <tr>\r\n" + "                <td>Password</td>\r\n"
						+ "                <td >" + password + "</td>\r\n" + "            </tr>\r\n"
						+ "            <tr>\r\n" + "                <td>Date of Birth</td>\r\n"
						+ "                <td >" + dob + "</td>\r\n" + "            </tr>\r\n" + "            <tr>\r\n"
						+ "                <td>Father's Name</td>\r\n" + "                <td >" + fathername
						+ "</td>\r\n" + "            </tr>\r\n" + "            <tr>\r\n"
						+ "                <td>Address</td>\r\n" + "                <td >" + address + "</td>\r\n"
						+ "            </tr>\r\n" + "            <tr>\r\n" + "                <td>Mobile</td>\r\n"
						+ "                <td >" + mobile + "</td>\r\n" + "            </tr>\r\n"
						+ "            <tr>\r\n" + "                <td>PAN</td>\r\n" + "                <td >" + pan
						+ "</td>\r\n" + "            </tr>\r\n" + "            <tr>\r\n"
						+ "                <td>Aadhar</td>\r\n" + "                <td >" + aadhar + "</td>\r\n"
						+ "            </tr>\r\n" + "        </table>\r\n" + "    </div>\r\n" + "</body>\r\n"
						+ "</html>\r\n" + "");

			} else {
				out.println("No data found for the user!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
