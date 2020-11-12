<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload</title>

</head>
<body>
	<h1>Upload</h1>
	<h2>${messages}</h2>
	<form:form action="${pageContext.request.contextPath}/upload" method="POST" modelAttribute="Upload" enctype="multipart/form-data">
		<table>
			<tr>					
				<td>
            		<label>Select a photo to upload</label>
            	</td>
            </tr>
            <tr>
            	<td>
            		<input type="file" name="file" accept="image/*" />
            		
            	</td>
            </tr>
            <tr>					
				<td>
            		<label>Description</label>
            	</td>
            </tr>
            <tr>
            	<td>
            		<textarea id="desc" name="desc" rows="4" cols="50"></textarea>
            	</td>
            </tr>
            <tr>
            	<td>
            		<input type="submit" name="submit" value="Post">
            	</td>
            </tr>
		</table>
	</form:form>
</body>
</html>