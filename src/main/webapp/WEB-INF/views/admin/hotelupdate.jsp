<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 회원 정보 수정</title>
<!-- JQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- JQuery -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/hotelupdate.css">
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
<form action="../loginCheck/HotelUpload" method="POST" encType="multipart/form-data">
<input type="hidden" name="seq" value="${hDTO.seq}">
<input type="hidden" name="hotel_img" value="${hDTO.hotel_img}">

이름:<input type="text" name="name" value="${hDTO.name}" class="input" required/><br><br>

지역:<select class="place booking_input input" id="place booking_input" name="place">
		<option value="서울" <c:if test="${hDTO.place eq '서울'}">selected</c:if>>서울</option>
		<option value="부산" <c:if test="${hDTO.place eq '부산'}">selected</c:if>>부산</option>
		<option value="제주" <c:if test="${hDTO.place eq '제주'}">selected</c:if>>제주</option>
	</select>
	<br><br>
주소:<input type="text" name="addr" value="${hDTO.addr}" class="input" required/><br><br>

사진이름:${hDTO.hotel_img}<br><br>
※사진파일명을 위에 사진이름과 동일하게 수정 후 업로드 바랍니다.
<br><br>
호텔사진:<input type="file" name="theFile">
<br><br>
<div id="test">
<input type="submit" value="수정" class="btn btn-primary update">
</div>
</form>
</div>
</body>
</html>