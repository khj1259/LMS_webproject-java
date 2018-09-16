<%@page import="com.gurobit5.model.entity.DtoLecInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String rootPath = (String)request.getAttribute("rootPath");
	pageContext.setAttribute("rootPath", rootPath);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비트캠프 구로점 - 강의소개</title>

<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>

<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">


<style type="text/css">

.card{
	width: 18%;
	float: left;
	margin : 2px;
	padding : 2px;
	border: 1px solid #e2e2e2;
}

.keyword_img{
	width: 80%;
	margin: 2px auto;
	display: block;
}
.txt_center{
	text-align: center;
}
.txt_left{
	text-align: left;
}
.txt_right{
	text-align: right;
}

 a:link { color: black; text-decoration: none;}
 a:visited { color: black; text-decoration: none;}
 a:hover { color: blue;}
 
 .list_bar{
 	width: 80%;
 	margin: 20px auto;
 }
 
 div.card:hover{
     background: #F4CDA5;
 }
 
 .clr_both{
	clear: both;
}
.pagenavi_bar{
	width: 80%;
	margin: 2px auto;
	text-align: center;
}
.smbtn{
display: inline-block; 
border: 2px solid lightgray;
background-color: white;
float: left;
text-align: center;
cursor: pointer;
position: relative;
box-sizing: border-box;
overflow: hidden;
margin: 2px;
}
.smbtn:hover{
	background-color: lightgray;
	font-weight: bold;
}

</style>
<script>
	$(function(){
		$(".lec_preview").click(function(event){
			event.preventDefault();
			var url = $(this).attr("txt");
			window.open(url, 'youtube',
				'location=yes,resizable=yes,status=yes,menubar=yes,resizable=yes,width=500,height=500');
		});
	
		
		$('.lec_consult').click(function(e){
			e.preventDefault();
			var leccode = $(this).attr("txt");
			window.location.href = "${rootPath}bbs/consult.bit?mode=formlec&idx="+leccode;
		});
		
		$('.pagenavi_bar').find('a').each( function(){
			var now = $(this).attr("now");
			var after = $(this).attr("after");
				$(this).css('display','inline-block');		
			if(now == after) {
				$(this).find('button').css('background-color','lightgray');		
			}
		});
		
		$(".keyword_img").each(function(){
			var target= $(this);
			console.log(target);
			var img_text= $(this).text();
			if(img_text=="java"){
				target.replaceWith( "<img src='${rootPath}img/category/java.png' class='keyword_img'/>" );
			}else if(img_text=="db"){
				target.replaceWith( "<img src='${rootPath}img/category/db.png' class='keyword_img'/>" );
			}else if(img_text=="web"){
				target.replaceWith( "<img src='${rootPath}img/category/web.png' class='keyword_img'/>" );
			}
			
		});
		
	});// 온로드 시점
</script>	
<body>
	<div id="body">
	<%@include file="/template/header.jsp" %>
	<%@include file="/template/nav.jsp" %><!-- 각 회원에 맞게 바꿔 주셔야 합니다. -->
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
		<span class="navigator final">강의목록</span>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
	<hr class="bar"/>
	<article id="article">
		<div id="container">
<!--  내용시작 -->
	
	
	<div class="list_bar">
		<c:set var="bean" value="<%=new DtoLecInfo() %>"></c:set>
		<c:forEach items="${requestScope.lecbeans }" var="bean">
			<div class="card">
				<a href="?pageid=leclist&idx=${bean.lecCode}"><span class="keyword_img">${bean.keyword }</span></a>
				<div class="mainT txt_center">
					<h1><a href="?pageid=leclist&idx=${bean.lecCode}">${bean.className }</a></h1>
				</div>
				<div class="subT txt_left"><h5>${bean.openDate } ~ </h5></div>
				<div class="subT txt_right"><h5>${bean.endDate }</h5></div>
				<div style="width:85%; margin: 0PX auto;">
				<!-- 버튼기능 넣어야됨... -->
				<button class="smbtn lec_preview" txt="${bean.preView}">미리보기</button>
				<button class="smbtn lec_consult" txt="${bean.lecCode}" >신청하기</button>
				</div>
			</div>
		</c:forEach>
	<div class="clr_both"></div>
	<div class="pagenavi_bar">
		<% 	pageContext.setAttribute("wantUrl", "?pageid=leclist" ); %>
		<%@ include file="/template/pagenavi.jsp" %>
	</div>
	</div>
	<div class="clr_both"></div>
	
			<!--  내용끝 -->
		</div>
</article>
	<%@include file="/template/footer.jsp" %>
    </div>
</body>
</html>