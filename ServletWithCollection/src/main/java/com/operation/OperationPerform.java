
package com.operation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OperationPerform extends HttpServlet {
	// Static variables to store information temporarily (not recommended for
	// real-world scenarios)
	static String name;
	static String email;
	static String password;
	static String mobile;
	static PrintWriter out;

	// HashMap to store person information (Name -> [Email, Password, Mobile])
	HashMap<String, LinkedList<String>> personsMap = new HashMap<>();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// Get the action parameter from the request
		String action = req.getParameter("action");
		out = res.getWriter();
		res.setContentType("text/html");

		// Get information from the request parameters
		name = req.getParameter("name");
		email = req.getParameter("email");
		password = req.getParameter("password");
		mobile = req.getParameter("mobile");

		try {
			if ("Add".equalsIgnoreCase(action)) {
				// If the action is "Add", call the doPost method
				doPost(req, res);
			} else if ("read".equalsIgnoreCase(action)) {
				// If the action is "read", call the doGet method
				doGet(req, res);
			} else if ("update".equalsIgnoreCase(action)) {
					// If the action is "update", call the doPut method
					doPut(req, res);
			} else if ("delete".equalsIgnoreCase(action)) {
				// If the action is "delete", call the doDelete method
				doDelete(req, res);
			} else if ("Exit".equalsIgnoreCase(action)) {
				// If the action is "Exit", print a message and exit the system
				out.print("exit");
				System.exit(0);
			} else {
				// Handle other cases if needed
			}

		} catch (Exception e) {
			// Handle exceptions if any
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Add a new person to the personsMap
		LinkedList list = new LinkedList();
		list.add(email);
		list.add(password);
		list.add(mobile);
		personsMap.put(name, list);

		// Print success message
		out.print("Person added successfully.");
		out.println("<a href='index.html'> home</a>");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Display the contents of personsMap in an HTML table
		resp.setContentType("text/html");
		out.print("<html>");
		out.print("<head>");

		out.print("</head>");
		out.print("<body>");
		out.print("<table>");

		out.print("<tr>");
		out.print("<th>");
		out.print("Name");
		out.print("</th>");
		out.print("<th>");
		out.print("Email");
		out.print("</th>");
		out.print("<th>");
		out.print("Password");
		out.print("</th>");
		out.print("<th>");
		out.print("Mobile");
		out.print("</th>");
		out.print("</tr>");

		// Iterate over personsMap and display each person's information
		for (Map.Entry<String, LinkedList<String>> entry : personsMap.entrySet()) {
			String Name = entry.getKey();
			LinkedList<String> personList = entry.getValue();

			out.print("<tr>");
			out.print("<td>");
			out.print(Name);
			out.print("</td>");

			for (String person : personList) {
				out.print("<td>");
				out.print(person);
				out.print("</td>");
			}

			out.print("</tr>");
		}

		out.print("</table>");
		out.print("</body>");
		out.print("</html>");
		out.println("<a href='index.html'> home</a>");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Update person information in personsMap
		resp.setContentType("text/html");

		if (personsMap.containsKey(name)) {
			LinkedList<String> personList = personsMap.get(email);

			// Create a new list with updated information
			LinkedList list = new LinkedList();
			list.add(email);
			list.add(password);
			list.add(mobile);
			personsMap.put(name, list);

			out.println("Person updated successfully.");
			out.println("<a href='index.html'> home</a>");
		} else {
			out.println("Invalid key(name) No person updated.");
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Delete a person from personsMap
		if (personsMap.containsKey(name)) {
			personsMap.remove(name);
			out.println("Persons deleted successfully.");
			out.println("<a href='index.html'> home</a>");
		} else {
			out.println("No persons found with the provided name.");
		}
	}
}