<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.dto.NotiPageDTO" %>
<%@ page import="com.dto.EventPageDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항보기</title>


<!--부트스트랩  -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" 
rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
 crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
 integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" 
 crossorigin="anonymous"></script>
 
 <!-- noti 전용 css -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/notification.css">
<style>
	#write{
		text-align:right;
		margin-top:20px;
	}
	a{
		text-decoration:none;
		color:black;
	}
	a:hover {
		text-decoration:underline;
	}
	.card{
		width:280px;
		float:left;
		margin:10px;
	}
	
	.outer::after{
		content:"";
		display:block;
		clear:both;
	}
	#paging{
		text-align:center;
	}
	#change{
		text-align:right;
		padding-right:10px;
	}
	#wrapper{
		height : 850px;
	}
	
		
</style>

</head>
<body>
<div id="wrapper">
	<ul class="nav nav-tabs" id="myTab" role="tablist">
	  <li class="nav-item" role="presentation">
	    <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home" type="button" role="tab" aria-controls="home" aria-selected="true">공지사항</button>
	  </li>
	  <li class="nav-item" role="presentation">
	    <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile" type="button" role="tab" aria-controls="profile" aria-selected="false">FAQ</button>
	  </li>
	</ul>
<!--  탭별 내용  -->
	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
			<table id="board" class="table">
				<tr id = "column">
					<td width="50">번호</td>
					<td width="220">제 목</td>
					<td width="100">작성자</td>
					<td width="100">작성일</td>
					<td width="50">조회수</td>
				</tr>
				<c:forEach var = "item" items="${pDTO.list }">
					<tr>
						<td class="normal">${item.num }</td>
						<td align="left">
							<a class="tablename" href="notiCheck?num=${item.num}"><span class="badge rounded-pill bg-info text-dark">Info</span>  ${item.subject }</a>
						</td>
						<td class="normal">${item.author }</td>
						<td class="normal">${item.regdate }</td>
						<td class="normal">${item.hit }</td>
					</tr>
				</c:forEach>
				
			</table>
			<div style="text-align: center;">
			
			<% 
			NotiPageDTO pDTO = (NotiPageDTO)request.getAttribute("pDTO");
			int curPage = pDTO.getCurPage();//현재페이지
			int perPage = pDTO.getPerPage();//페이지당 게시물수 
			int totalCount = pDTO.getTotalCount();//전체 레코드 수
			int totalPage = totalCount/perPage;  //필요한 페이지 
			if(totalCount%perPage!=0) totalPage++;
			System.out.println("totalPage"+totalPage);
			for(int i=1; i<= totalPage; i++){
			System.out.println("i"+i);
				
				if(i == curPage){
					out.print(i+"&nbsp;");
				}else{
					out.print("<a href='notification?curPage="+i+"'>"+i+"</a>&nbsp;");
				}
			}
			%>
			</div>
			<c:if test="${login.u_id eq 'admin'}">
				<div id="write">
					<a class="btn btn-outline-secondary" href="loginCheck/notiWrite">글쓰기</a>	
				</div>
			</c:if>
		</div>
	
	
	
		<!-- FAQ -->
		<div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
		<c:forEach var="fDTO" items="${fList}">
			<div class="accordion" id="accordionExample">
	  			<div class="accordion-item">
		    		<h2 class="accordion-header" id="headingOne">
			      		<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#${fDTO.code}" aria-expanded="false" aria-controls="collapseOne">
			       			${fDTO.subject }
			      		</button>
		    		</h2>
		    		<div id="${fDTO.code}" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
		      			<div class="accordion-body">
		       				${fDTO.content }
						</div>
						<div id="change"><a href="loginCheck/faq_del?code=${fDTO.code}">삭제</a></div>
		   			</div>
				</div>
	  		</div>
	  	</c:forEach>
			<div style="text-align: center;">
					  
			<c:if test="${login.u_id eq 'admin'}">
					<div id="write">
						<a class="btn btn-outline-secondary" href="loginCheck/faqWrite">글쓰기</a>	
					</div>
			</c:if>
		
			</div>
		</div>
	</div><!-- end myContent  -->
</div><!-- end wrapper -->
</body>
</html>