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
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#pw").blur(function(event) {
			var passwd = $("#pw").val();
			var passwd2 = $("#pw1").val();
			if (passwd != passwd2) {
				alert("비밀번호가 다릅니다. 다시 입력해주세요");
				event.preventDefault();
			} else {
				alert("비밀번호가 같습니다. 회원가입을 계속 진행해주세요");
				event.preventDefault();
			}
		})

		/* $("#u_id").blur(function(event) {
			$.ajax({
				type : "post",
				url : "MemberIdCheck",
				dataType : "text",
				data : {
					u_id : $("#u_id").val()
				},
				success : function(responseData, status, xhr) {
					alert(responseData);
					event.preventDefault();
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
						console.log("중복");
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
		}); //end u_id   */

		//중복검사 버튼 클릭
		$(".id_check_button").click(function(){
			if ($("#u_id").val() == '') {
    		      alert("아이디를 입력해주세요.");
    		      return;
    		    }
			$.ajax({
				type : "post",
				url : "MemberIdCheck",
				dataType : "text",
				data : {
					u_id : $("#u_id").val()
				},
				success : function(responseData, status, xhr) {
					if (responseData == "아이디가 중복됩니다. 다시 입력해주세요") {
						console.log("중복");
						alert(responseData);
					} else {
						console.log("통과");
						alert("사용가능한 아이디입니다.");
				        $(".id_check_img").show();
				        $(".id_check_button").hide();
						$("#register").attr("action", "MemberAdd");
						$("#register").submit();
					}
				},
				error : function(xhr, status, error) {
					console.log("error")
				}
			});//end ajax 
		})
		
		//아이디 중복검사후 변경할 때
		$("#u_id").keyup(function () {
     		$(".id_check_button").show();
      		$(".id_check_img").hide();
    	})
    	
		//약관 동의
		$("#submit").click(function() {
			if ($(".id_check_img").css('display') == 'none' ){
				alert("아이디 중복검사를 해주세요.");
				return false;
			}
			if ($(".checked").is(":checked") == false) {
				alert("약관 동의를 해주세요.");
				return false;
			}
			if ($("#confirm").is(":disabled") == false){
				alert("휴대폰 인증을 해주세요.");
				$("#to").focus();
				return false;
			}
		})//end 약관동의
		
		

		//팝업창 중앙 정렬
		$("#Agree").click(function(){
			window.screen.width
			// 좌우 크기 반환 Ex) 1920

			window.screen.height
			// 상하 크기 반환, Ex) 1080
			
			var popupWidth = 600;

			var popupHeight = 800;

			 var popupX = (window.screen.width / 2) - (600 / 2);
			// 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음
			
			var popupY= (window.screen.height / 2) - (800 / 2);
			// 만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼주었음 
		
			window.open('agree', '_blank', 'status=no, width=600,height=800, left='+ popupX + ', top='+ popupY);

		})// end 팝업창 중앙 정렬
		
		
		
		//	휴대폰 인증 
		var count = 0;	//인증번호 받기 사용횟수
		$("#send").click(function() {
       
       var number = Math.floor(Math.random() * 100000) + 100000;
          if(number > 100000){
             number = number - 10000;
          }

          $("#text").val(number);      /* 난수로 생성된 인증번호를 hidden name : text 에 숨긴다 */
       
       var to = $("#to").val();
       
       if(to == "" || to == null){
          alert("핸드폰 번호를 입력해주세요.");
       }
       
       else {
       var con_test = confirm("해당번호로 인증문자를 발송하시겠습니까?");   /* 문자를 보낼껀지 물어본다 */  
          if(con_test == true){ 
             if(count < 2){      /*사용횟수 1번 */
                 
               $.ajax({
                   url:"phone",
                   type:"post",
                   data:{to: $("#to").val(),
                        text: $("#text").val()
                        },
                 success:function(){
                   alert("인증번호를 발송했습니다");
                   count++;
                   $(".in-line2").show(); 
             	  $("#number").focus(); 
                   }
                });
             } else {
                alert("경고!")
             }
          
          }
             else if(con_test == false){
                
             }
         }   
    })	// end 휴대폰 인증
    
			//인증번호 비교
				    $("#confirm").click(function() {   /* 내가 작성한 번호와 인증번호를 비교한다 */
				       
				       var userNum = $("#number").val(); 
				       
				       var sysNum = $("#text").val();         
				       
				       if(userNum == null || userNum == ""){
				          alert("인증번호를 입력해주세요");
				       }     
				       else{     
				          if(userNum.trim() == sysNum.trim()){
				              alert("인증이 완료되었습니다.");
				              $(".in-line2").find("input").prop("disabled",true);
				              $(".in-line2").animate({opacity:"0.5"});
				           }
				           else {
				              alert("인증번호를 확인해주세요.");
				           }          
				       }
				    });// end 인증번호 비교
			
	});//end jQuery
