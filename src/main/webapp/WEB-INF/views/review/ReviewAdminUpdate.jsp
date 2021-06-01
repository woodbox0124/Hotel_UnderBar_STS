<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.dto.MemberDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
<link rel="stylesheet" href="jquery-ui-1.12.1/fontawesome-stars.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="jquery-ui-1.12.1/jquery.barrating.min.js"></script>


</head>
<body>
<%
int num = Integer.parseInt(request.getParameter("num"));
String hotelname = request.getParameter("hotelname");
%>
<form action="ReviewAdminUp" method="POST" enctype="multipart/form-data" >
<input type="hidden" id="num" name="num" value="<%=num%>">
<input type="hidden" id="hotelname" name="hotelname" value="<%=hotelname%>">

작성자(ID): <input type="text" name="u_id" id="u_id" value="${login.u_name}"><br>
제목: <input type="text" name="title" id="title"><br>
내용: <input type="text" name="content" id="content"><br>
<input type="reset" value="초기화">
<input type="submit" value="수정"/>
</form>
</body>
</html>