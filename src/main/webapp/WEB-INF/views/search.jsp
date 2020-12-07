<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>

</head>
<body>
	<h1>Search</h1>
	
	<form:form method="POST" modelAttribute="mainPage">
		<table>
			<tr>
						
				<td><input type="text" name="search" id="search" /></td>
				<td><input type="submit" value="Search" id="Search"></td>
				
			</tr>
			<c:forEach var="user" items="${userList}">
				<tr>
					<td>
						<div style="flex-direction: column; padding-top: 40px;">
							
								<a style = "color:black; font-size:30px; "
									href="${pageContext.request.contextPath}/userContent/?username=${user.getUsername()}">
									<b>${user.getUsername()}</b>
								</a>
					
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>
	</form:form>
</body>
</html>