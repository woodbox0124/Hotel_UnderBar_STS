<%@page import="javafx.scene.control.Alert"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.dto.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<%@page import="com.dto.MemberDTO"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="assets/css/font.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
	crossorigin="anonymous"></script>
	
	<style type="text/css">
* {
	font-family: 'twayair', 'Roboto', 'sans-serif' !important;
}
th, td{
padding-top: 5px;
padding-bottom: 5px;
border: 1px solid #444444;
border-collapse: collapse;
}

table {
    width: 100%;
    border: 1px solid #444444;
  }
  
#table{
width: 505px;
}

#title{
margin-top: 20px;
margin-bottom: 10px;
}

</style>

<meta charset="UTF-8">
<title>Q&#38;A 게시판</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
function updateBoard(e, f) {
	e.preventDefault();//주의 preventDefault() 해야 함 
	f.action="BoardUpdateServlet"; //action지정
	f.method="post";
	f.submit();	//submit 이벤트반드시 해야함
} 
function deleteBoard(e, num) {
	//f.action="BoardDeleteServlet?num="+num;
	//f.submit();	
	e.preventDefault();//주의 preventDefault() 해야 함 
	location.href="BoardDeleteSerlvet?num="+num;
}

</script>
</head>
<body>
<div class="container">
<%
BoardDTO dto=(BoardDTO)session.getAttribute("retrieve");
int num=dto.getNum();
String title=dto.getTitle();
String author=dto.getAuthor();
String content=dto.getContent();
String writeday=dto.getWriteday();
int readCnt=dto.getReadCnt();
%>
<% 
        MemberDTO dto3 = (MemberDTO) session.getAttribute("login");
		String name1=dto3.getU_id(); //로그인한 사람 아이디
		String name2=dto.getAuthor(); //쓴사람 아이디
		System.out.println("로그인한 사람 아이디"+name1);
		System.out.println("쓴사람 아이디"+name2);
		%>
		
		<%String originauthor=(String)session.getAttribute("originauthor"); %>

<h3 id="title" style="text-align: center;">게시글 보기</h3>
<form name="myForm">
<input type="hidden" name="num" value="<%=num%>">
<table id="table" style="margin: 0 auto" border="1">
		<tr>
			<td width="70" align="center">작성자</td>
			<td><input type="text" size="45" value="<%=author %>" name="author"></td>
		</tr>
		<tr>
			<td width="70" align="center">글제목</td>
			<td><input type="text" size="45" value="<%=title %>" name="title"></td>
		</tr>
		<tr>
			<td width="70" align="center">글내용</td>
			<td >
			<textarea style="margin-left: 1px; width: 425px; height: 500px; resize: none;" rows="10" cols="30" name="content"><%=content%></textarea>
			</td>
		</tr>
			<tr>
			<td colspan="2" align="center">
			<%if (name1.equals(name2)) {%>
			<input class="btn btn-primary" type="button" value="수정" onclick="updateBoard(event, myForm)">
             <input class="btn btn-primary" type="button" value="삭제" onclick="deleteBoard(event, <%=num%>)">
             <%} %>
			</td>
		</tr>
	</table>
	</form>
	
	<br>
	<div style="text-align: center;">
	<a class="btn btn-primary" href="BoardListServlet">목록보기</a>
	<% if(name1.equals("admin") || name1.equals(originauthor))  {%> <!-- 이름이 관리자인경우만 답글보이거나 글의 원글을 쓴 userid와 같은 userid를 가지고있으면 답글을 쓸수있게-->
    <a class="btn btn-primary" href="boardAnswer.jsp">답글쓰기</a>
   <%} %>
    </div>
	</div>
	
</body>
</html>