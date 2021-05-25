
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="assets/css/font.css" />
<link rel="stylesheet" type="text/css" href="assets/css/default.css">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap" rel="stylesheet">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" type="text/css" href="styles/main_styles.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="assets/css/font.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<!-- <style type="text/css">
* {
	font-family: 'twayair', 'Roboto', 'sans-serif' !important;
}

#main_header {
	font-size: 24px;
	color: black;
	font-weight: bold;
	position: relative;
	left: 1px;
	top: 1px;
}

#menu {
	height: 53px;
}

a {
	text-decoration: none
}
</style> -->
</head>
<body>
	<%-- <nav class="navbar navbar-expand-lg navbar-light bg-light" id="menu">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item" id="li_main"><a class="nav-link active"
						aria-current="page" href="main.jsp" id="main_header">Hotel
							UnderBar</a></li>
					<li class="nav-item"></li>
					<li class="nav-item dropdown">

						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li></li>
							<li></li>
							<li></li>
							<li></li>
						</ul>
					</li>
					<li class="nav-item"></li>
				</ul>
				<jsp:include page="common/top.jsp" flush="false" />
					
			</div>
		</div>
	</nav> --%>
	<jsp:include page="common/menu2.jsp" flush="true"></jsp:include><br>
	<jsp:include page="board/list.jsp" flush="true" ></jsp:include>
	<jsp:include page="hotel/footer.jsp" flush="true"></jsp:include>
</body>
</html>
	
	
