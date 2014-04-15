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
	<h2>Buildings</h2>
	<jsp:useBean id="map" class="utils.BuildingGetter"/>
	<c:set var="buildings" value="${map.getMap()}"/>
	<c:forEach items="${buildings}" var="entry">
		<p>id:${entry.key} Name: ${entry.value}</p>
	</c:forEach>
</body>
</html>