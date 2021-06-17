<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
<style>
.button {
    background-color: #00acee;
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  cursor: pointer;
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
<title>Customer Page</title>
</head>

<body>
<%
String userName =  (String) session.getAttribute("username"); //"testUser";
%>

<br/>
	<a href='index.jsp' class="logoutbt" style="background-color: #dedede;float:right;margin-right:3%;">Logout</a>
	<a href='bookings.jsp' class="logoutbt" style="background-color: #dedede;float:right;margin-right:1%;">Home</a>
	
	<br/>
	<br/>

<h1 align="center">Welcome <%=userName%> to RentARide Customer Page</h1>

<br/>
<br/>
<br/>

<h3 align="center">Select Your Preference</h3>

<table align="center">
<tr>
<td>
<form action="manageBooking.jsp" method="POST">
<button type="submit" class="button">Manage Booking</button>
<input type="hidden" name="userName" value="<%=userName%>">
</form>
</td>
<td>
<form action="searchRentals.jsp" method="POST">
<button type="submit" class="button">Search Vehicles</button>
<input type="hidden" name="userName" value="<%=userName%>">
</form>
</td>
</tr>
</table>

</body>
</html>