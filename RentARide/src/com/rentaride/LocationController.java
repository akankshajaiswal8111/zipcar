package com.rentaride;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LocationController")
		
public class LocationController extends HttpServlet {  
	private static final long serialVersionUID = 1L;
    //private static String INSERT = "/AddLocations.jsp";
    private static String Edit = "/EditLocations.jsp";
    private static String ListRecord = "/ListLocations.jsp";
    private RentLocationDAO dao;

    public LocationController() throws SQLException, ClassNotFoundException {
        super();
        dao = new RentLocationDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        String redirect="";
        String rentLocationId = request.getParameter("RentLocationId");        
        String action = request.getParameter("action");
        if(action.equalsIgnoreCase("insert"))
        {
        	//int id = Integer.parseInt(rentLocationId);
        	RentLocation rentLocation = new RentLocation();
        	int capacity = Integer.parseInt(request.getParameter("Vehicle Capacity"));
        	//rentLocation.setId(id);
        	rentLocation.setName(request.getParameter("Name"));
        	rentLocation.setAddress(request.getParameter("Address"));
        	rentLocation.setVehicleCapacity(capacity);
        	rentLocation.setAvailableCapacity(capacity);
        	//String name = request.getParameter("name");
            //String address = request.getParameter("address");
            //int vehicleCapacity = Integer.parseInt(request.getParameter("vehicleCapacity"));
           
           // RentLocation rentLocation = new RentLocation(name, address, vehicleCapacity);
        	
        	dao.addLocation(rentLocation);
        	
        	//redirect = ListRecord;
            try {
				request.setAttribute("users", dao.listAllRentLocations());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
           	System.out.println("Record Added Successfully");
           	
           	RequestDispatcher rd = request.getRequestDispatcher(ListRecord);
            rd.forward(request, response);
        }
        else if (action.equalsIgnoreCase("delete")){
            String rentlocationid = request.getParameter("RentLocationId");
            int id = Integer.parseInt(rentlocationid);
            if(!dao.removeLocation(id)) {
	            PrintWriter out = response.getWriter(); 
				out.println("<html><body><b>There are vehicles associated with the location. Delete the associated vehicles before deleting the Location.</b></body></html>");
            }
            else {
            	try {
    				request.setAttribute("locations", dao.listAllRentLocations());
    			} catch (SQLException e) {
    				
    				e.printStackTrace();
    			}
            	RequestDispatcher rd = request.getRequestDispatcher(ListRecord);
                rd.forward(request, response);
                
            }
			
            
            //PrintWriter out = response.getWriter();
            //out.println("Record Deleted Successfully");
        }else if (action.equalsIgnoreCase("editform")){ 
        	RequestDispatcher rd = request.getRequestDispatcher(Edit);
            rd.forward(request, response);
        	         
        } else if (action.equalsIgnoreCase("edit")){
        	// if vehiclecapacity is increased, increase availableCapacity
        	// if vehicle capacity is decreased, get number of vehicles associated with that location and if they are more than 
        	//the new vehicleCapacity, throw error to remove else reduce the vehicle capacity to new number and update available capacity with that difference
        	String rentlocationid = request.getParameter("RentLocationId");
            int uid = Integer.parseInt(rentlocationid); 
            int new_VC = Integer.parseInt(request.getParameter("Vehicle Capacity"));
            RentLocation u_rloc = new RentLocation();
            u_rloc.setId(uid);
            u_rloc.setName(request.getParameter("Name"));
            u_rloc.setAddress(request.getParameter("Address"));
            u_rloc.setVehicleCapacity(new_VC);
            RentLocation rLoc = dao.getLocationById(uid);
            int curr_VC = rLoc.getVehicleCapacity();
            int curr_AC = rLoc.getAvailableCapacity();
            System.out.println("Curr VC:"+ curr_VC);
            System.out.println("Curr AC:"+ curr_AC);
            System.out.println("New VC:"+ new_VC);
            int new_AC=0;
            try {
				
					if(new_VC >= curr_VC) {
						System.out.println("1...");
						new_AC = curr_AC+(new_VC - curr_VC);
						System.out.println("New AC:"+ new_AC);
						if(dao.editLocation(u_rloc)) dao.updateCapacityById(uid, new_AC);
		            	RequestDispatcher rd = request.getRequestDispatcher(ListRecord);
		                rd.forward(request, response);
						
					}
					else if(new_VC < curr_VC) {
						System.out.println("2...");
						if(new_VC >= (curr_VC - curr_AC)) {
							new_AC = curr_AC-(curr_VC-new_VC);
							System.out.println("New AC:"+ new_AC);
							if(dao.editLocation(u_rloc)) dao.updateCapacityById(uid, new_AC);
			            	RequestDispatcher rd = request.getRequestDispatcher(ListRecord);
			                rd.forward(request, response);
							redirect = ListRecord;
						} else {
							System.out.println("3...");
							PrintWriter out = response.getWriter();
							out.println("<font color=red>There are more vehicles associated with this location. Remove some vehicles or assign them to new location.<p><a href=\"ListLocations.jsp\">Back</a></p></font>");
						
						}
					}
					
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //request.setAttribute("user", u_rloc);
            //RequestDispatcher rd = request.getRequestDispatcher(ListRecord);
            //rd.forward(request, response);
            
            
            System.out.println("Record updated Successfully");
         } else if (action.equalsIgnoreCase("listlocation")){
            //redirect = ListRecord;
        	 RequestDispatcher rd = request.getRequestDispatcher(ListRecord);
             rd.forward(request, response);
			try {
				request.setAttribute("rentlocations", dao.listAllRentLocations());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

         }//else if (action.equalsIgnoreCase("insert")) {
        	// System.out.println("Inside default else");
           // redirect = INSERT;
        //}
         else {
        	 int locId = Integer.parseInt(request.getParameter("loc"));
        	 int availableCapacityAtLoc = -1;
        	 availableCapacityAtLoc = dao.getCapacityById(locId);
        	 response.setContentType("text/plain");
        	 response.setCharacterEncoding("UTF-8");
             response.getWriter().write(availableCapacityAtLoc > 0 ? "true" : "false");
         }
        //RequestDispatcher rd = request.getRequestDispatcher(redirect);
        //rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
