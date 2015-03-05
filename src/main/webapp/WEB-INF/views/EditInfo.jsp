<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Person ${person.login}</title>
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

		<!-- PERSON INFO BLOCK  -->
		<div id="contentwrapper">
			<div id="contentcolumn">
				<div class="innertube">
					<h4>Info about ${person.fName} &nbsp; ${person.lName}</h4>
					<table>
						<form:form action="savePerson" method="post"
							modelAttribute="person">
							<form:hidden path="id" />
							<form:hidden path="password" />
							<form:hidden path="login" />
							<tr>
								<td>Login:</td>
								<td>${person.login}</td>
							</tr>
							<tr>
								<td>First Name:</td>
								<td><form:input path="fName" /></td>
							</tr>
							<tr>
								<td>Last Name:</td>
								<td><form:input path="lName" /></td>
							</tr>
							<tr>
								<td>Birthday (mm/dd/yyyy):</td>
								<td><form:input path="dob" /></td>
							</tr>
							<tr>
								<td>Phone:</td>
								<td><form:input path="phone" /></td>
							</tr>
							<tr>
								<td>Address:</td>
								<td><form:input path="address" /></td>
							</tr>

							<tr>
								<td colspan="2" align="center"><input type="submit"
									value="Save"></td>
							</tr>

						</form:form>
					</table>
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


		<!-- FOOTER BLOCK  -->
		<!-- 	<div id="footer">
			<a href="http://www.dynamicdrive.com/style/">Dynamic Drive CSS
				Library</a>
		</div> -->

	</div>
</body>

</html>
