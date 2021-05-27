<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<c:if test="${!empty mesg }">
	<script>
		alert("${mesg}");
		 window.close();
	</script>
	<%
		session.removeAttribute("mesg");
	%>
</c:if>

</head>
<body>

</body>
</html>