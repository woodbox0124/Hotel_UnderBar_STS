<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항작성</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/notification.css">
<style>

table {
	width:100%;
	margin-bottom:10px;
}
tr{
	border-bottom:0.5px solid #ddd;
}


th{
	width:20%;
	border-right:0.5px solid #ddd;
}

.buttons{
	margin-top:60px;
}
#registerBtn{
	text-align:right;
}

#wrapper{
	height:100%;
}


</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#revise").on("click",function(){
			$("#eventForm").attr("action","loginCheck/eventUpdate");
			console.log($("#code").val())
			$("#eventForm").submit();
			alert("수정되었습니다.");
		})
		$("#delete").on("click",function(){
			$("#eventForm").attr("action","loginCheck/eventDelete");
			$("#eventForm").submit();
			alert("삭제되었습니다.");
		})
		
	})
</script>
</head>
<body>
<div id="wrapper">
	<div class="content">
		<c:choose>
			<c:when test="${login.u_id eq 'admin'}">
				<form action="" method="post" id="eventForm">
					<input type="hidden" name="code" value="${eDTO.code }" id="code">
					<table>
						<tr>
							<th>제목</th>
							<td><input type="text" class="form-control-plaintext" name="subject" id="exampleFormControlInput1" value="${eDTO.subject }"></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td><input type="text" class="form-control-plaintext" name="author" id="exampleFormControlInput1"  value="${eDTO.author}"  readonly></td>
						</tr>
						<tr>
							<th>작성일</th>
							<td><input type="text" class="form-control-plaintext" name="regdate" id="exampleFormControlInput1"  value="${eDTO.regdate}" readonly></td>
						</tr>
						<tr>
							<td colspan="2">
								<textarea class="form-control-plaintext"  name="content" id="exampleFormControlTextarea1" rows="20" >${eDTO.content }</textarea>
							</td>
						</tr>
					</table>
					<div class="buttons">
						<input class="btn btn-outline-secondary" type="button" value="수정" id="revise">
						<!-- content or subject empty -> modal 적용  -->					
						<input class="btn btn-outline-secondary" type="button" value="삭제" id="delete"> 
						<a class="btn btn-outline-secondary" href="notification">목록보기</a>
					</div>
				</form>
			</c:when>
			<c:otherwise>
					<table>
						<tr>
							<th>제목</th>
							<td><input type="text" class="form-control-plaintext" disabled name="subject" id="exampleFormControlInput1" value="${eDTO.subject }"></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td><input type="text" class="form-control-plaintext" disabled id="exampleFormControlInput1"  value="${eDTO.author}"disabled></td>
						</tr>
						<tr>
							<th>작성일</th>
							<td><input type="text" class="form-control-plaintext" disabled id="exampleFormControlInput1"  value="${eDTO.regdate}"></td>
						</tr>
						<tr>
							<td colspan="2">
								<textarea class="form-control-plaintext" disabled id="exampleFormControlTextarea1" rows="20" >${eDTO.content }</textarea>
							</td>
						</tr>
					</table>
					<div class="buttons">
						<a class="btn btn-outline-secondary" href="event">목록보기</a>
					</div>
			</c:otherwise>
		</c:choose>
		<!-- 댓글창 구현  -->
		    <form id="commentForm" name="commentForm" method="post">
		    <br><br>
		        <div>
		            <div>
		                <span><strong>Comments</strong></span> <span id="cCnt"></span>
		            </div>
		            <div>
		                <table class="table">                    
		                    <tr>
		                        <td>
		                        <c:choose>
									<c:when test="${login.u_id ne null}">
		                            <textarea style="width:100%;"rows="3" cols="30" id="comments" name="comments" placeholder="댓글을 입력하세요"></textarea>
		                            <br>
		                            <div>
		                                <a href='javascript:' id="btnAdd" class="btn pull-right btn-success">등록</a>
		                            </div>
		                            </c:when>
		                            <c:otherwise>
		                            	<textarea style="width:100%;"rows="3" cols="30" id="comments" name="comments" placeholder="로그인 후 입력가능합니다." readonly></textarea>
		                            	<br>
		                            	<div>
		                                <a href='javascript:' class="btn pull-right btn-success">등록</a>
		                            </div>
		                            </c:otherwise>
		                        </c:choose>
		                        </td>
		                    </tr>
		                </table>
		            </div>
		        </div>
		    </form>
		<form id="commentListForm" name="commentListForm" method="post">
		    <div id="commentList">
			</div>
		</form>
		</div>
	</div>
		
		
<Script>
//댓글작성 

