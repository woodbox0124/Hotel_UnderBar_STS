<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="assets/css/default.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="assets/css/font.css">
<style type="text/css">
	* {
		font-size: 20px;
	}
	.chat {
		margin-left: 10px;
	}
	#messageinput {
		width: 90%;
		height: 30px;
		font-size: 20px;
	}
</style>
<title>실시간 상담</title>
</head>
<body>
    <div class="chat">
        <button type="button" class="btn btn-outline-primary" onclick="openSocket();">대화방 참여</button>
        <button type="button" class="btn btn-outline-primary" onclick="closeSocket();">대화방 나가기</button>
    	<br/><br/><br/>
  		메시지 입력 :
        <input type="text" id="sender" value="${sessionScope.id}" style="display: none;">
        <input type="text" class="form-control" id="messageinput">
        <br><br>
        <button type="button" class="btn btn-outline-primary" onclick="send();">메시지 전송</button>
        <button type="button" class="btn btn-outline-primary" onclick="javascript:clearText();">대화내용 지우기</button><br>
               실시간 상담은 오전 9시 부터 오후6시까지 가능합니다.
    </div>
    <!-- Server responses get written here -->
    <div id="messages" class="chat">
    </div>
    <!-- websocket javascript -->
    <script type="text/javascript">
        var ws;
        var messages = document.getElementById("messages");
        
        function openSocket(){
            if(ws !== undefined && ws.readyState !== WebSocket.CLOSED ){
                writeResponse("WebSocket is already opened.");
                return;
            }
            //ws = new WebSocket("ws://localhost:8041/echo.do");
            ws = new WebSocket("ws://localhost:8041/hotelunderbar/echo.do");
            ws.onopen = function(event){
                if(event.data === undefined){
              		return;
                }

                writeResponse(event.data);
            };
            
            ws.onmessage = function(event){
                console.log('writeResponse');
                console.log(event.data)
                writeResponse(event.data);
            };
            
            ws.onclose = function(event){
                writeResponse("대화 종료");
            }
            
        }
        
        function send(){
           // var text=document.getElementById("messageinput").value+","+document.getElementById("sender").value;
            var text = document.getElementById("messageinput").value+","+document.getElementById("sender").value;
            ws.send(text);
            text = "";
        }
        
        function closeSocket(){
            ws.close();
        }
        
        function writeResponse(text){
            messages.innerHTML += "<br/>"+text;
        }

        function clearText(){
            console.log(messages.parentNode);
            messages.parentNode.removeChild(messages)
      	}
        
  </script>
</body>
</html>