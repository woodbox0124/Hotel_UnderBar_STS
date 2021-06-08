<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
  <!-- adminHead css -->
    <style type="text/css">
    .adminmenu{
    width: 100%;
    margin: 0 auto;
    text-align: center;
    }
    .menubar3{
    font-size: 40px
    }
    </style>
    <!-- admin css 끝 -->
</head>
<body>
<jsp:include page="common/menu.jsp" flush="true" /><br>
<div class="adminmenu">
<a href="loginCheck/adminMember" id="member" class="menubar3" style="color: blue; text-decoration: none;" >회원관리</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="loginCheck/adminHotel" id="hotel" class="menubar3" style="color: blue; text-decoration: none;" >호텔관리</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="loginCheck/adminRoom" id="room" class="menubar3" style="color: blue; text-decoration: none;" >객실관리</a>
</div>
<jsp:include page="admin/room.jsp" flush="true"></jsp:include>
</body>
</html>