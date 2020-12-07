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
	<form:form action="${pageContext.request.contextPath}/photoContent" method="POST" modelAttribute="photoContent">
		<table>
			
            <tr>					
				<td>
            		<img src = "${image.getFileLocation()}"  style="Width:300px;border: 1px solid #ddd;border-radius: 4px;padding: 5px;">
            	</td>
            </tr>
            <tr>
            	<td>
            		<a style = "color:black;"
									href="${pageContext.request.contextPath}/userContent/?username=${image.getUsername()}">
            		${image.getUsername()}    
            		</a>
            		${image.getDesc()}
            	</td>
            </tr>
            <tr>
            	<td>
            		<p>------------------------------------------</p>
            	</td>
            </tr>
            
			<tr></tr>
			
			<tr>
				<td>
					<textarea id="comment" name="comment" placeholder="Add a comment" rows="4" cols="50"></textarea>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="submit" value="Post">
				</td>
			</tr>
			
			 <tr>
            	<td>
            		<p>------------------------------------------</p>
            	</td>
            </tr>
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