$(document).ready(function(){
	
	getList();
		
	//댓글작성 
	$("#btnAdd").click(function(){
		var writer = "${login.u_id}";
		var comments = $("#comments").val();
		var e_code = "${eDTO.code}";
		console.log(writer,e_code,comments);
		var d = {
				writer:writer,
				e_code:e_code,
				comments:comments
		}
		console.log(d);
		$.ajax({
			type:'post',
			url : '<c:url value="/replies/add"/>',
			data:d
		}).done(function(){
			alert('글이 등록되었습니다.');
			$("#comments").val("");
			getList();
			
		}).fail(function(error){
			console.log(error);
		})
	})
})	

//댓글리스트 
function getList(){
	var e_code = "${eDTO.code}";
	var u_id = "${login.u_id}";
	console.log(e_code);
	$.ajax({
		type:'get',
		url : '<c:url value="/replies/getList"/>',
		data:{e_code : e_code},
		dataType:'json',
		contentType: 'application/x-www-form-urlencoded;charset=utf-8',
		}).done(function(data){
			var html = "";
            var cCnt = data.length;
            if(data.length > 0){
                for(i=0; i<data.length; i++){
            		if(u_id == data[i].writer){
	                    html += '<div id="c_code' + data[i].c_code + '">';
	                    html += "<div><table class='table'><h6><strong>"+data[i].writer+"</strong>&nbsp;&nbsp;&nbsp;&nbsp;";
	                    html += '<a href="javascript:void(0)" onclick="fn_editComment(' + data[i].c_code + ', \'' + data[i].comment + '\', \'' + data[i].writer + '\' )" style="padding-right:5px; text-decoration:none; color:#444;">수정</a>';
	                    html += '<a href="javascript:" onclick="fn_delComment(' + data[i].c_code +')" style="padding-right:5px; text-decoration:none; color:#444;">삭제</a></h6>';
	                    html += "<br>"+data[i].comment + "<br><tr><td></td></tr>";
	                    html += "</table></div>";
	                    html += "</div>";
            		}else{
            			html += '<div id="c_code' + data[i].c_code + '">';
	                    html += "<div><table class='table'><h6><strong>"+data[i].writer+"</strong>&nbsp;&nbsp;&nbsp;&nbsp;</h6>";
	                    html += data[i].comment + "<tr><td></td></tr>";
	                    html += "</table></div>";
	                    html += "</div>";
            		}
                }
            } else {
                html += "<div>";
                html += "<div><table class='table'><h6><strong>등록된 댓글이 없습니다.</strong></h6>";
                html += "</table></div>";
                html += "</div>";
            }
            
            $("#cCnt").html(cCnt);
            $("#commentList").html(html);
		}).fail(function(error){
			console.log(error);
		})
		
		
}
function fn_editComment( c_code, comment, writer){
	console.log("수정",c_code, comment , writer);
	var html = "";
	html += '<div class="media text-muted pt-3" id="c_code' + c_code + '">';
	html += '<title>Placeholder</title>';
	html += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';
	html += '<span class="d-block">';
	html += '<strong class="text-gray-dark">' + writer + '</strong>';
	html += '<span style="padding-left: 7px; font-size: 9pt">';
	html += '<a href="javascript:" onclick="fn_updateComment('  + c_code + ', \'' + comment + '\', \'' + writer + '\' )" style="padding-right:5px" return false;>저장</a>';
	html += '<a href="javascript:" onclick="getList()">취소<a>';
	html += '</span>';
	html += '</span>';		
	html += '<textarea name="editContent" id="editContent" class="form-control" rows="3">';
	html += comment;
	html += '</textarea>';
	html += '</p>';
	html += '</div>';
	$('#c_code' + c_code).replaceWith(html);
	$('#c_code' + c_code + ' #editContent').focus();
} 

function fn_delComment( c_code){
	console.log("삭제",c_code)
		$.ajax({
			type:'post',
			url : '<c:url value="/replies/delReply"/>',
			data:{c_code:c_code}
		}).done(function(){
			alert('글이 삭제되었습니다.');
			getList();
			
		}).fail(function(error){
			console.log(error);
		})
	}
	
 

function fn_updateComment(c_code, writer){
	var comment = $("#editContent").val();
	var dto =(
			{"c_code":c_code,
			"writer":writer,
			"comment":comment}
			);
    console.log("fn_updateComment : ",dto);
		$.ajax({
			type:'post',
	        url : "<c:url value='/replies/updateReply'/>",
	        ContentType:'application/x-www-form-urlencoded;charset=UTF-8',
	        data: dto,
	        dataType : "text"
		}).done(function(){
			alert('글이 수정되었습니다.');
			getList();
			
		}).fail(function(error){
			console.log(error);
		})
		
	}
	
		

 

 
</script>
</body>
</html>