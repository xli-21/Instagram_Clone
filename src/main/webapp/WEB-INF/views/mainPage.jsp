<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MainPage</title>

</head>
<body>
	<h1>MainPage</h1>
	<h2>${messages}</h2>
	<form:form method="POST" modelAttribute="mainPage">
		<table>
			<tr>
				<td>Hi, ${name}</td>
					
				<td><input type="button"  onclick="location.href='editPerson'" value="Edit personal info" ></td>
				<td><input type="button"  onclick="location.href='photoLibrary'" value="Photo Library" ></td>
			</tr>
		</table>
	</form:form>
</body>
</html>