<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--부트스트랩  -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
<style>
	body{
		width:100%;
	}
	#wrapper{
		width:1200px;
		height:750px;
		margin:0 auto;
		padding-top:40px;
		position:relative;
	}
	.searchbar{
		text-align:right;
		padding:10px 0;
	}
	table {
		text-align:center;
	}
	#board > tr:first-child{
		height:75.33px;
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
	  <li class="nav-item" role="presentation">
	    <button class="nav-link" id="contact-tab" data-bs-toggle="tab" data-bs-target="#contact" type="button" role="tab" aria-controls="contact" aria-selected="false">이벤트</button>
	  </li>
	</ul>
<!--  탭별 내용  -->
<div class="tab-content" id="myTabContent">
	<div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
		<table id="board" class="table">
			<tr>
	 			<td colspan="5">
	  				<form action="loginCheck/boardList" class="searchbar">
	   					<select name="searchName">
							<option value="title">제목</option>
							<option value="author">작성자</option>
	  					</select>
					    <input type="text" id="bar" name="searchValue">
					    <input type="submit"  class="btn btn-outline-secondary" value="검색">
	 				</form>  
	 			</td> 
			</tr>
			<tr id = "column">
				<td width="50">번호</td>
				<td width="220">제 목</td>
				<td width="100">작성자</td>
				<td width="100">작성일</td>
				<td width="50">조회수</td>
			</tr>
			<tr>
				<td class="normal">0</td>
				<td align="left">
					<a class="tablename" href="#">0</a>
				</td>
				<td class="normal">0</td>
				<td class="normal">0</td>
				<td class="normal">0</td>
			</tr>
		</table>
		<div style="text-align: center;">
				  <%--  <%
					   String searchName = (String)request.getAttribute("searchName");
						String searchValue = (String)request.getAttribute("searchValue");
					        int curPage = pDTO.getCurPage();//현재페이지
					        int perPage = pDTO.getPerPage();//페이지당 게시물수 
							int totalCount = pDTO.getTotalCount();//전체 레코드 수
							int totalPage = totalCount/perPage;  //필요한 페이지 
							if(totalCount%perPage!=0) totalPage++;
					        for(int i=1; i<= totalPage; i++){
					          	if(i== curPage){
					          		out.print(i+"&nbsp;");
					          	}else{
			out.print("<a href='loginCheck/boardList?curPage="+i+"&searchName="+searchName+"&searchValue="+searchValue+"'>"+i+"</a>&nbsp;"); 		          	}
					        }//end for
					   %> --%>
	
		</div>
		<div id="write">
			<a class="btn btn-outline-secondary" href="loginCheck/boardWrite">글쓰기</a>	
		</div>
	</div>
		
	<div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
		<table id="board" class="table">
			<tr></tr>
			<tr id = "column">
				<td width="50">번호</td>
				<td width="220">제 목</td>
				<td width="100">작성자</td>
				<td width="100">작성일</td>
				<td width="50">조회수</td>
			</tr>
			<tr>
				<td class="normal">0</td>
				<td align="left">
					<a class="tablename" href="#">0</a>
				</td>
				<td class="normal">0</td>
				<td class="normal">0</td>
				<td class="normal">0</td>
			</tr>
		</table>
		<div style="text-align: center;">
				  <%--  <%
					   String searchName = (String)request.getAttribute("searchName");
						String searchValue = (String)request.getAttribute("searchValue");
					        int curPage = pDTO.getCurPage();//현재페이지
					        int perPage = pDTO.getPerPage();//페이지당 게시물수 
							int totalCount = pDTO.getTotalCount();//전체 레코드 수
							int totalPage = totalCount/perPage;  //필요한 페이지 
							if(totalCount%perPage!=0) totalPage++;
					        for(int i=1; i<= totalPage; i++){
					          	if(i== curPage){
					          		out.print(i+"&nbsp;");
					          	}else{
			out.print("<a href='loginCheck/boardList?curPage="+i+"&searchName="+searchName+"&searchValue="+searchValue+"'>"+i+"</a>&nbsp;"); 		          	}
					        }//end for
					   %> --%>
	
		</div>
		<div id="write">
			<a class="btn btn-outline-secondary" href="loginCheck/boardWrite">글쓰기</a>	
		</div>
	</div>
	
	<!-- 이벤트  -->
	<div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
			<table id="search" class="table">
				<tr></tr>
				<tr id = "column">
					<td width="50">번호</td>
					<td width="220">제 목</td>
					<td width="100">작성자</td>
					<td width="100">작성일</td>
					<td width="50">조회수</td>
				</tr>
				<tr>
					<td class="normal">0</td>
					<td align="left">
						<a class="tablename" href="#">0</a>
					</td>
					<td class="normal">0</td>
					<td class="normal">0</td>
					<td class="normal">0</td>
				</tr>
			</table>
			<div style="text-align: center;">
				  <%--  <%
					   String searchName = (String)request.getAttribute("searchName");
						String searchValue = (String)request.getAttribute("searchValue");
					        int curPage = pDTO.getCurPage();//현재페이지
					        int perPage = pDTO.getPerPage();//페이지당 게시물수 
							int totalCount = pDTO.getTotalCount();//전체 레코드 수
							int totalPage = totalCount/perPage;  //필요한 페이지 
							if(totalCount%perPage!=0) totalPage++;
					        for(int i=1; i<= totalPage; i++){
					          	if(i== curPage){
					          		out.print(i+"&nbsp;");
					          	}else{
			out.print("<a href='loginCheck/boardList?curPage="+i+"&searchName="+searchName+"&searchValue="+searchValue+"'>"+i+"</a>&nbsp;"); 		          	}
					        }//end for
					   %> --%>
	
			</div>
			<div id="write">
				<a class="btn btn-outline-secondary" href="loginCheck/boardWrite">글쓰기</a>	
			</div>
		</div><!-- end 이벤트 -->
	</div><!-- end mytab -->
</div><!-- end wrapper -->
</body>
</html>