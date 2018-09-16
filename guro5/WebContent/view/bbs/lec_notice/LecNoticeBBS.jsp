<%@page import="com.gurobit5.model.bbs.entity.DtoLecAf"%>
<%@page import="java.util.ArrayList"%>
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
<style>
    #table {
        width: 100%;
        margin: 0px auto;
        margin-bottom: 100px;
    }
    .row {
        
    }
    .cell {
        display: inline-block;
        padding: 3px;
        border-bottom: 1px solid lightgray;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        text-align: center;
    }
    .top{
        font-weight: bold;
        background-color: lightgray;
    }
    .col1 {
        width: 10%;
    }
    .col2 {
        width: 45%;
    }
    .col3 {
        width: 10%;
    }
    .col4 {
        width: 20%;
    }
    .col5 {
        width: 10%;
    }
    a:link { color: black; text-decoration: none;}
 	a:visited { color: black; text-decoration: none;}
	a:hover { color: blue;}
	h1{text-align: center;}
	.clearboth{
    clear: both;
    }
    .bt{
    	float: right;
    	margin-top: 10px;
    	margin-bottom: 20px;
    }
</style>
<title>비트캠프 구로점-강의공지사항 게시판</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('button').click(function(){
			window.location.href='?mode=add';
		});
		
	});
</script>
</head>
<body>
	<div id="body">
	<%@include file="/template/header.jsp" %>
	<%@include file="/template/nav.jsp" %><!-- 각 회원에 맞게 바꿔 주셔야 합니다. -->
	<!-- 학생이면 nav_student 강사면 nav_teacher template 폴더를 확인하세요 -->
		<br> <br> <br> <br> <br>
		<hr class="bar"/>
		<span class="navigator deps">홈</span>
		<span class="navigator deps">&nbsp;&rsaquo;&nbsp;</span>
		<span class="navigator deps">내메뉴</span>
		<span class="navigator deps">&nbsp;&rsaquo;&nbsp;</span>
		<span class="navigator final">강의공지사항</span>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
	<hr class="bar"/>
	<article id="article">
			<div id="container">
				<div>
					<!-- 내용을 넣어주세요 -->
					<h1>강의공지 게시판</h1>
					<div id="table">
						<div class="row">
						<span class="cell top col1">글번호</span>
						<span class="cell top col2">제목</span>
						<span class="cell top col3">아이디</span>
						<span class="cell top col4">날짜</span>
						<span class="cell top col5">조회수</span>
						</div>
						<c:forEach items="${requestScope.alist }" var="dtoLecNotice">
						<div class="row">
							<span class="cell col1">${dtoLecNotice.rn }</span> 
							<span class="cell col2"><a href="?mode=detail&idx=${dtoLecNotice.num}&click=on">${dtoLecNotice.title }</a></span>
							<span class="cell col3">${dtoLecNotice.id }</span> 
							<span class="cell col4">${dtoLecNotice.nalja }</span>
							<span class="cell col5">${dtoLecNotice.viewNum }</span>
						</div>
						</c:forEach>
						<c:if test="${web_mem_type eq 1 }">
						<div class="bt">
							<!-- 강사만 -->
							<button class="button">글쓰기</button>
						</div>
						</c:if>
						<div class="clearboth"></div>
					</div>

				</div>
			</div>
		</article>
	<%@include file="/template/footer.jsp" %>
    </div>
</body>
</html>