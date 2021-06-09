<%@page import="oracle.net.aso.e"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dto.RoomInfoDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="com.dto.MemberDTO"%>
<%@ page import="com.dto.RoomDTO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- 글꼴 CSS 시작-->
<link rel="stylesheet" type="text/css" href="assets/css/font.css">
<!-- <link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap" rel="stylesheet"> -->

<!-- 글꼴 CSS 끝-->
<link rel="stylesheet" type="text/css" href="assets/css/default.css">
<style type="text/css">
*{
  font-family: 'twayair' , 'Roboto', 'sans-serif' !important;	
 }
</style>
<script type="text/javascript">
var slideIndex = 0; //slide index

//HTML 로드가 끝난 후 동작
window.onload=function(){
showSlides(slideIndex);

//Auto Move Slide
var sec = 1500;
setInterval(function(){
slideIndex++;
showSlides(slideIndex);

}, sec);
}


//Next/previous controls
function moveSlides(n) {
slideIndex = slideIndex + n
showSlides(slideIndex);
}

//Thumbnail image controls
function currentSlide(n) {
slideIndex = n;
showSlides(slideIndex);
}

function showSlides(n) {

var slides = document.getElementsByClassName("mySlides");
var dots = document.getElementsByClassName("dot");
var size = slides.length;

if ((n+1) > size) {
slideIndex = 0; n = 0;
}else if (n < 0) {
slideIndex = (size-1);
n = (size-1);
}

for (i = 0; i < slides.length; i++) {
 slides[i].style.display = "none";
}
for (i = 0; i < dots.length; i++) {
 dots[i].className = dots[i].className.replace(" active", "");
}

slides[n].style.display = "block";
dots[n].className += " active";
}

var bDisplay = true;
function doDisplay() {
	var con = document.getElementById("myDIV");
	if(con.style.display == 'block'){
		con.style.display = 'none';
	}else{
		con.style.display = 'block';		
	}
}
</script>
<style type="text/css">


/* Slideshow container */
.slideshow-container {
  max-width: 600px;
  max-height: 750px;
  position: relative;
  margin: auto;
  
}
.slideshow-container .mySlides img {
  padding-top : 50px;
  height: 400px;
}

/* 앞뒤 버튼 */
.prev, .next {
  cursor: pointer;
  position: absolute;
  top: 50%;
  width: auto;
  margin-top: -22px;
  padding: 16px;
  color: white;
  font-weight: bold;
  font-size: 18px;
  transition: 0.6s ease;
  border-radius: 0 3px 3px 0;
  user-select: none;
}

/*이미지 다음버튼 */
.next {
  right: 0;
  border-radius: 3px 0 0 3px;
}

/* The dots/bullets/indicators */
.dot {
  cursor: pointer;
  height: 15px;
  width: 15px;
  margin: 0 2px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  transition: background-color 0.6s ease;
}


/* Fading animation */
.fadein {
  -webkit-animation-name: fade;
  -webkit-animation-duration: 1.5s;
  animation-name: fade;
  animation-duration: 1.5s;
}
#main{
    width: 100%;
    height: 688px;
}
#herder{
	text-align: center;
    font-size: 30px;
}
#aa{
    width: 16%;
    float: right;
    position: relative;
    right: 285px;
    top: 50px;
}
#ff{
	width: 100%;
    height: 133px;
    position: relative;
    text-align: center;
    top: 47px;
    left: 7.5%;
}
</style>
</head>
<body>
<%
MemberDTO mdto = (MemberDTO)session.getAttribute("login");
String u_id = mdto.getU_id();

String location = (String)session.getAttribute("location");
String checkin = (String)session.getAttribute("checkin");
String checkout = (String)session.getAttribute("checkout");
String guest = (String)session.getAttribute("guest");
%>
<div id ="main">
<div id ="herder">객실정보</div><br>

	<c:forEach var="y" items="${roominfo}">
	
	<div id="aa">
	<img src="images/icon/icon.jpg">청결 정책<br>
	<img src="images/icon/icon_check.jpg">정기 소독<img src="images/icon/icon_check.jpg">안전거리유지<img src="images/icon/icon_check.jpg">새로운안전프로토콜<br>
	<img src="images/icon/icon_check.jpg">손 소독제제공<img src="images/icon/icon_check.jpg">체온측정<img src="images/icon/icon_check.jpg">의료서비스<br>
	<img src="images/icon/icon_check.jpg">룸서비스<img src="images/icon/icon_check.jpg">대응프로토콜<br>
	
	<br>

	<div id="bb">
	
	<div><a style="font-size: 24px;">객실 편의시설</a></div>
	
	<img src="${pageContext.request.contextPath}/images/icon/icon_bath.jpg"><div style="font-size: 22px;">욕실</div>
	<div style="font-size: 18px;">&#42;${y.bath}<br></div>    
	
	<img src="${pageContext.request.contextPath}/images/icon/icon_eat.jpg"><div style="font-size: 22px;">식음료</div>
	<div style="font-size: 18px;">&#42;${y.eat}<br></div>
	
	
	<img src="${pageContext.request.contextPath}/images/icon/icon_etc.jpg"><div style="font-size: 22px;">인터넷</div>
	<div style="font-size: 18px;">&#42;${y.internet}</div><br>

	
	<img src="${pageContext.request.contextPath}/images/icon/icon_internet.jpg"><div style="font-size: 22px;">기타</div>
	<div style="font-size: 18px;">&#42;${y.etc}</div>

	
</div>
	</div>
	</c:forEach>

	<c:forEach var="x" items="${roomlist}">

	<div  id="cc">
	
	 <div class="slideshow-container">
       <div class="mySlides fadein" >
        <img src="${pageContext.request.contextPath}/images/room/${x.room_img}" style="width:100%; "> 
      </div>
      <div class="mySlides fadein">
        <img src="${pageContext.request.contextPath}/images/room/${x.room_img_real}" style="width:100%;">
      </div>
    
      <!-- Next and previous buttons -->
      <a class="prev" onclick="moveSlides(-1)">&#10094;</a>
      <a class="next" onclick="moveSlides(1)">&#10095;</a>
    </div>
  
    <!-- The dots/circles -->
    <div id ="ff">
    <div>
      <span class="dot" onclick="currentSlide(0)"></span>
      <span class="dot" onclick="currentSlide(1)"></span>
    </div>
    
   
   
    <div id="jj">
    <a href="loginCheck/RoomReserv?u_id=<%=u_id%>&hotelseq=${x.hotelseq }&hotelname=${hotelname}
				&roomseq=${x.seq}&price=${x.price}&name=${x.name}">지금예약하기<!-- <div style="font-size:18px;">지금 예약하기</div> --></a>
	<br>
	<div id="ll">
	
	
	<span>${x.name}</span>
</div>
	<div>가격  :${x.price}<br>
	
	<img src="${pageContext.request.contextPath}/images/icon/icon_guest.jpg">최대인원수:${x.max_guest} </div>
	 </div>
	</div>
	</div>
	</c:forEach>

	

	
</div>
</body>
</html>