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

<link rel="stylesheet"
	href="<c:url value="/resources/gallery/css/bootstrap.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/gallery/css/blueimp-gallery.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/gallery/css/bootstrap-image-gallery.min.css" />">

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
		<div
			style="border: solid 0px green; border-radius: 5px; padding: 10px;">
			<a href="viewPerson?id=${person.id}" class="disablehref a">Return
				to page of ${person.fName} ${person.lName}</a>
			<h2
				style="border: solid 0px green; border-radius: 5px; padding: 10px;">
				${album.name}</h2>
			${album.description} (${photos.size()} photos)<br>
			<c:if test="${isEditor}">
				<a href="removeAlbum?id=${album.id}" class="disablehref a">[Remove
					Album <span class="glyphicon glyphicon-remove"></span>]
				</a>&nbsp;
											<a href="editAlbumInfo?id=${album.id}" class="disablehref a">[Edit
					Album <span class="glyphicon glyphicon-pencil"></span>]
				</a>
			</c:if>
			<!-- INSERT TAGS !!!  -->
			<br>
			<div id="links">
			<c:forEach var="photo" items="${photos}" varStatus="status">
				<span
					style="display: inline-block; border: solid 0px green; border-radius: 5px; padding: 10px; margin: 10px;">
					${photo.name} <br> ${photo.description} &nbsp; <br>
				<a href="<c:url value="/imageDisplay?id=${photo.id}"/>" title="${photo.description}" data-gallery> <img
						src="<c:url value="/previewImageDisplay?id=${photo.id}"/>"
						alt="Persons photo" class="img-thumbnail">
				</a> <c:if test="${isEditor}">
						<br>
						<a href="editPhoto?id=${photo.id}" class="disablehref a">[Edit
							Photo <span class="glyphicon glyphicon-pencil"></span>]
						</a>
						<br>
						<br>
						<a href="removePhoto?id=${photo.id}" class="disablehref a">[Remove
							Photo <span class="glyphicon glyphicon-remove"></span>]
						</a>
					</c:if>
				</span>
			</c:forEach>
			</div>
			<c:if test="${photos.isEmpty()}">
				<br>
				<br>
				<br>
				<br>
				<br>
				<h3>Album '${album.name}' doesn't have any photo</h3>
			</c:if>
		</div>
		<c:if test="${isEditor && '51' gt photos.size()}">
			<a href="addPhoto?id=${album.id}"
				class="disablehref a btn btn-success">Add New Photo</a>
				MayBe added ${'50' - photos.size()}  photos
		</c:if>
	</div>
	
	
	<!-- The Bootstrap Image Gallery lightbox, should be a child element of the document body -->
	<div id="blueimp-gallery" class="blueimp-gallery">
		<!-- The container for the modal slides -->
		<div class="slides"></div>
		<!-- Controls for the borderless lightbox -->
		<h3 class="title"></h3>
		<a class="prev">‹</a> <a class="next">›</a> <a class="close">×</a> <a
			class="play-pause"></a>
		<ol class="indicator"></ol>
		<!-- The modal dialog, which will be used to wrap the lightbox content -->
		<div class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" aria-hidden="true">&times;</button>
						<h4 class="modal-title"></h4>
					</div>
					<div class="modal-body next"></div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default pull-left prev">
							<i class="glyphicon glyphicon-chevron-left"></i> Previous
						</button>
						<button type="button" class="btn btn-primary next">
							Next <i class="glyphicon glyphicon-chevron-right"></i>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="<c:url value="/resources/js/jquery-2.1.3.js" />"></script>
	<script
		src="<c:url value="/resources/gallery/js/jquery.blueimp-gallery.min.js" />"></script>
	<script src="<c:url value="/resources/gallery/css/bootstrap-image-gallery.min.css" />"></script>
</body>

</html>
