<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import ="java.util.*"%>
<%@ page import="com.rentaride.RentLocationDAO"%>
<%@ page import="com.rentaride.RentLocation"%>
<%@ page import="java.io.PrintWriter" %>

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
<h1>Rental Locations</h1>
<p><a href="AdminHome.jsp">Back to Admin Dashboard</a></p>
<%
	//RentLocation rentLocation = new RentLocation();
RentLocationDAO dao = new RentLocationDAO();
List<com.rentaride.RentLocation> rentLocationList = dao.listAllRentLocations();
%>
<p><a href="AddLocations.jsp" style="background-color: #25446b" class="button">Add Rental Location</a></p>
<table border="1">
<tr>
<th>Id</th>
<th>Name</th>
<th>Address</th>
<th>Vehicle Capacity</th>
<th>Actions</th>
</tr>
<tr>
<%
	for (RentLocation rentlocation : rentLocationList) {
	
%>
<td><%=rentlocation.getId()%></td>
<td><%=rentlocation.getName()%></td>
<td><%=rentlocation.getAddress()%></td>
<td><%=rentlocation.getVehicleCapacity()%></td>
<td><a href="LocationController?action=editform&RentLocationId=<%=rentlocation.getId()%>" style="background-color: #256b31;" class="button">Edit</a>
<% try {  %>
<a href="LocationController?action=delete&RentLocationId=<%=rentlocation.getId()%>" style="background-color: #c11e16;" class="button">Delete</a></td>
<% }
 catch(Exception e) { 

out.print("There are vehicles associated with this Location. Delete the associated vehicles before deleting the Location.");
 } %>
</tr>
<%
}
%>
</table>
</body>
</html>