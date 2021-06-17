
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>



<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
<script >
function test(reservationid)
{
 	/* var f1=document.getElementById("demo");
	f1.innerHTML=name;
    console.log("hello"); */
 
  	var f=document.getElementById("ViewBookingForm");
 	f.method="post";
 	console.log("test"+name);
    f.action='/reservationDetails?reservationId='+reservationid+'&booked=true&action=reserve';
    f.submit();
}


</script>

<style>
.filter {
	display: inline;
}
.logoutbt {
	font: bold 13px Arial;
	text-decoration: none;
	color: black;
	padding: 4px 8px 5px 9px;
	border-top: 1px solid #CCCCCC;
	border-right: 1px solid #333333;
	border-bottom: 1px solid #333333;
	border-left: 1px solid #CCCCCC;
}
</style>
<meta charset="UTF-8">
<title>Manage Bookings</title>
</head>
<body id="body">
	<br/>
	<a href='index.jsp' class="logoutbt" style="background-color: #dedede;float:right;margin-right:3%;">Logout</a>
	<a href='bookings.jsp' class="logoutbt" style="background-color: #dedede;float:right;margin-right:1%;">Home</a>
	
	<br/>
	<br/>
	<h1 align="center">Your Car Rental Reservations</h1>	
	<%
				String username= request.getParameter("userName");

				String hostname=<<AWS RDS URL>>;
				 String port="3306";
			    String dbName = "rentaride"; 
			    String dbUsername = <<DB USERNAME>>; 
			    String dbPassword = <<DB PASSWORD>>; 
			    String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + dbUsername + "&password=" + dbPassword;
				Connection conn = null;
				Statement stat = null;
				ResultSet result = null;
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(jdbcUrl);


				PreparedStatement ps = null;

				stat = conn.createStatement();
				String data = "call ShowUserBooking(?) ";

				ps = conn.prepareStatement(data);
				ps.setString(1, username);

				ResultSet rs = ps.executeQuery();

				if (rs.next() == false) {
			%>
			<h3 align ='center'>You do not have any car bookings</h3>
			<%
				} else {
					rs = ps.executeQuery();
			%>

	
	<div class="container">
	<table class="table" border='1' align='center' cellpadding='10'>
		<thead>
			<tr>
				<th>Car Make</th>
				<th>Model No</th>
				<th>Location</th>
				<th>Pick Up Date</th>
				<th>Pick Up Time</th>
				<th>Drop Off Date</th>
				<th>Drop Off Time</th>
				<th>Edit</th>
			</tr>

		</thead>
		<%
				while (rs.next()) {
			%>
		<tbody>
			
			<tr>
				<td style="display:none;"><%=rs.getString("reservationid")%></td>
				<td><%=rs.getString("make")%></td>
				<td><%=rs.getString("modelno")%></td>
				<td><%=rs.getString("name")%></td>
 				<td><%=rs.getString("pickupdate")%></td>
 				<td><%=rs.getString("pickuptime")%></td>
 				<td><%=rs.getString("dropoffdate")%></td>
				<td><%=rs.getString("dropofftime")%></td>
			 	<td>
			 	<form name="ViewBookingForm" method="post" action="reservationDetails">
			 	<input type="submit" class="btn btn-primary" name="Edit" value="Edit">
				<input type="hidden" name="reservationId" value="<%=rs.getString("reservationid")%>"/>
        		<input type="hidden" name="booked" value="true"/>
        		<input type="hidden" name="action" value="reserve"/>
        		</form>
        		</td>  
			</tr>
			<tr></tr>
			<tr></tr>

			<%
				}
			}
				
			%>
			

		</tbody>

	</table>
	</div>
	<p id="demo"></p>
</body>
</html>
