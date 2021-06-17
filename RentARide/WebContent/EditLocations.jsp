<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ page import="com.rentaride.RentLocationDAO"%>
<%@ page import="com.rentaride.RentLocation"%>
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
	background-color: #EEEEEE;
	color: #333333;
	padding: 4px 8px 5px 9px;
	border-top: 1px solid #CCCCCC;
	border-right: 1px solid #333333;
	border-bottom: 1px solid #333333;
	border-left: 1px solid #CCCCCC;
}
</style>
<head>
</head>
<body>
<%
RentLocation rentLocation = new RentLocation();
%>
<%
RentLocationDAO dao = new RentLocationDAO();
%>
<form method="POST" action='LocationController' name="frmEditLocation"><input
type="hidden" name="action" value="edit" /> <%
String rlocid = request.getParameter("RentLocationId");
if (rlocid != null) {
int id = Integer.parseInt(rlocid);
rentLocation = dao.getLocationById(id);
%>
<p><b>Edit Location</b></p>
<p><a href="AdminHome.jsp">Back to Admin Home</a></p>
<table>
<tr>
<td>Rent Location ID</td>
<td><input type="text" name="RentLocationId" readonly="readonly"
value="<%=rentLocation.getId()%>"></td>
</tr>
<tr>
<td>Name</td>
<td><input type="text" name="Name" required value="<%=rentLocation.getName()%>"/></td>
</tr>
<tr>
<td>Address</td>
<td><input type="text" name="Address" required value="<%=rentLocation.getAddress()%>"/></td>
</tr>
<tr>
<td>Vehicle Capacity</td>
<td><input type="number" min="1" step="1" name="Vehicle Capacity" required value="<%=rentLocation.getVehicleCapacity()%>"/></td>
</tr>
<tr>
<td></td>
<td><input type="submit" value="Update" /></td>
</tr>
</table>
<p><a href="LocationController?action=listlocation">List All Locations</a></p>
<%
} else
out.println("Rent Location ID Not Found");
%>
</form>
</body>
</html>