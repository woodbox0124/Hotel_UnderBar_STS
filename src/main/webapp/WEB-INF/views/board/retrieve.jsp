<%@page import="javafx.scene.control.Alert"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
}

#title{
margin-top: 20px;
margin-bottom: 10px;
}

</style>

<meta charset="UTF-8">
<title>Q&#38;A 게시판</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
function updateBoard(e, f) {
	e.preventDefault();//주의 preventDefault() 해야 함 
	f.action="boardUpdate"; //action지정
	f.method="post";
	f.submit();	//submit 이벤트반드시 해야함
} 
function deleteBoard(e, f) {
	//f.action="BoardDeleteServlet?num="+num;
	//f.submit();	
	e.preventDefault();//주의 preventDefault() 해야 함
	f.action="boardDelete"; //action지정
	f.method="get"
	f.submit();	//submit 이벤트반드시 해야함
}
function boardAnswer(e, f) {
	//f.action="BoardDeleteServlet?num="+num;
	//f.submit();	
	e.preventDefault();//주의 preventDefault() 해야 함
	f.action="boardAnswer"; //action지정
	f.method="get"
	f.submit();	//submit 이벤트반드시 해야함
}

</script>
</head>
<body>
<div class="container">
<h3 id="title" style="text-align: center;">게시글 보기</h3>
<form name="myForm">
<input type="hidden" name="num" value="${bDTO.num}">
<input type="hidden" name="groupnum" value="${bDTO.groupnum}">
<input type="hidden" name="grouplayer" value="${bDTO.grouplayer}">
<input type="hidden" name="origin" value="${bDTO.origin}">
<table id="table" style="margin: 0 auto" border="1">
		<tr>
			<td width="70" align="center">작성자</td>
			<td><input type="text" size="45" value="${bDTO.author}" name="author" readonly></td>
		</tr>
		<tr>
			<td width="70" align="center">글제목</td>
			<td><input type="text" size="45" value="${bDTO.title}" name="title"></td>
		</tr>
		<tr>
			<td width="70" align="center">글내용</td>
			<td >
			<textarea style="margin-left: 1px; width: 425px; height: 500px; resize: none;" rows="10" cols="30" name="content">${bDTO.content}</textarea>
			</td>
		</tr>
			<tr>
			<td colspan="2" align="center">
			<c:if test="${bDTO.author eq login.u_id}">
				<input class="btn btn-primary" type="button" value="수정" onclick="updateBoard(event, myForm)">
             	<input class="btn btn-primary" type="button" value="삭제" onclick="deleteBoard(event, myForm)">
             	<a class="btn btn-primary" href="boardList">목록보기</a>
				<c:if test="${bDTO.author eq login.u_id || bDTO.author eq 'admin'}">
					<input class="btn btn-primary" type="button" value="답글쓰기" onclick="boardAnswer(event, myForm)">
				</c:if>
			</c:if>
			</td>
		</tr>
	</table>
	</form>
	
	<br>
	
	</div>
	
</body>
</html>