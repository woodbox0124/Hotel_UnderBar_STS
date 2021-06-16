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
	.card-img-top{
		width:280px;
		height:320px;
		
	}
	
	.outer::after{
		content:"";
		display:block;
		clear:both;
	}
	#paging{
		text-align:center;
	}
	.outer{
		height:930px;
	}

		
</style>

</head>
<body>
<div id="wrapper">
	<!-- 이벤트  -->
		<div class="outer">	
			<c:forEach var="eDTO" items="${epDTO.list}">
				<div class="card">
					<img class="card-img-top" src="images/uploadImg/${eDTO.eventImg }" alt="이벤트">
					<div class="card-body">
						<h5 class="card-title">${eDTO.subject }</h5>
						
						<a href="eventRetrieve?code=${eDTO.code}" class="btn btn-primary">click</a>
					</div>
					
				</div>
			</c:forEach>
		</div>
		<div style="text-align: center;">
			<% 
			EventPageDTO epDTO = (EventPageDTO)request.getAttribute("epDTO");
			int curPage = epDTO.getCurPage();//현재페이지
			int perPage = epDTO.getPerPage();//페이지당 게시물수 
			int totalCount = epDTO.getTotalCount();//전체 레코드 수
			int totalPage = totalCount/perPage;  //필요한 페이지 
			System.out.println(curPage);//2
			System.out.println(perPage);//8
			System.out.println(totalCount);//9
			System.out.println(totalPage);//1
			if(totalCount%perPage!=0) totalPage++;//2
			System.out.println("totalPage"+totalPage);
			for(int i=1; i<= totalPage; i++){
			System.out.println("i"+i);
				
				if(i == curPage){
					out.print(i+"&nbsp;");
				}else{
					out.print("<a href='event?curPage="+i+"'>"+i+"</a>&nbsp;");
				}
			}
			%>
			</div>
		<c:if test="${login.u_id eq 'admin'}">
			<div id="write">
				<a class="btn btn-outline-secondary" href="loginCheck/eventWrite">글쓰기</a>	
			</div>
		</c:if>
	</div><!-- end wrapper  -->
</body>
</html>