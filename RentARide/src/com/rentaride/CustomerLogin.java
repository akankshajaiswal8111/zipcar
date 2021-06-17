package com.rentaride;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CustomerLogin
 */
@WebServlet("/CustomerLogin")
public class CustomerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				try {
					Connection con;
					con = DatabaseManager.initializeDatabase();
					 Statement stmt = con.createStatement();
				        String sql;
				        sql = "SELECT Username,Password FROM customerdetails WHERE Username='"+request.getParameter("userName")+"' AND Password = '"+request.getParameter("password")+"' AND isActive = 1";
				        //sql="SELECT Username,Password FROM AdminDetails ";
				        System.out.print(sql);
				        ResultSet rs = stmt.executeQuery(sql);
				        int cnt=0;
				        while(rs.next()) {
				        	
				        	cnt++;
				        }
				        System.out.print("cnt..."+cnt);
				     
				        
				        
				      //  System.out.print("rs..."+rs.next());
				        if(cnt == 0) {
				        	System.out.print("Invalid Credentials");
				        	PrintWriter out = response.getWriter(); 
							out.println("<html><body><b>Invalid Credentials. Please <a href=CustLogin.jsp>Retry</a></b></body></html>"); 
				        }
				        else if(cnt > 0) {
				        	System.out.print("In else"+rs.next());
				        	while(rs.next()) {
				        		System.out.print("In here");
				        		
				        	}
				        	System.out.print("Success");
							
				        	
				        	
				        	//response.sendRedirect("CustHome.jsp");
				        	
				        	HttpSession session = request.getSession(false);
							// save message in session
							session.setAttribute("username", request.getParameter("userName"));
							response.sendRedirect("bookings.jsp");
				        }
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			       
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
