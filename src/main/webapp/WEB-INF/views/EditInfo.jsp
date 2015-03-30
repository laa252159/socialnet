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
<title>Edit Person ${person.login}</title>
<link
	href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />"
	rel="stylesheet">
	<link
	href="<c:url value="/resources/css/jquery-ui.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/my.css" />" rel="stylesheet">
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-2.1.3.js"/>"></script>
	<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-ui.js"/>"></script>
<script type="text/javascript">
	function checkSize(max_img_size) {
		var input = document.getElementById("upload");
		// check for browser support (may need to be modified)
		if (input.files && input.files.length == 1) {
			if (input.files[0].size > max_img_size) {
				alert("The file must be less than "
						+ (Math.round(max_img_size / 1024)) + "KB");
				return false;
			}
		}

		return true;
	}
</script>
<script>
  $(function() {
    $( "#dob" ).datepicker();
  });
</script>
<script>
$('#formEditPerson').validate({
    rules: {
        dob: "required"
    }
});
</script>
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
						<form:form action="updatePerson" id="formEditPerson" enctype="application/x-www-form-urlencoded; charset=UTF-8" method="post"
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
								<td><form:input path="fName" class="form-control" /></td>
							</tr>
							<tr>
								<td>Last Name:</td>
								<td><form:input path="lName" class="form-control" /></td>
							</tr>
							<tr>
								<td>Birthday (mm/dd/yyyy):</td>
								<td><input name="dob" id="dob" class="form-control" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${person.dob}" />"></td>
							</tr>
							<tr>
								<td>Phone:</td>
								<td><form:input id="phone" path="phone" class="form-control"/></td>
							</tr>
							<tr>
								<td>Address:</td>
								<td><form:input path="address" class="form-control" /></td>
							</tr>

							<tr>
								<td colspan="2" align="center"><input type="submit" class="btn btn-default"
									value="Save"></td>
							</tr>

						</form:form>
					</table>
					<form method="post" action="uploadPhoto"
						enctype="multipart/form-data" onsubmit="return checkSize(200152)">
						<input type="hidden" name="id_person" class="form-control" value="${person.id}">
						Photo to upload: <input type="file" name="file" id="upload" class="btn btn-default"><br />
						<input type="submit" class="btn btn-default" value="Upload"> Press here to upload
						the file!
					</form>
				</div>
			</div>
		</div>

		<!-- PHOTO BLOCK  -->
		<div id="photo">
			<div class="innertube">
				<h4>${personInfo.login}&nbsp;photo</h4>
				<div style="border: 1px solid #cecece;">
					<img src="<c:url value="/imageDisplay?id=${person.id}"/>"
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
