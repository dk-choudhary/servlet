package com.Listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.annotation.WebListener;
@WebListener
public class AttributeListener implements ServletContextAttributeListener {
	
	
	
	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		
		Object name= event.getValue();
	System.out.println("attributee added "+ name);
	}
	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		String name=(String) event.getValue();
		System.out.println("attributee removed " +name);
	}
	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		String name=(String) event.getValue();
		System.out.println("attributee edeit " + name);
	}

}
