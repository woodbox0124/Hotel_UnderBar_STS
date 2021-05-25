<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/font.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
<style type="text/css">

#title{
	font-size: 22px;
	font-weight: bold;
}

#menu_background {
	height: 47px;
}

a {
	text-decoration: none
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light" id="menu_background">
		<div class="container-fluid">
			<!-- <button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button> -->
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item" id="li_main"><a class="nav-link active"
						aria-current="page" href="/hotelunderbar" id="title" style="color: black; text-decoration: none;">Hotel
							UnderBar</a></li>
					<!-- 링크 "/hotelunderbar"시 메인으로 이동 -->
				</ul>
			</div>
			<jsp:include page="top.jsp" flush="true"></jsp:include>
		</div>
	</nav>
</body>
</html>