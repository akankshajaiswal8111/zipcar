package com.rentaride;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class CustomerSignUp
 */
@WebServlet("/CustomerSignUp")
public class CustomerSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerSignUp() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		try {
			if (request.getParameter("userName").isBlank() || request.getParameter("password").isBlank()
					|| request.getParameter("name").isBlank() || request.getParameter("email").isBlank()
					|| request.getParameter("phone").isBlank() || request.getParameter("address").isBlank()
					|| request.getParameter("licenseNum").isBlank() || request.getParameter("licenseState").isBlank()
					|| request.getParameter("cardType").isBlank() || request.getParameter("creditCardNum").isBlank()
					|| request.getParameter("cvv").isBlank() || request.getParameter("expiryDate").isBlank()) {
				System.out.print("blank.....");
				PrintWriter out = response.getWriter();
				out.println(
						"<html><body><b>Please enter all fields before clicking Pay and Register <input type=button value=\"Back\" onclick=\"history.back();\" "
								+ "</b></body></html>");
			}

			else {

				if (request.getParameter("password").length() < 8) {
					PrintWriter out = response.getWriter();
					out.println(
							"<html><body><b> Minimum length of password is 8 charecters.<input type=button value=\"Back\" onclick=\"history.back();\" "
									+ "</b></body></html>");

				} else if (request.getParameter("creditCardNum").length() != 16
						|| !request.getParameter("creditCardNum").matches("[0-9]+")) {
					PrintWriter out = response.getWriter();
					out.println(
							"<html><body><b> Please enter a valid credit card number<input type=button value=\"Back\" onclick=\"history.back();\" "
									+ "</b></body></html>");

				}

				else if (!request.getParameter("expiryDate").contains("/")
						|| request.getParameter("expiryDate").length() != 5) {
					PrintWriter out = response.getWriter();
					out.println(
							"<html><body><b> Please enter a valid expiry date.<input type=button value=\"Back\" onclick=\"history.back();\" "
									+ "</b></body></html>");
				} else if (request.getParameter("cvv").length() != 3
						|| !request.getParameter("cvv").matches("[0-9]+")) {
					PrintWriter out = response.getWriter();
					out.println(
							"<html><body><b> Please enter a valid CVV<input type=button value=\"Back\" onclick=\"history.back();\" "
									+ "</b></body></html>");

				}

				else {
					Boolean expDateError = false;
					String[] expDateArray = request.getParameter("expiryDate").split("/");
				//	System.out.print("exp..." + Arrays.toString(expDateArray));

					if (Integer.valueOf(expDateArray[1]) == 20) {
						System.out.print("heyyyyyyy");
						Date today = new Date();

						Calendar cal = Calendar.getInstance();
						cal.setTime(today);
						int month = cal.get(Calendar.MONTH);
						System.out.print("month........" + month);
						if (Integer.valueOf(expDateArray[0]) < month || Integer.valueOf(expDateArray[0]) > 12) {
							PrintWriter out = response.getWriter();
							out.println(
									"<html><body><b> Please enter a valid expiry date.<input type=button value=\"Back\" onclick=\"history.back();\" "
											+ "</b></body></html>");
							expDateError = true;
						}

					}

					else if (Integer.valueOf(expDateArray[1]) < 20) {
						PrintWriter out = response.getWriter();
						out.println(
								"<html><body><b> Please enter a valid expiry date.<input type=button value=\"Back\" onclick=\"history.back();\" "
										+ "</b></body></html>");
						expDateError = true;
					}

					else if (Integer.valueOf(expDateArray[1]) > 20) {
						if (Integer.valueOf(expDateArray[0]) < 1 || Integer.valueOf(expDateArray[0]) > 12) {
							PrintWriter out = response.getWriter();
							out.println(
									"<html><body><b> Please enter a valid expiry date.<input type=button value=\"Back\" onclick=\"history.back();\" "
											+ "</b></body></html>");
							expDateError = true;
						}
					}

					if (!expDateError) {
						System.out.print("here...");
						// Initialize the database
						Connection con = DatabaseManager.initializeDatabase();

						// Create a SQL query to insert data into demo table
						// demo table consists of two columns, so two '?' is used
						String query = " insert into customerdetails (Username, Password, Name, EmailId, PhoneNum,ResidentialAddress,LicenseNumber,LicenseState,CardType,CreditCardNumber,CVV,ExpiryDate,isActive)"
								+ " values (?, ?, ?, ?, ?,?,?,?,?,?,?,?,?)";

						PreparedStatement st = con.prepareStatement(query);

						st.setString(1, request.getParameter("userName"));
						st.setString(2, request.getParameter("password"));
						st.setString(3, request.getParameter("name"));
						st.setString(4, request.getParameter("email"));
						st.setString(5, request.getParameter("phone"));
						st.setString(6, request.getParameter("address"));
						st.setString(7, request.getParameter("licenseNum"));
						st.setString(8, request.getParameter("licenseState"));
						st.setString(9, request.getParameter("cardType"));
						st.setString(10, request.getParameter("creditCardNum"));
						st.setInt(11, Integer.valueOf(request.getParameter("cvv")));
						st.setString(12, request.getParameter("expiryDate"));
						st.setString(13, "1");
						Integer amount = Integer.valueOf(request.getParameter("regAmnt"));
						// Execute the insert command using executeUpdate()
						// to make changes in database
						st.executeUpdate();
						
						 String sql;
						 Statement stmt = con.createStatement();
					        sql = "SELECT Username FROM customerdetails WHERE Username='"+request.getParameter("userName")+"'";
					        ResultSet rs = stmt.executeQuery(sql);
					        String cust = null;
					        while(rs.next()) {
					        	cust = rs.getString("Username");
					        }
					/*	System.out.print("cust....."+cust);
						String paymentQuery = "insert into paymentinvoices (Amount,Customer)"+ " values(?,?)";
						PreparedStatement st1 = con.prepareStatement(paymentQuery);
						st1.setInt(1, amount);
						st1.setString(2, cust);
						st1.executeUpdate();*/
						
								
						// Close all the connections
						st.close();
						//st1.close();
						con.close();

						// Get a writer pointer
						// to display the successful result
						PrintWriter out = response.getWriter();
						out.println("<html><body><h1><b>Successfully Registered! " + "</b></h1><br/><p> Payment of $"+amount+"done successfully!</body></html>"
								+ "<a href =\"CustLogin.jsp\">Login</a>");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			String msg = e.getMessage(); 
			PrintWriter out = response.getWriter();
			out.println("<html><body><b>Error! </b></body></html>");
		}

	}

}
