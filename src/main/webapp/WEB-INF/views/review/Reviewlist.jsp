<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.dto.ReviewDTO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="assets/css/font.css">
<link rel="stylesheet" type="text/css" href="assets/css/default.css">
<style type="text/css">

	select {
 		 font-size: 20px;
  }
  
	form {
 		 padding-left: 300px;
 		 padding-right: 600px;
 		 color: black;
 		 font-size: 20px;
  }
  
	.hotellist{
 		 font-size : 20px;
 		 text-align: center;
  }

	.title {
		font-weight: 900;
		font-size : 25px;
	}
	.content {
		font-weigth: 400;
		font-size: 20px;
	}
	.sort {
		padding-top: 30px;
	}
/*   summary {
    cursor: pointer;
  }

  summary {
    list-style: none;
  }
  summary::-webkit-details-marker {
    display: none;
  } */
  
</style>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String hotelname = (String)session.getAttribute("hotelname");
String checkin = (String)session.getAttribute("checkin");
String checkout = (String)session.getAttribute("checkout");
String guest = (String)session.getAttribute("guest");
String location = (String)session.getAttribute("location");
%>
<form class="sort">
<select class="selectsort">
	<option value="최신순">최신순</option>
	<option value="오래된순">오래된순</option>
</select>
<input class="btn btn-primary" type="submit" value="변경">

</form>

<form class="review">
<c:forEach var="list" items="${reviewlist}">

<c:choose>
	<c:when test="${list.groupnum eq 0 and list.rating eq 1}">
		${list.u_id}님 : <img src='images/review/1star.png'/> ${list.rating}점 ${list.writedate}
	</c:when>
	<c:when test="${list.groupnum eq 0 and list.rating eq 2}">
		${list.u_id}님 : <img src='images/review/2star.png'/> ${list.rating}점 ${list.writedate}
	</c:when>
	<c:when test="${list.groupnum eq 0 and list.rating eq 3}">
		${list.u_id}님 : <img src='images/review/3star.png'/> ${list.rating}점 ${list.writedate}
	</c:when>
	<c:when test="${list.groupnum eq 0 and list.rating eq 4}">
		${list.u_id}님 : <img src='images/review/4star.png'/> ${list.rating}점 ${list.writedate}
	</c:when>
	<c:when test="${list.groupnum eq 0 and list.rating eq 5}">
		${list.u_id}님 : <img src='images/review/5star.png'/> ${list.rating}점 ${list.writedate}
	</c:when>
</c:choose>
<c:choose>	
	<c:when test="${list.groupnum eq 1}">
		&nbsp;&nbsp;&nbsp;<img src='images/board/reply_icon.gif'/> ${list.u_id}님 : ${list.writedate}
	</c:when>
</c:choose>

<c:if test="${u_id1 eq list.u_id and list.groupnum eq 0}">
	<input class="btn btn-primary update" type="button" value="수정" onclick="reviewUpdate(${list.num})"> <input class="btn btn-primary delete" type="button" value="삭제" onclick="reviewDelete(event, ${list.origin})"><br>
</c:if>

<c:choose>
	<c:when test="${admin eq 1 and list.groupnum eq 0}">
		<input class="btn btn-primary answer" type="button" value="답글" onclick="reviewAnswer(${list.num})"> <input class="btn btn-primary adminDelete" type="button" value="삭제" onclick="reviewAdminDelete(event, ${list.num})"><br>
	</c:when>
	
	<c:when test="${admin eq 1 and list.groupnum eq 1}">
		<input class="btn btn-primary update" type="button" value="수정" onclick="reviewAdminUpdate(${list.num})"> <input class="btn btn-primary adminDelete" type="submit" value="삭제" onclick="reviewAdminDelete(event, ${list.num})"><br>
	</c:when>
</c:choose>

<c:choose>
	<c:when test="${list.groupnum eq 1}">
  			<br>&nbsp;&nbsp;&nbsp;<span class="title">${list.title}</span><br>
 			&nbsp;&nbsp;&nbsp;<span class="content">${list.content}</span><br>
 			<hr><br>
	</c:when>
	<c:when test="${empty list.review_img}">
  			<p class="title">${list.title}</p><br>
 			<p class="content">${list.content}</p><br>
 			<hr><br>
	</c:when>
	
	<c:when test="${!empty list.review_img}">
  			<p class="title">${list.title}</p> 
  			<img src="c://upload/${list.review_img}">
 			<p class="content">${list.content}</p><br>
 			<hr><br>
	</c:when>
</c:choose>
	
</c:forEach>	
</form>		

<p class="hotellist"><a href="HotelSearch?checkin=<%=checkin%>&checkout=<%=checkout%>&guest=<%=guest%>&location=<%=location%>">호텔 리스트로 돌아가기</a></p>
<script type="text/javascript">
$(function () {
	$(".selectsort").change(function () {
		console.log($(this).val());
		if($(this).val() == "최신순"){
			$(".sort").attr("action","loginCheck/ReviewNew?hotelname=<%=hotelname%>");
		}
		else if($(this).val() == "오래된순"){
			$(".sort").attr("action","loginCheck/ReviewOrder?hotelname=<%=hotelname%>");
		}
		})
	})
function reviewUpdate(num) {
	var url= "loginCheck/ReviewUpdate?hotelname=<%=hotelname%>&num="+num;
	var name = "popup test"
	var option = "width = 500, height=500, top=100,left=200";
	window.open(url, name, option);
}
function reviewAdminUpdate(num) {
	var url= "loginCheck/ReviewAdminUpdate?hotelname=<%=hotelname%>&num="+num;
	var name = "popup test"
	var option = "width = 500, height=500, top=100,left=200";
	window.open(url, name, option);
}	
function reviewAnswer(num) {
	var url= "loginCheck/ReviewAnswer?hotelname=<%=hotelname%>&num="+num;
	var name = "popup test"
	var option = "width = 500, height=500, top=100,left=200";
	window.open(url, name, option);
}	
function reviewDelete(e, origin) {
	e.preventDefault();
	location.href="loginCheck/loginCheck/ReviewDelete?origin="+origin;
	alert("리뷰가 삭제 되었습니다");
}
function reviewAdminDelete(e, num) {
	e.preventDefault();
	location.href="loginCheck/loginCheck/ReviewAdminDelete?num="+num;
	alert("리뷰가 삭제 되었습니다");
}
</script>


</body>
</html>