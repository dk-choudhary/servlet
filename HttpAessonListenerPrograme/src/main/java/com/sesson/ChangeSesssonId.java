package com.sesson;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionIdListener;
@WebListener
public class ChangeSesssonId  implements HttpSessionIdListener{

	@Override
	public void sessionIdChanged(HttpSessionEvent arg0, String arg1) {
		System.out.println("session id changed ");
		
	}

}
