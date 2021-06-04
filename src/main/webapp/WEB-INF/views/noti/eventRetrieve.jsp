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
			$("form").attr("action","loginCheck/eventUpdate");
			$("form").submit();
		})
		$("#delete").on("click",function(){
			
			$("form").attr("action","loginCheck/eventDelete");
			$("form").submit();
		})
		
	})
</script>
</head>
<body>
<div id="wrapper">
	<div class="content">
		<c:choose>
			<c:when test="${login.u_id eq 'admin'}">
				<form action="" method="post">
					<input type="hidden" name=num value="${eDTO.code }">
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
		<div style="width:700px; text-align:center;">
			<c:if test="${!login.userid eq null }" >
				<textarea id="reply" rows="5" cols="80" placeholder="댓글을 작성해주세요.">
				</textarea>
				<br />
				<button id="btnReply">댓글쓰기</button>
				
			</c:if>
		</div>
		<!-- 목록출력 -->
		<div id="replyList"></div>
		
		
		
		
		<Script>
		$(function(){
			$("#btnReply").click(function(){
				reply();
			})
		})
		function reply(){
			var $replyText = ${"#replytext"}.val();
			var e_code = "${eDTO.code}";
			var param={"replytext":$replyText, "e_code":e_code};
			$.ajax({
				type:"post",
				url:"${path}/event/addComment",
				data : param,
				success : function(){
					
				}//end success
			})//end ajax 
		}//end reply function
		
		
	
	/* 
	    

$(function(){
    getCommentList();
});

function getCommentList(){
    
    $.ajax({
        type:'GET',
        url : "<c:url value='/event/commentList'/>",
        dataType : "json",
        data:$("#commentForm").serialize(),
        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
        success : function(data){
            var html = "";
            var cCnt = data.length;
            if(data.length > 0){
            	$(data).each(function(){
            		console.log(data);
                	console.log(this.c_code,this.e_code,this.writer,this.comments);
                    html += '<div id="c_code' + this.c_code + '">';
                    html += '<div>';
                    html += '<a href="javascript:" onClick="fn_editComment('+ this.c_code+','+ this.e_code+ ',' +this.writer +','+this.comments +')" class="updateComment" style="text-decoration:none; color:#444;">수정</a>&nbsp;&nbsp;&nbsp;';
                    html += '<a href="javascript:" onClick="fn_delComment('+this.c_code+')" class="deleteComment" style="text-decoration:none; color:#444;" return false;>삭제</a>&nbsp;&nbsp;&nbsp;';
                    html += "<div style='height:20px;'></div>";
                    html += "<h6><strong>"+this.writer+"</strong>&nbsp;&nbsp;&nbsp;<span>"+this.regdate+"</span></h6>";
                    html += "<div>"+this.comments+"</div>";
                    html += "</div>";
                    html += "</div><hr>";
                })
                
            } else {
                html += "<div>";
                html += "<div><table class='table'><h6><strong>등록된 댓글이 없습니다.</strong></h6>";
                html += "</table></div>";
                html += "</div>";
            }
            
            $("#cCnt").html(cCnt);
            $("#commentList").html(html);
            
        },
        error:function(request,status,error){
            
       }
        
    });
	

    
}
function fn_editComment( c_code, e_code, writer, comments){
	console.log("수정",c_code, e_code, writer, comments)
	var htmls = "";
	htmls += '<div class="media text-muted pt-3" id="c_code' + c_code + '">';
	htmls += '<title>Placeholder</title>';
	htmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';
	htmls += '<span class="d-block">';
	htmls += '<strong class="text-gray-dark">' + writer + '</strong>';
	htmls += '<span style="padding-left: 7px; font-size: 9pt">';
	htmls += '<a href="javascript:" onclick="fn_updateComment(' + c_code + ', '+ e_code + ','+ writer + ',' + comments +')" style="padding-right:5px" return false;>저장</a>';
	htmls += '<a href="javascript:" onclick="getCommentList()">취소<a>';
	htmls += '</span>';
	htmls += '</span>';		
	htmls += '<textarea name="editContent" id="editContent" class="form-control" rows="3">';
	htmls += comments;
	htmls += '</textarea>';
	htmls += '</p>';
	htmls += '</div>';
	$('#c_code' + c_code).replaceWith(htmls);
	$('#c_code' + c_code + ' #editContent').focus();
}

//댓글 삭제하기(Ajax)
function fn_delComment(code){
	var c_code ={"code":code};
    console.log("delComment c_code : ",c_code);
    $.ajax({
        type:'post',
        url : "<c:url value='/event/delComment'/>",
        data: c_code,
        success : function(){
        	getCommentList();
        },
        error:function(request,status,error){
            //alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
       }
    });
}
//댓글 수정하기(Ajax)
function fn_updateComment(c_code, e_code, writer,comments){
	var dto =(
			{"c_code":c_code,
			"e_code":e_code,
			"writer":writer,
			"comments":comments}
			);
    console.log("fn_updateComment : ",dto);
    $.ajax({
        type:'post',
        url : "<c:url value='/event/updateComment'/>",
        ContentType:'application/x-www-form-urlencoded;charset=UTF-8',
        data: dto,
        dataType : "json", 
        success: function(result) {
           	var htmls = "";
            $(result).each(function(){
            	console.log(result);
            htmls += '<div class="media text-muted pt-3" id="c_code' + this.c_code + '">';
            htmls += '<title>Placeholder</title>';
            htmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';
            htmls += '<span class="d-block">';
            htmls += '<strong class="text-gray-dark">' + this.writer + '</strong>';
            htmls += '<span style="padding-left: 7px; font-size: 9pt">';
            htmls += '<a href="javascript:" onclick="fn_editComment(' + this.c_code + ',' + this.writer + ',' + this.comments +')" style="padding-right:5px">수정</a>';
            htmls += '<a href="javascript:" onclick="fn_delComment(' + this.c_code + ')" >삭제</a>';
            htmls += '</span>';
            htmls += '</span>';
            htmls += this.comments;
            htmls += '</p>';
            htmls += '</div>';
           });	//each end
         $('#c_code' + c_code).replaceWith(htmls);
        },	   // Ajax success end
        error:function(request,status,error){
            //alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
       }
    }); 
}
 */

 
</script>
</body>
</html>