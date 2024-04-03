package com.nboiBank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/showAllLoanAccount")
public class ShowAllLoanAccountByBankEmp extends HttpServlet {
	static int totalRow=0;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext context = req.getServletContext();
		PrintWriter pw = resp.getWriter();
		HttpSession session=req.getSession();
		String page = "<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "<title>Bank Website</title>\r\n" + "<style>\r\n" + "body {\r\n"
				+ "	font-family: Arial, sans-serif;\r\n" + "	margin: 0;\r\n" + "	padding: 0;\r\n" + "}\r\n" + "\r\n"
				+ "header {\r\n" + "	background-color: #333;\r\n" + "	color: #fff;\r\n" + "	padding: 20px;\r\n"
				+ "	text-align: center;\r\n" + "	display: flex;\r\n" + "	justify-content: space-between;\r\n"
				+ "	align-items: flex-end; /* Align items to the bottom */\r\n" + "}\r\n" + "\r\n" + "#user-info {\r\n"
				+ "	display: flex;\r\n" + "	align-items: center;\r\n" + "}\r\n" + "\r\n" + "#user-info p {\r\n"
				+ "	margin-right: 10px;\r\n" + "}\r\n" + "\r\n" + "#user-info button {\r\n"
				+ "	padding: 8px 12px; /* Adjusted padding */\r\n"
				+ "	background-color: #4CAF50; /* Changed button color */\r\n" + "	color: white;\r\n"
				+ "	border: none;\r\n" + "	border-radius: 5px;\r\n" + "	cursor: pointer;\r\n" + "}\r\n" + "\r\n"
				+ "#user-info button:hover {\r\n" + "	background-color: #45a049; /* Hover color */\r\n" + "}\r\n"
				+ "\r\n" + "footer {\r\n" + "	background-color: #333;\r\n" + "	color: #fff;\r\n"
				+ "	padding: 20px;\r\n" + "	text-align: center;\r\n" + "	position: fixed;\r\n" + "	bottom: 0;\r\n"
				+ "	width: 100%;\r\n" + "}\r\n" + "\r\n" + "#container {\r\n" + "	display: flex;\r\n"
				+ "	justify-content: space-between;\r\n" + "	padding: 20px;\r\n"
				+ "	margin-bottom: 60px; /* Adjusted for footer */\r\n" + "}\r\n" + "\r\n" + "#options {\r\n"
				+ "	flex: 1;\r\n" + "	margin-right: 20px;\r\n" + "	padding: 20px;\r\n"
				+ "	background-color: #f4f4f4;\r\n" + "	font-size: 18px; /* Increased font size */\r\n" + "}\r\n"
				+ "\r\n" + "#options h2 {\r\n" + "	border-bottom: 2px solid #ccc;\r\n" + "	padding-bottom: 10px;\r\n"
				+ "}\r\n" + "\r\n" + "#options ul {\r\n" + "	list-style-type: none;\r\n" + "	padding: 0;\r\n"
				+ "}\r\n" + "\r\n" + "#options ul li {\r\n" + "	margin-bottom: 10px;\r\n" + "}\r\n" + "\r\n"
				+ "#options ul li a {\r\n" + "	display: block;\r\n" + "	padding: 10px;\r\n"
				+ "	text-decoration: none;\r\n" + "	color: #333;\r\n" + "	background-color: #ccc;\r\n"
				+ "	border: 1px solid #999;\r\n" + "	border-radius: 5px;\r\n" + "}\r\n" + "\r\n"
				+ "#options ul li a:hover {\r\n" + "	background-color: #999;\r\n" + "	color: #fff;\r\n" + "}\r\n"
				+ "\r\n" + "#fund-transfer-form {\r\n" + "	flex: 2;\r\n" + "	padding: 20px;\r\n"
				+ "	width: 300px; /* Adjusted width */\r\n" + "	padding-left: 10%;\r\n" + "}\r\n" + "\r\n"
				+ "#fund-transfer-form h2 {\r\n" + "	margin-bottom: 20px;\r\n" + "	padding-left: 200px;\r\n"
				+ "}\r\n" + "\r\n" + "#fund-transfer-form label {\r\n" + "	display: block;\r\n"
				+ "	margin-bottom: 10px;\r\n" + "}\r\n" + "\r\n" + "#fund-transfer-form input[type=\"text\"] {\r\n"
				+ "	width: 50%;\r\n" + "	padding: 10px;\r\n" + "	margin-bottom: 10px;\r\n"
				+ "	border: 1px solid #ccc;\r\n" + "	border-radius: 4px;\r\n" + "	box-sizing: border-box;\r\n"
				+ "	height: 40px; /* Increased height */\r\n" + "}\r\n" + "\r\n"
				+ "#fund-transfer-form input[type=\"number\"], #fund-transfer-form button[type=\"submit\"]\r\n"
				+ "	{\r\n" + "	width: 20%;\r\n" + "	padding: 10px;\r\n" + "	margin-bottom: 10px;\r\n"
				+ "	border: 1px solid #ccc;\r\n" + "	border-radius: 4px;\r\n" + "	box-sizing: border-box;\r\n"
				+ "	height: 40px; /* Increased height */\r\n" + "}\r\n" + "\r\n"
				+ "#fund-transfer-form button[type=\"submit\"] {\r\n"
				+ "	background-color: #4CAF50; /* Changed button color */\r\n" + "	color: white;\r\n"
				+ "	border: none;\r\n" + "	border-radius: 4px;\r\n" + "	cursor: pointer;\r\n"
				+ "	font-size: 16px;\r\n" + "}\r\n" + "\r\n" + "#fund-transfer-form button[type=\"submit\"]:hover {\r\n"
				+ "	background-color: #45a049; /* Hover color */\r\n" + "}\r\n" + "  #account-table {\r\n"
				+ "    flex: 2;\r\n" + "    padding: 20px;\r\n" + "  }\r\n" + "  #account-table h2 {\r\n"
				+ "    margin-bottom: 20px;\r\n" + "  }\r\n" + "  table {\r\n" + "    width: 100%;\r\n"
				+ "    border-collapse: collapse;\r\n" + "  }\r\n" + "  th, td {\r\n" + "    padding: 8px;\r\n"
				+ "    border-bottom: 1px solid #ddd;\r\n" + "  }\r\n" + "  th {\r\n"
				+ "    background-color: #f2f2f2;\r\n" + "    text-align: left;\r\n" + "  }\r\n"

				+ "</style>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n" + "	<header>\r\n"
				+ "		<h1>National Bank Of India</h1>\r\n" + "		<div id=\"user-info\">\r\n" + "			<p>\r\n"
				+ "				Welcome "+session.getAttribute("name")+" Ji\r\n"
				+ "			</p>\r\n" + "			<button onclick=\"logout()\">Logout</button>\r\n"
				+ "		</div>\r\n" + "	</header>\r\n" + "\r\n" + "	<div id=\"container\">\r\n"
				+ "		<div id=\"options\">\r\n" + "			<h2>Task Manager</h2>\r\n" + "			<ul>\r\n"
				+ "				<li><a href=\"panndigTask?page=1\">Panding Task ("+session.getAttribute("totalPandingTask")+")"
		   		+"</a></li>\r\n"
		   		+ "				<li><a href=\"searchAc?type=account\">Search Account</a></li>\r\n"
		   		+ "				<li><a href=\"searchAc?type=loan\">Search Loan Account</a></li>\r\n"
		   		+ "				<li><a href=\"addEmployee\">Add New Employee</a></li>\r\n"
		   		+ "				<li><a href=\"showAllLoanAccount?page=1\">Show All Loan Account</a></li>\r\n"
		   		+ "				<li><a href=\"showAllAc?page=1\">Show All Account</a></li>\r\n"
		   		+ "			</ul>\r\n" + "		</div>\r\n" + "		<div id=\"fund-transfer-form\">\r\n"
				+ "			  <div id=\"account-table\">\r\n"
				+ "					    <h2>Loan Account List </h2>\r\n" + "					    <table>\r\n"
				+ "					      <thead>\r\n" + "					       <tr>\r\n"
				+ "					          <th>Loan A/C Number</th>\r\n"
				+ "					          <th>Loan Amount</th>\r\n" 
				+ "					          <th>Remining Amount</th>\r\n"
				+ "					         <th>Remining Emi</th>\r\n" 
				+ "					        </tr>\r\n" + "					      </thead>\r\n"
				+ "					      <tbody>\r\n";
		ServletConfig config=getServletConfig();
		String spageid = req.getParameter("page");
		int pageid=1;
		if(spageid!=null) {
			pageid = Integer.parseInt(spageid);
		}
		
		int total = 15;
		if (pageid == 1) {

		} else {
			pageid = pageid - 1;
			pageid = pageid * total + 1;
		}

		List<ArrayList> list = getPanndingTask(pageid, total, context,config);

		for (ArrayList list1 : list) {

			page = page + "<tr>\r\n" + "          <td>" + list1.get(0) + "</td>\r\n" + "          <td>" + list1.get(1)
					+ "</td>\r\n" + "          <td>" + list1.get(2) + "</td>\r\n" + "          <td>" + list1.get(3)
					+ "</td>\r\n" ;
			
		}
		page = page + "      </tbody>\r\n" + "    </table>\r\n" + "		</div>\r\n";

		int pageLink = 1;
		
		while (totalRow > 0) {
			totalRow = totalRow - 15;
			page = page + "<a href='panndigTask?page=" + (pageLink) + "'>" + (pageLink++) + "</a>\t";
		}
		page = page + "	</div>\r\n" + "\r\n" + "	<footer>\r\n"
				+ "		<p>&copy; 2024 Bank Website. All Rights Reserved.</p>\r\n" + "	</footer>\r\n" + "\r\n"
				+ "	<script>\r\n" + "    function logout() {\r\n" + "        window.location.href = \"logout\";\r\n"
				+ "    }\r\n" + "</script>\r\n" + "\r\n" + "</body>\r\n" + "</html>\r\n";
		pw.println(page);
		
	}

	public static List<ArrayList> getPanndingTask(int start, int total, ServletContext context,ServletConfig config) {
		List<ArrayList> list = new ArrayList<ArrayList>();
		try {
			Connection con = (Connection) context.getAttribute("connection");
			PreparedStatement ps = con.prepareStatement(
					"select loan_ac_number, loan_amount, remining_amount, remining_emi from customer_loan where stutas='active' limit " + (start - 1) + "," + total);
			ResultSet rs = ps.executeQuery();
			totalRow=rs.getRow();
			while (rs.next()) {
				ArrayList arrayList = new ArrayList<>();
				arrayList.add(rs.getLong("loan_ac_number"));
				arrayList.add(rs.getInt("loan_amount"));
				arrayList.add(rs.getInt("remining_amount"));
				arrayList.add(rs.getInt("remining_emi"));
				list.add(arrayList);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}

