<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/update.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/font.css">
<script type="text/javascript">

		/* opener.document.location.reload();
		self.close(); */
	</script> 
</head>
<body>
<div class="main">
<form action="../loginCheck/AdminMemberUpdate">
<input type="hidden" name="u_id" value="${mdto.u_id}">
아이디: ${mdto.u_id}<br><br>

이름: <input type="text" name="u_name" value="${mdto.u_name}"/><br><br>

비밀번호: <input type="text" name="u_pw" value="${mdto.u_pw}"/><br><br>

전화번호:<input type="text" name="u_phone" value="${mdto.u_phone}"/><br><br>

이메일:<input type="text" name="u_email" value="${mdto.u_email}"/><br><br>

<div id="test">
<input type="submit" value="수정" class="btn btn-primary">
</div>
</form>
</div>
</body>
</html>