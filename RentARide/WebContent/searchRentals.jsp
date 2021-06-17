
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.format.DateTimeFormatter"%> 



<!DOCTYPE html>
<html>
<head>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>
<script>
	function myFunction() {

		var vehtype = document.getElementById("VehType").value;
		var fromdate = document.getElementById("fromdate").value;
		var todate = document.getElementById("todate").value;
		
		var today = new Date();
		var dd = String(today.getDate()).padStart(2, '0');
		var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
		var yyyy = today.getFullYear();

		today = yyyy + '-' + mm + '-' + dd;


		if (vehtype == 0 || fromdate == "" || todate == "") {

			alert("Select all the parameters!");
		}
		else if  ((fromdate < today) || (todate < today)) {
			alert("Past date not allowed!");
		}

		if (emptyresult == true) {
			alert("No bookings!");
		}

	}
</script>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
<style>
.filter {
	display: inline;
}

label {
	padding-left: 5px;
}

.pair {
	border: 2px black solid;
	padding: 2px;
	border-radius: 5px;
}

.select1 {
	position: relative;
	font-family: Arial;
	width: 70px;
	text-align: center;
	border: 1px solid #808080;
}

.select2 {
	font-family: Arial;
	width: 110px;
	text-align: center;
	border: 1px solid #808080;
}

