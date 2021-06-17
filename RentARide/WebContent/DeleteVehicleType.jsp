<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
 <%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
int type =  Integer.valueOf(request.getParameter("type"));
String driverName = "com.mysql.jdbc.Driver";
String hostname=<<AWS RDS URL>>;
String port="3306";
String dbName = "rentaride"; 
String dbUsername = <<DB USERNAME>>; 
String dbPassword = <<DB PASSWORD>>; 
String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + dbUsername + "&password=" + dbPassword;
Connection connection = DriverManager.getConnection(jdbcUrl);
Statement st = connection.createStatement();
try{
st.executeUpdate("delete from vehicletype where vehicleTypeId='"+type+"'");
response.sendRedirect("VehicleTypes.jsp");
}
catch(Exception e){%>
<h2 style="color:red;">Error!</h2>
There are vehicles associated with the Vehicle Type. Delete the associated vehicles before deleting the Vehicle Type.</h2>
<a href="VehicleTypes.jsp">Back to Vehicle Types</a>
	<%
}
%>
<head>
<meta charset="ISO-8859-1">
<title>Delete Vehicle Type</title>
</head>
<body>

</body>
</html>