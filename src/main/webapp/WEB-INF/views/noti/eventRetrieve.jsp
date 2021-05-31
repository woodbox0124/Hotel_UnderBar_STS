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
		
		<div class="container">
		    <form id="commentForm" name="commentForm" method="post">
		    <br><br>
				<div>
					<div>
						<span><strong>Comments</strong></span> <span id="cCnt"></span>
					</div>
				<div>
				<%-- <c:if test="${!login.u_id eq null }"> --%>
					<table class="table">                    
						<tr>
							<td>
							<textarea style="width: 100%;" rows="3" cols="30" id="comment" name="comments" placeholder="댓글을 입력하세요"></textarea>
						    	<br>
								<div id="registerBtn">
						        	<a href='#' onClick="fn_comment('${eDTO.code }')" class="btn pull-right btn-success">등록</a>
						        </div>
						   	</td>
						</tr>
					</table>
					<%-- </c:if> --%>
				</div>
			</div>
			<input type="hidden" id="e_code" name="e_code" value="${eDTO.code }" />        
			<input type="hidden" id="writer" name="writer" value="${login.u_id }" />        
		</form>
		</div>
		
		<div class="container">
			<form id="commentListForm" name="commentListForm" method="post">
				<div id="commentList">
				</div>
		 	</form>
		</div>
	<!-- 댓글끝 -->
	</div><!-- end content  -->
</div><!-- end wrapper -->
<script>
//댓글 등록하기(Ajax)
function fn_comment(code){
    console.log(code);
    $.ajax({
        type:'POST',
        url : "<c:url value='/event/addComment'/>",
        data:$("#commentForm").serialize(),
        success : function(data){
            if(data=="success")
            {console.log(data);
                getCommentList();
                $("#comment").val("");
            }
        },
        error:function(request,status,error){
            //alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
       }
        
    });
}
 
/**
 * 초기 페이지 로딩시 댓글 불러오기
 */
$(function(){
    
    getCommentList();
    
});
 
/**
 * 댓글 불러오기(Ajax)
 */
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
                
                for(i=0; i<data.length; i++){
                    html += "<div>";
                    html += "<div><table class='table'>
                    html += "<h6><strong>"+data[i].writer+"</strong>&nbsp;&nbsp;&nbsp;<span>"+data[i].regdate+"</h6>";
                    html += data[i].comment + "<tr><td></td></tr>";
                    html += "</table></div>";
                    html += "</div>";
                }
                
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
 
</script>
</body>
</html>