</script>
<script>
//휴대폰번호 입력시 자동 하이픈(-) 추가
function inputPhoneNumber(obj) {
var number = obj.value.replace(/[^0-9]/g, "");
var phone = "";

if(number.length < 4) {
    return number;
} else if(number.length < 7) {
    phone += number.substr(0, 3);
    phone += "-";
    phone += number.substr(3);
} else if(number.length < 11) {
    phone += number.substr(0, 3);
    phone += "-";
    phone += number.substr(3, 3);
    phone += "-";
    phone += number.substr(6);
} else {
    phone += number.substr(0, 3);
    phone += "-";
    phone += number.substr(3, 4);
    phone += "-";
    phone += number.substr(7);
}
obj.value = phone;
}
//end 휴대폰번호 입력시 자동 하이픈(-) 추가
</script>
<style>
#login>p {
	text-align: center;
}

#check_span>a::before {
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
	font-size: 14px;
	width: 160%;
	height: 40px;
	position: relative;
	left: 42px;
	top: 8px;
}

#Agree2 {
	text-align: center;
	font-size: 12px;
	padding: 5px;
	position: relative;
	left: 5px;
}

.checked {
	position: relative;
	left: 31px;
}

.in-line {
	height: 40px;
}

.in-line2 {
	height: 40px;
}

#to {
	width: 70%;
	height: 100%;
	box-sizing: border-box;
	color: black;
}

#send {
	width: 31%;
	height: 101%;
	border: none;
	font-size: 13px;
	outline: none;
	display: inline;
	margin-left: -10px;
	box-sizing: border-box;
	border-radius: 12px;
	position: relative;
	left: 3px;
}

#send:hover {
	color: black;
	background-color: #6c757d;
	border-color: #6c757d;
}

#number {
	padding-top: 18px;
	width: 70%
}

#confirm {
	width: 20%;
	height: 100%;
	border: none;
	font-size: 13px;
	outline: none;
	display: inline;
	box-sizing: border-box;
	border-radius: 12px;
	position: relative;
	left: 9px;
}

#confirm:hover {
	color: black;
	background-color: #6c757d;
	border-color: #6c757d;
}

#u_id {
	width: 77%;
}

.id_check_img {
	width: 12%;
	position: relative;
	top: 10px;
	left: 10px;
}

