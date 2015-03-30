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
			<a href="<c:url value="/j_spring_security_logout" />"><h3>Error!!! Logout?</h3></a>
		</div>
	</div>

</body>
</html>
