<%@page import="java.io.Console"%>
<%@ page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약</title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="assets/css/default.css">
<link rel="stylesheet" type="text/css" href="assets/css/font.css">
<style type="text/css">
img {
	width: 100%;
	height: 100%;
}

</style>
<%
String location = (String)session.getAttribute("location");
String checkin = (String)session.getAttribute("checkin");
String checkout = (String)session.getAttribute("checkout");
String guest = (String)session.getAttribute("guest");

MemberDTO dto = (MemberDTO) session.getAttribute("login");
String u_id = dto.getU_id();
String phone = dto.getU_phone();
String name = dto.getU_name();

%>
<style type="text/css">
*{
font-family: 'twayair';
}
body{
 background-size: auto;
}

.room {
	margin-left: 8%;
	font-size : 30px;
}
.number {
	width: 60%;
	font-size: 30px;
	width: 200px;
}
.date {
	font-size: 20px;
}
.btn {
	
	text-align :center;
	position: relative;
	font-size: 50px;
	font-weight: 500;
	border-radius: 10px;
	margin-left: 40%;
	margin-bottom: 10px;
	font-family: 'twayair';
}

.hotelseq {
	font-size: 50px;
}

form{
	margin-bottom: 120px;
}

.list-caption {
	top : 100px;
	background-color: white;
	margin-left: 300px;
	margin-right: 300px;
	position: relative;
	color: black;
}

.main {
 	margin-bottom:500px;
 	color : white;
 	text-decoration-line: none;
}
</style>

</head>
<body>
	<form action="loginCheck/KakaoPay">
	
	<!-- kakao pay 값 전달을 위한 코드 -->
	<input type="hidden" name="roomseq" value="${roomseq}">
	<input type="hidden" name="location" value="<%=location%>">
	<input type="hidden" name="hotelseq" value="${hotelseq}">
	<input type="hidden" name="guest" value="<%=guest%>">
	<input type="hidden" name="checkin" value="<%=checkin%>">
	<input type="hidden" name="checkout" value="<%=checkout%>">
	<input type="hidden" name="u_id" value="<%=u_id%>">
	<input type="hidden" name="name" value="<%=name%>">
	<input type="hidden" name="phone" value="<%=phone%>">
	<input type="hidden" name="price" value="${price}">
		<div class="list-caption">
		<p class="hotelseq" style="text-align: center;">
				호텔이름 : ${hotelname}</p> <br>
				
			<div class="room">
				객실 이름 : <%=name%><br>
				인원수 : <%=guest %><br> 
				<p class="date"><%=checkin %>
					~
					<%=checkout %></p><br> 
					
				예약자 이름 : <%=u_id %><br> 
				예약자 전화번호 : <input class="number" type="text"
					value="<%=phone%>"> <br><br>
					요금 :  ${price}<br>
			</div>
			<input class="btn btn-primary input_btn" type="submit"
							value="결제하기"/>
		</div>

	
	</form>

</body>
</html>