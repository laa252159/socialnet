<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
						Info about ${personInfo.fName}&nbsp;${personInfo.lName}&nbsp; <a
							href="editPerson?id=${personInfo.id}">[edit]</a>
					</h4>
					<table style="margin-left: 20px;">
						<tr>
							<td>First Name:&nbsp;</td>
							<td>${personInfo.fName}</td>
						</tr>
						<tr>
							<td>Last Name:&nbsp;</td>
							<td>${personInfo.lName}</td>
						</tr>
						<tr>
							<td>Data of birth:&nbsp;</td>
							<td>${personInfo.dob}</td>
						</tr>
						<tr>
							<td>Phone:&nbsp;</td>
							<td>${personInfo.phone}</td>
						</tr>
						<tr>
							<td>Address:&nbsp;</td>
							<td>${personInfo.address}</td>
						</tr>
					</table>

					<!-- CHAT BLOCK  -->
					<div>
						<h4>CHAT whith ${personInfo.fName}</h4>
						<textarea rows="10" cols="45"></textarea>
					</div>
				</div>
			</div>
		</div>

		<!-- PHOTO BLOCK  -->
		<div id="photo">
			<div class="innertube">
				<h4>${personInfo.fName}&nbsp;${personInfo.lName}</h4>
				<div style="border: 1px solid #cecece;">
					<img src="<c:url value="/imageDisplay?id=${personInfo.id}"/>"
						alt="Mountain View" style="width: 160px; height: 160px">
				</div>
			</div>
			<!-- FRIENDSHIP STATUS BLOCK  -->
			<a href="friendshipRequest?id=${personInfo.id}"
				class="btn btn-success"
				style="margin-left: 50px; margin-top: 20px; margin-bottom: 20px; color: white;">
				Be friends</a>
			<!-- FRIENDSHIP REQUESTS BLOCK  -->
			<h4>Friendship requested to Me (${listOfFriends.size()})</h4>
			<c:forEach var="friend" items="${listOfFriends}" varStatus="status">
				<a href="viewPerson?id=${friend.id}" style="color: black;"
					class="list-group-item">&nbsp;${friend.fName}&nbsp;${friend.lName}</a>
			</c:forEach>
			<h4>Friendship requested by Me (${listOfFriends.size()})</h4>
			<c:forEach var="friend" items="${listOfFriends}" varStatus="status">
				<a href="viewPerson?id=${friend.id}" style="color: black;"
					class="list-group-item">&nbsp;${friend.fName}&nbsp;${friend.lName}</a>
			</c:forEach>
		</div>




		<!-- USER SEARCH AND FRIENDS BLOCK  -->
		<div id="myfriends">
			<div class="innertube" style="margin-left: 30px;">
				<h4>My Friends (${listOfFriends.size()})</h4>
				<c:forEach var="friend" items="${listOfFriends}" varStatus="status">
					<a href="viewPerson?id=${friend.id}" style="color: black;"
						class="list-group-item">&nbsp;${friend.fName}&nbsp;${friend.lName}</a>
				</c:forEach>
				<br> <br>
				<!-- SEARCH BLOCK BEGIN-->
				<div style="background-color: #F5F5DC; padding: 10px;">
					<h4>Search persons form</h4>
					<form role="form" action="viewPerson" method="post">
						<div class="form-group">
							<label for="fn">First name:</label> <input type="text"
								class="form-control" id="fn" name="fn">
						</div>
						<div class="form-group">
							<label for="ln">Last name:</label> <input type="text"
								class="form-control" id="ln" name="ln">
						</div>
						<input type="hidden" value="${personInfo.id}" name="id" id="id">
						<button type="submit" class="btn btn-default">Start
							search</button>
					</form>
					<h4>Founded ${foundedPersons.size()} persons</h4>
					<c:forEach var="foundedPerson" items="${foundedPersons}"
						varStatus="status">
						<a href="viewPerson?id=${foundedPerson.id}" style="color: black;"
							class="list-group-item">&nbsp;${foundedPerson.fName}&nbsp;${foundedPerson.lName}</a>
					</c:forEach>
				</div>
				<!-- SEARCH BLOCK END-->
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
