package com.userin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogIn extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext context = getServletContext();
		Connection con = (Connection) context.getAttribute("con");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		// Retrieve username and password from request parameters
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		// check if the username and password exist in the database
		String query = "SELECT  balance FROM register WHERE username = ? AND password = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			HttpSession session = req.getSession();
			session.setAttribute("username", username);
			if (rs.next()) {
			
				 String balance = rs.getString("balance");

				out.print("<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "<head>\r\n"
						+ "<meta charset=\"UTF-8\">\r\n"
						+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
						+ "<title>Bank Website</title>\r\n" + "<style>\r\n" + "  body {\r\n"
						+ "    font-family: Arial, sans-serif;\r\n" + "    margin: 0;\r\n" + "    padding: 0;\r\n"
						+ "  }\r\n" + "  header {\r\n" + "    background-color: #333;\r\n" + "    color: #fff;\r\n"
						+ "    padding: 20px;\r\n" + "    text-align: center;\r\n" + "    display: flex;\r\n"
						+ "    justify-content: space-between;\r\n" + "  }\r\n" + "  #user-info {\r\n"
						+ "    display: flex;\r\n" + "    align-items: center;\r\n" + "  }\r\n" + "  #user-info p {\r\n"
						+ "    margin-right: 10px;\r\n" + "  }\r\n" + "  #user-info button {\r\n"
						+ "    padding: 5px 10px;\r\n" + "    background-color: #ccc;\r\n" + "    color: #333;\r\n"
						+ "    border: none;\r\n" + "    border-radius: 5px;\r\n" + "    cursor: pointer;\r\n"
						+ "  }\r\n" + "  #user-info button:hover {\r\n" + "    background-color: #999;\r\n"
						+ "    color: #fff;\r\n" + "  }\r\n" + "  footer {\r\n" + "    background-color: #333;\r\n"
						+ "    color: #fff;\r\n" + "    padding: 20px;\r\n" + "    text-align: center;\r\n"
						+ "    position: fixed;\r\n" + "    bottom: 0;\r\n" + "    width: 100%;\r\n" + "  }\r\n"
						+ "  #container {\r\n" + "    display: flex;\r\n" + "    justify-content: space-between;\r\n"
						+ "    padding: 20px;\r\n" + "    margin-bottom: 60px; /* Adjusted for footer */\r\n"
						+ "  }\r\n" + "  #options {\r\n" + "    flex: 1;\r\n" + "    margin-right: 20px;\r\n"
						+ "    padding: 20px;\r\n" + "    background-color: #f4f4f4;\r\n"
						+ "    font-size: 18px; /* Increased font size */\r\n" + "  }\r\n" + "  #options h2 {\r\n"
						+ "    border-bottom: 2px solid #ccc;\r\n" + "    padding-bottom: 10px;\r\n" + "  }\r\n"
						+ "  #options ul {\r\n" + "    list-style-type: none;\r\n" + "    padding: 0;\r\n" + "  }\r\n"
						+ "  #options ul li {\r\n" + "    margin-bottom: 10px;\r\n" + "  }\r\n"
						+ "  #options ul li a {\r\n" + "    display: block;\r\n" + "    padding: 10px;\r\n"
						+ "    text-decoration: none;\r\n" + "    color: #333;\r\n" + "    background-color: #ccc;\r\n"
						+ "    border: 1px solid #999;\r\n" + "    border-radius: 5px;\r\n" + "  }\r\n"
						+ "  #options ul li a:hover {\r\n" + "    background-color: #999;\r\n" + "    color: #fff;\r\n"
						+ "  }\r\n" + "  #account-table {\r\n" + "    flex: 2;\r\n" + "    padding: 20px;\r\n"
						+ "  }\r\n" + "  #account-table h2 {\r\n" + "    margin-bottom: 20px;\r\n" + "  }\r\n"
						+ "  table {\r\n" + "    width: 100%;\r\n" + "    border-collapse: collapse;\r\n" + "  }\r\n"
						+ "  th, td {\r\n" + "    padding: 8px;\r\n" + "    border-bottom: 1px solid #ddd;\r\n"
						+ "  }\r\n" + "  th {\r\n" + "    background-color: #f2f2f2;\r\n" + "    text-align: left;\r\n"
						+ "  }\r\n" + "</style>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n" + "<header>\r\n"
						+ "  <h1> Bank Of India</h1>\r\n" + "  <div id=\"user-info\">\r\n" + "    <p>Welcome " + username
						+ "</p>\r\n" + "     <button onclick=\"logout()\">Logout</button>\r\n" +" <script>\r\n"
								+ "        function logout() {\r\n"
								+ "            window.location.href = \"logout\";\r\n"
								+ "        }\r\n"
								+ "    </script>"+ "  </div>\r\n" + "</header>\r\n" + "\r\n"
						+ "<div id=\"container\">\r\n" + "  <div id=\"options\">\r\n" + "    <h2>Our Services</h2>\r\n"
						+ "    <ul>\r\n"

						+ "      <li><a href=\"Personal_Details\">Personal Details</a></li>\r\n"
						+ "<li><a href=\"edit_user\"> Edit Account Details</a></li>\r\n"
						+ "      <li><a href=\"transaction_history.html\">Transaction History Download</a></li>\r\n"
						+ "      <li><a href=\"transfer_funds.html\">Fund Transfer</a></li>\r\n"
						+ "      <li><a href=\"#\">ATM Withdrawal</a></li>\r\n"
						+ "      <li><a href=\"apply_loan.html\">Apply For Loan</a></li>\r\n"
						+ "      <li><a href=\"bill_payment.html\">Bill Payment</a></li>\r\n"
						+ "            <li><a href=\"manage_cards.html\"> Manage Cards</a></li>\r\n"

						+ "      <!-- Add more options as needed -->\r\n" + "    </ul>\r\n" + "  </div>\r\n"
						+ "  <div id=\"account-table\">\r\n" + "    <h2>Account Details</h2>\r\n" + "    <table>\r\n"
						+ "      <thead>\r\n" + "        <tr>\r\n" + "          <th>Account Type</th>\r\n"
						+ "          <th>Balance</th>\r\n" + "          <th>Transaction</th>\r\n" + "        </tr>\r\n"
						+ "      </thead>\r\n" + "      <tbody>\r\n" + "        <tr>\r\n"
						+ "          <td>Savings</td>\r\n" + "          <td>"+balance+"</td>\r\n"
						+ "          <td><a href=\"#\">View Transactions</a></td>\r\n" + "        </tr>\r\n"
						+ "        <tr>\r\n"  + "        </tr>\r\n"
						+ "        <!-- Add more rows for other accounts -->\r\n" + "      </tbody>\r\n"
						+ "    </table>\r\n" + "  </div>\r\n" + "</div>\r\n" + "\r\n" + "<footer>\r\n"
						+ "  <p>&copy; 2024 Bank Website. All Rights Reserved.</p>\r\n" + "</footer>\r\n" + "\r\n"
						+ "</body>\r\n" + "</html>");

			} else {
				// Invalid username or password
				//out.println("<h5>Invalid username or password</h5>");
			    out.println("<h3 style=\"position: fixed; top: 0; left: 0; width: 100%; background-color: #ffffff; padding: 10px; text-align: center; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); z-index: 999;\">Invalid username or password</h3>");

				RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.html");
				requestDispatcher.include(req, resp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("<h1>Error occurred. Please try again later.</h1>");
		}
	}
}
