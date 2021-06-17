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
<title>Customer List</title>
</head>
<body>
<%@page import="java.sql.DriverManager"%>
	<%@page import="java.sql.ResultSet"%>
	<%@page import="java.sql.Statement"%>
	<%@page import="java.sql.Connection"%>
<h1>List of Registered Customers</h1>
<a href="AdminHome.jsp">Back to Admin Dashboard</a>
<br/>
<br/>
<table border="1">
			<thead>
				<tr>
					<th>Customer Name</th>
					<th>Email ID</th>
					<th>Phone No.</th>
					<th>Address</th>
					<th>License Number</th>
					<th>Action</th>
				</tr>
				<tr>
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
					String sql = "SELECT * FROM customerdetails ";
					resultSet = statement.executeQuery(sql);
					while (resultSet.next()) {
			%>
			<tr>
				<td><%=resultSet.getString("Name")%></td>
				<td><%=resultSet.getString("EmailId")%></td>
				<td><%=resultSet.getString("PhoneNum")%></td>
				<td> <%=resultSet.getString("ResidentialAddress")%></td>
				<td> <%=resultSet.getString("LicenseNumber")%></td>
				<td><%if(resultSet.getBoolean("isActive") == true){ %> <a href='TerminateCustomer.jsp?username=<%=resultSet.getString("Username")%>&isActive=false' class="button" style="background-color: #c11e16;">Terminate</a><%} %>
				<% if(resultSet.getBoolean("isActive") == false){ %><a href='TerminateCustomer.jsp?username=<%=resultSet.getString("Username")%>&isActive=true' class="button" style="background-color: #256b31;">Activate</a><%} %>
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