<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <meta http-equiv="refresh" content="5; URL=viewPerson?id=${currrentPersonId}"> --%>
<title>Person Manager Home</title>
<link
	href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/my.css" />" rel="stylesheet">
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-2.1.3.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
</head>
<body background="<c:url value='/resources/images/grass.jpg'/>"
	style="background-size: 90%;">
	<div id="maincontainer">

		<!-- TOP BLOCK  -->
		<div id="topsection">
			<div class="innertube">
				<h1>
					<a href="goHome" class="disablehref a">Social NET</a>
				</h1>
			</div>
		</div>

		<!-- PERSON INFO AND CHAT BLOCK  -->
		<div id="contentwrapper">
			<div id="contentcolumn">
				<div class="innertube">
					<h4>
						Info about ${personInfo.fName}&nbsp;${personInfo.lName}&nbsp;
						<c:if test="${isMyPage}">
							<a href="editPerson?id=${personInfo.id}">[edit]</a>
						</c:if>
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
						<c:if test="${isMyPage || isFriend}">
							<tr>
								<td>Date of birth:&nbsp;</td>
								<td><fmt:formatDate pattern="dd/MM/yyyy"
										value="${personInfo.dob}" /></td>
							</tr>
							<tr>
								<td>Phone:&nbsp;</td>
								<td>${personInfo.phone}</td>
							</tr>
							<tr>
								<td>Address:&nbsp;</td>
								<td>${personInfo.address}</td>
							</tr>
						</c:if>
					</table>

					<!-- LIST OF UNREADED MESSAGES  -->
					<div class="innertube" style="padding-top: 30px; width: 150px;">
						<c:if test="${not empty unreadLinks && isMyPage}">
							<h5>YOU HAVE NEW MESSAGES FROM:</h5>
							<c:forEach var="link" items="${unreadLinks}" varStatus="status">
								<a href="viewPerson?id=${link.senderId}"
									style="color: black; width: 150px;" class="btn btn-success"><span
									class="badge">${link.senderName}</span></a>
								<br>&nbsp;
							</c:forEach>
							<hr>
						</c:if>
					</div>
					<!-- Collapse SECTIONS  -->
					<div class="panel-group" id="accordion"
						style="height: 220px; width: 433px;">
						<!-- CHAT SECTION  -->
						<c:if test="${!isMyPage && isFriend}">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion" href="#collapse1">CHAT whith ${personInfo.fName}</a>
									</h4>
								</div>
								<div id="collapse1" class="panel-collapse collapse in">
									<div class="panel-body">
										<div>
											<div id="chat"
												style="height: 200px; width: 400px; border: 1px solid rgb(169, 169, 169); background: white; border-radius: 5px 5px 5px 5px; overflow:auto; padding: 10px;">
											</div>
											<div id="senderId" style="display: none;">${currrentPersonId}</div>
											<div id="receiverId" style="display: none;">${personInfo.id}</div>
											<div id="messageval" style="display: none;">BLA BLA BLA</div>
											<script type="text/javascript">
								$("#chat").load(
										"getAllMessages?senderId="
												+ $('#senderId').text()
												+ "&receiverId="
												+ $('#receiverId').text());
								setInterval(function() {
									$("#chat").load(
											"getAllMessages?senderId="
													+ $('#senderId').text()
													+ "&receiverId="
													+ $('#receiverId').text());
									$("textarea").scrollTop(99999);
									$("textarea").scrollTop(
											$("textarea").scrollTop() * 12);
								}, 3000);
							</script>
											<textarea id="iputText"
												style="height: 50px; width: 400px; margin-top: 10px;"></textarea>
											<div class="btn btn-default"
												style="margin-top: 20px; margin-left: 320px;"
												onclick="send()">SEND</div>
											<script type="text/javascript">
								function send() {
									$.post('addMessage', {
										value : $("#iputText").val(),
										senderId : $('#senderId').text(),
										receiverId : $('#receiverId').text()
									});
									$("#iputText").val('');
								};
							</script>
										</div>
									</div>
								</div>
							</div>
						</c:if>
						<!-- GALLERY SECTION  -->
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion" href="#collapse2">Photo gallery of ${personInfo.fName}</a>
								</h4>
							</div>
							<div id="collapse2" <c:if test="${!isMyPage}">class="panel-collapse collapse"</c:if> <c:if test="${isMyPage}">class="panel-collapse collapse in"</c:if>>
								<div class="panel-body">
									<div style="width: 400px;">
										<c:forEach var="album" items="${personsAlbums}"
											varStatus="status">
											<a href="viewAlbum?id=${album.id}" 
												class="list-group-item">	
													<img src="<c:url value="/albumImageDisplay?id=${album.id}"/>" 
													     alt="Albums picture" style="height: 100px"
													     class="img-thumbnail">
													     ${album.name}
												
											</a>
										</c:forEach>
										<c:if test="${personsAlbums.isEmpty()}">${personInfo.fName} didn't add any album</c:if>
									</div>
								</div>
							</div>
						</div>
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
						alt="Persons photo" style="width: 160px; height: 160px"
						class="img-thumbnail">
				</div>
			</div>

			<!-- FRIENDSHIP STATUS BLOCK  -->
			<c:if test="${!isMyPage && !bindingExists && !isFriend}">
				<a href="friendshipRequest?id=${personInfo.id}"
					class="btn btn-success"
					style="margin-left: 50px; margin-top: 20px; margin-bottom: 20px; color: white;">
					Be friends</a>
			</c:if>
			<c:if test="${!isMyPage && bindingExists && isFriend}">
				<a href="deleteFriendship?id=${personInfo.id}"
					class="btn btn-danger"
					style="margin-left: 20px; margin-top: 20px; margin-bottom: 20px; color: white;">
					Delete from friends</a>
			</c:if>
			<c:if test="${!isMyPage && bindingExists && needApprove}">
				<a href="approveFriendship?id=${personInfo.id}"
					class="btn btn-warning"
					style="margin-left: 20px; margin-top: 20px; margin-bottom: 20px; color: white;">
					Approve friendship</a>
				<a href="deleteFriendship?id=${personInfo.id}"
					class="btn btn-danger"
					style="margin-left: 20px; margin-top: 20px; margin-bottom: 20px; color: white;">
					Cancel</a>
			</c:if>
			<hr>
			<c:if test="${isMyPage}">
				<!-- FRIENDSHIP REQUESTS BLOCK  -->
				<h5>Need my approve (${listOfWaiters.size()})</h5>
				<c:forEach var="friend" items="${listOfWaiters}" varStatus="status">
					<a href="viewPerson?id=${friend.id}" style="color: black;"
						class="list-group-item">&nbsp;${friend.fName}&nbsp;${friend.lName}</a>
				</c:forEach>
				<hr>
				<h5>I am waiting for approve (${listOfApprovers.size()})</h5>
				<c:forEach var="friend" items="${listOfApprovers}"
					varStatus="status">
					<a href="viewPerson?id=${friend.id}" style="color: black;"
						class="list-group-item">&nbsp;${friend.fName}&nbsp;${friend.lName}</a>
				</c:forEach>
			</c:if>
		</div>



		<!-- USER SEARCH AND FRIENDS BLOCK  -->
		<div id="myfriends">
			<div class="innertube" style="margin-left: 30px;">
				<h4>My Friends (${listOfFriends.size()})</h4>
				<div style="height: 168px; overflow:auto;">
					<c:forEach var="friend" items="${listOfFriends}" varStatus="status">
						<a href="viewPerson?id=${friend.id}" style="color: black;"
							class="list-group-item">&nbsp;${friend.fName}&nbsp;${friend.lName}</a>
					</c:forEach>
				</div>
				<br> <br>
				<!-- SEARCH BLOCK BEGIN-->
				<div style="background-color: #F5F5DC; padding: 10px; border: solid 1px green; border-radius: 5px; padding:10px;">
					<h4>Search persons form</h4>
					<form action="viewPerson" method="post">
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
					<div style="height: 168px; overflow:auto;">
						<c:forEach var="foundedPerson" items="${foundedPersons}"
							varStatus="status">
							<a href="viewPerson?id=${foundedPerson.id}" style="color: black;"
								class="list-group-item">&nbsp;${foundedPerson.fName}&nbsp;${foundedPerson.lName}</a>
						</c:forEach>
					</div>
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
	<div>
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h5>
				Welcome : ${pageContext.request.userPrincipal.name} | <a
					href="<c:url value="/j_spring_security_logout" />">Logout</a>
			</h5>
		</c:if>
	</div>
</body>
</html>
