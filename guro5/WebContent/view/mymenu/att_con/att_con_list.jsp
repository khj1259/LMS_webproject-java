<%@page import="com.gurobit5.model.bbs.entity.DtoLecAf"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비트캠프 구로점-출석확인</title>
<!--  css -->
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">

<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	function checkAttStatus(ele) {
		var target = window.event.target;
		console.log(target);
		var id = $(ele).attr("idx");
		console.log(id);
		$.post("${rootPath}ajax.bit?mode=check_att",{'single':id},function(date){
			
			if(date){
				var tokens = date.split("@");
				$(target).parent().parent().replaceWith("<span class='col3 cell att_info' style='color:#"+tokens[1]+"'>"+tokens[0]+"</span>");
			}
		});
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
    width: 40%;
}
.col2 {
    width: 30%;
}
.col3 {
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
	margin-right: 50px;
	margin-top: 30px;
}
.clear{
	clear: both;
}
</style>

</head>
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
		<span class="navigator deps">내메뉴</span> 
		<span class="navigator deps">&nbsp;&rsaquo;&nbsp;</span>
		<span class="navigator final">출결확인</span>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
	<hr class="bar"/>
	<article id="article">
		<div id="container">
		 	<!-- 내용을 넣어주세요 -->
		

	<h1 class="txt_center">출결확인</h1>
		<div id="table">
		<div class="row">
			<span class="cell top col1">학생이름/연락처</span>
			<span class="cell top col2">강의명</span>
			<span class="cell top col3">출결상태</span>
		</div>
		<c:forEach items="${daoMemberlist}" var="dtoMember">
		<div class="row">
			<span class="cell col1">
				<a href="?pageid=attend_con&mode=detail&id=${dtoMember.id}">
				${dtoMember.name}/${0+dtoMember.phone}</a>
			</span>
			<span class="cell col2" >${dtoMember.lecCodeName}&nbsp;</span>
			<span class="cell col3 "><div style="width:50px; margin: auto;"><button class="act button" style="margin: auto; width:50px; height: 30px;" idx="${dtoMember.id}" onclick="checkAttStatus(this);">
				조회
			</button></div></span>
		</div>
		</c:forEach>
		</div><!-- 테이블 -->
				<!--  내용끝 -->
		</div><!-- container -->
</article>
	<%@include file="/template/footer.jsp" %>
    </div>
</body>
</html>
	
</body>
</html>