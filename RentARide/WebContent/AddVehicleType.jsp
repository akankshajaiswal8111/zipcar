<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Vehicle Type</title>
</head>
<body>
<div style="position:absolute;top:20%;left:20%;margin-top:-50px;margin-left:-50px;">
 <h1>Add a new Vehicle Type</h1>
 <form action="./AddVehicleType" method="post"> 
        <p>Vehicle Type:</p>  
        <input type="text" name="name"/> 
        <br/> 
        <p>Description:</p>  
        <input type="text" name="description"/> 
        <p>Price/Hr:</p>  
        <input type="text" name="pricePerHr"/> 
        <br/><br/><br/> 
        <input type="submit"/> 
    </form> 
    <br/>
   <a href="VehicleTypes.jsp">List All Vehicle Types</a>
</div>
</body>
</html>