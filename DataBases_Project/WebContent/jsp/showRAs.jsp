<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../css/bootstrap.css" rel="stylesheet"/>

<title>RAs</title>
</head>
<body>
	<a href="../index.jsp">Home</a><br> <br>
	<jsp:useBean id="rag" class="utils.RADAO"/>
	<c:set var="ras" value="${rag.getRAs()}"/>
	<div class="table-reponsive">
	<table class="table table-bordered table-striped">

		<tr>
			<th>Building</th>
			<th>RA Name</th>
			<th>Floor Number</th>
			<th>Room Number</th>
		</tr>
		<c:forEach items="${ras}" var="ra">
			<c:set var="st" value="${ra.getStudent()}"/>
			<tr>
				<td>${ra.buildingName}</td>
				<td>${st.firstName} ${st.lastName}</td>
				<td>${ra.floorNum}</td>
				<td>${ra.roomNum}</td>
			</tr>
		</c:forEach>
	</table>
	</div>


</body>
</html>
