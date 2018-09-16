<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%

	String rootPath = (String)request.getAttribute("rootPath");
	pageContext.setAttribute("rootPath", rootPath);

	//한개의 dto에서 공통된 정보(이름,지각,결석,최대결석일)를 가져오기 위해
	ArrayList list = (ArrayList)request.getAttribute("list");
	pageContext.setAttribute("dtoAttend", list.get(0));
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
    #table {
        width: 75%;
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
    }
    .top{
        font-weight: bold;
        background-color: lightgray;
    }
    .col1 {
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
    }
	h1{text-align: center;}
	
	.clearboth{
    	clear: both;
    }
    .state{
    	font-size: 30px;
    	margin-bottom: 10px;
    }
    .bt{
    	float: right;
    	margin-top: 10px;
    	margin-bottom: 20px;
    }
    #st1{color:blue;}
    #st2{color: skyblue;}
    #st3{color: maroon;}
    #st4{color: red;}
    #st5{color: red;}
    
    
</style>
<title>비트캠프 구로점-출결관리 상세</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(function(){
	
	//뒤로버튼 누를 때 
	$('#back').click(function(){
		window.location.href='?pageid=attend_con&mode=check';
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
		<c:if test="${web_mem_type eq 1 }">
		<span class="navigator final">출결관리</span>
		</c:if>
		<c:if test="${web_mem_type eq 0 }">
		<span class="navigator final">출석확인</span>
		</c:if>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
		<hr class="bar" />
		<article id="article">
			<div id="container">
					
				<!-- 내용을 넣어주세요 -->
				<div id="table">
					<h1>출석확인</h1>
					<div class="state">
						<span>${dtoAttend.name } &nbsp님의 출결&nbsp&nbsp </span> 
						<span>지각:</span><span id="late">${dtoAttend.late }</span>
						<span>결석:</span><span id="absent">${dtoAttend.absent }</span>
						<!-- (지각횟수*0.5)+결석횟수 -->
						<c:set var="count" value="${dtoAttend.late*0.5+dtoAttend.absent }"></c:set>
						<!-- 최대결석일과 내 결석횟수를 비교하여 수료가능성 5단계로 표시  -->
						<c:choose>
							<c:when test="${(dtoAttend.max_ab*0.2) gt pageScope.count }"><span id="st1">수료가능성:아주좋음</span></c:when>
							<c:when test="${(dtoAttend.max_ab*0.4) gt pageScope.count }"><span id="st2">수료가능성:좋음</span></c:when>
							<c:when test="${(dtoAttend.max_ab*0.6) gt pageScope.count }"><span id="st3">수료가능성:보통</span></c:when>
							<c:when test="${(dtoAttend.max_ab*0.8) gt pageScope.count }"><span id="st4">수료가능성:위험</span></c:when>
							<c:otherwise><span id="st5">수료가능성:매우위험</span></c:otherwise>
						</c:choose>
					</div>
					<div>
						<div>
							<span class="cell top col1">일자</span> 
							<span class="cell top col2">입실</span> 
							<span class="cell top col3">퇴실</span> 
							<span class="cell top col4">출결상태</span>
						</div>
						<c:forEach items="${requestScope.list }" var="dtoAttend">
							<div>
								<span class="cell col1">${dtoAttend.nalja }</span> 
								<span class="cell col2">${dtoAttend.inDate }</span>
								<span class="cell col3">${dtoAttend.outDate }</span> 
								<span class="cell col4">${dtoAttend.state }</span>
							</div>
						</c:forEach>
					</div>
					<c:if test="${web_mem_type eq 1 }">
					<div class="bt">
						<button class="button" id="back">뒤로</button>
					</div>
					</c:if>
					<div class="clearboth"></div>
				</div>


			</div>
		</article>
		<%@include file="/template/footer.jsp"%>
	</div>

</body>
</html>