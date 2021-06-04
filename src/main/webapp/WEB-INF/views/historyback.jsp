<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<c:choose>
<c:when test="${!empty MaxGuest}">
<script>
alert('예약 인원수가 객실 최대 인원수 보다 많습니다.');
<%
session.removeAttribute("MaxGuest");
%>
history.go(-1);
</script>
</c:when>
<c:otherwise>
<script>
alert('이미 예약된 방입니다.');
history.go(-1);
</script>
</c:otherwise>
</c:choose>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
</body>
</html>