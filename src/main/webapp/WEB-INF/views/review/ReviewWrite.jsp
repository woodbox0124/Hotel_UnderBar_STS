<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.dto.MemberDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
<link rel="stylesheet" href="jquery-ui-1.12.1/fontawesome-stars.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="jquery-ui-1.12.1/jquery.barrating.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="assets/css/font.css">
<link rel="stylesheet" type="text/css" href="assets/css/default.css">
<script type="text/javascript">
   $(function() {
      $('#star').barrating({
        theme: 'fontawesome-stars'
      });
   });
</script>
<style type="text/css">
#theFile{
font-size: 13px;
}
#title{
font-size: 13px;}
#content{
font-size: 13px;}
#u_id{
font-size: 13px;
}
#aa{
font-size: 15px;
margin: auto;
}
#jumbotron{
height: 80px;
width: 800px;
margin: auto;
}

</style>
</head>
<body>
<%String hotelname=request.getParameter("hotelname");
%>

<div id="jumbotron" class="jumbotron">
<p style="font-size: 30px;">호텔에 대한 리뷰를 남겨주세요!</p>
</div>

<form action="Reviewupload" method="POST" enctype="multipart/form-data" >
<input type="hidden" id="hotelname" name="hotelname" value="<%=hotelname%>">
<input type="hidden" name="u_id" id="u_id" value="${login.u_name}"><br>
<a id="aa">평점</a>:<select id="star" name="star">
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5">5</option>
</select>
 <input type="text" name="title" id="title" class="form-control mt-4 mb-2"
		placeholder="제목을 입력해주세요." required
	>
	<div class="form-group">
		<textarea class="form-control" rows="10" name="content" id="content"
			placeholder="내용을 입력해주세요" required
		></textarea>
	</div>
<input type="file" name="theFile" id="theFile">
<br>
<br>
<input type="reset" class="btn btn-primary"  value="초기화">
<input type="submit" class="btn btn-primary" value="리뷰작성"/>
</form>
</body>
</html>