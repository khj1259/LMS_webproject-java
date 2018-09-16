<%@page import="com.gurobit5.model.entity.DtoLecInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String rootPath = (String)request.getAttribute("rootPath");
	pageContext.setAttribute("rootPath", rootPath);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비트캠프 구로점 - 강의소개</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">

	function newOpen(){
		
		
	$("#preView").click(function(event){
			event.preventDefault();
			window.open('${dtoLecInfo.preView }', 'youtube',
				'location=yes,resizable=yes,status=yes,menubar=yes,resizable=yes,width=500,height=500');
		});
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
	<%@include file="/template/header.jsp" %>
	<%@include file="/template/nav.jsp" %><!-- 각 회원에 맞게 바꿔 주셔야 합니다. -->
	<!-- 학생이면 nav_student 강사면 nav_teacher template 폴더를 확인하세요 -->
	<br>
	<br>
	<br>
	<br>
	<br>
	<hr class="bar"/>
		<span class="navigator deps">홈</span>
		<span class="navigator deps">&nbsp;&rsaquo;&nbsp;</span>
		<span class="navigator deps">강의 소개</span>
		<span class="navigator deps">&nbsp;&rsaquo;&nbsp;</span>
		<span class="navigator final">강의 상세보기</span>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
	<hr class="bar"/>
	<article id="article">
		<div id="container">
		 	<!-- 내용을 넣어주세요 -->		
		 <div class="detail">
		<h1>[ ${dtoLecInfo.className } ] 상세보기</h1>
		
		<div class="toprow">		
			<span class="cell sub">강의명</span><span class="val">${dtoLecInfo.className }</span>
		</div>
		
		<div class="row">
			<span class="cell sub">강사명</span><span class="val">${dtoLecInfo.teacher }</span>
			<span class="cell sub">키워드</span><span class="val">${dtoLecInfo.keyword }</span>
		</div>
		<div class="row">	
			<span class="cell sub">개강일</span><span class="val">${dtoLecInfo.openDate }</span>
			<span class="cell sub">종강일</span><span class="val">${dtoLecInfo.endDate }</span>
		</div>
		<div class="row">
			<span class="cell sub">강의미리보기</span>
			<a id="preView" href="#" onclick="newOpen();"><span class="val">${dtoLecInfo.preView }</span></a>
		</div>
		<div class="content"><span>${dtoLecInfo.contents }</span></div>
		
		<div class=" action btn">
			<a href="${rootPath }bbs/consult.bit?mode=formlec&idx=${dtoLecInfo.lecCode}"><button class="button">수강신청</button></a>
			<a href="${rootPath }bbs/consult.bit?mode=form"><button class="button">상담신청</button></a>
		</div>
				<!--  내용끝 -->
		 	</div><!-- detail div end -->
		</div>
</article>
	<%@include file="/template/footer.jsp" %>
    </div>
		
</body>
</html>



</body>
</html>