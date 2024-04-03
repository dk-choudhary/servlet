package com.user;


		
		

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;




public class UserEdit extends HttpServlet{
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get Edit name and Crate a Edit query
		String edit_id = request.getParameter("id");
		int new_id = Integer.valueOf(edit_id);
		String sql_edit_query = "SELECT * FROM emp WHERE id='" + new_id + "'";

		//create cookie object
		Cookie ck = new Cookie("cookie_name",edit_id);
		
		//set the cookie
		response.addCookie(ck);
		
		//set content
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("hii "+sql_edit_query);
		try {		
			
			Statement st = connection.createStatement();			
			ResultSet rss = st.executeQuery(sql_edit_query);
			out.print("hello");
			
			//read data 
			while(rss.next()) {
			String newId = rss.getString("id");                   
       	 	String newName = rss.getString("name");
            String newEmail = rss.getString("email");
            String newAge = rss.getString("dob");
            
            LocalDate barthDate = LocalDate.parse(newAge);
          //get local date
            LocalDate localDate = LocalDate.now();
            
            //calculte age
            Period period = Period.between(barthDate, localDate);
            //Extract years and month
             int yea = period.getYears();
             int mont = period.getMonths();
            String newPassword = rss.getString("password");
            String newGender = rss.getString("gender");
            String newWeight = rss.getString("weight");
            String newHieght = rss.getString("height");		
           
            
			out.print("<html>");
			out.print("<head>");
			out.print("<title>User List</title>");
			  
			out.print("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
			out.print("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
			out.print("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js\"></script>");
			out.print("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>");
			out.print("</head>");
			out.print("<body>");

			out.print("<div class=\"container\">");
			
			
			out.print("<form action=\"ServletEdit\" method=\"get\">");
			out.print("<h2>Edit User</h2>");		
			            
			out.print("<table class=\"table table-bordered\">");			
			out.print("<tr>");
			out.print("<td>");
			out.print("Id");
			out.print("</td>");
			out.print("<td>");
			out.print("<input type=\"text\" name=\"id\" value="+newId+" /> ");
			out.print("</td>");
			out.print("</tr>");
			
			out.print("<tr>");
			out.print("<td>");
			out.print("Name");
			out.print("</td>");
			out.print("<td>");
			out.print("<input type=\"text\" name=\"name\" value="+newName +" />" );
			out.print("</td>");
			out.print("</tr>");
			
			out.print("<tr>");
			out.print("<td>");
			out.print("Mail");
			out.print("</td>");
			out.print("<td>");
			out.print("<input type=\"text\" name=\"email\" value="+newEmail +" />" );
			out.print("</td>");
			out.print("</tr>");
			
			out.print("<tr>");
			out.print("<td>");
			out.print("Age");
			out.print("</td>");
			out.print("<td>");
			out.print("<input type=\"text\" name=\"age\" value="+yea+"year " +mont+" month />" );
			out.print("</td>");
			out.print("</tr>");
			
			out.print("<tr>");
			out.print("<td>");
			out.print("Password");
			out.print("</td>");
			out.print("<td>");
			out.print("<input type=\"text\" name=\"password\" value="+newPassword+" />" );
			out.print("</td>");
			out.print("</tr>");
			
			out.print("<tr>");
			out.print("<td>");
			out.print("Gender");
			out.print("</td>");
			out.print("<td>");
			out.print("<input type=\"text\" name=\"gender\" value="+newGender+" />" );
			out.print("</td>");
			out.print("</tr>");
			
			out.print("<tr>");
			out.print("<td>");
			out.print("Weight");
			out.print("</td>");
			out.print("<td>");
			out.print("<input type=\"text\" name=\"weight\" value="+newWeight+" />" );
			out.print("</td>");
			out.print("</tr>");
			
			out.print("<tr>");
			out.print("<td>");
			out.print("Hieght");
			out.print("</td>");
			out.print("<td>");
			out.print("<input type=\"text\" name=\"height\" value="+newHieght+" />");
			out.print("</td>");
			out.print("</tr>");
			
			
			out.print("</table>");
			
			}
			out.print("<input type=\"submit\" value=\"Submit\">");out.print("<br><br>");
			out.print("<a href=\"Logging.html\">Home Page</a>");
						
			
			out.print("</body>");
			out.print("</html>");			
			
		} 
		catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}

}


