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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#revise").on("click",function(){
			$("form").attr("action","loginCheck/notiUpdate");
			$("form").submit();
		})
		$("#delete").on("click",function(){
			
			$("form").attr("action","loginCheck/notiDelete");
			$("form").submit();
		})
		
	})
</script>
</head>
<body>
<div id="wrapper">
	<div class="content">
		<c:choose>
			<c:when test="${login.u_id eq 'admin'}">
				<form action="" method="post">
					<input type="hidden" name=num value="${nDTO.num }">
					<table>
						<tr>
							<th>제목</th>
							<td><input type="text" class="form-control-plaintext" name="subject" id="exampleFormControlInput1" value="${nDTO.subject }"></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td><input type="text" class="form-control-plaintext" name="author" id="exampleFormControlInput1"  value="${nDTO.author}"  readonly></td>
						</tr>
						<tr>
							<th>작성일</th>
							<td><input type="text" class="form-control-plaintext" name="regdate" id="exampleFormControlInput1"  value="${nDTO.regdate}" readonly></td>
						</tr>
						<tr>
							<td colspan="2">
								<textarea class="form-control-plaintext"  name="content" id="exampleFormControlTextarea1" rows="20" >${nDTO.content }</textarea>
							</td>
						</tr>
					</table>
					<div class="buttons">
						<input class="btn btn-outline-secondary" type="button" value="수정" id="revise">
						<!-- content or subject empty -> modal 적용  -->					
						<input class="btn btn-outline-secondary" type="button" value="삭제" id="delete"> 
						<a class="btn btn-outline-secondary" href="notification">목록보기</a>
					</div>
				</form>
			</c:when>
			<c:otherwise>
					<table>
						<tr>
							<th>제목</th>
							<td><input type="text" class="form-control-plaintext" disabled name="subject" id="exampleFormControlInput1" value="${nDTO.subject }"></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td><input type="text" class="form-control-plaintext" disabled id="exampleFormControlInput1"  value="${nDTO.author}"disabled></td>
						</tr>
						<tr>
							<th>작성일</th>
							<td><input type="text" class="form-control-plaintext" disabled id="exampleFormControlInput1"  value="${nDTO.regdate}"></td>
						</tr>
						<tr>
							<td colspan="2">
								<textarea class="form-control-plaintext" disabled id="exampleFormControlTextarea1" rows="20" >${nDTO.content }</textarea>
							</td>
						</tr>
					</table>
					<div class="buttons">
						<a class="btn btn-outline-secondary" href="notification">목록보기</a>
					</div>
			</c:otherwise>
		</c:choose>
	</div><!-- end content  -->
</div><!-- end wrapper -->
</body>
</html>