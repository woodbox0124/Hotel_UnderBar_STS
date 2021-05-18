
<%@page import="com.dto.PageDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dto.HotelDTO"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="assets/css/font.css">
<link rel="stylesheet" type="text/css" href="assets/css/default.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">



	$(document).ready(function(){			
		$(window).scroll(function(event) {
			if(jQuery(window).scrollTop() > jQuery(".filter").offset().top)
				{
					jQuery("#filter").css("position", "fixed");
				}
			else if((jQuery(window).scrollTop() < jQuery(".filter").offset().top))
				{
					jQuery("#filter").css("position", "static");
				}
		})
		
	})
	
</script>
<meta charset="UTF-8">

<!-- 카카오 지도 불러오기 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=39d2b4ae6de92d2bfb3a0a214f2d3d4e"></script>

<style>
* {
	font-family: 'twayair', 'Roboto', 'sans-serif' !important;
}
#container {
	overflow: hidden;
	height: 280px;
	width: 280px;
	position: relative;
	position: sticky;
}

#btnRoadview, #btnMap {
	position: absolute;
	top: 5px;
	left: 5px;
	padding: 7px 12px;
	font-size: 14px;
	border: 1px solid #dbdbdb;
	background-color: #fff;
	border-radius: 2px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, .04);
	z-index: 1;
	cursor: pointer;
}

#btnRoadview:hover, #btnMap:hover {
	background-color: #fcfcfc;
	border: 1px solid #c1c1c1;
}

#container.view_map #mapWrapper {
	z-index: 10;
}

#map {
	border-radius: 10px;
}

#container.view_map #btnMap {
	display: none;
}

#container.view_roadview #mapWrapper {
	z-index: 0;
}

#container.view_roadview #btnRoadview {
	display: none;
}

.section {
	width: 100%;
	height: 100%;
}

.wrapper {
	width: 1200px;
	height: 1000px;
	margin: 0 auto;
}


.wrapper .filter, .cards{
	float: left;
}

.section .filter {
	width: 280px;
	height: 920px;
	margin: 0 20px;
}


.section .cards {
	width: 880px;
}

.cards .card {
	width: 380px;
	height: 400px;
	margin: 30px;
	float: left;
}

img {
	width: 100%;
	height: 100%;
}

#place {
	position: absolute;
	right: 9px;
}

.card-body>.btn {
	position: absolute;
	right: 9px;
	bottom: 9px;
}

.card-body {
	height: 170px;
}

#people>a {
	display: inline-block;
	width: 20px;
	text-align: center;
	font-size: 20px;
	text-decoration: none;
}

.booking_input {
	width: 80%;
	height: 40px;
	background: rgba(255, 255, 255, 0.2);
	border: solid 2px #ccc;
	padding-left: 27px;
	font-size: 16px;
	font-weight: 700;
	color: #000000;
	outline: none;
	margin: 20px 0;
}

.input_btn {
	margin: 5px;
}

#filter {
	margin: 0 auto;
	text-align: center;
}

/* footer */
#footer {
	overflow: hidden;
	width: 100%;
	height: 100%;
}

.sns_box {
	width: 56px;
	height: 25px;
	margin: 0 auto;
}

.sns_box>a {
	width: 25px;
	display: inline-block;
	float: left;
	margin: 0 1.5px;
}

#certy {
	width: 1200px;
	margin: 0 auto;
	text-align: center;
}

.foo {
	float: left;
	margin: 20px 30px;
	text-align: left;
}

#footer_wrap {
	width: 1200px;
	height: 260px;
	margin: 0 auto;
	text-align: center;
}
.page{
	font-size:20px;
	text-align:center;
}
</style>

