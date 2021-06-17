package com.rentaride;
	

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddVehicleType
 */
@WebServlet("/AddVehicleType")
public class AddVehicleType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddVehicleType() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		if(request.getParameter("name").isEmpty() || request.getParameter("description").isEmpty() || request.getParameter("pricePerHr").isEmpty()) {
			PrintWriter out = response.getWriter();
			out.println(
					"<html><body><b>Please enter all fields before submitting. <input type=button value=\"Back\" onclick=\"history.back();\" "
							+ "</b></body></html>");
		}
		else if(!request.getParameter("pricePerHr").matches("[0-9]+")){
			PrintWriter out = response.getWriter();
			out.println(
					"<html><body><b>Please enter a valid amount.<input type=button value=\"Back\" onclick=\"history.back();\" "
							+ "</b></body></html>");
		}
		else {
			try {
				Connection con = DatabaseManager.initializeDatabase();
				String query = "insert into vehicletype (Type,PricePerHr,Description) "+" values(?,?,?)";
				PreparedStatement st = con.prepareStatement(query);
				st.setString(1, request.getParameter("name"));
				st.setString(3, request.getParameter("description"));
				st.setInt(2, Integer.valueOf(request.getParameter("pricePerHr")));
				System.out.println("query..."+query);
				st.executeUpdate();
				st.close();
				//PrintWriter out = response.getWriter();
				//out.println("<html><body><h1><b>Successfully Added a New Vehicle Type! " + "</b></h1><br/>"
				//		+ "<a href =\"VehicleTypes.jsp\">View VehicleTypes</a></body></html>");
				response.sendRedirect("VehicleTypes.jsp");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		}
		
	

}
