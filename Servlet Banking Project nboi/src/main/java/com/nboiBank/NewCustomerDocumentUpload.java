package com.nboiBank;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/uploadDocument")
@MultipartConfig
public class NewCustomerDocumentUpload extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		HttpSession session = req.getSession(false);
		String customer = (String) session.getAttribute("customer");
		Connection connection = (Connection) getServletContext().getAttribute("connection");
		String document[] = { "aadharCard", "panCard", "addressProof", "photo", "signature" };

		try {
			PreparedStatement ps = connection.prepareStatement(
					"insert into customer_document(temp_ac_num,aadhar_card,pan_card,address_prof,photo,signature)values(?,?,?,?,?,?);");
            ps.setLong(1, (long)session.getAttribute("temp_ac_num"));
            String MainPath = "D:\\NBOI_document\\" + customer;
            File folder=new File(MainPath);
            folder.mkdir();
            int i=2;
			for (String file : document) {
				Part part = req.getPart(file);
				String fileName = part.getSubmittedFileName();
				String path = MainPath + "\\" + fileName; // second method of give a seprator
				// String path=req.getParameter("location")+File.separator+fileName;
                ps.setString(i, path);
				part.write(path);
				i++;
			}
			ps.execute();
			i=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.sendRedirect("NewCustomerAtmCheckBookForm.html");
	}
}
