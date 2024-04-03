package com.streamservlet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/video")
public class ServletOutputStreamDemo extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("video/mp4");
		FileInputStream stream1=new FileInputStream("C:\\Users\\DELL\\Downloads\\video1.mp4");
		ServletOutputStream stream2=resp.getOutputStream();
		int ch;
		while((ch=stream1.read()) !=-1) {
			stream2.write(ch);
		}
	}

}
