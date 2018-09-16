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
<title>비트캠프 구로점 - 취업정보 상세보기</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	function moveEdit(){
		window.location.href="?mode=edit&idx=${dtoJobInfo.num}";
	}
	function moveAdd(){
		window.location.href="?mode=add";
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
		<span class="navigator final">취업정보 상세보기</span>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
	<hr class="bar"/>
	<article id="article">
		<div id="container">
			<div class="detail">
				<h1>취업정보 상세보기</h1>
					<div class="toprow">		
						<!-- num은 나중에 천천히 -->
						<span class="cell sub" >공고제목</span>
						<span class="inp">${dtoJobInfo.title}</span>
					</div>	
					<div class="row">
						<span class="cell sub">업체명</span>
						<span>${dtoJobInfo.company}</span>
						<span class="cell sub">모집인원</span>
						<span>${dtoJobInfo.hireNum}</span>
						<span class="cell sub">고용형태</span>
						<span>${dtoJobInfo.hireType}</span>
					</div>
					<div class="row">
						<span class="cell sub">근무지</span>
						<span>${dtoJobInfo.region}</span>
						<span class="cell sub">마감일</span>
						<span>${dtoJobInfo.dueDate}</span>
						<span class="cell sub">급여</span>
						<span>${dtoJobInfo.sal}</span>
					</div>
					<div class="row">
						<span class="cell sub">모집자격</span>
						<span>${dtoJobInfo.qualify}</span>
						<span class="cell sub">접수처메일</span>
						<span>${dtoJobInfo.email}</span>
					</div>
					<div class="content">
						<span>${dtoJobInfo.contents}</span>
					</div>
					<div class ="action btn">
				<c:if test="${web_mem_type eq 5 || web_mem_type eq 2 }">	
						<button class="button" type="button" onclick="moveAdd();">글쓰기</button>		
						<button class="button" type="button" onclick="moveEdit();">수정</button>		
				</c:if>	
						<button class="button" type="button" onclick="window.history.back();">뒤로</button>		
					</div>
				<div class="clear"></div>
				</div><!-- detail end -->
			</div>
			</article>
	<%@include file="/template/footer.jsp" %>
    </div>
</body>
</html>