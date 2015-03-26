<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
					<h4 style="border: solid 0px green; border-radius: 5px; padding:10px;">
						ALBUM ${album.name} of person<a href="viewPerson?id=${person.id}" class="disablehref a">  ${person.fName} ${person.lName}</a>
					</h4>
					<!-- INSERT TAGS !!!  -->
					<br>
					<c:forEach var="photo" items="${photos}" varStatus="status">
						<span style="display: inline-block; border: solid 0px green; border-radius: 5px; padding: 10px; margin: 10px;">
							${photo.name} <br> ${photo.description} &nbsp; 
							<br>
							<img src="<c:url value="/photoDisplay?id=${photo.id}"/>" alt="Persons photo" style="width: 100px; height: 100px" class="img-thumbnail">
							<a href="removePhoto?id=${photo.id}" class="disablehref a"> 
								<span class="glyphicon glyphicon-remove"></span>
							</a>&nbsp;
							<a href="editPhoto?id=${photo.id}" class="disablehref a">
								<span class="glyphicon glyphicon-pencil"></span>
							</a>
						</span>
					</c:forEach>
					<c:if test="${photos.isEmpty()}">
						<br>
						<br>
						<br>
						<br>
						<br>
						<h3>Album '${album.name}' doesn't have any photo</h3>
					</c:if>
				</div>
				<a href="addPhoto?id=${album.id}" class="disablehref a btn btn-success">Add New Photo</a>
			</div>
		</div>
	</div>
</body>

</html>
