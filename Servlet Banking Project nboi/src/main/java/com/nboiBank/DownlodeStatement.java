package com.nboiBank;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/downlodeTransHistory")
public class DownlodeStatement extends HttpServlet{
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("text/html");

	ServletContext context = req.getServletContext();
	HttpSession session = req.getSession(false);
	long ac_number = (long) session.getAttribute("ac_number");
	try {
		Connection con = (Connection) context.getAttribute("connection");
		PreparedStatement ps=con.prepareStatement("select * from transction where ac_number=?;");
		ps.setLong(1, ac_number);
		ResultSet rs=ps.executeQuery();
		Document document=new Document();
		FileOutputStream fos=new FileOutputStream("D:\\Statements\\"+ac_number+"statement.pdf");
		PdfWriter.getInstance(document, fos);
		document.open();
		PdfPTable table =new PdfPTable(7);
		ResultSetMetaData metaData=(ResultSetMetaData) rs.getMetaData();
		
		for(int i=1;i<=7;i++) {
			table.addCell(metaData.getColumnName(i));
		}
		//document.add(new Paragraph("\n"));
		while(rs.next()) {
			table.addCell((rs.getLong(1)+"\t"));
			table.addCell(rs.getLong(2)+"\t");
			table.addCell(rs.getLong(3)+"\t");
			table.addCell(rs.getString(4)+"\t\t");
			table.addCell(rs.getLong(5)+"\t\t\t");
			table.addCell(rs.getLong(6)+"\t\t\t");
			table.addCell(rs.getTimestamp(7).toString());
		}
		document.add(table);
		document.close();
		resp.sendRedirect("statementDownloadedPopup.html");
	} catch (SQLException | DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
}
}
