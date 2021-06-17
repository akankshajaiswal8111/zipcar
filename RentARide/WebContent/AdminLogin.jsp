<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Login</title>
</head>
<body>

<div style="position:absolute;top:20%;left:20%;margin-top:-50px;margin-left:-50px;">
 <h1>Admin Login</h1>
 <form action="./AdminLogin" method="get"> 
        <p>UserName:</p>  
        <input type="text" name="userName"/> 
        <br/> 
        <p>Password:</p>  
        <input type="password" name="password"/> 
        <br/><br/><br/> 
        <input type="submit"/> 
    </form> 
</div>
</html>