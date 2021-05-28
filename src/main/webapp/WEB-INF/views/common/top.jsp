<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style type="text/css">
a {
	text-decoration: none
}
#menubar{
	font-size: 16px; 
	font-weight: bolder;
}
</style>
<div id = "menubar">
<c:if test="${!empty login}">
	<a class="menubar1" style="color: black; text-decoration: none;">${login.u_id}님 어서오세요.</a> <a href="loginCheck/logout" class="a" style="color: blue; text-decoration: none;" >&nbsp;로그아웃</a>
	<a href="loginCheck/myPage" class="menubar2" style="color: blue; text-decoration: none;" >&nbsp;마이페이지&nbsp;</a>
	<a href="loginCheck/resvMy" class="menubar2" style="color: blue; text-decoration: none;" >예약 확인</a>
	<a href="loginCheck/boardList" class="menubar2" style="color: blue; text-decoration: none;" >Q&A게시판</a>
	<a href="notification" style="color: blue; text-decoration: none;" >공지사항/이벤트</a>
<c:if test="${login.admin==1}">		
	<a href="loginCheck/adminMember" class="menubar2" style="color: blue; text-decoration: none;" >관리자게시판</a>
</c:if>	
</c:if>
<c:if test="${empty login}">
	<a href="login_register" class="a" id="a_login" style="color: blue; text-decoration: none;" >로그인·회원가입</a>
	<a href="notification" style="color: blue; text-decoration: none;" >공지사항/이벤트</a>
</c:if>
</div>