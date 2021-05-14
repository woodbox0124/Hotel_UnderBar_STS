<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	MemberDTO dto = (MemberDTO) session.getAttribute("login");
String u_id = dto.getU_id();
String u_pw = dto.getU_pw();
String u_name = dto.getU_name();
String u_Phone = dto.getU_phone();
String u_email = dto.getU_email();
%>

<!DOCTYPE HTML>


<html>
<head>
<!-- 글꼴 -->
<link rel="stylesheet" type="text/css" href="assets/css/font.css">
<!-- 글꼴 -->

<!-- mypage css 시작 -->
<link rel="stylesheet" type="text/css" href="assets/css/mypage.css">
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
		 location.href="MemberDeleteServlet?u_id="+u_id;
	 })	
});
</script>
<!-- jQuery 끝-->
<!-- table css 끝 -->
<title>MyPage</title>
<meta charset="utf-8">
<!-- main배경 css -->
<!-- <link rel="stylesheet" type="text/css"
	href="styles/bootstrap-4.1.2/bootstrap.min.css"> -->

<!-- main배경 css 끝 -->
<%
	String mesg = (String) session.getAttribute("mesg");
if (mesg != null) {
%>
<script type="text/javascript">
     alert('<%=mesg%>');
</script>
<%
	session.removeAttribute("mesg");
}
%>

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
<body>
	<jsp:include page="menu_mypage.jsp"></jsp:include><br>
	<div id="main">
		<!-- Header -->
		<div id="header">
			<form action="MemberUpdateServlet" id=form>
				<table class="resv_table" id="resv_table">
					<tr>
						<th colspan="2" id="membername">
							<h1><%=u_name%>님의 정보
							</h1>
						</th>
					</tr>
					<tbody id=tbody>
						<tr>
							<th>아이디</th>
							<td><%=u_id%><input type="hidden" value="<%=u_id%>"
								name="u_id" style=""></td>
						</tr>
						<tr>
							<th>이름</th>
							<td><%=u_name%><input type="hidden" value="<%=u_name%>"
								name="u_name"></td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input type="password" class="update" id="pw"
								value="<%=u_pw%>" name="u_pw"></td>
						</tr>
						<tr>
							<th>비밀번호 확인</th>
							<td><input type="password" class="update" id="pw"></td>
						</tr>

						<tr>
							<th>전화번호</th>
							<td><input type="text" class="update" value="<%=u_Phone%>"
								name="u_phone"></td>
						</tr>

						<tr>

							<th>e-mail</th>
							<td><input type="text" class="update" value="<%=u_email%>"
								name="u_email"></td>
						</tr>
					</tbody>
				</table>
				<div id="button1">
					<button id="submit" type="submit" class="btn btn-outline-primary">회원정보
						수정하기</button>
				</div>
			</form>
			<div id="button2">
				<button id="delete" data-xxx="<%=u_id%>"
					class="btn btn-outline-primary">회원 탈퇴하기</button>
			</div>
		</div>
	</div>
</body>
</html>