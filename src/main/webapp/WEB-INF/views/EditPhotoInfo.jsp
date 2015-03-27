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
<title>Edit photo ${photo.name}</title>
<link
	href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/my.css" />" rel="stylesheet">
<script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.3.js" />"></script>
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

		<!-- photo INFO BLOCK  -->
		<div id="contentwrapper">
			<div id="contentcolumn">
				<div class="innertube">
					<h4>Editing photo '${photo.name}'</h4>
					<table>
						<form:form action="updatephotoToGallery" enctype="application/x-www-form-urlencoded; charset=UTF-8" method="post"
							modelAttribute="photo">
							<form:hidden path="id"/>
							<form:hidden path="albumId"/>
							<tr>
								<td><label>Name: &nbsp;&nbsp;&nbsp;</label></td>
								<td><form:input path="name" class="form-control"/></td>
							</tr>
								<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td><label>Description: &nbsp;&nbsp;&nbsp;</label></td>
								<td><form:input path="description" class="form-control"/></td>
							</tr>
								<tr>
								<td>&nbsp;</td>
							</tr>

							<tr>
								<td colspan="2" align="center"><input type="submit"
									value="Save" class="btn btn-default"></td>
							</tr>

						</form:form>
					</table>
					<form method="post" action="uploadPhotoToGallery"
						enctype="multipart/form-data" onsubmit="return checkSize(2000152)">
						<input type="hidden" name="id_photo" value="${photo.id}">
						Photo to upload: <input type="file" name="file" id="upload" class="btn btn-default"><br />
						<input type="submit" value="Upload" class="btn btn-default"> Press here to upload
						the file!
					</form>
				</div>
			</div>
		</div>

		<!-- PHOTO BLOCK  -->
		<div id="photo">
			<div class="innertube">
				<h4>${photo.name}</h4>
				<div>
					<img src="<c:url value="/photoDisplay?id=${photo.id}"/>"
						alt="Mountain View" class="img-thumbnail">
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
