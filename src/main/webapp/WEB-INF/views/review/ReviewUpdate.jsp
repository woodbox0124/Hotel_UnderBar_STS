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
<script type="text/javascript">
   $(function() {
      $('#star').barrating({
        theme: 'fontawesome-stars'
      });
   });
</script>

</head>
<body>
<%
int num =Integer.parseInt(request.getParameter("num"));
String hotelname = request.getParameter("hotelname");
%>
<form action="ReviewUpdateUp" method="POST" enctype="multipart/form-data" >
<input type="hidden" id="num" name="num" value="<%=num%>">
<input type="hidden" id="hotelname" name="hotelname" value="<%=hotelname%>">

<select id="star" name="star">
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5">5</option>
</select>

작성자(ID): <input type="text" name="u_id" id="u_id" value="${login.u_name}"><br>
제목: <input type="text" name="title" id="title"><br>
내용: <input type="text" name="content" id="content"><br>
<input type="file" name="theFile" id="theFile">
<input type="reset" value="초기화">
<input type="submit" value="업로드"/>
</form>
</body>
</html>