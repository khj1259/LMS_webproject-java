<%@page import="com.gurobit5.model.entity.DtoLecInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String rootPath = (String)request.getAttribute("rootPath");
	pageContext.setAttribute("rootPath", rootPath);
%><!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
    #table {
        width: 80%;
        margin: 0px auto;
        margin-bottom: 30px;
    }
    .cell {
        display: inline-block;
        padding: 3px;
        border-bottom: 1px solid lightgray;
        overflow: hidden;
        text-overflow: ellipsis;
        /* white-space: nowrap; */
        text-align: center;
        width:18%
    }
    .top{
    	margin-left:5px;
        font-weight: bold;
        background-color: lightgray;
    }
    /* .col1 {
        width: 24%;
    }
    .col2 {
        width: 25%;
    }
    .col3 {
        width: 25%;
    }
    .col4 {
        width: 20%;
    } */
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
<title>비트캠프 구로점-성적관리</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">

	$(function(){
		//수정 버튼을 누를때 
		$('#edit').click(function(){
			//체크된 학생의 아이디를 배열에 저장
			var str='';
			$('input[type=checkbox]').each(function(idx){
				if($(this).prop('checked')){
					if(idx==0) str=$(this).val();
					else{str=$(this).val()+','+str};
				}
			});
			window.location.href='?pageid=score_con&mode=edit&ids='+str;
			
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
		<span class="navigator final">성적관리</span>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
		<hr class="bar" />
		<article id="article">
			<div id="container">

				<!-- 내용을 넣어주세요 -->
				<div id="table">
					<h1>성적 관리</h1>
					<div>
						<div>
							<span class="cell top">학생목록</span> 
							<span class="cell top">JAVA</span> 
							<span class="cell top">WEB</span> 
							<span class="cell top">DB</span>
							<span class="cell top">총점</span>
						</div>
						<c:forEach items="${requestScope.list }" var="dtoScore">
							<div>
								<input type="checkbox" name="id" value="${dtoScore.id }">
								<span class="cell col">${dtoScore.name }</span> 
								<span class="cell col">${dtoScore.java }</span> 
								<span class="cell col">${dtoScore.web}</span>
								<span class="cell col">${dtoScore.db }</span> 
								<span class="cell col">${dtoScore.tot }</span>
							</div>
						</c:forEach>
					</div>
					<div class="bt">
						<button class="button" id="edit">수정</button>
					</div>
					<div class="clearboth"></div>
				</div>


			</div>
		</article>
		<%@include file="/template/footer.jsp"%>
	</div>

</body>
</html>