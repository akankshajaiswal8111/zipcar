<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style>
.card {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
	transition: 0.3s;
	width: 60%;
	background: lightsteelblue;
}
.card:hover {
	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
}
.container {
	padding: 2px 16px;
}
.button {
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
<head>
<meta charset="ISO-8859-1">
<title>Admin Home</title>
</head>
<body>

	<%
		String s = (String) session.getAttribute("role");
	%>
	<%
			if (s.equals("Manager")) {
		%>
	<div></div><h1 style="margin-left: 34%;">Manager Dashboard</h1>
	<%} %>
	
	<%
			if (!s.equals("Manager")) {
		%>
	<div></div><h1 style="margin-left: 34%;">Admin Dashboard</h1>
	<%} %>
	<a href='index.jsp' class="button" style="background-color: #dedede;float:right;margin-right:7%;">Logout</a>
	</div>
	
	<table style="width: 100%; margin-left: 8%;" cellspacing="15">
		<tr>
			<td>
				<div class="card">

					<div class="container">
						<h2>
							<b><a style="padding-left: 26%;color:black;" href="VehicleTypes.jsp">Vehicle
									Types</a></b>
						</h2>
					</div>
				</div>
			</td>

			<td>
				<div class="card">

					<div class="container">
						<h2>
							<b><a style="padding-left: 26%;color:black;" href="ListLocations.jsp">Rent Locations</a></b>
						</h2>
					</div>
				</div>
			</td>
		</tr>

		<tr>
			<td>
				<div class="card">

					<div class="container">
						<h2>
							<b><a style="padding-left: 26%;color:black;" href="ListVehicles.jsp">Vehicles</a></b>
						</h2>
					</div>
				</div>
			</td>
			<td>
				<div class="card">

					<div class="container">
						<h2>
							<b><a style="padding-left: 26%;color:black;" href="CustomerList.jsp">Customers</a></b>
						</h2>
					</div>
				</div>
			</td>

		</tr>
		<%
			if (s.equals("Manager")) {
		%>
		<tr>
			<td>
				<div class="card">

					<div class="container">
						<h2>
							<b><a style="padding-left: 26%;color:black;" href="FeesAndCharges.jsp">Fees/Charges</a></b>
						</h2>
					</div>
				</div>
			</td>


			<td>
				<div class="card">

					<div class="container">
						<h2>
							<b><a style="padding-left: 26%;color:black;" href="AdminSignUp.jsp">Add
									an Admin</a></b>
						</h2>
					</div>
				</div>
			</td>

		</tr>
		<%}%>
	</table>
</body>
</html>