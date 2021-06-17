package com.rentaride;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminSignUp
 */
@WebServlet("/AdminSignUp")
public class AdminSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		try {
			if (request.getParameter("userName").isEmpty() || request.getParameter("password").isEmpty()
					|| request.getParameter("name").isEmpty() || request.getParameter("email").isEmpty()
					|| request.getParameter("phone").isEmpty() || request.getParameter("address").isEmpty()) {
				PrintWriter out = response.getWriter();
				out.println(
						"<html><body><b>Please enter all fields before clicking Register <input type=button value=\"Back\" onclick=\"history.back();\" "
								+ "</b></body></html>");
			}
			else {
				Connection con = DatabaseManager.initializeDatabase();
				String query = "insert into admindetails (Username,Password,Name,EmailId,PhoneNum,Role,Address) "+" values(?,?,?,?,?,?,?)";
				PreparedStatement st = con.prepareStatement(query);
				st.setString(1, request.getParameter("userName"));
				st.setString(2, request.getParameter("password"));
				st.setString(3, request.getParameter("name"));
				st.setString(4, request.getParameter("email"));
				st.setString(5, request.getParameter("phone"));
				st.setString(6, "Admin");
				st.setString(7, request.getParameter("address"));
				st.executeUpdate();
				st.close();
				PrintWriter out = response.getWriter();
				out.println("<html><body><h1><b>Successfully Registered! " + "</b></h1><br/>"
						+ "<a href =\"AdminLogin.jsp\">Login</a></body></html>");
				
			
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