.button1 {
	font-family: Times New Roman;
	font-size: 14px;
	padding: 2px 4px;
	border-radius: 4px;
	border: 1px solid #808080;
	width: 80px;
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
<title>Search Rentals</title>
</head>
<body id="body">
	<br />
	<a href='index.jsp' class="logoutbt"
		style="background-color: #dedede; float: right; margin-right: 3%;">Logout</a>
	<a href='bookings.jsp' class="logoutbt"
		style="background-color: #dedede; float: right; margin-right: 1%;">Home</a>
	<br />
	<br />
	<h1 align="center">Search Rental Vehicles</h1>

	<div class="container">
		<div class="row">
			<div class="filter">
				<form method="post" action="">
					<h3>Select your preferences</h3>
					<label>Vehicle Type</label> <select id="VehType" name="vehicletype"
						class="select1">
						<option value="0" selected>Select</option>
						<%
							try {

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

								String query = "Select * from vehicletype";
								Statement stm = conn.createStatement();
								ResultSet rs = stm.executeQuery(query);
								while (rs.next()) {
						%>
						<option value="<%=rs.getString("vehicletypeid")%>"><%=rs.getString("Type")%></option>
						<%
							}
							} catch (Exception ex) {
								ex.printStackTrace();
								out.println("Error" + ex.getMessage());
							}
						%>
					</select> <label cellpadding='10'>Location</label> <select id="location"
						name="location" class="select1">
						<option value="0" selected>Select</option>
						<%
							try {

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

								String query = "Select * from rentlocations";
								Statement stm = conn.createStatement();
								ResultSet rs = stm.executeQuery(query);
								while (rs.next()) {
						%>
						<option value="<%=rs.getString("RentLocationid")%>"><%=rs.getString("Name")%></option>
						<%
							}
							} catch (Exception ex) {
								ex.printStackTrace();
								out.println("Error" + ex.getMessage());
							}
						%>
					</select> <label>From Date</label> <input type="date" id="fromdate"
						name="fromdate" class="select2"> <label>From Time</label>
					<select id="fromtime" name="fromtime" class="select1">
						<option value="07:01:00">07:00</option>
						<option value="08:01:00">08:00</option>
						<option value="09:01:00" selected>09:00</option>
						<option value="10:01:00">10:00</option>
						<option value="11:01:00">11:00</option>
						<option value="12:01:00">12:00</option>
						<option value="13:01:00">13:00</option>
						<option value="14:01:00">14:00</option>
						<option value="15:01:00">15:00</option>
						<option value="16:01:00">16:00</option>
						<option value="17:01:00">17:00</option>
						<option value="18:01:00">18:00</option>
						<option value="19:01:00">19:00</option>
						<option value="20:01:00">20:00</option>
						<option value="21:01:00">21:00</option>
						<option value="22:01:00">22:00</option>
						<option value="23:01:00">23:00</option>
						<option value="24:01:00">24:00</option>
						<option value="00:01:00">00:00</option>
						<option value="01:01:00">01:00</option>
						<option value="02:01:00">02:00</option>
						<option value="03:01:00">03:00</option>
						<option value="04:01:00">04:00</option>
						<option value="05:01:00">05:00</option>
						<option value="06:01:00">06:00</option>
					</select> <label>To Date</label> <input type="date" name="todate"
						id="todate" class="select2"> <label>To Time</label> <select
						id="totime" name="totime" class="select1">
						<option value="06:59:00">07:00</option>
						<option value="07:59:00">08:00</option>
						<option value="08:59:00" selected>09:00</option>
						<option value="09:59:00">10:00</option>
						<option value="10:59:00">11:00</option>
						<option value="11:59:00">12:00</option>
						<option value="12:59:00">13:00</option>
						<option value="13:59:00">14:00</option>
						<option value="14:59:00">15:00</option>
						<option value="15:59:00">16:00</option>
						<option value="16:59:00">17:00</option>
						<option value="17:59:00">18:00</option>
						<option value="18:59:00">19:00</option>
						<option value="19:59:00">20:00</option>
						<option value="20:59:00">21:00</option>
						<option value="21:59:00">22:00</option>
						<option value="22:59:00">23:00</option>
						<option value="23:59:00">24:00</option>
						<option value="00:00:00">00:00</option>
						<option value="12:59:00">01:00</option>
						<option value="01:59:00">02:00</option>
						<option value="02:59:00">03:00</option>
						<option value="03:59:00">04:00</option>
						<option value="04:59:00">05:00</option>
						<option value="05:59:00">06:00</option>
					</select> <input type="submit" value="Submit" onclick="myFunction()"
						class="btn btn-primary">
				</form>

			</div>

		</div>
	</div>
	<span style="display: inline-block; width: 10;"></span>

	<div class="container">
		<div class="row">
			<div class="filter">
				<%
					boolean emptyresult = false;
				%>
				<%
					if (request.getParameter("userName") != null) {
						String pg = "searchRentals.jsp";
						session.setAttribute("userName", request.getParameter("userName"));
						response.sendRedirect(pg);
					}

					if (request.getParameter("location") != null && request.getParameter("vehicletype") != null
							&& request.getParameter("fromdate") != null && request.getParameter("todate") != null
							&& request.getParameter("fromtime") != null && request.getParameter("totime") != null) {

						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

						LocalDate today = LocalDate.now();
						DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");

						LocalDate frmDate = LocalDate.parse("1900-01-01", myFormatObj);
						LocalDate toDate = LocalDate.parse("1900-01-01", myFormatObj);

						if (request.getParameter("fromdate") != "" || request.getParameter("todate") != "") {
							frmDate = LocalDate.parse(request.getParameter("fromdate"), myFormatObj);
							toDate = LocalDate.parse(request.getParameter("todate"), myFormatObj);
						}

						if (!frmDate.isBefore(today) && !toDate.isBefore(today)) {

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

							int sendlocation;
							sendlocation = 0;
							int vehtype;
							vehtype = 0;

							if (request.getParameter("location") != null) {
								sendlocation = Integer.parseInt(request.getParameter("location"));
							}

							if (request.getParameter("vehicletype") != null) {
								vehtype = Integer.parseInt(request.getParameter("vehicletype"));
							}

							String fromdate = "";
							if (request.getParameter("fromdate") != "") {
								fromdate = request.getParameter("fromdate");
							} else {
								fromdate = "1991/01/01";
							}
							String todate = "";
							if (request.getParameter("todate") != "") {
								todate = request.getParameter("todate");
							} else {

								todate = "1990/01/01";
							}

							String fromtime = request.getParameter("fromtime");
							String totime = request.getParameter("totime");

							String fromdatetime = fromdate + " " + fromtime;
							String todatetime = todate + " " + totime;

							PreparedStatement ps = null;

							stat = conn.createStatement();
							String data = "call SearchVehicle(?,?,?,?) ";

							ps = conn.prepareStatement(data);
							ps.setInt(1, vehtype);
							ps.setInt(2, sendlocation);
							ps.setString(3, fromdatetime);
							ps.setString(4, todatetime);

							ResultSet rs = null;

							if (request.getParameter("VehType") == "0" || request.getParameter("fromdate") == ""
									|| request.getParameter("todate") == "") {
								rs = null;
							} else {

								rs = ps.executeQuery();

								if (rs.next() == false) {
									rs = null;
									stat = conn.createStatement();
									data = "call SearchVehicleAllLocation(?,?,?) ";

									ps = conn.prepareStatement(data);
									ps.setInt(1, vehtype);
									ps.setString(2, fromdatetime);
									ps.setString(3, todatetime);

									rs = ps.executeQuery();

									if (rs != null) {
										emptyresult = true;
									}

								} else {
									rs.previous();
								}
							}

							if (rs.next()) {
				%>
				<table class="table" border='1' cellpadding='10' align='center'
					padding-top='6px'>
					<thead>
						<tr>
							<th>Type</th>
							<th>Mileage</th>
							<th>Make</th>
							<th>Price/Hour</th>
							<th>Model No.</th>
							<th>Last Service Date</th>
							<th>Rent Location</th>
							<th>Reserve</th>
						</tr>

					</thead>
					<tbody>
						<%
							rs.first();

										do {
						%>

						<tr>
							<td style="display: none;"><%=rs.getString("regno")%></td>
							<td style="display: none;"><%=rs.getString("PickupDate")%></td>
							<td style="display: none;"><%=rs.getString("dropdate")%></td>
							<td><%=rs.getString("type")%></td>
							<td><%=rs.getString("mileage")%></td>
							<td><%=rs.getString("Make")%></td>
							<td><%=rs.getString("priceperhr")%></td>
							<td><%=rs.getString("modelno")%></td>
							<td><%=rs.getString("lastservicedate")%></td>
							<td><%=rs.getString("name")%></td>


							<td>
								<form name="ReserveForm" action="reserve" method="post">
									<input type="hidden" name="FromDateTime"
										value="<%=rs.getString("PickupDate")%>" /> <input
										type="hidden" name="ToDateTime"
										value="<%=rs.getString("dropdate")%>" /> <input type="hidden"
										name="vehRegNo" value="<%=rs.getString("regno")%>" /> <input
										type="hidden" name="UserID"
										value="<%=session.getAttribute("userName")%>" /> <input
										type="submit" class="btn btn-primary" name="reserve"
										value="Reserve" />
								</form>
							</td>

						</tr>
						<tr></tr>
						<tr></tr>

						<%
							} while (rs != null && rs.next());
						%>


					</tbody>

				</table>
				<%
					} else {
				%>
				<h3 align='center'>No bookings available</h3>
				<%
					}
						}
					}
				%>
			</div>
		</div>
	</div>
	<p id="demo"></p>
</body>
</html>
