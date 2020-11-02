<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>

</head>
<body>
	<h1>Profile</h1>
	<h2>${messages}</h2>
	<form:form method="POST" modelAttribute="profile">
		<table>
			<tr>
				<td>Hi,</td>
			</tr>
			<tr>
				<td>${name}</td>
			</tr>
			
			<tr>			
				<td><input type="submit" name="action" value="edit" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>