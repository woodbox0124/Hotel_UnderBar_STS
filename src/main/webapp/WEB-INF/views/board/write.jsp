<%@page import="javafx.scene.control.Alert"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.dto.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="com.dto.MemberDTO"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/font.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
	crossorigin="anonymous"></script>
	
	<style type="text/css">
* {
	font-family: 'twayair', 'Roboto', 'sans-serif' !important;
}
th, td{
padding-top: 5px;
padding-bottom: 5px;
border: 1px solid #444444;
border-collapse: collapse;
}

table {
    width: 100%;
    border: 1px solid #444444;
  }
  
#table{
width: 505px;
margin-bottom: 30px;
}

#title{
margin-top: 20px;
margin-bottom: 10px;
}


</style>

<meta charset="UTF-8">
<title>Q&#38;A 게시판</title>

</head>
<body>
<div class="container">
<%
MemberDTO dto = (MemberDTO) session.getAttribute("login");
%>
<h3 id="title" style="text-align: center;">게시글 작성</h3>
<form action="loginCheck/boardInsert" method="post">
<table id="table" style="margin: 0 auto" border="1">
		<tr>
			<td width="70" align="center">제목</td>
			<td><input name="title" size="45" type="text" id="title"></td>
		</tr>
		<tr>
			<td width="70" align="center">작성자</td>
			<td ><input type="text" size="45" name="author" id="author" value="<%=dto.getU_id()%>"></td>
		</tr>
		<tr>
			<td width="70" align="center">글내용</td>
			<td >
			<textarea style="width: 426px; height: 500px; resize: none;" rows="25" cols="30" name="content" id="content"></textarea>
			</td>
		</tr>
		<tr style="text-align: center;">
		<td colspan="2">
			<input class="btn btn-primary" type="submit" value="글쓰기">
			<input class="btn btn-primary" type="reset" value="다시작성"> 
			<a class="btn btn-primary" href="boardList">목록보기</a>
		</td>
		</tr>
	</table>
</form>	
	</div>
	
</body>
</html>