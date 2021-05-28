<%@page import="java.util.List"%>
<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>회원관리</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#delete").click(function() {
		console.log("delete 클릭 ");
		var u_id= $(this).attr("data-id");//삭제할 아이디
		var xxx= $(this); //td태그 
		$.ajax({
			url: "loginCheck/delete",
			type:"get",
			dataType:"text",
			data: {
				u_id:u_id
			},
			success: function(data, status, xhr) {
				console.log("success");
				//dom삭제 
				xxx.parents().filter("tr").remove();
				alert("탈퇴 되었습니다.");
			},
			error: function(xhr, status, error) {
				console.log(error);
			}			
		});//end ajax
	});//end #delete
});//end jQuery
</script>
<style type="text/css">
* {
	font-family: 'twayair', 'Roboto', 'sans-serif' !important;
}

.listform{
	text-align: center;
	margin: 0 auto;
}
.normal{
	text-align: center;
}

#title{
margin-top: 20px;
margin-bottom: 10px;
}

#ab
{
	background-color: #0D6EFD;
	color: white;
	text-align: center;
}

#write
{
	text-align: center;
}
.container{
	height:860px;
}
#texttitle{
text-overflow: ellipsis; white-space: nowrap; max-width: 40px; /* 40px를 넘어가는 제목일 경우 "제목..."으로 표기됨 */
}

 .tablename:link { color: black; text-decoration: none;}	
 .tablename:visited { color: black; text-decoration: none;}	
 .tablename:hover { color: black; text-decoration: underline;}
 
 .searchbar
 {
 	text-align: right;
 	margin-right: 10px;
 	
 }
 
 #bar{
	font-size: 15px;
	width: 300px;
}

</style>
<% List<MemberDTO> list = (List<MemberDTO>)request.getAttribute("member");%>
</head>
<body>
<div class="container">
	<h1 id="title" align="center" style="font-weight: bold; font-size: 40px;">회원 관리</h1>
	<table id="search" class="table">
		<tr>
		 <td colspan="7">
		  <form action="loginCheck/searchName" class="searchbar">
		   <select name="searchName">
		    <option value="name">이름</option>
		    <option value="id">아이디</option>
		   </select>
		    <input type="text" id="bar" name="searchValue">
		    <input type="submit"  class="btn btn-primary" value="검색">
		 </form>  
		 </td> 
		</tr>
		<tr id = "ab">
			<td style="color: white;" width="70">이  름</td>
			<td style="color: white;" width="70">아이디</td>
			<td style="color: white;" width="70">비밀 번호</td>
			<td style="color: white;" width="70">전화번호</td>
			<td style="color: white;" width="120">이메일</td>
			<% if(list!=null){%>
			<td style="color: white;" width="50"></td>
			<td style="color: white;" width="50"></td>
			<%} %>
		</tr>
		<%		
		if(list!=null){
		System.out.println(list);
		for (MemberDTO dto : list) {
			String u_id = dto.getU_id();
			String u_pw = dto.getU_pw();
			String u_name = dto.getU_name();
			String u_phone = dto.getU_phone();
			String u_email = dto.getU_email();
		 %>				
		<tbody>
		<tr>
			<td class="normal"><%=u_name %></td>
			<td class="normal"><%=u_id %></td>
			<td class="normal"><%=u_pw %></td>
			<td class="normal"><%=u_phone %></td>
			<td class="normal"><%=u_email %></td>
			<% if(list!=null){%>
			<td class="normal"><button class="btn btn-primary">수정</button></td>
			<td class="normal"><input type="button" value="탈퇴" id="delete" class="btn btn-primary" data-id="<%=u_id %>"></td>			
			<%} %>
		</tr>			
		</tbody>
	<%}} %>
	</table>
		<div style="text-align: center;">
			   </div><br>
</div>
</body>
</html>