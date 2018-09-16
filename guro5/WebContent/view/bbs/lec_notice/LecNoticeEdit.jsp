<%@page import="com.gurobit5.model.bbs.entity.DtoLecAf"%>
<%@page import="java.util.ArrayList"%>
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
<style>
	h1{
		text-align: center;
	}
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
    .row55{
    	display:inline-block;
    	width:100%;
    	height: 350px;
    	border-bottom: 1px solid black;
    }
    .bt{
    	float: right;
    	margin-top: 10px;
    	margin-bottom: 20px;
    }
    .clearboth{
    clear: both;
    }
    
</style>
<title>비트캠프 구로점-강의공지사항 수정</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('button[type="button"]').click(function(){
			window.location.href='?mode=list';
		});
	});
</script>
</head>
<body>
	<div id="body">
		<%@include file="/template/header.jsp"%>
		<%@include file="/template/nav.jsp"%><!-- 각 회원에 맞게 바꿔 주셔야 합니다. -->
		<!-- 학생이면 nav_student 강사면 nav_teacher template 폴더를 확인하세요 -->
		<br> <br> <br> <br> <br>
		<hr class="bar" />
		<span class="navigator deps">홈</span>
		<span class="navigator deps">&nbsp;&rsaquo;&nbsp;</span>
		<span class="navigator deps">내메뉴</span>
		<span class="navigator deps">&nbsp;&rsaquo;&nbsp;</span>
		<span class="navigator final">강의공지사항 수정</span>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
		<hr class="bar" />
		<article id="article">
			<div id="container">
				<div>

					<!-- 내용을 넣어주세요 -->
					<div class="detail">
					<h1>강의게시판 수정</h1>
						<!-- title, contents 필수 -->
						<form method="post">
							<div class="toprow">
								<span class="cell sub">제목</span>
								<input type="text" value="${dtoLecNotice.title }"
									name="title" id="title" /> <input type="hidden"
									value="${dtoLecNotice.num }" name="num" />
							</div>
							<div>
								<span class="cell sub">글쓴이(ID)</span><span>${dtoLecNotice.id }</span> 
								<span class="cell sub">작성일</span><span>${dtoLecNotice.nalja }</span>
								<span class="cell sub">조회수</span><span>${dtoLecNotice.viewNum }</span>
							</div>
							<div>
								<textarea type="text" name="contents" id="contents" class="row55">${dtoLecNotice.contents}</textarea>
							</div>
							<div class="bt">
								<button type="submit" class="button">수정</button>
								<button type="button" class="button">취소</button>
							</div>
						</form>
						<div class="clearboth"></div>
					</div>
					<!--  -->

				</div>
			</div>
		</article>
		<%@include file="/template/footer.jsp"%>
	</div>

</body>
</html>