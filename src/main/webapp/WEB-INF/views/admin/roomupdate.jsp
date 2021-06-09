<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 방 정보 수정</title>
<!-- JQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- JQuery -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/roomupdate.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/font.css">
<c:if test="${!empty updatecomplete }">
	<script>
		alert("업데이트가 완료 되었습니다.");
		opener.location.reload();
		<%
		session.removeAttribute("updatecomplete");
		%>
		window.close();
	</script>
</c:if>
</head>
<body>
<div class="main">
<form action="../loginCheck/roomUpload" method="POST" encType="multipart/form-data">

<div id = "room">
<input type="hidden" name="seq" value="${rDTO.seq}">
<input type="hidden" name="room_img" value="${rDTO.room_img}">

<%-- 호텔 이름 : <input type="text" name="hotelname" value="${hotelname}" class="input" disabled required/><br><br> --%>
객실 이름 : <input type="text" name="name" value="${rDTO.name}" class="input" required/><br><br>

가격 : <input type="text" name="price" value="${rDTO.price}" class="input" required/><br><br>
최대 인원 수 : <input type="text" name="max_guest" value="${rDTO.max_guest}" class="input" required/><br><br>
</div>
사진이름 : ${rDTO.room_img}<br><br>
※사진파일명을 위에 사진이름과 동일하게 수정 후 업로드 바랍니다.
<br><br>
객실 사진 : <input type="file" name="theFile">
<br><br>
<div id="test">
<input type="submit" value="수정" class="btn btn-primary update">
</div>
</form>
</div>
</body>
</html>