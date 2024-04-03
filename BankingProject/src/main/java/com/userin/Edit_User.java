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

@WebServlet("/edit_user")
public class Edit_User extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		ServletContext context = getServletContext();
		Connection con = (Connection) context.getAttribute("con");
		HttpSession sesson = req.getSession(false);
		String username = (String) sesson.getAttribute("username");
		String query = "SELECT * FROM register WHERE username = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String username1 = rs.getString("username");
				String password = rs.getString("password");
				String dob = rs.getString("dob");
				String fathername = rs.getString("fathername");
				String address = rs.getString("address");
				String mobile = rs.getString("mobile");
				String pan = rs.getString("pan");
				String aadhar = rs.getString("aadhar");

				out.print("<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "\r\n" + "<head>\r\n"
						+ "    <meta charset=\"UTF-8\">\r\n"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
						+ "    <title>Edit Account Details</title>\r\n" + "    <style>\r\n" + "        body {\r\n"
						+ "            font-family: Arial, sans-serif;\r\n"
						+ "            background-color: #f0f0f0;\r\n" + "            margin: 0;\r\n"
						+ "            padding: 0;\r\n" + "        }\r\n" + "        h1 {\r\n"
						+ "            text-align: center;\r\n" + "            color: #333;\r\n" + "        }\r\n"
						+ "        form {\r\n" + "            max-width: 500px;\r\n"
						+ "            margin: 20px auto;\r\n" + "            background-color: #fff;\r\n"
						+ "            border-radius: 5px;\r\n" + "            padding: 20px;\r\n"
						+ "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n" + "        }\r\n"
						+ "        label {\r\n" + "            font-weight: bold;\r\n"
						+ "            display: block;\r\n" + "            margin-bottom: 5px;\r\n" + "        }\r\n"
						+ "        input[type='text'], input[type='password'], input[type='date'], input[type='tel'] {\r\n"
						+ "            width: 100%;\r\n" + "            padding: 10px;\r\n"
						+ "            margin-bottom: 10px;\r\n" + "            border: 1px solid #ccc;\r\n"
						+ "            border-radius: 3px;\r\n" + "            box-sizing: border-box;\r\n"
						+ "        }\r\n" + "        textarea {\r\n" + "            width: 100%;\r\n"
						+ "            padding: 10px;\r\n" + "            margin-bottom: 10px;\r\n"
						+ "            border: 1px solid #ccc;\r\n" + "            border-radius: 3px;\r\n"
						+ "            box-sizing: border-box;\r\n" + "        }\r\n"
						+ "        input[type='submit'] {\r\n" + "            background-color: #4C0050;\r\n"
						+ "            color: white;\r\n" + "            padding: 10px 20px;\r\n"
						+ "            border: none;\r\n" + "            border-radius: 3px;\r\n"
						+ "            cursor: pointer;\r\n" + "            font-size: 16px;\r\n" + "        }\r\n"
						+ "        input[type='submit']:hover {\r\n" + "            background-color: #000000;\r\n"
						+ "        }\r\n" + "    </style>\r\n" + "</head>\r\n" + "\r\n" + "<body>\r\n"
						+ "    <h1>Edit Account Details</h1>\r\n"
						+ "    <form action=\"Edit_User_Action\" method=\"post\">\r\n"
						+ "        <label for=\"username\">Username</label>\r\n"
						+ "        <input type=\"text\" id=\"username\" name=\"username\" value=\"" + username1
						+ "\" disabled><br>\r\n" + "\r\n" + "        <label for=\"password\">Password</label>\r\n"
						+ "        <input type=\"password\" id=\"password\" name=\"password\" value=\"" + password
						+ "\" required><br>\r\n" + "\r\n" + "        <label for=\"dob\">Date of Birth</label>\r\n"
						+ "        <input type=\"date\" id=\"dob\" name=\"dob\" value=\"" + dob + "\" required><br>\r\n"
						+ "\r\n" + "        <label for=\"fathername\">Father's Name</label>\r\n"
						+ "        <input type=\"text\" id=\"fathername\" name=\"fathername\" value=\"" + fathername
						+ "\" required><br>\r\n" + "\r\n" + "        <label for=\"address\">Address</label>\r\n"
						+ "        <textarea id=\"address\" name=\"address\" rows=\"4\" required>" + address
						+ "</textarea><br>\r\n" + "\r\n" + "        <label for=\"mobile\">Mobile Number</label>\r\n"
						+ "        <input type=\"tel\" id=\"mobile\" name=\"mobile\" value=\"" + mobile
						+ "\" pattern=\"[0-9]{10}\" required><br>\r\n" + "\r\n"
						+ "        <label for=\"pan\">PAN Card Number</label>\r\n"
						+ "        <input type=\"text\" id=\"pan\" name=\"pan\" value=\"" + pan + "\" required><br>\r\n"
						+ "\r\n" + "        <label for=\"aadhar\">Aadhar Card Number</label>\r\n"
						+ "        <input type=\"text\" id=\"aadhar\" name=\"aadhar\" value=\"" + aadhar
						+ "\" required><br>\r\n" + "\r\n"
						+ "        <input type=\"submit\" value=\"Update Details\">\r\n" + "    </form>\r\n"
						+ "</body>\r\n" + "\r\n" + "</html>\r\n" + "");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
