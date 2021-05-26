<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	 $("#member").click(function() {
		$("jsp:include").attr("page","member.jsp");		
	 });	
	 $("#hotel").click(function(){
		 $("jsp:include").attr("page","hotel.jsp");		
		}); 
	 $("#room").click(function(){
		 $("jsp:include").attr("page","room.jsp");	
		}); 
});
</script>
    <!-- admin css -->
    <style type="text/css">
    .adminmenu{
    width: 100%;
    margin: 0 auto;
    text-align: center;
    }
    .menubar3{
    font-size: 40px
    }
    </style>
    <!-- admin css 끝 -->
    </head>
<div class="adminmenu">
<a href="loginCheck/adminMember" id="member" class="menubar3" style="color: blue; text-decoration: none;" >회원관리</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="loginCheck/adminHotel" id="hotel" class="menubar3" style="color: blue; text-decoration: none;" >호텔관리</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="loginCheck/adminRoom" id="room" class="menubar3" style="color: blue; text-decoration: none;" >방관리</a>
<jsp:include page="member.jsp" flush="true"></jsp:include>
</div>