<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.rentaride.RentLocationDAO"%>
<%@ page import="com.rentaride.RentLocation"%>
<%@ page import="com.rentaride.VehicleDAO"%>
<%@ page import="com.rentaride.VehicleMgr"%>
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
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	function checkCapacity() {
		var location = document.forms["frmEditVehicle"]["Location"].value;
		$.get('LocationController', {
			action : null,
			loc : location
		}, function(responseText) {
			console.log(responseText);
			if (responseText == "false") {
				alert("Location is full. Cannot enter vehicle");
				document.getElementById('submitbutton').disabled = true;

			} else {
				document.getElementById('submitbutton').disabled = false;
			}
			return true;
		});

	}
</script>
</head>
<body>
	<%
		VehicleMgr vehicle = new VehicleMgr();
		VehicleDAO dao = new VehicleDAO();
	%>
	<form method="POST" action='VehicleController' name="frmEditVehicle">
		<input type="hidden" name="action" value="edit" />
		<%
			String regno = request.getParameter("RegNo");
			if (regno != null) {
				vehicle = dao.getVehicleByRegNo(regno);
		%>
		<p>
			<b>Edit Vehicle</b>
		</p>
		<p>
			<a href="AdminHome.jsp">Back to Admin Home</a>
		</p>
		<table>
			<tr>
				<td>RegNo</td>
				<td><input type="text" name="RegNo" readonly="readonly"
					value="<%=vehicle.getRegNo()%>"></td>
			</tr>
		
		
		
		<tr>
	<%
				String driverName = "com.mysql.jdbc.Driver";
				String selectedVehicleType="";
				String selectedVehicleTypeId="";
				String hostname=<<AWS RDS URL>>;
				 String port="3306";
			     String dbName = "rentaride"; 
			     String dbUsername = <<DB USERNAME>>; 
			     String dbPassword = <<DB PASSWORD>>; 
			     String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + dbUsername + "&password=" + dbPassword;
				 Map<String, String> vehicleTypeMap = new HashMap<>();
				try {
					Class.forName(driverName);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				Connection connection = null;
				Statement statement = null;
				ResultSet resultSet = null;
			%>
			</tr>
				<%
				try {
					connection = DriverManager.getConnection(jdbcUrl);
					statement = connection.createStatement();
					String sql = "SELECT * FROM vehicletype";
					resultSet = statement.executeQuery(sql);
					while (resultSet.next()) {
						if(resultSet.getString("VehicleTypeId").equals(vehicle.getType())){
							 selectedVehicleType = resultSet.getString("Type");
							 selectedVehicleTypeId = resultSet.getString("VehicleTypeId");
						}
						if(!selectedVehicleTypeId.equals(resultSet.getString("VehicleTypeId")))
						vehicleTypeMap.put(resultSet.getString("VehicleTypeId"),resultSet.getString("Type"));
					}
					
			%>
			
	
	<td>VehicleType</td><%=vehicle.getType()%>
				<td><select name="VehicleType" required><option
							value=<%=vehicle.getType()%>><%=selectedVehicleType%></option>
				<%
				 for (Map.Entry<String, String> entry : vehicleTypeMap.entrySet()) {
								
						%>
						<option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
						<%
							}
						%>

				</select></td>
					<%
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			%>
		
	<tr>
				<%
					//RentLocation rentLocation = new RentLocation();
						RentLocationDAO dao1 = new RentLocationDAO();
						List<com.rentaride.RentLocation> rentLocationList = dao1.listAllRentLocations();
						String locationName = null;
						for (RentLocation loc : rentLocationList) {
							if (loc.getId() == (Integer.parseInt(vehicle.getLocation()))) {
								locationName = loc.getName();
							}
						}
				%>
				<td>Location</td>
				<td><select name="Location" onChange="return checkCapacity()"
					required><option value=<%=vehicle.getLocation()%>><%=locationName%></option>
						<%
							for (RentLocation loc : rentLocationList) {
									if (loc.getId() == (Integer.parseInt(vehicle.getLocation()))) {
										continue;
									}
						%>
						<option value="<%=loc.getId()%>"><%=loc.getName()%></option>
						<%
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td>Make</td>
				<td><input type="text" name="Make" required
					value="<%=vehicle.getMake()%>" /></td>
			</tr>
			<tr>
				<td>ModelNo</td>
				<td><input type="number" min="1" step="1" name="ModelNo"
					required value="<%=vehicle.getModelNo()%>" /></td>
			</tr>
			<tr>
				<td>Mileage</td>
				<td><input type="number" min="0" step="0.01" name="Mileage"
					required value="<%=vehicle.getMileage()%>" /></td>
			</tr>
			<tr>
				<td>LastServiceDate</td>
				<td><input type="date" name="LastServiceDate" required
					value="<%=vehicle.getLastServiceDate()%>" /></td>
			</tr>
			<tr>
				<td>Year</td>
				<td><input type="text" min="1" step="1" name="Year" required
					value="<%=vehicle.getYear()%>" /></td>
			</tr>
			<tr>
				<td>Quality</td>
				<td><input type="text" name="Quality" required
					value="<%=vehicle.getQuality()%>" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input id="submitbutton" type="submit" value="Update" /></td>
			</tr>
		</table>
		<p>
			<a href="VehicleController?action=listvehicles">List All Vehicles</a>
		</p>
		<%
} else
out.println("Registration Number Not Found");
%>
	</form>
</body>
</html>