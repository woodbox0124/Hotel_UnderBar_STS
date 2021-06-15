<%@page import="com.dto.ResvPageDTO"%>
<%@page import="com.dto.HotelDTO"%>
<%@page import="com.dto.PageDTO"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.dto.ResvMyDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.dto.ResvDTO"%>
<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>

<html>
<head>

<!-- 글꼴 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/font.css">
<!-- <link rel="preconnect" href="https://fonts.gstatic.com"> -->
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap"
	rel="stylesheet">
<!-- 글꼴 -->

<!-- resvMy css 시작 -->
 <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/default.css"> 
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/vendor/animate/animate.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/vendor/select2/select2.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/vendor/perfect-scrollbar/perfect-scrollbar.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/resvmy_css/util.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/resvmy_css/main.css">
<!-- resvMy css 끝 -->

<!-- 부트스트랩 css 시작 -->
 <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/bootstrap-4.1.2/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/vendor/bootstrap/css/bootstrap.min.css">

<!-- 부트스트랩 css 끝 -->

<!-- jQuery 시작 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$(".cancel").click(function() {
			var seq = $(this).attr("data-xxx");
			if (confirm("취소하시겠습니까?")) {
				alert("취소되었습니다.");
				location.href = "loginCheck/resvCancel?seq=" + seq;
			}
			$("#form").attr("action", "loginCheck/resvMy");
			$("#form").submit();
		})
		$(".write").click(function(){ //리뷰쓰기 팝업창구현
		 var hotelname = $(this).attr("data-yyy");
		 console.log(hotelname);
		 var url= "ReviewWrite?hotelname="+hotelname;
				var name = "popup test"
				var option = "width = 500, height=600, top=100,left=200";
				window.open(url, name, option);
			})
		$(".checkout").click(function(){ //체크아웃
		 var seq = $(this).attr("data-zzz");
		 if (confirm("체크아웃 하시겠습니까?")) {
				alert("체크아웃 되셨습니다. 이용해주셔서 감사합니다.");
				location.href = "loginCheck/checkout?seq=" + seq;
			}
			$("#form").attr("action", "loginCheck/resvMy");
			$("#form").submit();
		})
	});
	
	window.onload = function() {
		document.body.classList.remove('is-preload');
	}
	window.ontouchmove = function() {
		return false;
	}
	window.onorientationchange = function() {
		document.body.scrollTop = 0;
	}
</script>
<!-- jQuery 끝-->

<title>예약확인</title>
<meta charset="utf-8">

<!-- alert mesg 시작 -->
<c:if test="${!empty mesg }">
	<script>
		alert("${mesg}");
	</script>
	<%
		session.removeAttribute("mesg");
	%>
</c:if>
<!-- alert mesg 끝 -->
</head>


<body>
	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100 ver1 m-b-110">
					<div class="table100-head">
						<div class="username">${login.u_name}님의예약정보</div>
						<table>
							<!-- <thead> -->
							<tr class="row100 head">
								<th class="cell100 column1">예약번호</th>
								<th class="cell100 column2">평점</th>
								<th class="cell100 column3">호텔이름</th>
								<th class="cell100 column4">룸 종류</th>
								<th class="cell100 column5">체크인 날짜</th>
								<th class="cell100 column6">체크아웃 날짜</th>
								<th class="cell100 column7">예약날짜</th>
								<th class="cell100 column8">인원 수</th>
								<th class="cell100 column9">가격</th>
								<th class="cell100 column10">취소</th>
								<th class="cell100 column11">리뷰쓰기</th>
							</tr>


							<tr class="row100 body" class="table100-body js-pscroll">
								<%
										ResvPageDTO RpDTO = (ResvPageDTO) request.getAttribute("RpDTO");

										List<ResvMyDTO> list = RpDTO.getList();
										for (int i = 0; i < list.size(); i++) {

											ResvMyDTO dto = list.get(i);

											int seq = dto.getSeq();
											double rating = dto.getRating();
											String hotelname = dto.getHotelname();
											String roomname = dto.getRoomname();
											String checkin = dto.getCheckin();
											String checkout = dto.getCheckout();
											String resvdate = dto.getResvdate();
											int guest = dto.getGuest();
											int price = dto.getPrice();
											int checkresv = dto.getCheckresv();
									%>

								<td class="cell100 column1"><%=seq%></td>
								<td class="cell100 column2"><%=rating%></td>
								<td class="cell100 column3"><%=hotelname%></td>
								<td class="cell100 column4"><%=roomname%></td>
								<td class="cell100 column5"><%=checkin%></td>
								<td class="cell100 column6"><%=checkout%></td>
								<td class="cell100 column7"><%=resvdate%></td>
								<td class="cell100 column8"><%=guest%></td>
								<td class="cell100 column9"><%=price%></td>

								<td>
									<button type="button" class="btn btn-outline-primary cancel"
										style="margin-bottom: 15px" data-xxx="<%=seq%>">취소</button>
								</td>
								<td>
									<%
								if(checkresv == 0){
								%>
								<button type="button"  class="btn btn-outline-primary checkout"
										style="margin-bottom: 15px" data-zzz="<%=seq%>">체크아웃</button>
								<%
								}else{
								%>
								<button type="button"  class="btn btn-outline-primary write"
								style="margin-bottom: 15px" data-yyy="<%=hotelname%>">리뷰쓰기</button>
								<% 
								}
								%>
								</td>
							</tr>

							<%
		}
	%>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>




	<div class="page" style="text-align: center;">
		<%
			String u_id = (String) request.getAttribute("u_id");
			int curPage = RpDTO.getCurPage();//현재페이지
			int perPage = RpDTO.getPerPage();//페이지당 게시물수
			int totalCount = RpDTO.getTotalCount();//전체 레코드수 
			int totalPage = totalCount / perPage;// 필요한 페이지
			System.out.println(curPage);
			System.out.println(perPage);
			System.out.println(totalCount);
			System.out.println(totalPage);

			if (totalCount % perPage != 0)
				totalPage++;
			for (int i = 1; i <= totalPage; i++) {
				if (i == curPage) {
					System.out.print("if i" + i);
					out.print(i + "&nbsp;");
				} else {
					System.out.print("else i" + i);
					out.print("<a href='loginCheck/resvMy?curPage=" + i + "&u_id=" + u_id + "'>" + i + "</a>&nbsp;");
				} //end for		
			}
		%>

	</div>
<jsp:include page="../common/sidemenu.jsp" flush="true"></jsp:include>
</body>
</html>