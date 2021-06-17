<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import ="java.time.*"%>
<%@ page import ="java.util.*"%>
<%@ page import="com.rentaride.VehicleDAO"%>
<%@ page import="com.rentaride.VehicleMgr"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!Doctype HTML>
<html>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

.button {
	font: bold 13px Arial;
	text-decoration: none;
	color: #dedede;
	padding: 4px 8px 5px 9px;
	border-top: 1px solid #CCCCCC;
	border-right: 1px solid #333333;
	border-bottom: 1px solid #333333;
	border-left: 1px solid #CCCCCC;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Locations</title>
</head>
<body>
<h1>Vehicles</h1>
<p><a href="AdminHome.jsp">Back to Admin Dashboard</a></p>
<%
	//RentLocation rentLocation = new RentLocation();
VehicleDAO dao = new VehicleDAO();
List<VehicleMgr> vehicleList = dao.listAllVehicles();
%>
<p><a href="AddVehicles.jsp" style="background-color: #25446b" class="button">Add New Vehicle</a></p>
<table border="1">
<tr>
<th>RegNo</th>
<th>VehicleType</th>
<th>Location</th>
<th>Make</th>
<th>ModelNo</th>
<th>Mileage</th>
<th>LastServiceDate</th>
<th>Year</th>
<th>Quality</th>
<th>Actions</th>
</tr>
<tr>
<%
	for (VehicleMgr vehicle : vehicleList) {
%>
<td><%=vehicle.getRegNo()%></td>
<td><%=vehicle.getType()%></td>
<td><%=vehicle.getLocation()%></td>
<td><%=vehicle.getMake()%></td>
<td><%=vehicle.getModelNo()%></td>
<td><%=vehicle.getMileage()%></td>
<td><%=vehicle.getLastServiceDate()%></td>
<td><%=vehicle.getYear()%></td>
<td><%=vehicle.getQuality()%></td>

<td><a href="VehicleController?action=editform&RegNo=<%=vehicle.getRegNo()%>" style="background-color: #256b31;" class="button">Edit</a>
<% try { %>
<a href="VehicleController?action=delete&RegNo=<%=vehicle.getRegNo()%>" style="background-color: #c11e16;" class="button">Delete</a></td>
<% }
 catch(Exception e) { %>
 <h2 style="color:red;">Error!</h2>
 There are vehicles associated with the Vehicle Type. Delete the associated vehicles before deleting the Vehicle Type.</h2>
 <% } %>
</tr>
<%
}
%>
</table>

</body>
</html>