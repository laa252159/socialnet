<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Album ${album.name}</title>
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

		<!-- ALBUM VIEW BLOCK  -->
		<div id="contentwrapper">
			<div id="contentcolumn">
				<div class="innertube">
					<h4>ALBUM ${album.name} of person ${person.fName} ${person.lName}</h4>
					<!-- INSERT TAGS !!!  -->
				</div>
			</div>
		</div>
	</div>
</body>

</html>
