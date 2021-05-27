<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.dto.ReviewDTO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<style type="text/css">
  summary {
    cursor: pointer;
  }

  summary {
    list-style: none;
  }
  summary::-webkit-details-marker {
    display: none;
  }
  
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
<input type="submit" value="변경">
</form>

<form class="review">
<c:forEach var="list" items="${reviewlist}">
<c:if test="${list.groupnum == 1}">
<img src='images/board/reply_icon.gif' />
</c:if>

<c:choose>
	<c:when test="${list.groupnum eq 0}">
		${list.u_id} : ${list.rating} ${list.writedate}
	</c:when>
	<c:when test="${list.groupnum eq 1}">
		${list.u_id} : ${list.writedate}
	</c:when>
</c:choose>

<c:if test="${u_id1 eq list.u_id and list.groupnum eq 0}">
<input class="update" type="button" value="수정"> <input type="button" value="삭제" onclick="reviewDelete(event, ${list.origin})">
</c:if>

<c:choose>
	<c:when test="${admin eq 1 and list.groupnum eq 0}">
		<input class="answer" type="button" value="답글"> <input type="button" value="삭제" onclick="reviewAdminDelete(event, ${list.num})">
	</c:when>
	
	<c:when test="${admin eq 1 and list.groupnum eq 1}">
		<input class="update" type="button" value="수정"> <input type="submit" value="삭제" onclick="loginCheck/ReviewAdminDelete=${reviewlisst.num}">
	</c:when>
</c:choose>
<details>
  <summary>${list.title}</summary> 
  <img src="reviewFolder/${list.review_img}">
  <p>${list.content}</p>
</details><br>	
</c:forEach>	
</form>		
<a href="ReviewWriteUI?hotelname=<%=hotelname%>">작성</a>
<a href="HotelSearch?checkin=<%=checkin%>&checkout=<%=checkout%>&guest=<%=guest%>&location=<%=location%>">호텔 리스트로 돌아가기</a>
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
		$(".update").click(function () {
			location.href="ReviewUpdateServlet";
	})
})
function reviewDelete(e, origin) {
	e.preventDefault();
	location.href="loginCheck/ReviewDelete?origin="+origin;
	alert("리뷰가 삭제 되었습니다");
}
function reviewAdminDelete(e, num) {
	e.preventDefault();
	location.href="loginCheck/ReviewAdminDelete?num="+num;
	alert("리뷰가 삭제 되었습니다");
}
</script>


</body>
</html>