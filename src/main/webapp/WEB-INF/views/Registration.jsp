<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration new Account</title>
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

		<!-- PERSON LOGIN AND PASSWORD BLOCK  -->
		<div id="contentwrapper">
			<div id="contentcolumn">
				<div class="innertube">
					<br> <br> <br>
					<h4>Enter login and password of new user</h4>
					<br>
					<form:form action="savePerson" method="post"
						modelAttribute="person">
						<div class="form-group">
							<label for="login">Login:</label>
							<form:input path="login" class="form-control" />
						</div>
						<div class="form-group">
							<label for="password">Password:</label>
							<form:input path="password" class="form-control" />
						</div>
						<div class="form-group">
							<label for="fname">Name:</label>
							<form:input path="fName" class="form-control" />
						</div>
						<div class="form-group">
							<label for="lname">Last name:</label>
							<form:input path="lName" class="form-control" />
						</div>

						<input type="submit" value="Create new USER" class="btn">
						<br>
						<br>
						<br>
						<br>
						<br>
						<a href="goHome" class="btn btn-danger">CANCEL</a>
					</form:form>
					<c:if test="${not empty reasonDenine}"><div style="color: red;">Причина отказа в регистрации: "${reasonDenine}". Пробуйте снова с новыми данными.</div></c:if>
				</div>

			</div>
		</div>
	</div>
</body>

</html>
