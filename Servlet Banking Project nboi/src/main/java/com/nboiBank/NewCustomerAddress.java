package com.nboiBank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/newcustAddress")
public class NewCustomerAddress extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		String checkBox = req.getParameter("sameAddress");
         HttpSession session=req.getSession(false);
		try {
			Connection connection = (Connection) getServletContext().getAttribute("connection");
			String address = req.getParameter("presentWardNo") + "," + req.getParameter("presentColony") + ","
					+ req.getParameter("presentVillage") + "," + req.getParameter("presentBlock") + ","
					+ req.getParameter("presentCity") + "," + req.getParameter("presentDistrict") + ","
					+ req.getParameter("presentState") + "," + req.getParameter("presentPincode");
			if (checkBox != null) {
				String quearyPersent = "INSERT INTO  customer_address(temp_ac_num,Persent_address,Parmanent_address) VALUES (?,?,?);";

				PreparedStatement ps = connection.prepareStatement(quearyPersent);
				ps.setLong(1,(long)session.getAttribute("temp_ac_num") );
				ps.setString(2, address);
				ps.setString(3, address);
				ps.execute();

			} else {
				String PersentAddress = req.getParameter("permanentWardNo") + "," + req.getParameter("permanentColony") + ","
						+ req.getParameter("permanentVillage") + "," + req.getParameter("permanentBlock") + ","
						+ req.getParameter("permanentCity") + "," + req.getParameter("permanentDistrict") + ","
						+ req.getParameter("permanentState") + "," + req.getParameter("permanentPincode");
				String quearyPersent = "INSERT INTO  customer_address(temp_ac_num,Persent_address,Parmanent_address) VALUES (?,?,?);";

				PreparedStatement ps = connection.prepareStatement(quearyPersent);
				ps.setLong(1,(long)session.getAttribute("temp_ac_num") );
				ps.setString(2, address);
				ps.setString(3, PersentAddress);
				ps.execute();
			}

			resp.sendRedirect("NewAccountDocumentUplode.html");

		} catch (SQLException e) {
			System.out.println(e);
		}

	}
}
