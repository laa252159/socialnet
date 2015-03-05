<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Person Manager Home</title>
<link
	href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/my.css" />" rel="stylesheet">
</head>
<body background="<c:url value='/resources/images/grass.jpg'/>"
	style="background-size: 90%;">
	<div id="maincontainer">

		<!-- TOP BLOCK  -->
		<div id="topsection">
			<div class="innertube">
				<h1>
					<a href="goHome" class="disablehref,a">Social NET</a>
				</h1>
			</div>
		</div>

		<!-- PERSON INFO AND CHAT BLOCK  -->
		<div id="contentwrapper">
			<div id="contentcolumn">
				<div class="innertube">
					<h4>
						Info about ${personInfo.login} &nbsp; <a
							href="editPerson?id=${personInfo.id}">[edit]</a>
					</h4>
					<table>
						<tr>
							<td>First Name</td>
							<td>${personInfo.fName}</td>
						</tr>
						<tr>
							<td>Last Name</td>
							<td>${personInfo.fName}</td>
						</tr>
						<tr>
							<td>Data of birth</td>
							<td>${personInfo.dob}</td>
						</tr>
						<tr>
							<td>Address</td>
							<td>${personInfo.address}</td>
						</tr>
					</table>

					<!-- CHAT BLOCK  -->
					<div>
						<h4>CHAT whith ${personInfo.login}</h4>
						<textarea rows="10" cols="45"></textarea>
					</div>
				</div>
			</div>
		</div>

		<!-- PHOTO BLOCK  -->
		<div id="photo">
			<div class="innertube">
				<h4>${personInfo.login}&nbsp;photo</h4>
				<div style="border: 1px solid #cecece;">
					<img src="<c:url value="/resources/images/test-photo.png"/>"
						alt="Mountain View" style="width: 160px; height: 160px">
				</div>
			</div>
		</div>

		<!-- FRIENDS BLOCK  -->
		<div id="myfriends">
			<div class="innertube" style="margin-left: 30px;">
				<h4>My Friends (${listOfFriends.size()})</h4>
				<c:forEach var="friend" items="${listOfFriends}" varStatus="status">
					<p>
						<a href="viewPerson?id=${friend.id}" style="color: black;">${friend.login}
							&nbsp; ${friend.password} &nbsp; </a>
					</p>
				</c:forEach>
			</div>
		</div>

		<!-- FOOTER BLOCK  -->
		<!-- 	<div id="footer">
			<a href="http://www.dynamicdrive.com/style/">Dynamic Drive CSS
				Library</a>
		</div> -->

	</div>
</body>
</html>
