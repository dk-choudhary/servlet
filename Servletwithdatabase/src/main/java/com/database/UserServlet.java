package com.database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static Connection connection ;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Load JDBC driver
        	
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            String jdbcUrl = "jdbc:mysql://localhost:3306/servlet";
            String username = "root";
            String password = "123456";
             connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Perform CRUD operations
            String action = request.getParameter("action");

            if ("create".equalsIgnoreCase(action)) {
                String insertQuery = "INSERT INTO users (username,email, password) VALUES (?,?, ?)";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                insertStatement.setString(1, request.getParameter("username"));
                insertStatement.setString(2, request.getParameter("email"));
                insertStatement.setString(3, request.getParameter("password"));
                
                insertStatement.executeUpdate();
                insertStatement.close();
                out.println("User created successfully!");
            } else if ("read".equalsIgnoreCase(action)) {
                String selectQuery = "SELECT * FROM users";
                PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
                ResultSet resultSet = selectStatement.executeQuery();

                out.println("<html><body>");
                while (resultSet.next()) {
                    out.println("ID: " + resultSet.getInt("id") + "<br>");
                    out.println("Username: " + resultSet.getString("username") + "<br>");
                    out.println("email: " + resultSet.getString("email") + "<br>");
                    out.println("Password: " + resultSet.getString("password") + "<br><br>");
                }
                out.println("</body></html>");

                resultSet.close();
                selectStatement.close();
            } else if ("update".equalsIgnoreCase(action)) {
                // Add code for update operation
            } else if ("delete".equalsIgnoreCase(action)) {
               doDelete(request, response);
            } else {
                out.println("Invalid action!");
            }

            // Close resources
            connection.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
    
  @Override
protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	String deletQuery = "delete FROM users where username=? ";
	try {	
		PreparedStatement Statement = connection.prepareStatement(deletQuery);
		Statement.setString(1, req.getParameter("username"));
		int result_set = Statement.executeUpdate();
		// Statement.execute();
		System.out.println(result_set);
		Statement.close();
	} catch (Exception e) {
		System.out.println("++++++++++++++++++");
	}
}
}
