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
<title>비트캠프 구로점 - 직원게시판</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#golist").click(function(e){
			e.preventDefault();
			window.location.href="?mode=list";
		});
		$("#goedit").click(function(e){
			e.preventDefault();
			window.location.href="?mode=edit&idx=${dtoEmpNotice.num }";
		});
		$("#godel").click(function(e){
			e.preventDefault();
			if(confirm("해당 게시글을 삭제하시겠습니까?")){
				$.post('?mode=del',{'idx':${dtoEmpNotice.num }},function(){
					window.location.href='?mode=list';
				});
			}
		});
		
	});
	
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
		<span class="navigator deps">내메뉴</span>
		<span class="navigator deps">&nbsp;&rsaquo;&nbsp;</span>
		<span class="navigator final">직원공지사항 상세보기</span>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
	<hr class="bar"/>
	<article id="article">
		<div id="container">
				<h1>직원공지사항 게시글</h1>
				<form method="post" class="innerCon">
					<div>
						<input type="hidden" value="${dtoEmpNotice.num }" name="num" />
					</div>
					<div class="toprow">
						<span class="cell sub">제목</span>
						<span>${dtoEmpNotice.title }</span>
					</div>
					<div class="row">
						<span class="cell sub">조회수</span>
						<span>${dtoEmpNotice.view_num }</span>
						<span class="cell sub">날짜</span>
						<span>${dtoEmpNotice.nalja }</span>
					</div>
					<div class="content">
						<span>${dtoEmpNotice.contents }</span>
					</div>
					<div  class="btn">
						<button id="golist" class="button">목록</button>
						<button id="goedit" class="button">수정</button>
						<button id="godel" class="button">삭제</button>
					</div>
				</form>
				<div class="clear">
				</div>
			</div>
			</article>
	<%@include file="/template/footer.jsp" %>
    </div>
</body>
</html>