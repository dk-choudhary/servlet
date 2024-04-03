package com.hidden;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionOracle {

	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "123456";

	public static Connection getConnection() throws SQLException {
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			throw new SQLException("Oracle JDBC driver not found.", e);
		}
	}

	public static void createUserTable(Connection conn) throws SQLException {
		try {

			java.sql.Statement stmt = conn.createStatement();

			String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" + "id INT AUTO_INCREMENT PRIMARY KEY,"
					+ "username VARCHAR(50) NOT NULL," + "password VARCHAR(50) NOT NULL)";
			stmt.executeUpdate(createTableSQL);
			System.out.println("User table created successfully!");
		} catch (SQLException e) {
			throw new SQLException("Error creating user table.", e);
		}
	}

	public static void insertUserData(Connection conn, String username, String password) throws SQLException {
		try {

			String insertUserSQL = "INSERT INTO users (username, password) VALUES (?, ?)";

			java.sql.PreparedStatement pstmt = conn.prepareStatement(insertUserSQL);

			pstmt.setString(1, username);
			pstmt.setString(2, password);

			pstmt.executeUpdate();
			System.out.println("User data inserted successfully!");
		} catch (SQLException e) {
			throw new SQLException("Error inserting user data.", e);
		}
	}

	public static void deleteUserData(Connection conn, String username) throws SQLException {
		try {

			String deleteUserSQL = "DELETE FROM users WHERE username = ?";

			java.sql.PreparedStatement pstmt = conn.prepareStatement(deleteUserSQL);

			pstmt.setString(1, username);

			pstmt.executeUpdate();
			System.out.println("User data deleted successfully!");
		} catch (SQLException e) {
			throw new SQLException("Error deleting user data.", e);
		}
	}
}
