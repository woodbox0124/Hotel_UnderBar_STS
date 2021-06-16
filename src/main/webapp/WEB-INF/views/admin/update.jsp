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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/update.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/font.css">
<script type="text/javascript">
$(document).ready(function() {
	$(".update").click(function() {
		var u_id=$("#u_id").val();
		var u_pw=$("#u_pw").val();
		var u_name=$("#u_name").val();
		var u_phone=$("#u_phone").val();
		var u_email=$("#u_email").val();
		$.ajax({
			url: "../loginCheck/AdminMemberUpdate",
			type:"post",
			dataType:"text",
			data: {
				u_id:u_id,
				u_pw:u_pw,
				u_name:u_name,
				u_phone:u_phone,
				u_email:u_email
			},
			success: function(data, status, xhr) {
				console.log(data);
				opener.$(".name"+u_id).text(u_name);
				opener.$(".pw"+u_id).text(u_pw);
				opener.$(".phone"+u_id).text(u_phone);
				opener.$(".email"+u_id).text(u_email);
				alert("회원정보 수정이 완료되었습니다.");				
				window.close();
			},
			error: function(xhr, status, error) {
				console.log(error);
			}			
		});//end ajax
		
	});		
});
</script> 
</head>
<body>
<div class="main">
<input type="hidden" id="u_id" value="${mdto.u_id}">
아이디: ${mdto.u_id}<br><br>

이름: <input type="text" id="u_name" value="${mdto.u_name}"/><br><br>

비밀번호: <input type="password" id="u_pw" value="${mdto.u_pw}"/><br><br>

전화번호:<input type="text" id="u_phone" value="${mdto.u_phone}"/><br><br>

이메일:<input type="text" id="u_email" value="${mdto.u_email}"/><br><br>

<div id="test">
<input type="button" value="수정" class="btn btn-primary update">
</div>
</div>
</body>
</html>