<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dto.HotelDTO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HotelUnderbar</title>
</head>
<body>
	<jsp:include page="common/menu.jsp" flush="true"></jsp:include><br>
	<jsp:include page="hotel/hotelList.jsp" flush="true" ></jsp:include>
	<jsp:include page="hotel/footer.jsp" flush="true"></jsp:include>
	<jsp:include page="common/sidemenu.jsp" flush="true"></jsp:include>
</body>
</html>