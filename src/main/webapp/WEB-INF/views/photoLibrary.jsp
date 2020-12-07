<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Photo Library</title>

</head>
<body>
	<h1>Photo Library</h1>
	<h2>${messages}</h2>

	<form:form method="POST" modelAttribute="photoLibrary">
		<table>
		<tr>					
				<td>
            		<input type="submit" name="back" value="Back to Main Page">
            	</td>
            </tr>
			<tr>					
				<td>
            		<input type="button"  onclick="location.href='upload'" value="Upload an image" >
            	</td>
            </tr>
           
				
					
			<c:forEach var="image" items="${images}">
				<tr>
					<td>
						<a href="${pageContext.request.contextPath}/editPhoto/?id=${image.id}">
							<img src = "${image.getFileLocation()}"  style="max-height:500px;max-width:300px;border: 1px solid #ddd;border-radius: 4px;padding: 5px;">
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</form:form>
</body>
</html>