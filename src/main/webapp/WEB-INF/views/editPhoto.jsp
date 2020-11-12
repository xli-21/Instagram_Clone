<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>editPhoto</title>
</head>
<body>
	<h1>editPhoto</h1>
	<form:form action="${pageContext.request.contextPath}/editPhoto" method="POST" modelAttribute="editPhoto">
		<table>
			
            <tr>					
				<td>
            		<img src = "${image.getFileLocation()}"  style="height:200px;border: 1px solid #ddd;border-radius: 4px;padding: 5px;">
            	</td>
            </tr>
            <tr>
            	<td>
            		<textarea id="desc" name="desc" id="desc" rows="4" cols="50">${image.getDesc()}</textarea>
            	</td>
            </tr>
            <tr>
            	<td>
            		<input type="submit" name="finish" value="Finish Editing">
            	</td>
            </tr>
            <tr>
            	<td>
            		<input type="submit" name="delete" value="DELETE">
            	</td>
            </tr>
			<tr></tr>
			<c:forEach var="comment" items="${comments}">
				<tr>
					<td>
						<div><b>${comment.getUsername()}:</b></div>
						<div>${comment.getText()}</div>
					</td>
				</tr>
				<div></div>
			</c:forEach>
		</table>
	</form:form>
</body>
</html>