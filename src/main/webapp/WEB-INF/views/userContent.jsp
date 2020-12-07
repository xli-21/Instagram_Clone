<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${hostname}</title>
</head>
<body>
	<h1>${hostname}</h1>
	<form:form action="${pageContext.request.contextPath}/userContent"
		method="POST" modelAttribute="userContent">
		<table>


			<tr>
				<td><input type="submit" name="back" value="Back to Main Page">
				</td>
			</tr>



			<tr>
				<td><input type="submit" name="follow" value="${follow}">
				</td>
			</tr>
			<c:forEach var="image" items="${images}">
				<tr>

					<td>
						<div style="flex-direction: column; padding-top: 30px;">
							<a
								href="${pageContext.request.contextPath}/photoContent/?id=${image.id}">
								<img src="${image.getFileLocation()}"
								style="width: 400px; border: 1px solid #ddd; border-radius: 4px; padding: 5px;">
							</a>
						</div>
					</td>

				</tr>
			</c:forEach>

		</table>
	</form:form>
</body>
</html>