<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add an Admin</title>
</head>
<body>
<div>
 <h1 style="padding-left: 4%;">Add a new Admin</h1>
 <a  style="padding-left: 4%;" href="AdminHome.jsp">Back to Admin Dashboard</a>
 

 
 <form action="./AdminSignUp" method="post"> 
 
 
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
 		
 		</tr>
 		
 		
 		<tr>
 		<td style="width: 40%;padding-left: 5%;">
 		 <p> Name:</p>  
        <input type="text" name="name"/> 
 		</td>
 		<td style="width: 40%;padding-left: 5%;">
 		 <p>Email ID:</p>  
        <input type="text" name="email"/> 
        <br/> 
 		</td>
 		</tr>
 		<tr>
 		<td style="width: 40%;padding-left: 5%;">
 		  <p>Phone Number:</p>  
        <input type="text" name="phone"/> 
 		</td>
 		<td 	style="width: 40%;padding-left: 5%;">
 		  <p>Residential address</p>  
        <input type="text" name="address"/> 
		</td>
 		</tr>
 		
 		
 		</table>
 		
 		<br/>
	<br/>
      <input type="submit" value="Register"  style="margin-left:10%;"/>   
 		</form>
 		
</body>
</html>