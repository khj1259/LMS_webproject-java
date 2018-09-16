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
<title>비트캠프 구로점 - 취업현황 수정</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	function returnList(){
		window.location.href='?mode=list';
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
    .contents{
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
		<span class="navigator deps">취업센터</span>
		<span class="navigator deps">&nbsp;&rsaquo;&nbsp;</span>
		<span class="navigator final">취업현황 수정</span>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
	<hr class="bar"/>
	<article id="article">
		<div id="container">
				<h1>취업현황게시판 수정페이지</h1>
				<form method="post" class="innerCon">
				<div>
					<input type="hidden" value="${dtoJobAf.num }" name="num" />
				</div>
				<div class="toprow"  >
					<label for="title" class="cell sub">제목</label>
					<input type="text" name="title" id="title" value="${dtoJobAf.title }">
				</div>
				<div class="row">
					<label for="company" class="cell sub">취업업체명</label>
					<input type="text" name="company" id="company" value="${dtoJobAf.company }">
					<label for="lec_code" class="cell sub">교육과정</label>
					<select size="1" name="lec_code" id="lec_code" class="sel">
						<c:forEach items="${requestScope.lecinfo_list}" var="lecinfo_list">
							<option value="${lecinfo_list.lecCode}">${lecinfo_list.className}</option>	
						</c:forEach>
					</select>
					<label for="name" class="cell sub">취업자명</label>
					<input type="text" name="name" id="name" value="${dtoJobAf.name }">
					<label for="field" class="cell sub">취업분야</label>
					<input type="text" name="field" id="field" value="${dtoJobAf.field }">
				</div>
				<div>
					<textarea type="text" name="contents" id="contents" class="contents">${dtoJobAf.contents }</textarea>
				</div>
				<div class="btn">
					<button type="button" onclick="returnList()" class="button">목록</button>
					<button type="submit" class="button">수정</button>
					<button type="reset" class="button" onclick="returnList()">취소</button>
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