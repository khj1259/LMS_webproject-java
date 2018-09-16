<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String rootPath = (String)request.getAttribute("rootPath");
	pageContext.setAttribute("rootPath", rootPath);
	String urlThis = (String)request.getAttribute("urlThis");
	pageContext.setAttribute("urlThis", urlThis);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비트캠프 구로점 - 수강신청</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
</script>
<style>
#article{
	align-content: center;
}
#container{
	 width: 80%;
	 margin: 50px auto;
	 text-align: center;
}
.innerCon{
	width: 420px;
	text-align:left;
	margin: 0px auto;
}
.row{
	margin-bottom: 20px;
}
.postcon{
	align-content: center;
}
.input{
        width: 350px;
        padding: 10px;
        vertical-align: top;
        border: 1px solid lightgray;
    }
.but{
		float: right;
}
.clear{
	clear: both;
}
</style>
</head>
<body>
	<div id="body">
	<%@include file="/template/header.jsp" %>
	<%@include file="/template/nav.jsp" %>
	<!-- 각 회원에 맞게 바꿔 주셔야 합니다. -->
	<!-- 학생이면 nav_student 강사면 nav_teacher template 폴더를 확인하세요 -->
	<br>
	<br>
	<br>
	<br>
	<br>
	<hr class="bar"/>
		<span class="navigator deps">홈</span>
		<span class="navigator deps">&nbsp;&rsaquo;&nbsp;</span>
		<span class="navigator final">수강신청</span>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
	<hr class="bar"/>
	<article id="article">
		<div id="container">
		 		<h1>수강신청</h1>
		 	<form method="post" class="innerCon">
				<div class="title row">
					<label for="title" class="subject">제목</label>
					<input type="text" name="title" id="title" class="input">
				</div>
				<div class="name row">
					<label for="name" class="subject">이름</label>
					<input type="text" name="name" id="name" class="input">
				</div>
				<div class="gen row">
					<label for="gen" class="subject">성별</label>
					<input type="radio" name="gen" id="gen" value="남"> 남성
					<input type="radio" name="gen" id="gen" value="여"> 여성
				</div>
				<div class="email row">
					<label for="email" class="subject">이메일</label>
					<input type="email" name="email" id="email" class="input">
				</div>
				<div class="purpose row">
					<input type="hidden" name="purpose" id="purpose" value="lec">
					<input type="hidden" name="" id="lec_code" value="${dtoLecInfo.lecCode}"/>
					<label class="subject" class="input">수강명</label>
					<span>${dtoLecInfo.className}</span>
				</div>
				<div class="phone row">
					<label for="phone" class="subject">휴대폰</label>
					<input type="text" name="phone" id="phone" placeholder="'-'없이 숫자만 입력" class="input">
				</div>
				<div class="contents row">
					<label for="contents" class="subject content"></label>
					<textarea name="contents" id="contents" cols="65" rows="10"></textarea>
				</div>
				<div class="but">
					<button type="submit" class="button">상담신청</button>
					<button type="reset" class="button">리셋</button>
				</div>
				</form>
				<div class="clear">
				</div>
			</div>
			</article>
	<%@include file="../../../template/footer.jsp" %>
    </div>
</body>
</html>