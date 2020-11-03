<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>editPerson</title>
</head>
<body>
	<h1>editPerson</h1>
	<form:form method="POST" modelAttribute="editPerson">
		<table>
			<tr>
				<td><form:input type="hidden" path="username" id="username" />
					<form:errors path="username" style="color:red" /></td>
			</tr>
			<tr>
				<td><label>password</label></td>
				<td><form:input type="text" path="password" id="password" /> <form:errors
						path="password" style="color:red" /></td>
			</tr>
			<tr>
				<td><label>firstname</label></td>
				<td><form:input type="text" path="firstname" id="firstname" /> <form:errors
						path="firstname" style="color:red" /></td>
			</tr>
			<tr>
				<td><label>lastname</label></td>
				<td><form:input type="text" path="lastname" id="lastname"  /> <form:errors 
						path="lastname" style="color:red" /></td>
			</tr>
			<tr>
				<td><label>email</label></td>
				<td><form:input type="email" path="email" id="email"  /> <form:errors
						path="email" style="color:red" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit"></td>
			</tr>
		</table>
	</form:form>
</body>
</html>