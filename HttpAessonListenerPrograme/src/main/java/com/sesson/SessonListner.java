package com.sesson;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
@WebListener
public class SessonListner implements HttpSessionListener {@Override
public void sessionCreated(HttpSessionEvent se) {
	

}
@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
	}

}
