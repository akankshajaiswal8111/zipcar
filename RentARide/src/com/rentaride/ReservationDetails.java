package com.rentaride;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import static java.time.temporal.ChronoUnit.HOURS;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class reservationDetails
 */
@WebServlet("/reservationDetails")
public class ReservationDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DateUtils dtUtils;   
    private ReservationDAO dao;
    /**
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public ReservationDetails() throws ClassNotFoundException, SQLException {
    	super();
    	dtUtils = new DateUtils();
    	dao = new ReservationDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("Modify")!=null)
		{
			response.sendRedirect("searchRentals.jsp");
		}
		else
		{
		String destination = "reservationDetails.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
		String action = request.getParameter("action");
		String userName = request.getParameter("username");	
		if("RESERVE".equals(action.toUpperCase()))
		{
			try
			{
			if (request.getParameter("booked") != null)
			{
				ReservationRecords reservationRecords = new ReservationRecords();
				reservationRecords.setReservationId(request.getParameter("reservationId"));
				ReservationRecords records =  dao.getReservationDetails(reservationRecords);
				
				
					request.setAttribute("custName", records.getCustomerName());
					request.setAttribute("pickUpLocation", records.getPickUpLocation());
						
					//get pickUpTime and split to Date and Time
					String frmDateStr = records.getPickUpDateTime();
					ArrayList<String> frmDateTime = new ArrayList<>();
					ArrayList<String> toDateTime = new ArrayList<>();
					
					frmDateTime = dtUtils.getDateAndTimeComponents(frmDateStr);
					
					request.setAttribute("pickUpDate", frmDateTime.get(0));
					request.setAttribute("pickUpTime", frmDateTime.get(1));
					
					//get dropOffTime and split to Date and Time
					String toDateStr = records.getDropOffDateTime();
					toDateTime = dtUtils.getDateAndTimeComponents(toDateStr);
					request.setAttribute("dropOffDate", toDateTime.get(0));
					request.setAttribute("dropOffTime", toDateTime.get(1));
					
					request.setAttribute("vehRegNo", records.getVehRegNo());
					request.setAttribute("rentalFees", records.getFeesPayable());
					requestDispatcher.forward(request, response);
			}
			else
			{
				ReservationRecords reservationRecords = new ReservationRecords();
				reservationRecords.setCustUserName(userName);
				reservationRecords.setVehRegNo(request.getParameter("vehRegNo"));
				reservationRecords.setVehTypeId(request.getParameter("vehTypeId"));
				reservationRecords.setPickUpDateTime(request.getParameter("pickUpDateTime"));
				reservationRecords.setDropOffDateTime(request.getParameter("dropOffDateTime"));
				dao.addReservationDetails(reservationRecords);
				
				
				ReservationRecords reserveRecords = new ReservationRecords();
				reserveRecords.setCustUserName(userName);
				reserveRecords.setVehRegNo(request.getParameter("vehRegNo"));
				ReservationRecords records = dao.getReservationByVehReg(reserveRecords);
				
				
				request.setAttribute("custName", records.getCustomerName());
				request.setAttribute("pickUpLocation", records.getPickUpLocation());
				
				//get pickUpTime and split to Date and Time
				String frmDateStr = records.getPickUpDateTime();
				ArrayList<String> frmDateTime = new ArrayList<>();
				ArrayList<String> toDateTime = new ArrayList<>();
				
				frmDateTime = dtUtils.getDateAndTimeComponents(frmDateStr);
				request.setAttribute("pickUpDate", frmDateTime.get(0));
				request.setAttribute("pickUpTime", frmDateTime.get(1));
				
				//get dropOffTime and split to Date and Time
				String toDateStr = records.getDropOffDateTime();
				toDateTime = dtUtils.getDateAndTimeComponents(toDateStr);
				request.setAttribute("dropOffDate", toDateTime.get(0));
				request.setAttribute("dropOffTime", toDateTime.get(1));
				
				
				request.setAttribute("vehRegNo", records.getVehRegNo());
				request.setAttribute("rentalFees", records.getFeesPayable());
				
				
			}
			
			requestDispatcher.forward(request, response);
			
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if("CANCEL".equals(action.toUpperCase()))
		{	
			try
			{
			//initialize the connection
			String cancelFees = null;
			String comments = request.getParameter("comments");
			
			String pickUpDateStr = request.getParameter("pickUpDate");
			Date pickUpDate = dtUtils.getDateComponent(pickUpDateStr);
				
			String pickUpTimeStr = pickUpDateStr + " " + request.getParameter("pickUpTime");
			
			ArrayList<LocalDateTime> arrDtTime = new ArrayList<>();
			arrDtTime = dtUtils.getLocalDateTime(pickUpTimeStr);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
			
			if(pickUpDate.compareTo(dateFormat.parse(dateFormat.format(new Date()))) <= 0 && HOURS.between(arrDtTime.get(0).plusSeconds(0), arrDtTime.get(1).plusSeconds(0)) >= 0)
			{
				ReservationRecords reserveRecords = new ReservationRecords();
				reserveRecords.setCustUserName(userName);
				reserveRecords.setVehRegNo(request.getParameter("vehRegNo"));
				cancelFees = dao.getRentalFeesPerHr(reserveRecords);
				
				
				ReservationRecords reservationRecords = new ReservationRecords();
				reservationRecords.setFeesPayable(cancelFees);
				reservationRecords.setComments(comments);
				reservationRecords.setCustUserName(userName);
				reservationRecords.setVehRegNo(request.getParameter("vehRegNo"));
				dao.cancelReservationWithFees(reservationRecords);
				
				
				PrintWriter out = response.getWriter();
				out.println("<html><body><h1><b>Your Reservation has been cancelled successfully! " + "</b></h1><h2>Cancellation fees of one hour rental has been applied.</h2>"
						+ "<a href =\"CustLogin.jsp\">Login</a></body></html>");
				
			}
			else
			{
			
			ReservationRecords reservationRecords = new ReservationRecords();
			reservationRecords.setComments(comments);
			reservationRecords.setCustUserName(userName);
			reservationRecords.setVehRegNo(request.getParameter("vehRegNo"));
			dao.cancelReservation(reservationRecords);
			
			PrintWriter out = response.getWriter();
			out.println("<html><body><h1><b>Your Reservation has been cancelled successfully! " + "</b></h1>"
					+ "<a href =\"CustLogin.jsp\">Login</a></body></html>");
			}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if("END".equals(action.toUpperCase()))
		{
			try
			{
			
			String comments = request.getParameter("comments");
			
			String dropOffDateStr = request.getParameter("dropOffDate");
			Date dropOffDate = dtUtils.getDateComponent(dropOffDateStr);
			
			String dropOffTimeStr = request.getParameter("dropOffDate") + " " + request.getParameter("dropOffTime");
			
			ArrayList<LocalDateTime> arrDtTime = new ArrayList<>();
			arrDtTime = dtUtils.getLocalDateTime(dropOffTimeStr);
		
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
			
			if(dropOffDate.compareTo(dateFormat.parse(dateFormat.format(new Date()))) <= 0 && HOURS.between(arrDtTime.get(0).plusSeconds(0), arrDtTime.get(1).plusSeconds(0)) >= 0)
			{
				String lateFees = null;
				lateFees = dao.getLateFees();
				
				String updatedRentalFees = null;
				String fees = null;
				ReservationRecords reservationRecords = new ReservationRecords();
				reservationRecords.setVehRegNo(request.getParameter("vehRegNo"));
				fees = dao.getFeesperHrOnVehicle(reservationRecords);
				
				updatedRentalFees = Long.toString(Long.parseLong(fees) * HOURS.between(arrDtTime.get(0).plusSeconds(0), arrDtTime.get(1).plusSeconds(0)));
				
				
				lateFees = Long.toString(Long.parseLong(lateFees) + Long.parseLong(request.getParameter("rentalFees")) + Long.parseLong(updatedRentalFees));
				
				
				ReservationRecords records = new ReservationRecords();
				records.setFeesPayable(lateFees);
				records.setComments(comments);
				records.setCustUserName(userName);
				records.setVehRegNo(request.getParameter("vehRegNo"));
				dao.endReservationWithFees(records);
				
				
				PrintWriter out = response.getWriter();
				out.println("<html><body><h1><b>Thank you for your rental from RentARide. Please let us know if we can do anything else to help! " + "</b></h1><h2>A late return fee has been applied along with the rental charges per hour.</h2>"
						+ "<a href =\"CustLogin.jsp\">Login</a></body></html>");
			}
			
			else if(dropOffDate.compareTo(dateFormat.parse(dateFormat.format(new Date()))) >= 0)
			{
				
				ReservationRecords reservationRecords = new ReservationRecords();
				reservationRecords.setComments(comments);
				reservationRecords.setCustUserName(userName);
				reservationRecords.setVehRegNo(request.getParameter("vehRegNo"));
				dao.endReservation(reservationRecords);
				
				
				PrintWriter out = response.getWriter();
				out.println("<html><body><h1><b>Thank you for your rental from RentARide. Please let us know if we can do anything else to help! " + "</b></h1>"
						+ "<a href =\"CustLogin.jsp\">Login</a></body></html>");
			
			}
			
			}
		
			catch (Exception e) {
				e.printStackTrace();
			}

		}
		}
		
	}
	
	

}
