<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항작성</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/notification.css">
<style>

table {
	width:100%;
	margin-bottom:10px;
}
tr{
	border-bottom:0.5px solid #ddd;
}


th{
	width:20%;
	border-right:0.5px solid #ddd;
}

.buttons{
	margin-top:60px;
}


</style>
</head>
<body>
<div id="wrapper">
	<div class="content">
		<table>
			<tr>
				<th>제목</th>
				<td><input type="text" class="form-control-plaintext" disabled name="subject" id="exampleFormControlInput1" value="${nDTO.subject }"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" class="form-control-plaintext" disabled id="exampleFormControlInput1"  value="${nDTO.author}" name="author" readonly></td>
			</tr>
			<tr>
				<td colspan="2"><textarea class="form-control-plaintext" disabled  name="content" id="exampleFormControlTextarea1" rows="20" >${nDTO.content }</textarea></td>
			</tr>
		</table>
	
		<div class="buttons">
			<c:if test="${u_id eq nDTO.author}">
				<input class="btn btn-outline-secondary" type="submit" value="수정">
				<!-- content or subject empty -> modal 적용  -->					
				<input class="btn btn-outline-secondary" type="reset" value="다시작성"> 
			</c:if>
			<a class="btn btn-outline-secondary" href="notification">목록보기</a>
		</div>
	</div>
</div>
</body>
</html>