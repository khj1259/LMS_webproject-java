<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="false" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>비트캠프 구로점</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>

<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">


$(function(){
	
	$("#gohome").click(function(e){
		e.preventDefault();
		location.href="${rootPath}";
	});
	
});

</script>
<style>
	.button {
		padding: 5px 5px;
		border-radius: 15px;
		color : black;
		border: 1px solid lightgray;
		background-color: hsla(0, 100%, 90%, 0.3);
		width: 100px;
		margin-left: 200px;
	}
</style>
</head>
<body>
	<div id="body">
	<%@include file="/template/header.jsp" %>
	<%@include file="/template/nav.jsp" %>
	<br>
	<br>
	<br>
	<br>
	<br>
	<hr class="bar"/>
		<span class="navigator deps">홈</span>
		<span class="navigator deps">&nbsp;&rsaquo;&nbsp;</span>
		<span class="navigator final">로그아웃</span>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
		<hr class="bar"/>
		<article id="article">
			<div id="container">
			<!-- 내용을 넣어주세요 -->
				<div style="width: 600px; margin: 10px auto; margin-bottom: 50px;">
					<h2  style="width: 500px; margin :0px auto; display: block; text-align: center;">안전하게 [ 로그아웃 ] 했습니다.</h2>
					<button class="button"  id="gohome" style="margin :10px auto;">홈</button>
				</div>
				<!--  내용끝 -->
			</div>
		</article>
		<div style="clear:both;"></div>
		
	<%@include file="/template/footer.jsp" %>
    </div>
</body>
</html>