.id_check_button{
    width: 21%;
    height: 39px;
    font-size: 12px;
    border-radius: 10px;
    outline: 0;
    border:0;
}
.id_check_button:hover{
	color: black;
	background-color: #6c757d;
	border-color: #6c757d;
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
<div class="wrap">
	<div class="form-wrap">
		<div class="button-wrap">
			<div id="btn"></div>
			<button type="button" class="togglebtn" onclick="login()">LOG
				IN</button>
			<button type="button" class="togglebtn" onclick="register()">REGISTER</button>
		</div>
		<form id="login" action="login" class="input-group" method="post">
			<input name="u_id" type="text" class="input-field"
				placeholder="Enter ID" required> <input name="u_pw"
				type="password" class="input-field" placeholder="Enter Password"
				required>
			<p>
				<span id="check_span"><a href="searchId"
					onclick="window.open(this.href, '_blank', 'width=500,height=700,toolbars=no,scrollbars=no'); return false;">아이디/비밀번호찾기</a>
				</span>
			</p>
			<button class="submit">Login</button>
			<button class="submit">
				<a href="/hotelunderbar">메인으로 돌아가기</a>
			</button>
		</form>
		<form id="register" action="#" class="input-group" method="post">
			<c:choose>
				<c:when test="${empty kemail}">
					<input name="u_id" id="u_id" type="text" class="input-field u_id"
						placeholder="id" required>
					<button type="button" class="id_check_button">중복검사</button>
					<img class="id_check_img" style="display: none;"
						src="images/icon/ID_Check.PNG">
					<input name="u_pw1" id="pw1" type="password"
						class="input-field u_pw1" placeholder="password" required>
					<input name="u_pw" id="pw" type="password"
						class="input-field u_pw2" placeholder="password" required>
					<input name="u_name" type="text" class="input-field u_name"
						placeholder="name" required>
					<input name="u_email" type="email" class="input-field u_email"
						placeholder="Email" required>
					<div class="in-line">
						<input name="u_phone" type="text" class="input-field phone"
							id="to" placeholder="Phone Number"
							onKeyup="inputPhoneNumber(this);" maxlength='13' required>
						<input type="button" name="name" id="send" value="인증번호 받기">
					</div>
					<div class="in-line2" style="display: none;">
						<input type="text" class="input-field number" id="number"
							placeholder="인증번호 입력" maxlength='6' required> <input
							type="button" name="name" id="confirm" value="확인"> <input
							type="hidden" name="text" id="text">
					</div>
					<span id="check_span"><p id="Agree">
							<input type="checkbox" id="check_2" class="checked" name="check"
								onclick="return false"> <a id="Agree"
								style="vertical-align: 9px;">약관동의(필수)</a>
						</p></span>
					<p id="Agree2">위 약관 동의를 클릭해주세요.</p>
					<button class="submit" id="submit" name="register">REGISTER</button>
					<button class="submit">
						<a href="/hotelunderbar">메인으로 돌아가기</a>
					</button>
				</c:when>

				<c:when test="${!empty kemail}">
					<input name="u_id" id="u_id" type="text" class="input-field u_id"
						placeholder="아이디" required>
					<input name="u_pw1" id="pw1" type="password"
						class="input-field u_pw1" placeholder="비밀번호" required>
					<input name="u_pw" id="pw" type="password"
						class="input-field u_pw2" placeholder="password" required>
					<input name="u_name" type="text" class="input-field u_name"
						placeholder="name" value="${name}" required>
					<input name="u_email" type="email" class="input-field u_email"
						placeholder="Email" value="${kemail}" required>
					<div class="in-line">
						<input name="u_phone" type="text" class="input-field phone"
							id="to" placeholder="Phone Number"
							onKeyup="inputPhoneNumber(this);" maxlength='13' required>
						<input type="button" name="name" id="send" value="인증번호 받기">
					</div>
					<div class="in-line2" style="display: none;">
						<input type="text" class="input-field number" id="number"
							placeholder="인증번호 입력" maxlength='6' required> <input
							type="button" name="name" id="confirm" value="확인"> <input
							type="hidden" name="text" id="text">
					</div>
					<span id="check_span"><p id="Agree">
							<input type="checkbox" id="check_2" class="checked" name="check"
								onclick="return false"> <a id="Agree"
								style="vertical-align: 9px;">약관동의(필수)</a>
						</p></span>
					<p id="Agree2">위 약관 동의를 클릭해주세요.</p>
					<button class="submit" id="submit" name="register">REGISTER</button>
					<button class="submit">
						<a href="/hotelunderbar">메인으로 돌아가기</a>
					</button>
				</c:when>
			</c:choose>
		</form>
	</div>
</div>
<jsp:include page="common/sidemenu.jsp" flush="true"></jsp:include>
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
