<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
 <%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
String uname = request.getParameter("username");
String isActive = request.getParameter("isActive");
Boolean active;
if(isActive =="true"){
	active = true;
}
else{
	active=false;
}
String driverName = "com.mysql.jdbc.Driver";
String hostname=<<AWS RDS URL>>;
String port="3306";
String dbName = "rentaride"; 
String dbUsername = <<DB USERNAME>;
String dbPassword = <<DB PASSWORD>>; 
String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + dbUsername + "&password=" + dbPassword;
Connection connection = DriverManager.getConnection(jdbcUrl);
Statement st = connection.createStatement();
	String q =  "update customerdetails set isActive="+request.getParameter("isActive")+" where userName='"+uname+"'";
st.executeUpdate(q);
response.sendRedirect("CustomerList.jsp");
%>
<head>
<meta charset="ISO-8859-1">
<title>Delete Vehicle Type</title>
</head>
<body>

</body>
</html>