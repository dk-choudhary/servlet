package com.nboiBank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/finalSubmit")
public class NewCustomerFinalSubmit extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		HttpSession session = req.getSession(false);
		String customer = (String) session.getAttribute("customer");
		Connection connection = (Connection) getServletContext().getAttribute("connection");
        
		try {
			PreparedStatement ps=connection.prepareStatement("update Customer_basic_deatils set stutas=true where temp_ac_num=?;");
			ps.setLong(1,(long)session.getAttribute("temp_ac_num"));
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String popup="<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Pop-up Message</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "\r\n"
				+ "    <script>\r\n"
				+ "        window.onload = function() {\r\n"
				+ "            // Show pop-up message\r\n"
				+ "            alert(\"Your Application is successfully submited. Your Temporary Account Number:"
				+session.getAttribute("temp_ac_num")+" and Password is: "+session.getAttribute("password")+"\");\r\n"
				+ "\r\n"
				+ "            // Redirect to home page after 2 seconds\r\n"
				+ "            setTimeout(function() {\r\n"
				+ "                window.location.href = \"BankLoginPage.html\";\r\n"
				+ "            }, 20); // 2000 milliseconds = 2 seconds\r\n"
				+ "        };\r\n"
				+ "    </script>\r\n"
				+ "</body>\r\n"
				+ "</html>\r\n";
		session.invalidate();
		resp.getWriter().println(popup);
	}
}
