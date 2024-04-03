package com.nboiBank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkRequestStatus")
public class CheckAccountStutas extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		HttpSession session = req.getSession(false);
		// change the session id for security purpose
		req.changeSessionId();
		try {
			ResultSet rs = (ResultSet) session.getAttribute("resultset");
			session.removeAttribute("resultset");
			
				if (rs.getLong("ac_number")!=0&&rs.getInt("task_panding")==0) {
					resp.sendRedirect("ApplicationAcceptedPopup.html");
				}else if(rs.getInt("task_panding")==100) {
					resp.sendRedirect("FileRejectedPopup.html");
				}else if(rs.getInt("task_panding")==-1) {
					resp.sendRedirect("AlreadyCheckedAcStutasPopup.html");
				}
				else {
					resp.sendRedirect("requestUnderProcessPopup.html");
					
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
