package com.hidden;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlConnection {
	 
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/fieldhiddenservlet";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "123456";

  

	static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	
		return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	
	}

	public static void createUserTable() throws ClassNotFoundException {
		try (Connection conn = getConnection()) {
			Statement stmt = conn.createStatement();
			String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" + "id INT AUTO_INCREMENT PRIMARY KEY,"
					+ "username VARCHAR(50) NOT NULL," + "password VARCHAR(50) NOT NULL)";
			stmt.executeUpdate(createTableSQL);
			System.out.println("User table created successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertUserData(String username, String password) throws ClassNotFoundException {
		try (Connection conn = getConnection()) {
			String insertUserSQL = "INSERT INTO users (username, password) VALUES (?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(insertUserSQL);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.executeUpdate();
			System.out.println("User data inserted successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteUserData(String username) throws ClassNotFoundException {
		try (Connection conn = getConnection()) {
			String deleteUserSQL = "DELETE FROM users WHERE username = ?";
			PreparedStatement pstmt = conn.prepareStatement(deleteUserSQL);
			pstmt.setString(1, username);
			pstmt.executeUpdate();
			System.out.println("User data deleted successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
