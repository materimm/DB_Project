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
	<h2>Buildings</h2>
	<form id="houseSearch" action="../HousingSearch" method="GET">
		<jsp:useBean id="bdao" class="utils.BuildingDAO"/>
		<c:set var="buildings" value="${bdao.getBuildings()}"/>
		<select name="build">
			<option value=0></option>
			<c:forEach items="${buildings}" var="b">
				<option value= "${b.buildingID}" >${b.name}</option>
			</c:forEach>
		</select>
		<input type="submit" value="Search"/>
	</form>
	<br><p>${Names}</p>
</body>
</html>
