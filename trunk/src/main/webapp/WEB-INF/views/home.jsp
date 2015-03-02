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
	        <h1>List of my friends</h1>
	        <%-- <h3><a href="newPerson">New Person</a></h3> --%>
	        <table border="1">
	        	<th>No</th>
	        	<th>ID</th>
	        	<th>LOGIN</th>
	        	<th>PASSWORD</th>
	        	
				<c:forEach var="friend" items="${listOfFriends}" varStatus="status">
	        	<tr>
	        		<td>${status.index + 1}</td>
					<td>${friend.id}</td>
					<td>${friend.login}</td>
					<td>${friend.password}</td>
					<td>
						<a href="viewPerson?id=${friend.id}">View</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="deletePersonFromFriends?id=${friend.id}">Delete from friends</a>
					</td>
							
	        	</tr>
				</c:forEach>	        	
			</table>
    	</div>
    </body>
</html>
