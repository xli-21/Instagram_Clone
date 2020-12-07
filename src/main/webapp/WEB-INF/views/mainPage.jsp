<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
				
				<td><input type="button"  onclick="location.href='search'" value="Search"></td>
				<td><input type="button"  onclick="location.href='editPerson'" value="Edit personal info" ></td>
				<td><input type="button"  onclick="location.href='photoLibrary'" value="Photo Library" ></td>
			</tr>
			<c:forEach var="image" items="${showList}">
				<tr>
					<td>
						<div style="flex-direction: column; padding-top: 30px;">
							<article>
								<header>
								
								<a style = "text-decoration:none; color:black; font-size:20px; "
									href="${pageContext.request.contextPath}/userContent/?username=${image.getUsername()}">
									<b>${image.getUsername()}</b>
								</a>
								</header>
								<a href="${pageContext.request.contextPath}/photoContent/?id=${image.id}">
									<img src = "${image.getFileLocation()}"  style="max-height:400px;max-width:300px;border: 1px solid #ddd;border-radius: 4px;padding: 5px;">
								</a>
							</article>
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>
	</form:form>
</body>
</html>