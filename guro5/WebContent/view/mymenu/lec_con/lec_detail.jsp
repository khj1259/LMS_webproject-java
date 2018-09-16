<%@page import="com.gurobit5.model.entity.DtoLecInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String rootPath = (String) request.getAttribute("rootPath");
	pageContext.setAttribute("rootPath", rootPath);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비트캠프 구로점-강의관리</title>
<link rel="stylesheet" type="text/css"
	href="${rootPath}template/button.css" />
<link rel="stylesheet" type="text/css"
	href="${rootPath}template/main.css" />
<link href="https://fonts.googleapis.com/css?family=Jua"
	rel="stylesheet">
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script>
	$(function(){
		$('#create_lec').click(function(){
			window.location.href ="?pageid=lec_con&mode=add";
		});
		$('#edit_lec').click(function(){
			window.location.href ="?pageid=lec_con&mode=edit&idx=${dtoLecInfo.lecCode}";
		});
	});// 온로드 시점
</script>	
<style>
h1 {
	text-align: center;
}

.detail {
	width: 80%;
	margin: 0px auto;
}

.column2_1 {
	width: 50%;
	float: left;
}

.column2_2 {
	width: 100%;
	clear: both;
}

.row {
	width: 100%;
	margin: 1px auto;
	display: inline-block;
}

.lab {
	background-color: #D3D3D3;
	height: 25px; line-height 25px;
	width: 25%;
	display: inline-block;
	text-align: center;
	margin-right: 1%;
}

.val {
	height: 25px; line-height 25px;
	width: 70%;
	display: inline-block;
	padding-left: 1%;
	text-align: left;
}

.contents {
	margin-top: 5px;
	height: 100%;
	min-height: 100px;
}

.clearboth {
	clear: both;
}

.bar_u {
	height: 1px;
	border-top: 1px solid black;
	margin-bottom: 2px;
	clear: both
}

.bar_b {
	height: 1px;
	margin-top: 2px;
	border-bottom: 1px solid black;
	clear: both
}

.act {
	text-decoration: none;
	color: black;
}

.act:hover {
	color: blue;
}

.btn {
	float: right;
	margin-right: 50px;
	margin-top: 30px;
}

.clear {
	clear: both;
}
</style>
</head>
<body>
	<div id="body">
		<%@include file="/template/header.jsp"%>
		<%@include file="/template/nav.jsp"%><!-- 각 회원에 맞게 바꿔 주셔야 합니다. -->
		<br> <br> <br> <br> <br>
		<hr class="bar" />
		<span class="navigator deps">홈</span> <span class="navigator deps">&nbsp;&rsaquo;&nbsp;</span>
		<span class="navigator deps">내 메뉴</span> <span class="navigator deps">&nbsp;&rsaquo;&nbsp;</span>
		<span class="navigator final">강의 상세보기</span>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
		<hr class="bar" />
		<article id="article">
			<div id="container">
				<!-- 내용을 넣어주세요 -->

				<div class="detail">
					<h1>[ ${dtoLecInfo.className } ] 상세보기</h1>

					<div class="bar_u"></div>
					<div class="column2_1">
						<div class="row">
							<span class="lab">강의명</span><span class="val">${dtoLecInfo.className }</span>
						</div>
						<div class="row">
							<span class="lab">강사명</span><span class="val">${dtoLecInfo.teacher }</span>
						</div>
						<div class="row">
							<span class="lab">강의실</span><span class="val">${dtoLecInfo.classno }</span>
						</div>
						<div class="row">
							<span class="lab">총원</span><span class="val">${dtoLecRoom.maxNum }</span>
						</div>
						<div class="row">
							<span class="lab">강의코드</span><span class="val">${dtoLecInfo.lecCode }</span>
						</div>
					</div>
					<div class="column2_1">
						<div class="row">
							<span class="lab">개강일</span><span class="val">${dtoLecInfo.openDate }</span>
						</div>
						<div class="row">
							<span class="lab">종강일</span><span class="val">${dtoLecInfo.endDate }</span>
						</div>
						<div class="row">
							<span class="lab">최대결석일</span><span class="val">${dtoLecInfo.maxAB }</span>
						<div class="row">
							<span class="lab">현재인원</span><span class="val">${dtoLecRoom.nowNum }</span>
						</div>
						</div>
						<div class="row">
							<span class="lab">키워드</span><span class="val">${dtoLecInfo.keyword }</span>
						</div>
					</div>
					<div class="clearboth"></div>
					<div class="column2_1">
						<div class="row">
							<span class="lab">강의미리보기</span><span class="val">${dtoLecInfo.preView }</span>
						</div>
					</div>
					<div class="column2_1">
						<div class="row">
							<span class="lab">공개여부</span>
							<c:if test="${dtoLecInfo.status eq 1}">
								<span class="val" style="color: #9E9E9A">비공개</span>
							</c:if>
							<c:if test="${dtoLecInfo.status eq 2}">
								<span class="val" style="color: #F37720">공개</span>
							</c:if>
							<c:if test="${dtoLecInfo.status eq 3}">
								<span class="val" style="color: #0080A4">진행중</span>
							</c:if>
							<c:if test="${dtoLecInfo.status eq 4}">
								<span class="val" style="color: #1B3D20;">종강</span>
							</c:if>
						</div>
					</div>
					<div class="bar_b"></div>
					<div class="contents">
						<span class="val" name="contents">${dtoLecInfo.contents }</span>
					</div>
					<div class="bar_b"></div>
					<c:if test="${web_mem_type eq 4 || web_mem_type eq 5 }">
					<div class="btnline" style="clear:both; margin: 10px 0px; float : right; padding:0px;">
						<button class="button" id="edit_lec">수정</button>
						<button class="button" id="create_lec">개설</button>
					</div>
					</c:if>
					<!--  내용끝 -->
				</div>
				<!-- detail div end -->
			</div>
		</article>
		<%@include file="/template/footer.jsp"%>
	</div>

</body>
</html>