<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비트캠프 구로점-로그인</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(function() {
    var input = $('input[type=text]');
    var input2 = $('input[type=password]');
    input.focus(function() {
         $(this).val('');
    });
    input2.focus(function() {
         $(this).val('');
    });
 });

	function loginChk(){
		var id = $("input[name=id]").val();
		var pw = $("input[name=password]").val();
		$.post("?mode=login", {"id":id,"password":pw} , function( data ) {
			  if(data.result == 1 ){
				  if(data.confirm == 'yes'){
						alert(data.name+"님 환영합니다.");
						window.location.href="${rootPath}";
				  }else{
						alert("승인요청 중 입니다... 잠시만 기다려주세요.");
						clearChk=true;
						$("input#id").focus();
				  }
			  }else{
				alert("입력한 정보로 로그인 할수 없습니다.");
				clearChk=true;
				$("input#id").focus();
			  }
		});
	}
</script>
<style>
	#article {
		
	}
	#innerCon{
		text-align: center;
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
		margin-left: 45px;
	}
	.space2{
		margin-left: 32px;
	}
	.bottom {
		margin-top: 20px;
	}
	#inp{
		margin-top: 20px;
		margin-bottom: 30px;
		margin-left: 200px;
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
				<div id="innerCon" style="margin-top: 20px">
					<span>아이디</span>  <input type="text" name="id" placeholder="아이디 입력" class="text space1" /><br>
					<span>비밀번호</span> <input type="password" name="password" placeholder="비밀번호 입력" class="text space2" /><br>
					<div class="bottom" >
						<span><a href="?mode=join">회원가입</a> | </span>
						<span><a href="?mode=idFind">아이디찾기</a> | </span>
						<a href="?mode=pwFind">비밀번호찾기</a>
					</div>
					<div id="inp">
						<button type="button" onclick="loginChk()" class="button">로그인</button><br>
						<br>
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