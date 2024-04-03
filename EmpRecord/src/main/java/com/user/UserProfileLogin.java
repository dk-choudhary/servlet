package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserProfileLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection connection;
	static PrintWriter out;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("text/html");
		out = response.getWriter();

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "123456");
		

		} catch (Exception e) {
			out.println("Error occurred: " + e.getMessage());

		}

		try {
			if (isUserRegistered(email, password)) {

				String selectQuery = "SELECT * FROM emp";
				PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
				ResultSet resultSet = selectStatement.executeQuery();

				out.println("<html><head>");
				out.println("<style>");
				out.println(
						"body { font-family: 'Arial', sans-serif; background-color: #f7f7f7; margin: 0; padding: 0; }");
				out.println("h2 { color: #3498db; text-align: center; padding: 20px 0; }");
				out.println(
						"table { border-collapse: collapse; width: 80%; margin: 20px auto; background-color: #ffffff; box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1); }");
				out.println(
						"th, td { border: 1px solid #dddddd; text-align: left; padding: 15px; transition: background-color 0.3s; }");
				out.println("th { background-color: #2ecc71; color: #ffffff; }");
				out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
				out.println("tr:hover { background-color: #e5e5e5; }");
				out.println("td { color: #000000; }");
				out.println("</style>");
				out.println("</head><body>");

				  out.println("<h2>User Information</h2>");
				    out.println("<table>");
				    out.println("<tr><th>ID</th><th>Name</th><th>Email</th><th>Gender</th><th>Weight</th><th>Height</th><th>Age</th><th>Action</th></tr>");
				    while (resultSet.next()) {
				        out.println("<tr>");
				        out.println("<td>" + resultSet.getInt("id") + "</td>");
				        out.println("<td>" + resultSet.getString("name") + "</td>");
				        out.println("<td>" + resultSet.getString("email") + "</td>");
				        out.println("<td>" + resultSet.getString("gender") + "</td>");
				        out.println("<td>" + resultSet.getString("weight") + "</td>");
				        out.println("<td>" + resultSet.getString("height") + "</td>");
				        String dob = resultSet.getString("dob");
				        int age = calculateAge(dob);
				        out.println("<td>" + age + "</td>");

				        // Adding buttons for each row
				        int intId = resultSet.getInt("id");
				        String id = String.valueOf(intId);
				        out.println("<td>");
				        out.println("<a href='UserEdit?id="+id+"'>Edit</a>");
				        out.println("<a href='#' class='btn btn-danger' onclick='deleteUser(" + resultSet.getInt("id") + ")'>Delete</a>");
				        out.println("<a href='#' class='btn btn-info' onclick='viewUser(" + resultSet.getInt("id") + ")'>View</a>");
				        out.println("</td>");
				        out.println("</tr>");
				    }

				    out.println("</table>");
				    out.println("</body></html>");

				resultSet.close();
				selectStatement.close();

			} else {
				out.println("<html><body>");
				out.println(" or User email or password doesn't matched !");
				out.println("</body></html>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public boolean isUserRegistered(String email, String password) throws Exception {
		String selectQuery = "SELECT * FROM emp WHERE email = ?";// AND password = ?";

		try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
			selectStatement.setString(1, email);
			// selectStatement.setString(2, password);

			try (ResultSet resultSet = selectStatement.executeQuery()) {
				if (resultSet.next()) {
					String selectQuery1 = "SELECT * FROM emp WHERE password = ?";
					try (PreparedStatement selectStatement1 = connection.prepareStatement(selectQuery1)) {
						selectStatement1.setString(1, password);
						try (ResultSet resultSet1 = selectStatement1.executeQuery()) {
							return resultSet1.next();
						}
					}
				}
				out.println("your email is not registered");
				return false;
			}
		}
	}

	private int calculateAge(String dob) {
		// Implement logic to calculate age from the date of birth
		// Assuming dob is in the format "yyyy-MM-dd"
		String[] dobParts = dob.split("-");
		int yearOfBirth = Integer.parseInt(dobParts[0]);
		int currentYear = java.time.Year.now().getValue();
		return currentYear - yearOfBirth;
	}
}