</head>
<%String location = (String)session.getAttribute("location"); 
String checkin = (String) session.getAttribute("checkin");
String checkout = (String) session.getAttribute("checkout");
String guest = (String)session.getAttribute("guest");%>
	<div class="section">
		<div class="wrapper">
			<div class="filter">
				<div id="filter">
					<form action="searchlocation" method="get">
						<div id="area">
							<span>지역</span> <select class="place booking_input" id="place booking_input"
								name="location">
								<option value="" <%if(location.equals("")){%>selected<%}%>>모든지역</option>
								<option value="서울" <%if(location.equals("서울")){%>selected<%}%>>서울</option>
								<option value="부산" <%if(location.equals("부산")){%>selected<%}%>>부산</option>
								<option value="제주" <%if(location.equals("제주")){%>selected<%}%>>제주</option>
							</select>
						</div>
						<div id="map">

							<div id="container" class="view_map">
								<div id="mapWrapper"
									style="width: 100%; height: 100%; position: relative;">
									<div id="map" style="width: 100%; height: 100%"></div>
									<!-- 지도를 표시할 div 입니다 -->
									<input type="button" id="btnRoadview"
										onclick="toggleMap(false)" title="로드뷰 보기" value="로드뷰">
								</div>
								<div id="rvWrapper"
									style="width: 100%; height: 100%; position: absolute; top: 0; left: 0;">
									<div id="roadview" style="height: 100%"></div>
									<!-- 로드뷰를 표시할 div 입니다 -->
									<input type="button" id="btnMap" onclick="toggleMap(true)"
										title="지도 보기" value="지도">
								</div>
								<!-- end form -->
								<script>
								var container = document.getElementById('container'), // 지도와 로드뷰를 감싸고 있는 div 입니다
							    mapWrapper = document.getElementById('mapWrapper'), // 지도를 감싸고 있는 div 입니다
							    btnRoadview = document.getElementById('btnRoadview'), // 지도 위의 로드뷰 버튼, 클릭하면 지도는 감춰지고 로드뷰가 보입니다 
							    btnMap = document.getElementById('btnMap'), // 로드뷰 위의 지도 버튼, 클릭하면 로드뷰는 감춰지고 지도가 보입니다 
							    rvContainer = document.getElementById('roadview'), // 로드뷰를 표시할 div 입니다
							    mapContainer = document.getElementById('map'); // 지도를 표시할 div 입니다

							// 지도와 로드뷰 위에 마커로 표시할 특정 장소의 좌표입니다 
							var placePosition = new kakao.maps.LatLng(37.55648107824386, 127.00616129183668);

							// 지도 옵션입니다 
							var mapOption = {
							    center: placePosition, // 지도의 중심좌표 
							    level: 3 // 지도의 확대 레벨
							};

							// 지도를 표시할 div와 지도 옵션으로 지도를 생성합니다
							var map = new kakao.maps.Map(mapContainer, mapOption);

							// 로드뷰 객체를 생성합니다 
							var roadview = new kakao.maps.Roadview(rvContainer);

							// 로드뷰의 위치를 특정 장소를 포함하는 파노라마 ID로 설정합니다
							// 로드뷰의 파노라마 ID는 Wizard를 사용하면 쉽게 얻을수 있습니다 
							roadview.setPanoId(1094586554, placePosition);

							// 특정 장소가 잘보이도록 로드뷰의 적절한 시점(ViewPoint)을 설정합니다 
							// Wizard를 사용하면 적절한 로드뷰 시점(ViewPoint)값을 쉽게 확인할 수 있습니다
							roadview.setViewpoint({
							    pan: 186.5155038417634,
							    tilt: -7.511953066537436,
							    zoom: 0
							});

							// 지도 중심을 표시할 마커를 생성하고 특정 장소 위에 표시합니다 
							var mapMarker = new kakao.maps.Marker({
							    position: placePosition,
							    map: map
							});

							// 로드뷰 초기화가 완료되면 
							kakao.maps.event.addListener(roadview, 'init', function() {

							    // 로드뷰에 특정 장소를 표시할 마커를 생성하고 로드뷰 위에 표시합니다 
							    var rvMarker = new kakao.maps.Marker({
							        position: placePosition,
							        map: roadview
							    });
							});

							// 지도와 로드뷰를 감싸고 있는 div의 class를 변경하여 지도를 숨기거나 보이게 하는 함수입니다 
							function toggleMap(active) {
							    if (active) {

							        // 지도가 보이도록 지도와 로드뷰를 감싸고 있는 div의 class를 변경합니다
							        container.className = "view_map"
							    } else {

							        // 지도가 숨겨지도록 지도와 로드뷰를 감싸고 있는 div의 class를 변경합니다
							        container.className = "view_roadview"   
							    }
							}
								</script>
							</div>
						</div>
						<input class="btn btn-primary input_btn" type="reset"
							value="reset" /> <input class="btn btn-primary input_btn"
							type="submit" value="search" />
					</form>
				</div>
			</div>

			<div class="cards">
			
				<%
				PageDTO pDTO = (PageDTO)request.getAttribute("pDTO");
				
				List<HotelDTO> list = pDTO.getList();
				
				for (int i = 0; i < list.size(); i++) {
				HotelDTO dto = list.get(i);
				
				String seq = dto.getSeq();
				String hotelname = dto.getName();
				String place = dto.getPlace();
				String addr = dto.getAddr();
				double use_count = dto.getUse_count();
				double rating = dto.getRating();
				String hotel_img = dto.getHotel_img();
				String hotel_img_real = dto.getHotel_img_real();
				%>

				<div class="card">
					<img src="images/hotel/<%= hotel_img %>.jpg" class="card-img-top"
						alt="...">
					<div class="card-body">
						<h5 class="card-title"><%= hotelname %></h5>
						<p class="card-text">
							<span style="color: red;"><%= rating %></span> <span id="place"><%= place %></span>
						</p>

						<a
							href="roomlist?hotelname=<%=hotelname%>&seq=<%=seq%>&checkin=<%=checkin%>&checkout=<%=checkout%>&place=<%=place%>"
							class="btn btn-primary">OPEN</a>
					</div>
				</div>
				<%
				}
				%>
		<div class="page">
		<%
			int curPage = pDTO.getCurPage();//현재페이지
			int perPage = pDTO.getPerPage();//페이지당 게시물수
			int totalCount = pDTO.getTotalCount();//전체 레코드수 
			int totalPage = totalCount/perPage;// 필요한 페이지
			System.out.println(curPage);
			System.out.println(perPage);
			System.out.println(totalCount);
			System.out.println(totalPage);
	
			if(totalCount%perPage!=0) totalPage++;
			for(int i = 1; i <= totalPage; i++){
				if(i==curPage){
					System.out.print("if i"+i);
					out.print(i+"&nbsp;");
				}else{
					System.out.print("else i"+i);
					out.print("<a href='HotelSearch?checkin="+checkin+"&checkout="+checkout+"&guest="+guest+"&curPage="+i+"&location="+location+"'>"+i+"</a>&nbsp;");
				}//end for		
			}
		%>
		
		</div>
			</div>
	</div><!-- end wrapper -->
</div><!-- end section -->