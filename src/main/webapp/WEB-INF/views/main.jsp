<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML>
<html>
<head>
<title>Hotel UnderBar</title>
<meta charset="utf-8">
<!-- main배경 css -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/bootstrap-4.1.2/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main_styles.css">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css" />

<!-- main배경 css 끝 -->
<!-- 글꼴 CSS 시작 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/font.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap"
	rel="stylesheet">
<!-- 글꼴 CSS 끝 -->
<!-- 달력 쿼리  -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-ui-1.12.1/jquery-ui.min.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="${pageContext.request.contextPath}/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/jquery-ui-1.12.1/datepicker-ko.js"></script>
<script type="text/javascript">
//<![CDATA[
$(function(){
	 $("#place1").on("change",function(){
	        var s = $(this).val();
	        $("#location").val(s);
	    });
	    
	 
    $("#checkin").datepicker({minDate: 0});
    $("#checkout").datepicker({minDate: 0});
    
    
    
    $("#checkin").datepicker("option", "onClose", function (selectedDate) {
        $("#checkout").datepicker( "option", "minDate", selectedDate );
        $("#checkin").datepicker("option", "maxDate", $("#checkout").val());
        $("#checkout").datepicker("option", "minDate", $("#checkin").val());
    });
    
    $("#checkout").datepicker("option", "onClose", function (selectedDate ) {	
    	$("#checkout").datepicker("option", "minDate", $("#checkin").val());
        $("#checkin").datepicker( "option", "maxDate", selectedDate );
    });
});
//]]>
</script>
<style type="text/css">
.ui-datepicker{ font-size: 15px; width: 325px; }
</style>
<!-- 달력 쿼리 끝 -->
<!-- alert mesg 시작 -->
<c:if test="${!empty mesg }">
<script>
    alert("${mesg}");
</script>
<%session.removeAttribute("mesg");%>
</c:if>
<!-- alert mesg 끝 -->
<!-- 자체 CSS -->
<style type="text/css">
body {
	font-family: 'twayair', 'Roboto', 'sans-serif' !important;
}

.booking_input {
    width: 100%;
    height: 54px;
    background: rgba(255, 255, 255, 0.2);
    border: solid 2px #ffffff;
    padding-left: 27px;
    font-size: 20px;
    font-weight: 700;
    color: white;
    outline: none;
}
option {
	color: black;
}
</style>
<!-- 자체 CSS 끝-->
</head>

<body>
<jsp:include page="common/menu.jsp" flush="true"></jsp:include><br>
	<div id="wrapper">
		<div id="main">
			<!-- Header -->
			<header id="header">
				<div class="home_title">Hotel UnderBar</div>
					<div class="booking_form_container">
						<form action="HotelSearch" class="booking_form">
							<div class="d-flex flex-xl-row flex-column align-items-start justify-content-start">
								<div class="booking_input_container d-flex flex-lg-row flex-column align-items-start justify-content-start">
									<div>
										<input type="text" class="datepicker booking_input booking_input_a booking_in"
										placeholder="체크인" required="required" id="checkin" name="checkin" autocomplete="off">
									</div>
									<div>
										<input type="text" class="datepicker booking_input booking_input_a booking_out"
										placeholder="체크아웃" required="required" id="checkout" name="checkout" autocomplete="off">
									</div>
									<div>
                                    	<input type="number" class="booking_input booking_input_b" placeholder="인원 수" required="required" name="guest">
                                    </div>
										<input type="hidden" name="location" value="" id="location">
									<div style="width:200px;">
										<select id="place1" class="booking_input">
											<option value="">모든지역</option>
											<option value="서울">서울</option>
											<option value="부산">부산</option>
											<option value="제주">제주</option>
										</select>
									</div>
									<button class="booking_button trans_200">검색</button>
								</div>
							</div>
						</form>
					</div>
				</header>
			</div>
		</div>
</body>
<jsp:include page="common/sidemenu.jsp" flush="true"></jsp:include>
</html>