package com.nboiBank;

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
@WebServlet("/basicDetailsCheck")
public class GetBasicDetailsOfCustomerByBankEmp extends HttpServlet{
 @Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   resp.setContentType("text/html");
	   HttpSession session = req.getSession(false);
	   ServletContext context=req.getServletContext();
	   PrintWriter pw = resp.getWriter();  
	   String name=(String) session.getAttribute("name");
	   long ac_number=Long.parseLong((String)session.getAttribute("getAcData"));
	   session.removeAttribute("getAcData");
	   String type=req.getParameter("type");
	   try {
           Connection con = (Connection) context.getAttribute("connection");
           PreparedStatement ps=null;
           ResultSet  rs=null;
           if(type.equals("account")) {
           ps = con.prepareStatement(
                   "select * from customer_basic_deatils b inner join customer_address a on b.ac_number=a.ac_number where b.ac_number=?");
           ps.setLong(1, ac_number);
           ResultSet  rs1 = ps.executeQuery();
           rs1.next();
           rs=rs1;
	       }
           else {
        
        	   ps = con.prepareStatement(
        					    "SELECT b.*, a.* FROM customer_basic_deatils b INNER JOIN customer_address a ON b.ac_number = a.ac_number WHERE b.ac_number = (SELECT b.ac_number FROM customer_basic_deatils b INNER JOIN customer_loan a ON b.ac_number = a.ac_number WHERE a.loan_ac_number = ?);");
               ps.setLong(1, ac_number);
               ResultSet  rs1 = ps.executeQuery();
               rs1.next();
               rs=rs1;
           }         

           pw.println("<!DOCTYPE html>");
           pw.println("<html lang=\"en\">");
           pw.println("<head>");
           pw.println("<meta charset=\"UTF-8\">");
           pw.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
           pw.println("<title>Personal Details</title>");
           pw.println("<style>");
           pw.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }");
           pw.println(".container { max-width: 600px; margin: 20px auto; background-color: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
           pw.println(".details { margin-bottom: 20px; display: flex; align-items: center; }");
           pw.println(".details label { font-weight: bold; flex: 1; margin-right: 10px; }");
           pw.println(".details input[type=\"text\"],[type=\"tel\"],[type=\"email\"], .details textarea { flex: 2; padding: 8px; border: 1px solid #ccc; border-radius: 4px; }");
           pw.println(".details input[type=\"text\"]:disabled { background-color: #f4f4f4; }");
           pw.println(".btn { background-color: #4caf50; color: #fff; border: none; padding: 10px 20px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px; border-radius: 4px; cursor: pointer; }");
           pw.println(".btn:hover { background-color: #45a049; }");
           pw.println("</style>");
           pw.println("</head>");
           pw.println("<body>");
           pw.println("<div class=\"container\">");
           pw.println("<h2>Personal Details</h2>");
           pw.println("<form action='empHomePage'>");
           pw.println("<div class=\"details\"><label for=\"ac_number\">Account Number:</label><input type=\"text\" id=\"ac_number\" name=\"ac_number\" disabled value='" + rs.getLong("ac_number") + "'></div>");
           pw.println("<div class=\"details\"><label for=\"name\">Name:</label><input type=\"text\" id=\"name\" name=\"name\" disabled value='" + rs.getString("Name") + "'></div>");
           pw.println("<div class=\"details\"><label for=\"father_name\">Father's Name:</label><input type=\"text\" id=\"father_name\" name=\"father_name\" disabled value='" + rs.getString("father_name") + "'></div>");
           pw.println("<div class=\"details\"><label for=\"mother_name\">Mother's Name:</label><input type=\"text\" id=\"mother_name\" name=\"mother_name\" disabled value='" + rs.getString("mother_name") + "'></div>");
           pw.println("<div class=\"details\"><label for=\"dob\">Date of Birth:</label><input type=\"text\" id=\"dob\" name=\"dob\" disabled value='" + rs.getString("dob") + "'></div>");
           pw.println("<div class=\"details\"><label for=\"email\">Email:</label><input type=\"email\" id=\"email\" name=\"email\" disabled value='" + rs.getString("email") + "'></div>");
           pw.println("<div class=\"details\"><label for=\"mobile\">Mobile:</label><input type=\"tel\" id=\"mobile\" name=\"mobile\" disabled value='" + rs.getString("mobile") + "' pattern=\"[0-9]{10}\"></div>");
           pw.println("<div class=\"details\"><label for=\"aadhar\">Aadhar:</label><input type=\"text\" id=\"aadhar\" name=\"aadhar\" disabled value='" + rs.getString("aadhar") + "'></div>");
           pw.println("<div class=\"details\"><label for=\"pan_card\">PAN Card:</label><input type=\"text\" id=\"pan_card\" name=\"pan_card\" disabled value='" + rs.getString("pan_card") + "'></div>");
           pw.println("<div class=\"details\"><label for=\"ac_type\">Account Type:</label><input type=\"text\" id=\"ac_type\" name=\"ac_type\" disabled value='" + rs.getString("ac_type") + "'></div>");
           pw.println("<div class=\"details\"><label for=\"user_id\">User ID:</label><input type=\"text\" id=\"user_id\" name=\"user_id\" value='" + rs.getString("user_id") + "' disabled></div>");
           pw.println("<div class=\"details\"><label for=\"current_balance\">Current Balance:</label><input type=\"text\" id=\"current_balance\" name=\"current_balance\" disabled value='₹" + rs.getLong("current_balance") + "'></div>");
           pw.println("<div class=\"details\"><label for=\"address\">Parmanent Address:</label><textarea id=\"address\" name=\"parmenentaddress\" rows=\"4\" disabled>" + rs.getString("Parmanent_address") + "</textarea></div>");
           pw.println("<div class=\"details\"><label for=\"address\">Persent Address:</label><textarea id=\"address\" disabled name=\"address\" rows=\"4\">" + rs.getString("Persent_address") + "</textarea></div>");
           pw.println("<button class=\"btn\" type=\"submit\" onclick=\"update()\">Go Back</button>");
           pw.println("</div>");
           pw.println("</form>");
           pw.println(" <script>\r\n"
					+ "        function update() {\r\n"
					+ "            window.location.href = \"empHomePage\";\r\n"
					+ "        }\r\n"
					+ "    </script>"
				);
           pw.println("</body>");
           pw.println("</html>");

           
	    }catch(Exception e) {
	    	e.printStackTrace();
    	   resp.sendRedirect("NotCustomerOurBankPopup.html");
       }
	  
}
}
