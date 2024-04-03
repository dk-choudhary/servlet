package com.streamservlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
@WebServlet("/input1")
public class VideoPlayAndWriteSelectledLocationWithPostMethod extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

		Part part = req.getPart("file");

		String filename = part.getSubmittedFileName();
		String target = req.getParameter("place");
		String location = target+'/'+filename;
		System.out.println(location);
		part.write(location);

	}

}
