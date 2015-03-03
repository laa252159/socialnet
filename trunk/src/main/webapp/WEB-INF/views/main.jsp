<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Person Manager Home</title>
</head>
<body>
	<div align="left">
		<a href="goHome"><h1>Social NET</h1></a>
		<table width="100%">
			<tr>
				<td>
					<%-- LEFT TOP --%> 
					PAGE OF ${personInfo.login} 
					

				</td>
				<td>
					<%-- RIGHT TOP --%>
					My friends: </br>
					<ul>
						<c:forEach var="friend" items="${listOfFriends}"
							varStatus="status">
							<li><a href="viewPerson?id=${friend.id}">${status.index + 1}
									&nbsp; ${friend.id} &nbsp; ${friend.login} &nbsp;
									${friend.password} &nbsp; </a>
						    </li>
						</c:forEach>
					</ul>
				</td>
			</tr>
			<tr>
				<td>
					<%-- LEFT BOTTOM --%>

				</td>
				<td>
					<%-- RIGHT BOTTOM --%>

				</td>
			</tr>
			<tr></tr>
		</table>
	</div>
</body>
</html>
