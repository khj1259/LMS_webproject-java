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
        width: 75%;
        margin: 0px auto;
        margin-bottom: 130px;
        margin-top: 90px;
    }
    .cell {
        display: inline-block;
        padding: 3px;
        border-bottom: 1px solid lightgray;
        overflow: hidden;
        text-overflow: ellipsis;
        /* white-space: nowrap; */
        text-align: center;
        /* margin-left: 10px; */
    }
    .top{
        font-weight: bold;
        background-color: lightgray;
    }
    .col1{width:25%;}
    .col2{width:17%;}
    .col3{width:17%;}
    .col4{width:17%;}
    .col5{width:17%;}
	h1{
		text-align: center;
	}
	.clearboth{
    	clear: both;
    }
    .score{
    	font-size: 25px;
    	margin-bottom: 10px;
    }
    
</style>
<title>비트캠프 구로점 - 성적확인</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	
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
		<span class="navigator final">성적확인</span>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
		<hr class="bar" />
		<article id="article">
			<div id="container">
				
				<!-- 내용을 넣어주세요 -->
				<h1>성적확인</h1>
				<div id="table">
					<div class="score">
						<span >${dtoScore.name }  &nbsp님의 성적 </span>
					</div>
					<div>
						<div>
							<span class="cell top col1">강의명</span> 
							<span class="cell top col2">JAVA</span> 
							<span class="cell top col3">DB</span> 
							<span class="cell top col4">WEB</span>
							<span class="cell top col5">총점</span>
						</div>
						<div>
							<span class="cell col1">${dtoScore.class_name }</span> 
							<span class="cell col2">${dtoScore.java }</span>
							<span class="cell col3">${dtoScore.db }</span> 
							<span class="cell col4">${dtoScore.web}</span> 
							<span class="cell col5">${dtoScore.tot }</span>
						</div>
					</div>
					<div class="clearboth"></div>
				</div>

			</div>
		</article>
		<%@include file="/template/footer.jsp"%>
	</div>
	
</body>
</html>