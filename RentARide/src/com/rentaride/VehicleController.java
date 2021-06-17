package com.rentaride;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import java.text.ParseException;
import java.util.Calendar;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/VehicleController")
		
public class VehicleController extends HttpServlet {  
	private static final long serialVersionUID = 1L;
    //private static String INSERT = "/AddVehicles.jsp";
    private static String Edit = "/EditVehicles.jsp";
    private static String ListRecord = "/ListVehicles.jsp";
    private VehicleDAO dao;
    private RentLocationDAO locdao;

    public VehicleController() throws SQLException, ClassNotFoundException {
        super();
        dao = new VehicleDAO();
        locdao= new RentLocationDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect="";
        String regNo = request.getParameter("RegNo");        
        String action = request.getParameter("action");
        if(action.equalsIgnoreCase("insert"))
        {
        	//String regNo = Integer.parseInt(rentLocationId);
        	VehicleMgr vehicle = new VehicleMgr();
        	String resDateStr = request.getParameter("LastServiceDate");
        	DateTimeFormatter sdf = DateTimeFormatter.ofPattern("[yyyy-MM-dd]");
        	LocalDate resDate=null;
        	resDate = LocalDate.parse(resDateStr, sdf);
			String resYearStr = request.getParameter("Year");
			DateTimeFormatter sdf2 = new DateTimeFormatterBuilder().appendPattern("yyyy").parseDefaulting(ChronoField.MONTH_OF_YEAR, 1).parseDefaulting(ChronoField.DAY_OF_MONTH, 1).toFormatter();

			LocalDate res=null;
			res = LocalDate.parse(resYearStr, sdf2);
			//Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
			//calendar.setTimeZone(res);
			int calendar1 = res.getYear();
			String locId = request.getParameter("Location");
        	vehicle.setRegNo(regNo);
        	vehicle.setType(request.getParameter("VehicleType"));
        	vehicle.setLocation(locId);
        	vehicle.setMake(request.getParameter("Make"));
        	vehicle.setModelNo(Integer.parseInt(request.getParameter("ModelNo")));
        	vehicle.setMileage(Double.parseDouble(request.getParameter("Mileage")));
        	vehicle.setLastServiceDate(resDate);
        	vehicle.setYear(calendar1);
        	vehicle.setQuality(request.getParameter("Quality"));
        	
        	if(dao.addVehicle(vehicle)) {
        	
        	int loc1 = Integer.parseInt(locId);
        	System.out.println("Location id on insert:"+ loc1);
        	int availCap = locdao.getCapacityById(loc1);
        	System.out.println("Available capacity from loc dao on insert is:"+ availCap);
        	availCap=availCap-1;
        	locdao.updateCapacityById(loc1,availCap);
        	}
        	redirect = ListRecord;
            try {
				request.setAttribute("vehicles", dao.listAllVehicles());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
           	System.out.println("Record Added Successfully");
        }
        else if (action.equalsIgnoreCase("delete")){
            String regno = request.getParameter("RegNo");
            
            redirect = ListRecord;
            VehicleMgr vMgr = dao.getVehicleByRegNo(regno);
            String locId = vMgr.getLocation();
            System.out.println("Location id :"+ locId);
            if(dao.removeVehicle(regno)) {
	            int loc1 = Integer.parseInt(locId);
	        	System.out.println("Location id:"+ loc1);
	        	int availCap = locdao.getCapacityById(loc1);
	        	System.out.println("Available capacity from loc dao is:"+ availCap);
	        	availCap++;
	        	locdao.updateCapacityById(loc1,availCap);
            }
            try {
				request.setAttribute("vehicles", dao.listAllVehicles());
			} catch (Exception e) {
				
				e.printStackTrace();
			}
            PrintWriter out = response.getWriter();
            out.println("Record Deleted Successfully");
        }else if (action.equalsIgnoreCase("editform")){        	
        	redirect = Edit;            
        } else if (action.equalsIgnoreCase("edit")) {
        	String regno = request.getParameter("RegNo");
            //int uid = Integer.parseInt(rentlocationid);            
            VehicleMgr u_vehicle = new VehicleMgr();
            String resDateStr = request.getParameter("LastServiceDate");
        	DateTimeFormatter sdf = DateTimeFormatter.ofPattern("[yyyy-MM-dd]");
        	LocalDate resDate=null;
        	resDate = LocalDate.parse(resDateStr, sdf);
			String resYearStr = request.getParameter("Year");
			//DateTimeFormatter sdf2 = DateTimeFormatter.ofPattern("yyyy");
			DateTimeFormatter sdf2 = new DateTimeFormatterBuilder().appendPattern("yyyy").parseDefaulting(ChronoField.MONTH_OF_YEAR, 1).parseDefaulting(ChronoField.DAY_OF_MONTH, 1).toFormatter();

			LocalDate res=null;
			res = LocalDate.parse(resYearStr, sdf2);
			//Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
			//calendar.setTimeZone(res);
			int calendar1 = res.getYear();
			String u_loc = request.getParameter("Location");
			int u_LocId = Integer.parseInt(u_loc);
            u_vehicle.setRegNo(regno);
            u_vehicle.setType(request.getParameter("VehicleType"));
            u_vehicle.setLocation(u_loc);
            u_vehicle.setMake(request.getParameter("Make"));
            u_vehicle.setModelNo(Integer.parseInt(request.getParameter("ModelNo")));
            u_vehicle.setMileage(Double.parseDouble(request.getParameter("Mileage")));
            u_vehicle.setLastServiceDate(resDate);
        	u_vehicle.setYear(calendar1);
        	u_vehicle.setQuality(request.getParameter("Quality"));
        	
        	VehicleMgr u_vehicle1 = dao.getVehicleByRegNo(regno);
        	String dbLoc = u_vehicle1.getLocation();
        	int dbLocId = Integer.parseInt(dbLoc);
        	try {
				if(dao.editVehicle(u_vehicle)) {
					if(u_LocId != dbLocId ) {
						int availCap = locdao.getCapacityById(u_LocId);
						int availCap1 = locdao.getCapacityById(dbLocId);
						availCap--;
						availCap1++;
						locdao.updateCapacityById(u_LocId,availCap);
						locdao.updateCapacityById(dbLocId,availCap1);
					}
				}
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            request.setAttribute("vehicles", u_vehicle);
            redirect = ListRecord;
            System.out.println("Record updated Successfully");
         } else if (action.equalsIgnoreCase("listvehicles")){
            redirect = ListRecord;
			try {
				request.setAttribute("vehicles", dao.listAllVehicles());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

         } //else if (action.equalsIgnoreCase("insert")) {
        	// System.out.println("Inside default else");
           // redirect = INSERT;
        //}

        RequestDispatcher rd = request.getRequestDispatcher(redirect);
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        //String capacity = request.getParameter("name");
    }
}

