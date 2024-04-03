package com.nboiBank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
@WebListener
public class ContextLeavelListoner implements ServletContextListener{
	private Connection connection;
@Override
public void contextInitialized(ServletContextEvent sce) {
	try {
		ServletContext context=sce.getServletContext();
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		connection=DriverManager.getConnection(context.getInitParameter("schema"),context.getInitParameter("user"),context.getInitParameter("password"));  // for desktop mysql connection
	    context.setAttribute("connection", connection);
	    // setInitParameter method is use in the contextInitialized method of servletContextListener only
	    //context.setInitParameter("name", "rajesh");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
@Override
	public void contextDestroyed(ServletContextEvent sce) {
		sce.getServletContext().removeAttribute("connection");
	}
}
