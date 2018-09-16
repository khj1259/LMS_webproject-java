<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비트캠프 구로점-아이디 찾기</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
</script>
<style>
	#article {
		
	}
	#container{
		width: 30%;
		margin: auto;
	}
	.text {
		margin: 8px 0;
		display: inline-block;
		border: 1px solid #ccc;
		box-sizeing: border-box;
		width: 200px;
	}
	.button {
		padding: 5px 5px;
		border-radius: 15px;
		color : black;
		border: 1px solid lightgray;
		background-color: hsla(0, 100%, 90%, 0.3);
		width: 100px;
		
	}
	.space1{
		margin-left: 55px;
	}
	.space2{
		margin-left: 40px;
	}
</style>
</head>
<body> 
<div id="body">
	<%@include file="/template/header.jsp"%>
	<%@include file="/template/nav.jsp"%><!-- 각 회원에 맞게 바꿔 주셔야 합니다. -->
	<!-- 학생이면 nav_student 강사면 nav_teacher template 폴더를 확인하세요 -->
	<br> <br> <br> <br> <br>
	<hr class="bar" />
	<article id="article">
		<div id="container">
			<div>
			<!-- 내용을 넣어주세요 -->
				<form method="post">
				<div id = "innerCon" style="margin-top: 20px">
					<label for=name>이름</label>
					<input type="text" name="name" class="text space1" placeholder="이름 입력"/><br>
					<label for="phone">연락처</label>
					<input type="tel" name="phone" class="text space2" placeholder="연락처 입력"/>
					<div style="margin-top: 20px">
						<button type="submit" class = button >찾기</button>
					</div>
				</div>
				</form>
			<!--  -->
			</div>
		</div>
					<div style="clear:both;"></div>
		
	</article>
	<%@include file="/template/footer.jsp"%>
</div>
</body>
</html>