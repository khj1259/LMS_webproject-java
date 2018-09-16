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
<title>비트캠프 구로점 - 취업현황 게시판</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#add').click(function(){
			window.location.href='?mode=add';
		});
	});

</script>
<style>
#article{

}
#container{
	 width: 100%;
	 margin: 50px auto;
	 text-align: center;
}

#table {
        width: 100%;
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
    width: 12.5%;
}
.col2 {
    width: 42.5%;
}
.col3 {
    width: 22.5%;
}
.col4 {
    width: 12.5%;
}
.act{
	text-decoration: none;
	color: black;
}
.act:hover {
	color: blue;
}
.btn{
	float: right;
	margin-top: 30px;
	margin-right: 50px;
}
.clear{
	clear: both;
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
		<span class="navigator deps">강의소개</span>
		<span class="navigator deps">&nbsp;&rsaquo;&nbsp;</span>
		<span class="navigator final">취업현황</span>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
	<hr class="bar"/>
	<article id="article">
		<div id="container">
		 		<h1>취업현황 게시판</h1>
				<div id="table">
					<div class="row">
						<span class="cell top col1">글번호</span>
						<span class="cell top col2">제목</span>
						<span class="cell top col3">취업자</span>
						<span class="cell top col4">작성일</span>
					</div>
					<c:forEach items="${requestScope.alist }" var="dtoJobAf">
					<div class="row">
						<span  class="cell col1">${dtoJobAf.rn }</span>
						<span  class="cell col2"><a href="?mode=detail&idx=${dtoJobAf.num}" class="act">${dtoJobAf.title }</a></span>
						<span  class="cell col3">${dtoJobAf.name }</span>
						<span  class="cell col4">${dtoJobAf.nalja }</span>
					</div>
					</c:forEach>
				</div>
				<c:if test="${web_mem_type eq 0 || web_mem_type eq 2 || web_mem_type eq 3 || web_mem_type eq 4 || web_mem_type eq 5 }">
				<div class="btn">
					<button id="add" class="button">글쓰기</button>
				</div>
				</c:if>
				<div class="clear">
				</div>
			</div>
			</article>
	<%@include file="../../../template/footer.jsp" %>
    </div>
</body>
</html>