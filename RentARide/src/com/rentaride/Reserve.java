package com.rentaride;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class reserve
 */
@WebServlet("/reserve")
public class Reserve extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private DateUtils dtUtils;
    private ReservationDAO dao;

	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @see HttpServlet#HttpServlet()
	 */
	public Reserve() throws ClassNotFoundException, SQLException {
		super();
		dtUtils = new DateUtils();
		dao = new ReservationDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			// Load rental vehicle information
			String destination = "reserve.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
	
			// split the pickUp datetime to date and time
			String fromDateStr = request.getParameter("FromDateTime");
			request.setAttribute("pickUpDateTime", fromDateStr);
			
			ArrayList<String> fromDateTime = new ArrayList<>();
		
			fromDateTime = dtUtils.getDateAndTimeComponents(fromDateStr);
			request.setAttribute("pickUpDate", fromDateTime.get(0));
			request.setAttribute("pickUpTime", fromDateTime.get(1));
			System.out.println("Date: " + fromDateTime.get(0));
			System.out.println("Time: " + fromDateTime.get(1));
		

			// split the dropOff datetime to date and time
			String toDateStr = request.getParameter("ToDateTime");
			request.setAttribute("dropOffDateTime", toDateStr);
			
			ArrayList<String> toDateTime = new ArrayList<>();
		
			toDateTime = dtUtils.getDateAndTimeComponents(toDateStr); 
			request.setAttribute("dropOffDate", toDateTime.get(0));
			request.setAttribute("dropOffTime", toDateTime.get(1));
			System.out.println("Date: " + toDateTime.get(0));
			System.out.println("Time: " + toDateTime.get(1));
		
		

		String vehRegNo = request.getParameter("vehRegNo");

		try {
			ArrayList<LocalDateTime> arrDtTime = new ArrayList<>();
			
			arrDtTime = dtUtils.getDateTime(fromDateStr, toDateStr);
			long diffInHours = Duration.between(arrDtTime.get(0), arrDtTime.get(1)).toHours();
			
			
			ReservationRecords records = new ReservationRecords();
			records = dao.getReservationDetailsByVehicle(vehRegNo);
			request.setAttribute("pickUpLocation", records.getPickUpLocation());
			request.setAttribute("vehicleType", records.getVehType());
			long fees = diffInHours * Long.parseLong(records.getFeesPayable());
			request.setAttribute("fees", Long.toString(fees));
			request.setAttribute("vehRegNo", records.getVehRegNo());
			request.setAttribute("vehMake", records.getVehMake());	
			request.setAttribute("vehTypeId", records.getVehTypeId());
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		// Load customer information
		String username = request.getParameter("UserID");
		request.setAttribute("username", username);
		try {
			
			ReservationRecords reservationRecords = new ReservationRecords();
			reservationRecords = dao.getCustomerDetails(username);
			request.setAttribute("name", reservationRecords.getCustomerName());
			request.setAttribute("phNum", reservationRecords.getPhoneNum());
			request.setAttribute("email", reservationRecords.getEmailId());
			request.setAttribute("licenseNum", reservationRecords.getLicenseNumber());
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		requestDispatcher.forward(request, response);
		
	}

}
