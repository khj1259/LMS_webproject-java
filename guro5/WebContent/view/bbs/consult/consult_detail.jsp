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
<title>비트캠프 구로점 - 상담상세보기</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	
	var test;
	function movelist(){
		window.location.href='?mode=list';
	}
	function answerWindow(num){
		test= window.open('?mode=answer&idx='+num,'상담답변','width=500,height=400');
	}
	function editWindow(num,answer){
		test = window.open('?mode=edit&idx='+num,'상담수정','width=500,height=400');
	}
	
	function deleteAlert(num){
		if(confirm('해당 게시물을 삭제하시겠습니까?')){
			$.post('?mode=del',{'idx':num},function(){
				window.location.href='?mode=list';
			});
		}
	}
</script>
<style>

	 .detail{
        width: 80%;
        margin: 0px auto;
    }
    .cell{
        width: 100px;
        display: inline-block;
    }
    .toprow{
        border-bottom: 1px solid black;
        border-top: 1px solid black;
    }
    .row{
        border-bottom: 1px solid black;
    }
    .sub{
        border-right: 1px solid lightgray;
        text-align: center;
        background-color: lightgray;
    }
    .content{
    	display:inline-block;
    	width:100%;
    	height: 350px;
    	border-bottom: 1px solid black;
    }
    .btn{
    	float: right;
    	margin-top: 10px;
    	margin-bottom: 20px;
    }
    .clear{
    clear: both;
    }
    .til{
    	text-align: center;
    }
    .answer{
    	display:inline-block;
    	width:100%;
    	height: 100px;
    	border-bottom: 1px solid black;
    }
</style>
</head>
<body>
	<div id="body">
	<%@include file="../../../template/header.jsp" %>
	<%@include file="../../../template/nav.jsp" %>
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
		<span class="navigator final">상담신청</span>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
	<hr class="bar"/>
	<article id="article">
		<div id="container">
				<div class="til">
			 		<h1>상담게시판 상세페이지</h1>
				</div>
				<form method="post" class="innerCon">
				<div class="toprow">
					<span class="cell sub">제목</span>
					<span>${dtoConsult.title }</span>
				</div>
				<div class="row">
					<span class="cell sub">글번호</span>
					<span>${dtoConsult.num }</span>
					<span class="cell sub">이름</span>
					<span>${dtoConsult.name }</span>
					<span class="cell sub">성별</span>
					<span>${dtoConsult.gen }</span>
				</div>
				<div class="row">
					<span class="cell sub">이메일</span>
					<span>${dtoConsult.email }</span>
					<span class="cell sub">수강목적</span>
					<span>${dtoConsult.purpose }</span>
					<span class="cell sub">휴대폰</span>
					<span>${dtoConsult.phone }</span>
				</div>
				<c:if test=" ${dtoConsult.lec_code ne 11111 }">
				<div class="row">
					<span class="cell sub">수강신청 강의명</span>
					<span>${dtoLecInfo.className }</span>
				</div>
				</c:if>
				<div>
					<span class="content">${dtoConsult.contents }</span>
				</div>
				<div>
					<span>상담내용</span>
					<span class="answer">${dtoConsult.answer }</span>
				</div>
				<div class="space"></div>
				<div class="btn">
					<button type="button" onclick="movelist()" class="button">목록</button>
					<button type="button" onclick="answerWindow(${dtoConsult.num})"  class="button">상담입력</button>
					<button type="button" onclick="editWindow(${dtoConsult.num})" class="button">상담수정</button>
					<button type="button" onclick="deleteAlert(${dtoConsult.num})" class="button">삭제</button>
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