<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Location</title>
</head>
<body>
<form method="POST" action='LocationController' name="frmAddLocation"><input
type="hidden" name="action" value="insert" />
<p><b>Add New Location</b></p>
<p><a href="AdminHome.jsp">Back to Admin Home</a></p>
<table>
<tr>
<td>Name</td>
<td><input type="text" name="Name" required /></td>
</tr>
<tr>
<td>Address</td>
<td><input type="text" name="Address" required /></td>
</tr>
<tr>
<td>Vehicle Capacity</td>
<td><input type="number" min="1" step="1" name="Vehicle Capacity" required /></td>
</tr>
<tr>
<td></td>
<td><input type="submit" value="Submit" /></td>
</tr>
</table>
</form>
<p><a href="LocationController?action=listlocation">List All Locations</a></p>
</body>
</html>