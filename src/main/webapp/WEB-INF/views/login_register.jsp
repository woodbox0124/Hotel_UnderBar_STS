<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<head>
<title>로그인 & 회원가입</title>
<link rel="stylesheet" href="styles/style.css">
<link rel="stylesheet" type="text/css" href="styles/main_styles.css">
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#pw").blur(function() {
			var passwd = $("#pw").val();
			var passwd2 = $("#pw1").val();
			if (passwd != passwd2) {
				alert("비밀번호가 다릅니다. 다시 입력해주세요");
			} else {
				alert("비밀번호가 같습니다. 회원가입을 계속 진행해주세요");
			}
		})

		$("#u_id").blur(function(event) {
			$.ajax({
				type : "post",
				url : "MemberIdCheck",
				dataType : "text",
				data : {
					u_id : $("#u_id").val()
				},
				success : function(responseData, status, xhr) {
					alert(responseData);
				},
				error : function(xhr, status, error) {
					console.log("error")
				}
			});//end ajax
		}); //end u_id
		$("#submit").click(function(event) {
			$.ajax({
				type : "post",
				url : "MemberIdCheck",
				dataType : "text",
				data : {
					u_id : $("#u_id").val()
				},
				success : function(responseData, status, xhr) {
					if (responseData == "아이디가 중복됩니다. 다시 입력해주세요") {
						console.log("world");
						alert(responseData);
					} else {
						console.log("통과");
						$("#register").attr("action", "MemberAdd");
						$("#register").submit();
					}
				},
				error : function(xhr, status, error) {
					console.log("error")
				}
			});//end ajax 
		}); //end u_id

		
		//약관 동의
		$("#submit").click(function() {
			if ($(".checked").is(":checked") == false) {
				alert("약관 동의 하셔야 합니다.");
				return false;
			}
		})//end 약관동의

	});//end jQuery
</script>
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
<style>
#login>p {
	text-align: center;
}

#check_span>a::before {
	content: ' | ';
	display: inline-block;
	margin: 0 10px;
}

#check_span>a {
	font-size: 14px;
	line-height: 32.5px;
}

.button-wrap>button {
	
}

#Agree {
	text-align: center;
	font-size: 14px;
	padding: 6px;
	position: relative;
	left: 31px;
}
#mesg{
	text-align: center;
    font-size: 12px;
}
.checked{
	position: relative;
    left: 31px;
}
</style>
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
<%
	String msg = (String) request.getAttribute("msg");
	Cookie[] c = request.getCookies();
	String cookieVal = "";
	if (c != null) {
		for (Cookie i : c) {
			if (i.getName().equals("savepw")) {
				cookieVal = i.getValue();
			} //end if
		} //end for
	} //end if
%>
<div class="wrap">
	<div class="form-wrap">
		<div class="button-wrap">
			<div id="btn"></div>
			<button type="button" class="togglebtn" onclick="login()">LOG
				IN</button>
			<button type="button" class="togglebtn" onclick="register()">REGISTER</button>
		</div>
		<div class="social-icons">
			<img src="assets/css/images/fb.png" alt="facebook"> <img
				src="assets/css/images/kakao.png" alt="kakao"> <img
				src="assets/css/images/gl.png" alt="google">
		</div>
		<form id="login" action="login" class="input-group" method="post">
			<input name="u_id" type="text" class="input-field"
				placeholder="Enter ID" required> <input name="u_pw"
				type="password" class="input-field"
				value="<%=cookieVal != "" ? cookieVal : ""%>"
				placeholder="Enter Password" required>
			<p>
				<input name="savepw" type="checkbox" class="checkbox"
					<%=cookieVal != "" ? "checked" : ""%>><span id="check_span">비밀번호저장
					&nbsp;<a href="searchId"
					onclick="window.open(this.href, '_blank', 'width=500,height=700,toolbars=no,scrollbars=no'); return false;">아이디/비밀번호찾기</a>
				</span>
			</p>
			<button class="submit">Login</button>
			<button class="submit">
				<a href="/hotelunderbar">메인으로 돌아가기</a>
			</button>
		</form>
		<form id="register" action="#" class="input-group" method="post">
			<input name="u_id" id="u_id" type="text" class="input-field u_id"
				placeholder="아이디"  required> <input name="u_pw1" id="pw1"
				type="password" class="input-field u_pw1" placeholder="비밀번호"
				required> <input name="u_pw" id="pw" type="password"
				class="input-field u_pw2" placeholder="password" required> <input
				name="u_name" type="text" class="input-field u_name"
				placeholder="name" required> <input name="u_email"
				type="email" class="input-field u_email" placeholder="Email"
				required> <input name="u_phone" type="text"
				class="input-field phone" placeholder="Phone Number" required>
			<span id="check_span"><p id="Agree">
					<input type="checkbox" id="check_2" class="checked" name="check"
						onclick="return false"><a
						id="Agree" href="Agree"
						onclick="window.open(this.href, '_blank', 'width=600,height=800,toolbars=no,scrollbars=no'); return false;"
						style="vertical-align: 1.5px;">약관동의(필수)</a>
				</p></span>
			<p id="mesg">위 약관 동의를 클릭해주세요.</p>
			<button class="submit" id="submit" name="register">REGISTER</button>
			<button class="submit">
				<a href="/hotelunderbar">메인으로 돌아가기</a>
			</button>
		</form>
	</div>
</div>
<script>
	var x = document.getElementById("login");
	var y = document.getElementById("register");
	var z = document.getElementById("btn");
	function login() {
		x.style.left = "50px";
		y.style.left = "450px";
		z.style.left = "0";
	}
	function register() {
		x.style.left = "-400px";
		y.style.left = "50px";
		z.style.left = "127.5px";
	}
</script>
