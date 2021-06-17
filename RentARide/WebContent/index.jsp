<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<style>
.card {
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  transition: 0.3s;
  width: 60%;
  background:lightsteelblue;
}
.card:hover {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}
.container {
  padding: 2px 16px;
}
.link{
	color:black;
}
</style>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Rent-A-Car</title>
</head>
<body>
<h1 style="margin-left:34%;">Welcome to Rent-A-Ride!</h1>
<table style="width:100%;margin-left:8%;" cellspacing="15">
<tr>
<td>
<div class="card" style="margin-left:24%;width:30%;">

  <div class="container">
    <h2><b><a style="padding-left:26%;color:black;" href="AdminLogin.jsp">Admin Login</a></b></h2> 
  </div>
</div>
</td>
</tr>
<tr>
<td>
<div class="card" style="margin-left:24%;width:30%;">

  <div class="container">
    <h2><b><a style="padding-left:26%;color:black;" href="CustomerSignup.jsp">Customer Sign up</a><br/><br/>
   <a style="padding-left:26%;color:black;" href="CustLogin.jsp">Customer Login</a> </b></h2> 
  </div>
</div>
</td>

</tr>

</table>

</body>
</html>