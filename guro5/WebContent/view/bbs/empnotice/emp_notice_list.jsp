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
<title>비트캠프 구로점 - 직원공지사항게시판</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	function moveAdd(){
		window.location.href="?mode=add";
	}
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
    width: 10%;
}
.col2 {
    width: 40%;
}
.col3 {
    width: 10%;
}
.col4 {
    width: 10%;
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
	margin-right: 200px;
	margin-top: 30px;
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
		<span class="navigator deps">내메뉴</span>
		<span class="navigator deps">&nbsp;&rsaquo;&nbsp;</span>
		<span class="navigator final">상담게시판</span>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
	<hr class="bar"/>
	<article id="article">
		<div id="container">
		 		<h1>직원 공지사항 게시판</h1>
				<div id="table">
					<div class="row">
						<span class="cell top col1">글번호</span>
						<span class="cell top col2">제목</span>
						<span class="cell top col3">아이디</span>
						<span class="cell top col4">날짜</span>
						<span class="cell top col5">조회수</span>
					</div>
					<c:forEach items="${requestScope.alist}" var="DtoEmpNotice">
					<div class="row">
						<span class="cell col1">${DtoEmpNotice.rn }</span>
						<span class="cell col2"><a href="?mode=detail&idx=${DtoEmpNotice.num}&click=on" class="act">${DtoEmpNotice.title }</a></span>
						<span class="cell col3">${DtoEmpNotice.id }</span>
						<span class="cell col4">${DtoEmpNotice.nalja }</span>
						<span class="cell col5">${DtoEmpNotice.view_num }</span>
					</div>
					</c:forEach>
					<div class="btn">
						<button onclick="moveAdd()" class="button">글쓰기</button>
					</div>
				</div>
				<div class="clear">
				</div>
			</div>
			</article>
	<%@include file="../../../template/footer.jsp" %>
    </div>
</body>
</html>