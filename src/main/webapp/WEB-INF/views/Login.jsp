
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Person Manager Home</title>
<!-- Bootstrap -->
<link
	href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/my.css" />" rel="stylesheet">
</head>
<body background="<c:url value='/resources/images/grass.jpg'/>"
	style="background-size: 90%;">
	<div id="maincontainer">
		<div id="topsection">
			<div class="innertube">
				<h1>
					<a href="goHome" class="disablehref,a">Social NET</a>
				</h1>
			</div>
		</div>
		<p></p>
		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br>
		<div class="container">
			<form:form action="credentials" method="post" modelAttribute="person"
				class="form-inline">
				<div class="form-group">
					<label for="email">Login:</label>
					<form:input path="login" class="form-control" />
				</div>
				<div class="form-group">
					<label for="pwd">Password:</label>
					<form:password path="password" class="form-control" />
				</div>
				<!-- <div class="checkbox">
					<label><input type="checkbox"> Remember me</label>
				</div> -->
				&nbsp;&nbsp;&nbsp;
				<button type="submit" class="btn btn-default">Submit</button>
			</form:form>
		</div>
	</div>
</body>
</html>
