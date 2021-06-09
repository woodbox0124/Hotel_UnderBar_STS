<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 호텔 정보 추가</title>
<!-- JQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- JQuery -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/roominsert.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/font.css">
<c:if test="${!empty updatecomplete }">
	<script>
		alert("업데이트가 완료 되었습니다.");
		opener.location.reload();
		<%
		session.removeAttribute("updatecomplete");
		%>
		history.go(-1);
	</script>
</c:if>

</head>
<body>
<div id="wrapper">
<form action="../loginCheck/roomInsert" method="POST" encType="multipart/form-data">
 <div class="main"> 
   <input type="hidden" name="hotelseq" value="${hotel_seq}">
   <input type="hidden" name="r_seq" value="${room_seq+1}">
    <h2>객실 정보</h2><br><br>
    <h4>객실 이름</h4>
    <input type="text" name="name" class="input" style = "text-align:center;" required/><br><br>
    <h4>가격</h4>
    <input type="text" name="price" class="input" style = "text-align:center;" required/><br><br>
    <h4>최대인원</h4>
    <input type="text" name="max_guest" style = "text-align:center;" required/><br><br>

        객실 사진이름: ROOM${room_seq+1}<br><br>
           ※사진파일명을 위에 객실 사진이름과 동일하게 수정 후 업로드 바랍니다.
    <br><br>
        객실 사진:<input type="file" name="theFile">
    <br><br>
       사진이름:ROOM${room_seq+1}_1<br><br>
          ※사진파일명을 위에 사진이름과 동일하게 수정 후 업로드 바랍니다.
    <br><br>
        객실 자세히 사진:<input type="file" name="theFile1">
    <br><br>
    </div>
 <div class="main">
   <h2>객실 자세히 보기 정보</h2><br><br>
   <h4>욕실</h4>
    <input type="text" name="bath" class="input" required/><br>
       예: 헤어드라이어, 고급세면용품, 레인폴 샤워기<br><br>
    <h4>비치용품</h4>  
     <input type="text" name="eat" class="input" required/><br>
        예: 전기주전자, 냉장고, 커피 티 메이커<br><br>
    <h4>Wifi</h4>
    <select class="place booking_input input" id="place booking_input" name="internet">
		<option value="무료 wifi">무료 wifi</option>
		<option value="유료 wifi">유료 wifi</option>
	</select><br><br>
	<h4>기타</h4>        
    <input type="text" name="etc" class="input" required/><br>
         예: 프리미엄 TV 채널,반려동물 동반 가능 <br><br>
  </div><br><br>
  <div id="test">
  <input type="submit" value="추가" class="btn btn-primary update">
  </div>
</form>
</div>
</body>
</html>