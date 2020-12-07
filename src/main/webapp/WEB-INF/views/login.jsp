<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>login</title>

</head>
<body>
	<h1>login</h1>
	<h2>${messages}</h2>
	<form:form method="POST" modelAttribute="login">
		<table>
			<tr>
				<td><label>username</label></td>
				<td><form:input type="text" path="username" id="username" />
					<form:errors path="username" style="color:red" /></td>
			</tr>
			<tr>
				<td><label>password</label></td>
				<td><form:input type="text" path="password" id="password" /> <form:errors
						path="password" style="color:red" /></td>
			<tr>
				<td><input type="submit" value="Login"></td>
				<td><input type="button"  onclick="location.href='register'" value="Register" ></td>
			</tr>
		</table>
	</form:form>
</body>
</html>