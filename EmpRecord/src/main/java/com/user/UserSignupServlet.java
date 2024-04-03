package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserSignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Connection connection;

    @Override
    public void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://localhost:3306/servlet";
            String username = "root";
            String dbPassword = "123456";
            connection = DriverManager.getConnection(jdbcUrl, username, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");
        int height = Integer.valueOf(request.getParameter("height"));
        int weight = Integer.valueOf(request.getParameter("weight"));

        try {
            if (insertUser(name, email, dob, gender, password, height, weight, out)) {
                out.println("<html><body>");
                out.println("User registered successfully!");
                out.println("</body></html>");
            } else {
                out.println("<html><body>");
                out.println("Failed to register user.");
                out.println("</body></html>");
            }
        } catch (Exception e) {
            out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean insertUser(String name, String email, String dob, String gender, String password, int height, int weight, PrintWriter out)
            throws SQLException {

        String insertQuery = "INSERT INTO emp (name, email, dob, password, gender, height, weight) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
            insertStatement.setString(1, name);
            insertStatement.setString(2, email);
            insertStatement.setString(3, dob);
            insertStatement.setString(4, password);
            insertStatement.setString(5, gender);
            insertStatement.setInt(6, height);
            insertStatement.setInt(7, weight);

            String selectQuery = "SELECT * FROM emp WHERE email = ?";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                selectStatement.setString(1, email);
                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    if (!resultSet.next()) {
                        int rowsAffected = insertStatement.executeUpdate();
                        return rowsAffected > 0;
                    } else {
                        out.println("Your email is already registered");
                        return false;
                    }
                }
            }
        }
    }
}
