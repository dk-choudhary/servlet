package com.streamservlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
@WebServlet("/input")
public class ServletInputStreamP extends HttpServlet {
	static String location;

	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

		Part part = req.getPart("file");

		String filename = part.getSubmittedFileName();

		location = "D:\\myfolder" + File.separator + filename;
		System.out.println(location);
		part.write(location);
		response.setContentType("video/mp4");
		System.out.println(location);
		doGet(req, response);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FileInputStream stream1 = new FileInputStream(location);
		ServletOutputStream stream2 = resp.getOutputStream();
		int ch;
		while ((ch = stream1.read()) != -1) {
			stream2.write(ch);
		}
	}
}
