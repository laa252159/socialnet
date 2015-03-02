<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Person Manager Home</title>
    </head>
    <body>
    	<div align="center">
	        <h1>Person List</h1>
	        <h3><a href="newPerson">New Person</a></h3>
	        <table border="1">
	        	<th>No</th>
	        	<th>ID</th>
	        	<th>LOGIN</th>
	        	<th>PASSWORD</th>
	        	
				<c:forEach var="person" items="${listPerson}" varStatus="status">
	        	<tr>
	        		<td>${status.index + 1}</td>
					<td>${person.id}</td>
					<td>${person.login}</td>
					<td>${person.password}</td>
					<td>
						<a href="editPerson?id=${person.id}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="deletePerson?id=${person.id}">Delete</a>
					</td>
							
	        	</tr>
				</c:forEach>	        	
			</table>
    	</div>
    </body>
</html>
