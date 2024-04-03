package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RandomClass extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		int count=Integer.valueOf(req.getParameter("cardCount")) ;
		for(int i=1;i<=count;i++) {
			out.println("your "+i+" atm card number is :");
			String number =generateSixteenDigitNumber();
			out.print(number);
			out.println(" and mobile number  is :");
			out.print(generateIndianMobileNumber());
			out.print("<br>");
		}
		
	}
	 public static String generateSixteenDigitNumber() {
	        Formatter formatter = new Formatter();
	        formatter.format("%04d-%04d-%04d-%04d", 
	                         (int)(Math.random() * 10000), 
	                         (int)(Math.random() * 10000), 
	                         (int)(Math.random() * 10000), 
	                         (int)(Math.random() * 10000));
	        String number = formatter.toString();
	        formatter.close();
	        return number;
	    }
	 public static String generateIndianMobileNumber() {
		    StringBuilder builder = new StringBuilder();
		    builder.append("+91");
		    Random random = new Random();
		 
		    int firstDigit = random.nextInt(3) + 7;
		    builder.append(firstDigit);
		   
		    for (int i = 0; i < 9; i++) {
		        int digit = random.nextInt(10);
		        builder.append(digit);
		    }
		    return builder.toString();
		}


}
