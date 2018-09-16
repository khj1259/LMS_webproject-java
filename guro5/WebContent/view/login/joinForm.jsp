<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비트캠프 구로점 - 회원가입</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	function checkId(){
		var id=$('input[id=id]').val();
		for(var i=0; i<id.length; i++){
			var su=id.charCodeAt(i);
			if(i==0){
				if(!((su>=65 && su<=90)
						|| (su>=97 && su<=122))) return false;
			} else {
				if(!((su>=65 && su<=90)
						|| (su>=97 && su<=122)
						|| (su>=48 && su<=57))) false; 
			}
		}
		return true;
	}
	
	function checkPw() {
		var boo1=false;
		var boo2=false;
		var boo3=false;
		var pw = $('input[id=password]').val();
		for(var i=0; i<pw.length; i++) {
			var su=pw.charCodeAt(i);
			if(su>=65 && su<=90) boo1 = true;
			if(su>=97 && su<=122) boo2 = true;
			if(su>=48 && su<=57) boo3 = true; // 숫자만
		}
		if(boo1 && boo2 && boo3) return true;
		else return false;
	}
	
	function checkRe() {
		var pw = $('input[id=password]').val();
		var re = $('input[id=pwchk]').val();
		if(pw==re)return true;
		else return false;
	}
	
	function checkKor() {
		name = $('input[id=name]').val();
		for(i=0; i<=name.length; i++){
			if(!((name.charCodeAt(i) > 0x3130 && name.charCodeAt(i) < 0x318F) ||
					(name.charCodeAt(i) >= 0xAC00 && name.charCodeAt(i) <= 0xD7A3))) {
				alert("이름을 한글로 작성해주세요.");
				name.value="";
				return false;
			}else return true;
		}
	}
	
	$(document).ready(function() {
		$('form input').first().focus();
		$('form input').focus(function(e){
	         $(this).val('');
		});
		$('form input').blur(function(e){
			var i=$('form input').index(this);
			$('.err').remove();
			var param=$(this).val();
			var err=$('<span/>').addClass('err');
			var msg;
			
			if(param==''){
				msg='값이비었음';
			}else{
				if(i==0 && !checkId()) msg='영문, 숫자만가능(첫글자 영문)';
				if(i==1 && !checkPw()) msg='영소,영대,숫자조합';
				if(i==2 && !checkRe()) msg='동일하지 않음';
				if(i==3 && !checkKor()) msg='한글만 가능';
			}
			err.text(msg);
			param=$(this).after(err);
		});
		
		$('form').submit(function(e){
			e.preventDefault();
			console.log(e);
			$('.err').remove();
			var id=$('input[id=id]').val();
			if(id=='') {
				id=$('input[id=id]').after('<span class="err">값이비었음</span>');
			}else if(!checkId()){
				id=$('input[id=id]').after('<span class="err">영문, 숫자만가능(첫글자 영문)</span>');
			}
			var pw=$('input[id=password]').val();
			if(pw==''){
				pw=$('input[id=password]').after('<span class="err">값이비었음</span>');
			}else if(!checkPw()){
				pw=$('input[id=password]').after('<span class="err">영소,영대,숫자 조합</span>');
			}
			var re=$('input[id=pwchk]').val();
			if(re==''){
				re=$('input[id=pwchk]').after('<span class="err">값이 비었음</span>');
			}else if(!checkRe()){
				re=$('input[id=pwchk]').after('<span class="err">동일하지 않음</span>');
			}
			var name=$('input[id=name]').val();
			if(name=''){
				name=$('input[id=name]').after('<span class="err">값이비었음</span>');
			}else if(!checkKor()){
				name=$('input[id=name]').after('<span class="err">한글만 입력가능</span>');
			}
			if($('.err').length==0){
				console.log("되니");

				var queryString = $("form").serialize() ;
				 
				$.post("?mode=join", queryString, function(data){
					if(data=="오류"){
						alert('알수없는 오류가 발생했습니다. 잠시후에 이용해주세요');
					}else if(data=="있음"){
						alert('이미 있는 ID 입니다. 다시 확인해주세요.');
					}else{
						alert('회원가입이 완료되었습니다. 승인을 기다려주세요!');
						window.location.href=data;
					}
				});
			}
			
		});
	});
	// 해당요소의 값 초기화
	function clear(elem) {
		//elem.$('input').value="";
		elem.document.getElementById("input").value="";
	}
	// 아이디 중복 여부 확인
	function openConfirmid() {
		// 아이디 입력여부 확인
		var inputid=$('input[id=id]').val();
		if(inputid=="") {
			alert("아이디를 입력하세요");
			return;
		}else if(inputid=="임시ID" || inputid == "admin" || inputid == "root" ){
			alert("해당이름으로는 가입할 수 없습니다.");
			return;
		}
		// url과 사용자 입력 id 조합
		url = "?mode=joinChk&id=" + inputid;
		
		// 새로운 윈도우 오픈
		open(url, "confirm",
				"toobar=no,location=no,status=no,menubar=no,scrollbars=no,resizeable=no,width=310,height=180");
	}
</script>
<style>
	#article {
		
	}
	#container{
		width: 50%;
		margin: auto;
		border: none;
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
		margin: none;
	}
	.space1{
		margin-left: 40px;
	}
	.space2{
		margin-left: 27px;
	}
	.space3{
		margin-left: 0px;
	}
	.space4{
		margin-left: 53px;
	}
	.space5{
		margin-left: 15px;
	}
	.space6{
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
				<div style="margin-top: 20px">
					<label for="id">아이디</label>
					<input class="text space1" type="text" name="id" id="id" placeholder="아이디 입력"/>
					<button type="button" id="overapId" onclick="openConfirmid()">ID중복확인</button>
					
				</div>
				<div>
					<label for="password">비밀번호</label>
					<input class="text space2" type="password" name="password" id="password" placeholder="영대/영소/숫자 3가지 조합"/>
				</div>
				<div>
					<label for="pwchk">비밀번호확인</label>
					<input class="text space3" type="password" name="pwchk" id="pwchk" placeholder="비밀번호 재입력" />
				</div>
				<div>
					<label for="name">이름</label>
					<input class="text space4" type="text" name="name" id="name" maxlength="9" placeholder="공백없이 한글만 입력가능"/>
				</div>
				<div>
					<label for="phone">휴대폰번호</label>
					<input class="text space5" type="tel" name="phone" id="phone" placeholder="'-'없이 숫자만 입력"/>
				</div>
				<div>
					<label for="email">이메일</label>
					<input class="text space6" type="email" name="email" id="email" placeholder="이메일 입력"/>
				</div>
				<div style="margin-top: 20px">
					<button type="submit" id="join" class="button">가입</button>
					<button type="reset" id="reset" class="button">취소</button>
					<br>
					<br>
					<br>
					<br>
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