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
<title>Fees and Charges</title>
</head>

<body>
	

	<div
		style="position: absolute; top: 20%; left: 20%; margin-top: -50px; margin-left: -50px;">
		<h1>Update Fees and Charges</h1>
		<a href="AdminHome.jsp">Back to Admin Dashboard</a>
		<form action="" method="post">
	
			<p>Registration Fees :</p>
			<input type="text" name="regFee"
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
		PreparedStatement st = null;
		Class.forName(driverName).newInstance();
		connection = DriverManager.getConnection(jdbcUrl);
	%>
			<%
				statement = connection.createStatement();
				String r = "Registation Fee";
				String query = "select * from fees where feeType ='" + r + "'";
				resultSet = statement.executeQuery(query);
				while (resultSet.next()) {
			%>
			
				value='<%=resultSet.getString("Amount")%>' /> <br />

			<%
				}
			%>
			


			<p>Late Return Fees (per Hr) :</p>
			<input type="text" name="lateFee"
			<%
				String late = "Late Return Fee";
				ResultSet resultSet1 = null;
				String query1 = "select * from fees where feeType ='" + late + "'";
				resultSet1 = statement.executeQuery(query1);
				while (resultSet1.next()) {
			%>
				value='<%=resultSet1.getString("Amount")%>' /> <br />


			<%
				}
			%>
			<br /> <br /> <input type="submit" />
		</form>
	</div>
</body>
</html>
<%String regFee =  request.getParameter("regFee");
	String lateFee =  request.getParameter("lateFee");
	if(regFee!= null){
		String ret = "Registation Fee";
		String q1 =  "update fees set amount=? where feeType='"+ret+"'";
		st = connection.prepareStatement(q1);
		st.setInt(1, Integer.valueOf(regFee));
		st.executeUpdate();
	}
	if(lateFee!= null){
		String lt = "Late Return Fee";
		String q2 =  "update fees set amount=? where feeType='"+lt+"'";
		st = connection.prepareStatement(q2);
		st.setInt(1, Integer.valueOf(lateFee));
		st.executeUpdate();
		response.sendRedirect("AdminHome.jsp");
		
	}
%>