<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ page import="com.rentaride.RentLocationDAO"%>
<%@ page import="com.rentaride.RentLocation"%>
<!Doctype HTML>
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
	background-color: #EEEEEE;
	color: #333333;
	padding: 4px 8px 5px 9px;
	border-top: 1px solid #CCCCCC;
	border-right: 1px solid #333333;
	border-bottom: 1px solid #333333;
	border-left: 1px solid #CCCCCC;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Vehicle</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
function checkCapacity() {
	var location = document.forms["frmAddVehicle"]["Location"].value;
	$.get('LocationController', {action: null, loc: location}, function(responseText){
		console.log(responseText);
		if (responseText == "false") {
			alert("Location is full. Cannot enter vehicle");
			document.getElementById('submitbutton').disabled = true;
			
		}
		else {
			document.getElementById('submitbutton').disabled = false;
		}
		return true;
	});
	
}
</script>
</head>
<body>
<form method="POST" action='VehicleController' name="frmAddVehicle" ><input
type="hidden" name="action" value="insert" />
<p><b>Add New Vehicle</b></p>
<p><a href="AdminHome.jsp">Back to Admin Home</a></p>

<table>
<tr>
<td>RegNo</td>
<td><input type="text" name="RegNo" required /></td>
</tr>
<tr>
<td>VehicleType</td>
<td><select name = "VehicleType" required><option value = 0 >Select Vehicle Type</option>
<% 
try {
	String Query = "select VehicleTypeId, Type from vehicletype";
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	 String hostname=<<AWS RDS URL>>;
	 String port="3306";
     String dbName = "rentaride"; 
     String dbUsername = <<DB Username>>; 
     String dbPassword = <<DB Password>>; 
     String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + dbUsername + "&password=" + dbPassword;
     Connection conn = DriverManager.getConnection(jdbcUrl); 
	Statement stm = conn.createStatement();
	ResultSet rs = stm.executeQuery(Query);
	while(rs.next()) {
	%>
	<option value = "<%=rs.getInt("VehicleTypeId")%>"><%=rs.getString("Type")%> </option>
	<% 
	}
		
}
catch(Exception e) {
e.printStackTrace();
}%>
</select></td>
</tr>
<tr>
<%
	//RentLocation rentLocation = new RentLocation();
RentLocationDAO dao = new RentLocationDAO();
List<com.rentaride.RentLocation> rentLocationList = dao.listAllRentLocations();
HashMap<Integer, Integer> map = new HashMap<>();

%>
<td>Location</td>
<td><select name = "Location" action='VehicleController' onChange ="return checkCapacity()" required><option value = 0>Select Rental Location</option>
<%
for(RentLocation loc : rentLocationList ) { 
	map.put(loc.getId(), loc.getAvailableCapacity());
	%>
	<option value = "<%=loc.getId()%>"><%=loc.getName()%></option>

	
<% }
%>
</select></td>
</tr>
<tr>
<td>Make</td>
<td><input type="text" name="Make" required /></td>
</tr>
<tr>
<td>Model</td>
<td><input type="number" name="ModelNo" required /></td>
</tr>
<tr>
<td>Mileage</td>
<td><input type="number" min="1" step="0.01" name="Mileage" required /></td>
</tr>
<tr>
<td>LastServiceDate</td>
<td><input type="date" name="LastServiceDate" required /></td>
</tr>
<tr>
<td>Year</td>
<td><input type="text" min="1" step="1" name="Year" required /></td>
</tr>
<tr>
<td>Quality</td>
<td><input type="text" name="Quality" required /></td>
</tr>
<tr>
<td></td>
<td><input id="submitbutton" type="submit" value="Submit"  /></td>
</tr>
</table>
</form>
<p><a href="VehicleController?action=listvehicles">List All Vehicles</a></p>
</body>
</html>
