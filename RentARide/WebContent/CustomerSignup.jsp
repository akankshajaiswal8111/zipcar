<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>


<div>
 <h1 style="padding-left: 4%;">Customer Signup</h1>
 
 

 
 <form action="./CustomerSignUp" method="post"> 
 
 <h2 style="padding-left: 4%;">Personal Details:</h2>
 		<table  >
 		<tr>
 		<td style="width: 40%;padding-left: 5%;">
 		  <p>UserName:</p>  
        <input type="text" name="userName"/> 
 		</td>
 		<td style="width: 40%;padding-left: 5%;">
 		<p>Password:</p>  
        <input type="password" name="password"/> 
 		</td>
 		<td >
 		 <p> Name:</p>  
        <input type="text" name="name"/> 
 		</td>
 		</tr>
 		
 		
 		<tr>
 		
 		<td style="width: 40%;padding-left: 5%;">
 		 <p>Email ID:</p>  
        <input type="text" name="email"/> 
        <br/> 
 		</td>
 		<td style="width: 40%;padding-left: 5%;">
 		  <p>Phone Number:</p>  
        <input type="text" name="phone"/> 
 		</td>
 		<td 	>
 		  <p>Residential address</p>  
        <input type="text" name="address"/> 
		</td>
 		</tr>
 		
 		
 		</table>
 		<table>
 			<tr>
 			</tr>
 		</table>
<hr>

<h2 style="padding-left: 4%;">License and Payment Details:</h2>
 		<table  >
 		<tr>
 		<td style="width: 40%;padding-left: 5%;">
 		  <p>License Number:</p>  
        <input type="text" name="licenseNum"/> 
 		</td>
 		<td style="width: 40%;padding-left: 5%;">
 		<p>License State:</p>  
        <input type="text" name="licenseState"/> 
 		</td>
 			<td>
 		 <p> Credit Card Type:</p>  
        <input type="text" name="cardType"/> 
 		</td>
 		</tr>
 		
 		
 		<tr>
 	
 		<td style="width: 40%;padding-left: 5%;">
 		 <p> Credit Card Number:</p>  
        <input type="text" name="creditCardNum"/> 
 		</td>
 		<td style="width: 40%;padding-left: 5%;">
 		  <p>Expiry Date</p>  
        <input type="text" name="expiryDate"/> 
 		</td>
 		<td >
 		 <p>CVV</p>  
        <input type="text" name="cvv"/> 
        <br/> 
 		</td>
 		</tr>
 		</table>
 		<table>
 			<tr>
 			</tr>
 		</table>
<br/>
<%
String driverName = "com.mysql.jdbc.Driver";
String hostname=<<AWS RDS URL>>;
String port="3306";
String dbName = "rentaride"; 
String dbUsername = <<DB USERNAME>>; 
String dbPassword = <<DB PASSWORD>>; 
String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + dbUsername + "&password=" + dbPassword;
try {
Class.forName(driverName);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>

 <%
try{ 
connection = DriverManager.getConnection(jdbcUrl);
statement=connection.createStatement();
String sql ="SELECT * FROM fees WHERE FeeType='Registation Fee'";
resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<p style="padding-left:5%;">6 Month Non refundable Registration fees to be paid is <b>$<%=resultSet.getString("Amount")%></b>. Please click Pay and Register to continue.</p>
<input type="hidden" name="regAmnt" value="<%=resultSet.getString("Amount")%>" />
<%
}
}catch (Exception e) {
e.printStackTrace();	
}
%>
<br/>
	
      <input type="submit" value="Pay and Register"  style="margin-left:35%;"/>   
    </form> 
</div>