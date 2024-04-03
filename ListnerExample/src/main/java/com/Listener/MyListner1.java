package com.Listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MyListner1 implements ServletContextListener{
	Connection connection;
	@Override
	public void contextInitialized(ServletContextEvent sce) {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		
	 connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/ram" ,"root", "123456");
	
		//get context
		ServletContext context=sce.getServletContext();
		//set connection as attribute
		 
		context.setAttribute("con", connection);
		System.out.println(context);
		
		
	}catch(Exception e){
		System.out.println(e);
		
	}
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
