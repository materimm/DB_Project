<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../css/bootstrap.css" rel="stylesheet"/>

<title>Database Project</title>
</head>
<body>
	<a href="../index.jsp">Home</a>
	
	<h2>Add a student</h2>
	<form id="StudentAdd" action="../StudentAdd" method="POST">
	
		<h4>Student Info:</h4>
		<label>First Name:</label>
		<input type="text" name="firstName" id="firstName"/><br>
		<label>Last Name:</label>
		<input type="text" name="lastName" id="lastName"/><br>
		<label>Grad Year:</label>
		<input type="text" name="gradYear" id="gradYear"/><br>
		<label>Major:</label>
		<input type="text" name="major" id="major"/><br>
		
		<h4>RA or RD?</h4>
		<label>Resident Advisor</label>
		<input type="radio" name="Res" id="RA" value="RA"/><br>
		<label>Resident Director</label>
		<input type="radio" name="Res" id="RD" value="RD"/><br>
		
		<h4>Housing</h4>
		<label>Building:</label>
			<jsp:useBean id="bg" class="utils.BuildingGetter"/>
			<c:set var="buildings" value="${bg.getMap()}"/>
			<select name="build">
				<option value=0></option>
				<c:forEach items="${buildings}" var="entry">
					<option value= "${entry.key}" >${entry.value}</option>
				</c:forEach>
			</select><br> 
		<label>Floor Number:</label>
		<input type="text" name="floornum" id="floornum"/><br>
		<label>Room Number:</label>
		<input type="text" name="roomnum" id="roomnum"/><br>
		
		<input type="submit" value="Add"/><br>
		
		<br><p>${StudentMessage}</p><br>
		<br><p>${ResMessage}</p><br>
		<br><p>${HousingMessage}</p><br>
	</form>
</body>
</html>
