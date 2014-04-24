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
		<input type="text" name="firstName" id="firstName" required/><br>
		<label>Last Name:</label>
		<input type="text" name="lastName" id="lastName" required/><br>
		<label>Grad Year:</label>
		<input type="text" name="gradYear" id="gradYear" required/><br>
		<label>Major:</label>
		<input type="text" name="major" id="major" required/><br>
		
		<h4>RA or RD?</h4>
		<label>Resident Advisor</label>
		<input type="radio" name="Res" id="RA" value="RA"/><br>
		<label>Resident Director</label>
		<input type="radio" name="Res" id="RD" value="RD"/><br>
		
		<h4>Housing</h4>
		<label>Building:</label>
		<jsp:useBean id="bdao" class="utils.BuildingDAO"/>
		<c:set var="buildings" value="${bdao.getBuildings()}"/>
		<select name="build">
			<option value=0></option>
			<c:forEach items="${buildings}" var="b">
				<option value="${b.buildingID}:${b.name}" >${b.name}</option>
			</c:forEach>
		</select>
		<label>Floor Number:</label>
		<input type="text" name="floornum" id="floornum" required/><br>
		<label>Room Number:</label>
		<input type="text" name="roomnum" id="roomnum" required/><br>
		
		<input type="submit" value="Add"/><br>
	</form>
</body>
</html>
