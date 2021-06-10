<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항작성</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/notification.css">


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">




/* <!-- 파일업로드시 미리보기  -->
	$("#file").on("change",function(){
		console.log("hi");
		if(this.files && this.files[0]){
			var reader = new FileReader;
			reader.onload = function(data){
				$(".selected_img img").attr("src","${pageContext.request.contextPath}"/data.target.result).width(300);
			}
			reader.readAsDataURL(this.files[0]);
		}		
	}) */
	
</script> 
</head>
<body>
<div id="wrapper">
	<div class="content">
		<form action="loginCheck/eventInsert" method="post" enctype="multipart/form-data">
			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">제목</label>
				<input type="text" class="form-control" name="subject" id="exampleFormControlInput1" placeholder="subject">
			</div>
			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">작성자</label>
				<input type="text" class="form-control" name="author" id="exampleFormControlInput1" value="${login.u_id }" readonly>
			</div>
			
			<div class="mb-3">
			  <label for="exampleFormControlTextarea1" class="form-label">내용</label>
			  <textarea class="form-control" name="content" id="exampleFormControlTextarea1" rows="15" placeholder="content"></textarea>
			</div>
			<div class="mb-3">
				<label for="formFileSm" class="form-label">파일첨부</label>
				 <label for="gdsImg">이미지</label>
				<input class="form-control form-control-sm" id="gdsImg" type="file" name="uploadFile" multiple>
				 <div class="select_img"><img src="" /></div>
			</div>
			 
			 <script>
			  $("#gdsImg").change(function(){
			   if(this.files && this.files[0]) {
			    var reader = new FileReader;
			    reader.onload = function(data) {
			     $(".select_img img").attr("src", data.target.result).width(200).height(300);        
			    }
			    reader.readAsDataURL(this.files[0]);
			   }
			  });
			 </script>
			</div>
			<div class="buttons">
				<input class="btn btn-outline-secondary" id="submit" type="submit" value="글쓰기">
				<input class="btn btn-outline-secondary" type="reset" value="다시작성"> 
				<a class="btn btn-outline-secondary" href="event">목록보기</a>
			</div>
		</form>	
	</div>
</div>
</body>
</html>