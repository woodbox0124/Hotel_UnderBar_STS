<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
<title>아이디 비밀번호 찾기</title>
<link rel="stylesheet" href="styles/style.css">
<link rel="stylesheet" type="text/css" href="styles/main_styles.css">
<%
	String mesg = (String)request.getAttribute("mesg");
	String mesg1 = (String)request.getAttribute("mesg1");
if (mesg != null) {
%>
<script type="text/javascript">
     alert('<%=mesg%>');
</script>
<%
} else if(mesg1 != null) {%>
	<script type="text/javascript">
    alert('메일 전송에 성공하였습니다.');
    window.close();
</script>
<%}
%>
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
	.button-wrap > button{
	}
	#formId {
    left: 50px;
    }
    #formPassword {
    left: 450px;
    }
    .form-wrap{
      width:380px;
    }
    .form-wrap > form{
      margin: 0 auto;
      text-align: center;
    }
</style>
</head>
        <div class="wrap">
            <div class="form-wrap">
                <div class="button-wrap">
                    <div id="btn"></div>
                    <button type="button" class="togglebtn" onclick="serachId()">아이디 찾기</button>
                    <button type="button" class="togglebtn" onclick="password()">비밀번호 찾기</button>
                </div>
                <form id=formId action="MemberIdSearch" class="input-group" method="post">
                    <input name= "u_name" type="text" class="input-field" placeholder="이름" required>
                    <input name= "u_phone" type="text"  class="input-field" placeholder="핸드폰 번호" required>                   
                    <input name= "u_email" type="text"  class="input-field" placeholder="이메일" required>                   
                    <button class="submit">확인</button>
                    <button class="submit"><a href="javascript:window.close();">창 닫기</a></button>                    
                </form>
                <form id="formPassword" action="MemberPwSearch" class="input-group" method="post">
                    <input name ="u_id" id="u_id" type="text" class="input-field u_id" placeholder="아이디" required>
                    <input name ="u_name" type="text" class="input-field u_name" placeholder="이름" required>
                    <input name ="u_email" type="email" class="input-field u_email" placeholder="이메일" required>
                    <input name ="u_phone" type="text" class="input-field phone" placeholder="핸드폰 번호" required>
                    <button class="submit" id ="submit">확인</button>  
                    <button class="submit"><a href="javascript:window.close();">창 닫기</a></button>                  
                </form>                      
            </div>
        </div>
        <script>
            var x = document.getElementById("formId");
            var y = document.getElementById("formPassword");
            var z = document.getElementById("btn");
            function serachId(){
                x.style.left = "50px";
                y.style.left = "450px";
                z.style.left = "0";
            }
            function password(){
                x.style.left = "-400px";
                y.style.left = "50px";
                z.style.left = "127.5px";
            }
        </script>
