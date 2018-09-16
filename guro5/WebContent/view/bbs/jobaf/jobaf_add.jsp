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
<title>비트캠프 구로점 - 취업현황 작성</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	function moveList(){
		window.location.href="?mode=list";
	}
</script>
<style>
#article{
	align-content: center;
}
#container{
	 width: 80%;
	 margin: 50px auto;
	 text-align: center;
}
.innerCon{
	width: 420px;
	text-align:left;
	margin: 0px auto;
}
.row{
	margin-bottom: 20px;
}
.postcon{
	align-content: center;
}
.input{
        width: 300px;
        padding: 10px;
        vertical-align: top;
        border: 1px solid lightgray;
        float: right;
        height: 10px;
    }
.sel{
 	clear: both;
 	margin-left: 40px;
 	width: 310px;
 	height: 35px;
 }
.but{
		float: right;
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
		<span class="navigator deps">취업센터</span>
		<span class="navigator deps">&nbsp;&rsaquo;&nbsp;</span>
		<span class="navigator final">취업현황 작성</span>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
	<hr class="bar"/>
	<article id="article">
		<div id="container" >
		 		<h1>취업현황게시판 작성페이지</h1>
				<form method="post" class="innerCon">
				<div class="row">
					<label for="company" class="subject">취업업체명</label>
					<input type="text" name="company" id="company" class="input">
				</div>
				<div class="row">
					<label for="lec_code" class="subject">강의코드</label>
					<select size="1" name="lec_code" id="lec_code" class="sel">
						<c:forEach items="${requestScope.lecinfo_list}" var="lecinfo_list">
							<option value="${lecinfo_list.lecCode}">${lecinfo_list.className}</option>	
						</c:forEach>
					</select>
				</div>
				<div class="row">
					<label for="title"  class="title" class="subject">제목</label>
					<input type="text" name="title" id="title" class="input">
				</div>
				<div class="row">
					<label for="name" class="subject">취업자명</label>
					<input type="text" name="name" id="name" class="input">
				</div>
				<div class="row">
					<label for="field" class="subject">취업분야</label>
					<input type="text" name="field" id="field" class="input">
				</div>
				<div class="row">
					<textarea type="text" name="contents" id="contents" class="row55" cols="80" rows="20"></textarea>
				</div>
				<div class="btn">
					<button type="submit" class="button">완료</button>
					<button type="reset" class="button" onclick="moveList()">취소</button>
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