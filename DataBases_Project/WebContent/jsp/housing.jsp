<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Database Project</title>
</head>
<body>
	<a href="../index.jsp">Home</a>
	<h2>Buildings</h2>
	<form id="HousingSearch" action="../HousingSearch" method="GET">
		<jsp:useBean id="bg" class="utils.BuildingGetter"/>
		<c:set var="buildings" value="${bg.getMap()}"/>
		<c:set var="numFloors" value="${bg.getVec()}"/>
		<select name="build">
			<option value=0></option>
			<c:forEach items="${buildings}" var="entry">
				<option value= "${entry.key}" >${entry.value}</option>
			</c:forEach>
		</select>
		<input type="submit" value="Search"/>
		
		<c:forEach items="${firstNames}" var="fn">
			<p>${fn}</p>
		</c:forEach>
	</form>
</body>
</html>