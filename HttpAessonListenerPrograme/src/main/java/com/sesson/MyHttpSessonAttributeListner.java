package com.sesson;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
@WebListener
public class MyHttpSessonAttributeListner implements HttpSessionAttributeListener {
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		String name=(String)event.getValue();
		
		
		System.out.println("attirbute added "+name);
	}
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		String name=(String)event.getValue();
	
			
			System.out.println("attirbute removed "+name);
	}
	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		String name=(String)event.getValue();
		
		
		System.out.println("attirbute changed "+name);
	}

}
