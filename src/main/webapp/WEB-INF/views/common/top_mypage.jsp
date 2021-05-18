<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="assets/css/font.css" />
<style type="text/css">
#menu_bar {
	font-family: 'twayair', 'Roboto', 'sans-serif' !important;
	color: black;
	font-weight: bold;
	position: relative;
	left: 1px;
	font-size: 17px;
}

a {
	text-decoration: none
}


</style>
<%
   MemberDTO dto =(MemberDTO)session.getAttribute("login");

   if(dto != null){
		 String u_id = dto.getU_id();
%>
<div id=menu_bar>
	<a class="a"><%= u_id %>님 어서오세요.</a> <a href="logout" class="a">&nbsp;로그아웃</a> 
	<a href="resvMy" class="a">&nbsp;예약 확인</a>
	<a href="loginCheck/boardList" class="a">Q&A게시판</a>
	<%
   }else{
%>
	<a href="login_register.jsp" class="a" id="a_login">로그인&nbsp;회원가입</a>


	<%
   }//end if~else
%>
</div>