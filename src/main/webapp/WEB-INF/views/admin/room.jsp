<%@page import="com.dto.AdminRoomPageDTO"%>
<%@page import="com.dto.AdminHotelPageDTO"%>
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
<title>관리자 객실 관리</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
$(".delete").click(function() {
	console.log("delete 클릭 ");
	var seq= $(this).attr("data-seq");//삭제할 객실 번호
	var xxx= $(this); //td태그 
	$.ajax({
		url: "loginCheck/RoomDelete",
		type:"get",
		dataType:"text",
		data: {
			seq:seq
		},
		success: function(data, status, xhr) {
			console.log("success");
			
			xxx.parents().filter("tr").remove();
			var con_test = confirm("객실을 삭제하시겠습니까?");  
	          if(con_test == true){
	        	  alert("객실이 삭제되었습니다.");
	          }
		},
		error: function(xhr, status, error) {
			console.log(error);
		}			
	});//end ajax
});//end delete

$(".update").click(function(){
	 console.log("수정버튼 클릭");
	 var seq= $(this).attr("data-seq");
	 console.log(seq);
	 var url= "loginCheck/roomSelect?seq="+seq;
			var name = "popup test"
			var option = "width = 550, height=450";
			window.open(url, name, option);
		}); //end update
})
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
	vertical-align: middle;
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
 	width: 113%;
 	margin-right: 10px;
 	
 }
 
 #bar{
	font-size: 15px;
	width: 300px;
}
</style>
</head>
<body>
 <div class="container">
	<h1 id="title" align="center" style="font-weight: bold; font-size: 40px;">객실 관리</h1>
	<table id="search" class="table">
		<tr>
		 <td colspan="7">
		  <form action="loginCheck/adminRoom" class="searchbar">
		   <select name="searchName">
		    <option value="hotelname">호텔 이름</option>
		    <option value="roomname">객실 이름</option>
		   </select>
		    <input type="text" id="bar" name="searchValue">
		    <input type="submit" class="btn btn-primary" value="검색">
		 </form>  
		 </td> 
		</tr>
		<tr id = "ab">
			<td style="color: white;" width="50">객실 번호</td>
			<td style="color: white;" width="100">호텔 이름</td>
			<td style="color: white;" width="100">객실 이름</td>
			<td style="color: white;" width="70">가격</td>
			<td style="color: white;" width="70">최대 인원 수</td>
			<td style="color: white;" width="80">객실 이미지 </td>
			<td style="color: white;" width="80">수정</td>
			<td style="color: white;" width="80">삭제</td>
		</tr>
		 <c:if test="${!empty arpDTO}">
		 <c:forEach var ="item" items="${arpDTO.list}">			
		<tbody>
		<tr class="tr">
			<td class="normal">${item.seq}</td>
			<td class="normal">${item.hotelname}</td>
			<td class="normal">${item.roomname}</td>
			<td class="normal">${item.price}원</td>
			<td class="normal">${item.max_guest}</td>
			<td class="normal"><img src="images/room/${item.room_img}.jpg" width="150" height="150"></td>
			<td class="normal"><input type="button" value="수정" class="btn btn-primary update"  data-seq="${item.seq}"></td>
			<td class="normal"><input type="button" value="삭제" class="btn btn-primary delete"  data-seq="${item.seq}"></td>			
		</tr>			
		</tbody>
		</c:forEach>
	</c:if>
	</table>
		<div style="text-align: center;">
		 <%
				AdminRoomPageDTO ahpDTO = (AdminRoomPageDTO)session.getAttribute("arpDTO");
			   String searchName = (String)session.getAttribute("searchName");
				String searchValue = (String)session.getAttribute("searchValue");
			        int curPage = ahpDTO.getCurPage();//현재페이지
			        int perPage = ahpDTO.getPerPage();//페이지당 게시물수 
					int totalCount = ahpDTO.getTotalCount();//전체 레코드 수
					int totalPage = totalCount/perPage;  //필요한 페이지 
					if(totalCount%perPage!=0) totalPage++;
			        for(int i=1; i<= totalPage; i++){
			          	if(i== curPage){
			          		out.print(i+"&nbsp;");
			          	}else{
	out.print("<a href='loginCheck/adminRoom?curPage="+i+"&searchName="+searchName+"&searchValue="+searchValue+"'>"+i+"</a>&nbsp;"); 		          	}
			        }//end for
			   %>
			   </div><br>
</div> 
</body>
</html>
