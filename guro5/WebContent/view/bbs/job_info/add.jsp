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
<title>비트캠프 구로점 - 취업정보 게시판</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	function moveList(){
		window.location.href="?mode=list";
	}
	function numCheck(jqele){	
		var num = jqele.val();
		for(var i=0; i<num.length; i++) {
			var su=num.charCodeAt(i);
			console.log( su +" 임");
			if(su<48 || su>57) return true; // 숫자만
		}
		return false;
	}
	$(function(){
		$('button#submit').click(function(e){
		    e.preventDefault();
		    var boo1= numCheck($('#hirenum'));
		    var boo2= numCheck($('#sal'));
		    
			if(boo1){
			console.log(numCheck($('#hirenum')));
				alert("모집인원에는 숫자만 입력가능합니다.");
				$('#hirenum').focus();
				return false;
			}else if(boo2){
				alert("급여는 숫자만 입력가능합니다.(단위: 만)");
				$('#sal').focus();
				return false;
			}else{ 	
				var queryString = $("form").serialize() ;
				 
				$.post("?mode=add", queryString, function(data){
					if(data=="오류"){
						alert('알수없는 오류가 발생했습니다. 잠시후에 이용해주세요');
					}else{
						alert('입력완료 되었습니다.');
						window.location.href="?mode=list";
					}
				});			
			}		
		});		
	});
</script>
<style>
	h1{
		text-align: center;
	}
	#container{
		align-content: center;
		text-align: center;
	}
    .addPage{
    	width: 540px;
        border-collapse: collapse;
        text-align: left;
        line-height: 1.5; 
        margin : 20px auto;
    }
    .subject{
    	display: inline-block;
    	width: 140px;
        padding: 10px;
        font-weight: bold;
        vertical-align: top;
    }
    .input{
       	display: inline-block;
        width: 345px;
        padding: 10px;
        vertical-align: top;
        border: 1px solid lightgray;
    }
    .subject_short{
    	display: inline-block;
        width: 55px;
        padding: 10px;
        font-weight: bold;
        vertical-align: top;
    }
    .input_short{
    	display: inline-block;
        width: 160px;
        padding: 10px;
        vertical-align: top;
        border: 1px solid lightgray;
    }
    .textarea{ /*textarea로 바꿔야함*/
    	width : 530px;
    	margin: 0px auto;
        margin-top: 20px;
        height: 200px;
    }
    .clearboth{
    clear: both;
    }
    .btn{
    	float: right;
    	margin-right: 75px;
    	margin-top: 10px;
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
		<span class="navigator deps">취업센터</span>
		<span class="navigator deps">&nbsp;&rsaquo;&nbsp;</span>
		<span class="navigator final">취업정보 글쓰기</span>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
	<hr class="bar"/>
	<article id="article">
		<div id="container" >
		 		<h1>취업정보 글쓰기</h1>
				<div class="addPage">
				<form method="post">
					<div class ="">		
						<!-- num은 나중에 천천히 -->
						<label class="subject" for="title">공고제목</label>
						<input class="input" type="text" name="title" id="title">
					</div>	
					<div class ="">
						<label class="subject" for="company">업체명</label>
						<input class="input" type="text" name="company" id="company">	
					</div>
					<div class ="">
						<label class="subject_short" for="hirenum">모집인원</label>
						<input class="input_short" type="text" name="hirenum" id="hirenum">	
						<label class="subject_short" for="hiretype">고용형태</label>
						<input class="input_short" type="text" name="hiretype" id="hiretype">	
					</div>
					<div class ="">
						<label class="subject_short" for="region">근무지</label>
						<input class="input_short" type="text" name="region" id="region">	
						<label class="subject_short"for="duedate">마감일</label>
						<input class="input_short" type="date" name="duedate" id="duedate">	
					</div>
					<div class ="">
						<label class="subject_short" for="sal">급여</label>
						<input class="input_short" type="text" name="sal" id="sal">	
						<label class="subject_short" for="qualify">모집자격</label>
						<input class="input_short" type="text" name="qualify" id="qualify">	
					</div>
					<div class ="">
						<label class="subject" for="email">접수처메일</label>
						<input class="input" type="text" name="email" id="email">	
					</div>
					<div>
						<textarea class="textarea" name="contents" rows="" cols=""></textarea>
					</div>	
					<div class ="action btn">
						<button type="submit" class="button">입력</button>		
						<button type="reset" class="button">리셋</button>		
						<button type="button" class="button" onclick="moveList()">뒤로</button>		
					</div>	
				</form>
				</div>
				<div class="clear">
				</div>
			</div>
			</article>
	<%@include file="/template/footer.jsp" %>
    </div>
</body>
</html>