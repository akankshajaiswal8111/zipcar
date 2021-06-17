<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Vehicle Type</title>
</head>
<body>
<%
String driverName = "com.mysql.jdbc.Driver";
String hostname=<<AWS RDS URL>>;
String port="3306";
String dbName = "rentaride"; 
String dbUsername = <<DB USERNAME>>; 
String dbPassword = <<DB PASSWORD>>; 
String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + dbUsername + "&password=" + dbPassword;
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
PreparedStatement st =null;
Class.forName(driverName).newInstance();
connection = DriverManager.getConnection(jdbcUrl);
%>

<div style="position:absolute;top:20%;left:20%;margin-top:-50px;margin-left:-50px;">
 <h1>Edit Vehicle Type</h1>
 <form action="" method="post"> 
  <%
 
 statement = connection.createStatement();
 Integer type =  Integer.valueOf(request.getParameter("type"));
 String query = "select * from vehicletype where vehicleTypeId='"+type+"'";
 resultSet = statement.executeQuery(query);
 
 while(resultSet.next()){
 %>
        <p>Vehicle Type:</p>  
        <input type="text" name="name" value='<%=resultSet.getString("Type") %>'/> 
        <br/> 
        <p>Description:</p>  
        <input type="text" name="description"  value='<%=resultSet.getString("description") %>'/> 
        <p>Price/Hr:</p>  
        <input type="text" name="pricePerHr"  value='<%=resultSet.getString("PricePerHr") %>'/> 
        <br/><br/><br/> 
        <%} %>
        <input type="submit"/> 
        <a href="VehicleTypes.jsp">Back to Vehicle Types</a>
    </form> 
</div>
</body>
</html>
<%String vehicleType =  request.getParameter("name");
	String desc =  request.getParameter("description");
	String price = request.getParameter("pricePerHr");
	if(vehicleType!= null && desc!= null && price != null){
		String q =  "update vehicletype set type=?, description=?, pricePerHr=? where vehicleTypeId='"+type+"'";
		st = connection.prepareStatement(q);
		st.setString(1, vehicleType);
		st.setString(2, desc);
		st.setInt(3, Integer.valueOf(price));
		st.executeUpdate();
		response.sendRedirect("VehicleTypes.jsp");
		
		
	}
%>