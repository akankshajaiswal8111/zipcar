package com.rentaride;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminLogin() throws ClassNotFoundException, SQLException {
		super();
		// TODO Auto-generated constructor stub

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Connection con;
			con = DatabaseManager.initializeDatabase();
			Statement stmt = con.createStatement();
			String role = "";
			String sql;
			sql = "SELECT Username,Password,Role FROM admindetails WHERE Username='" + request.getParameter("userName")
					+ "' AND Password = '" + request.getParameter("password") + "'";
			
			System.out.print(sql);
			ResultSet rs = stmt.executeQuery(sql);
			int cnt = 0;
			while (rs.next()) {

				cnt++;
				role = rs.getString("role");
			}

			if (cnt == 0) {
				PrintWriter out = response.getWriter();
				out.println(
						"<html><body><b>Invalid Credentials. Please <a href=AdminLogin.jsp>Retry</a></b></body></html>");
			} else if (cnt > 0) {
				
				HttpSession session = request.getSession(false);
				// save message in session
				session.setAttribute("role", role);
				response.sendRedirect("AdminHome.jsp");

			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
