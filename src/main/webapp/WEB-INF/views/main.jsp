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
	<%-- 	<div align="left">
		<h1>
			<a href="goHome">Social NET</a>
		</h1>
		<table width="100%">
			<tr>
				<td>PAGE OF ${personInfo.login} <br /> LEFT TOP
					
				</td>
				<td>RIGHT TOP My friends: <br>
					<ul>
						<c:forEach var="friend" items="${listOfFriends}"
							varStatus="status">
							<li><a href="viewPerson?id=${friend.id}">${status.index + 1}
									&nbsp; ${friend.id} &nbsp; ${friend.login} &nbsp;
									${friend.password} &nbsp; </a></li>
						</c:forEach>
					</ul>
				</td>
			</tr>
			<tr>
				<td>LEFT BOTTOM</td>
				<td>RIGHT BOTTOM</td>
			</tr>
			<tr></tr>
		</table>
	</div> --%>




	<div id="maincontainer">

		<div id="topsection">
			<div class="innertube">
				<h1>
					<a href="goHome" class="disablehref,a">Social NET</a>
				</h1>
			</div>
		</div>

		<div id="contentwrapper">
			<div id="contentcolumn">
				<div class="innertube">
					<h3>Info about ${personInfo.login}</h3>
					<!-- BLOCK PERSON INFO  -->
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
						<h3>CHAT whith ${personInfo.login}</h3>
						<textarea rows="10" cols="45"></textarea>
					</div>
				</div>
			</div>
		</div>
		
		<!-- PHOTO BLOCK  -->
		<div id="photo">
			<div class="innertube">
				<h3>${personInfo.login} photo</h3>
				<div style="border: 1px solid #cecece;">
				<img src="<c:url value="/resources/images/test-photo.png"/>" alt="Mountain View" style="width:160px;height:160px">
				</div>
			</div>
		</div>
		
		
		<div id="myfriends">
			<!-- FRIENDS BLOCK  -->
			<div class="innertube" style="margin-left: 30px;">
				<h3>My Friends</h3>
				sdsds<br> sdsds<br> sdsds<br> sdsds<br> sdsds<br>
				sdsds<br>
			</div>
		</div>

		<!-- 	<div id="footer">
			<a href="http://www.dynamicdrive.com/style/">Dynamic Drive CSS
				Library</a>
		</div> -->

	</div>







</body>
</html>
