<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Remove Student</title>
</head>
<body>
	<a href="../index.jsp">Home</a><br> <br>
	
	<form id="RemoveStudent" action="../RemoveStudent" method="POST">
		<jsp:useBean id="sg" class="utils.StudentGetter"/>
		<c:set var="map" value="${sg.getMap()}"/>
		<select name="students">
			<option value=0></option>
			<c:forEach items="${map}" var="entry">
				<option value= "${entry.key}" >${entry.value}</option>
			</c:forEach>
		</select>
		<input type="submit" value="Remove"/><br>
		
		<br><p>${message}</p><br>
	</form>
</body>
</html>