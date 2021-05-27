<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항작성</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/notification.css">

</head>
<body>
<div id="wrapper">
	<div class="content">
		<form action="loginCheck/notiInsert" method="post">
			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">제목</label>
				<input type="text" class="form-control" name="subject" id="exampleFormControlInput1" placeholder="subject">
			</div>
			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">작성자</label>
				<input type="text" class="form-control" id="exampleFormControlInput1" readonly value="${login.u_id}" name="author" readonly>
			</div>	
			<div class="mb-3">
			  <label for="exampleFormControlTextarea1" class="form-label">내용</label>
			  <textarea class="form-control" name="content" id="exampleFormControlTextarea1" rows="15" placeholder="content"></textarea>
			</div>
			<div class="mb-3">
				<label for="formFileSm" class="form-label">파일첨부</label>
				<input class="form-control form-control-sm" id="formFileSm" type="file">
			</div>
			<div class="buttons">
				<input class="btn btn-outline-secondary" type="submit" value="글쓰기">
				<!-- content or subject empty -> modal 적용  -->
				<input class="btn btn-outline-secondary" type="reset" value="다시작성"> 
				<a class="btn btn-outline-secondary" href="notiList">목록보기</a>
			</div>
		</form>	
	</div>
</div>
</body>
</html>