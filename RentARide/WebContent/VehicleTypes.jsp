<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
<meta charset="ISO-8859-1">
<title>Vehicle Types</title>
</head>
<body>
	<%@page import="java.sql.DriverManager"%>
	<%@page import="java.sql.ResultSet"%>
	<%@page import="java.sql.Statement"%>
	<%@page import="java.sql.Connection"%>
	<div class="row">


		<h1>Vehicle Types</h1>
		<a href="AdminHome.jsp">Back to Admin Dashboard</a>
		<h3></h3>
		<a href="AddVehicleType.jsp" class="button" style="background-color: #25446b;">Add a New Vehicle Type</a>
		</h3>
		<br /> <br /> <br />


		<table border="1">
			<thead>
				<tr>
					<th>Vehicle Type ID</th>
					<th>Vehicle Type</th>
					<th>Description</th>
					<th>Price/Hour</th>
					<th>Action</th>
				</tr>

			</thead>
			<%
				String driverName = "com.mysql.jdbc.Driver";
			 String hostname=<<AWS RDS URL>>;
			 String port="3306";
		     String dbName = "rentaride"; 
		    String dbUsername = <<DB USERNAME>>; 
		    String dbPassword = <<DB PASSWORD>>; 
		     String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + dbUsername + "&password=" + dbPassword;
				try {
					Class.forName(driverName);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				Connection connection = null;
				Statement statement = null;
				ResultSet resultSet = null;
			%>
			<%
				try {
					connection = DriverManager.getConnection(jdbcUrl);
					statement = connection.createStatement();
					String sql = "SELECT * FROM vehicletype";
					resultSet = statement.executeQuery(sql);
					while (resultSet.next()) {
			%>
			<tr>
				<td><%=resultSet.getString("VehicleTypeId")%></td>
				<td><%=resultSet.getString("Type")%></td>
				<td><%=resultSet.getString("description")%></td>
				<td>$ <%=resultSet.getInt("PricePerHr")%></td>
				<td><a href='EditVehicleType.jsp?type=<%=resultSet.getString("VehicleTypeId")%>' class="button" style="background-color: #256b31;">Edit</a>
				<a href='DeleteVehicleType.jsp?type=<%=resultSet.getString("VehicleTypeId")%>' class="button" style="background-color: #c11e16;">Delete</a>
				</td>
			</tr>
			<%
				}
				} catch (Exception e) {
					e.printStackTrace();
				}
			%>
		</table>
</body>
</html>