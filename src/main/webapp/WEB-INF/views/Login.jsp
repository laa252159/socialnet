<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	<!-- TOP BLOCK  -->
	<div id="topsection">
		<div class="innertube">
			<h1>
				<a href="goHome" class="disablehref,a">Social NET</a>
			</h1>
		</div>
	</div>
	<div id="maincontainer" style="margin-top: 10%">
		<div id="login-box">

			<h3>Login with Username and Password</h3>

			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>

			<form name='loginForm'
				action="<c:url value='j_spring_security_check' />" method='POST'
				class="form-inline">

				<label>Login:</label> <input type='text' name='username' value=''
					class="form-control"> <label>Password:</label> <input
					type='password' name='password' class="form-control" /> <input
					name="submit" type="submit" value="submit" class="btn btn-default" />
				<!-- <div class="checkbox">
					<label><input type="checkbox"> Remember me</label>
				</div> -->

				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />

			</form>
			
		</div>
		<br><br><br><br><br>
		<a href="reg" class="btn btn-default">Registration</a>
	</div>

</body>
</html>
