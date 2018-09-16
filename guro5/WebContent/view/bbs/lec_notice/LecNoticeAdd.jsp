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
	#container{
		align-content: center;
		text-align: center;
	}
    .addPage{
    	width: 500px;
        border-collapse: collapse;
        text-align: left;
        line-height: 1.5; 
        margin : 20px auto;
    }
    .subject{
        width: 150px;
        padding: 10px;
        font-weight: bold;
        vertical-align: top;
    }
    .input{
        width: 350px;
        padding: 10px;
        vertical-align: top;
        border: 1px solid lightgray;
    }
    .contentfield{ /*textarea로 바꿔야함*/
        margin-top: 20px;
        height: 200px;
    }
    .clearboth{
    clear: both;
    }
    .btn{
    	float: right;
    	margin-right: 75px;
    	margin-top: 10px;
    }
</style>
<title>비트캠프 구로점-강의공지 작성</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('button[type="button"]').click(function(){
			window.history.back();
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
		<span class="navigator final">강의공지사항 작성</span>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
	<hr class="bar"/>
		<article id="article">
			<div id="container">
				<div>
				
					<!-- 내용을 넣어주세요 -->
					<div>
					<h1>강의공지사항 작성</h1>
						<!-- title,contents 필수 -->
						<form method="post" class="addPage">
							<div>
								<label for="title" class="subject">제목</label> 
								<input type="text" name="title" id="title" class="input"/>
							</div>
							<div>
								<label for="contents" class="subject content">내용</label>
								<textarea type="text" name="contents" id="contents" class="input contentfield" ></textarea>
							</div>
							<div class="btn">
								<button type="submit" class="button">완료</button>
								<button type="button" class="button">취소</button>
							</div>
						<div class="clearboth"></div>
						</form>
					</div>

				</div>
			</div>
		</article>
		<%@include file="/template/footer.jsp" %>
    </div>
	
</body>
</html>