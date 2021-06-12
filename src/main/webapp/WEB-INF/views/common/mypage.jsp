<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
<!-- 글꼴 CSS 끝-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/font.css">
<!-- 글꼴  CSS 끝 -->
<!-- mypage css 시작 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/mypage.css">

<!-- mypage css 끝 -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap" rel="stylesheet">
<!-- jQuery 시작 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
 $(function() {
	 $("#delete").click(function() {
		 var u_id = $(this).attr("data-xxx");
		 console.log(u_id);
		 location.href="loginCheck/MemberDelete?u_id="+u_id;
	 })	
	 $("#update").click(function(event){
		 if($("#pw1").val()==$("#pw2").val()){
			$("#form").attr("action","loginCheck/MemberUpdate");
			$("#form").submit();
		 }else{
			 alert("비밀번호가 일치하지 않습니다.");
			 event.preventDefault();
		 }
		}) 
});
</script>
<!-- jQuery 끝-->

<title>MyPage</title>
<meta charset="utf-8">

<!-- alert mesg 시작 -->
<c:if test="${!empty mesg }">
<script>
    alert("${mesg}");
</script>
<%session.removeAttribute("mesg");%>
</c:if>
<!-- alert mesg 끝 -->

<script>
	window.onload = function() {
		document.body.classList.remove('is-preload');
	}
	window.ontouchmove = function() {
		return false;
	}
	window.onorientationchange = function() {
		document.body.scrollTop = 0;
	}
</script>
</head>
	<div id="main">
		<!-- Header -->
		<div id="header">
			<form action="" id=form method="post">
				<table class="resv_table" id="resv_table">
					<tr>
						<th colspan="2" id="membername">
							<h1>${login.u_name}님의 정보
							</h1>
						</th>
					</tr>
					<tbody id=tbody>
						<tr>
							<th>아이디</th>
							<td>${login.u_id}<input type="hidden" value="${login.u_id}"
								name="u_id" style=""></td>
						</tr>
						<tr>
							<th>이름</th>
							<td><input type="text" value="${login.u_name}"
								name="u_name"></td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input type="password" class="update pw" id="pw1"
								value="${login.u_pw}" name="u_pw"></td>
						</tr>
						<tr>
							<th>비밀번호 확인</th>
							<td><input type="password" class="update pw" id="pw2"></td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td><input type="text" class="update" value="${login.u_phone}"
								name="u_phone"></td>
						</tr>
						<tr>
							<th>e-mail</th>
							<td><input type="text" class="update" value="${login.u_email}"
								name="u_email"></td>
						</tr>
					</tbody>
				</table>
				<div id="button1">
					<button id="update" type="submit" class="btn btn-outline-primary">회원정보
						수정하기</button>
				</div>
			</form>
			<div id="button2">
				<button id="delete" data-xxx="${login.u_id}" 
					class="btn btn-outline-primary">회원 탈퇴하기</button>
			</div>
		</div>
	</div>
