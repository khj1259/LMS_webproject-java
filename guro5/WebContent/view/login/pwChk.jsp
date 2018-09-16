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
<script type="text/javascript">

</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비트캠프 구로점-비밀번호 확인</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
</head>
<body>
	<div id="body">
		<%@include file="/template/header.jsp"%>
		<%@include file="/template/nav.jsp"%><!-- 각 회원에 맞게 바꿔 주셔야 합니다. -->
		<!-- 학생이면 nav_student 강사면 nav_teacher template 폴더를 확인하세요 -->
		<br> <br> <br> <br> <br>
		<hr class="bar" />
		<article id="article">
			<div id="container">
				<div>
				<!-- 내용을 넣어주세요 -->
	<c:choose>
	<c:when test="${findM eq '없음' }">
		<div>
			<label>잘못된 정보를 입력하셨습니다.</label>
			<button type="button" onclick="window.history.back()">뒤로가기</button>
		</div>
	</c:when>
	<c:otherwise>	
		<div>
			<label>새로운 PW : ${findM}</label>
		</div>
		 <button type="button" onclick="location.href='?mode=list'">로그인</button>
	</c:otherwise>
	</c:choose>
		<!--  -->
				</div>
			</div>
			<div style="clear:both;"></div>
			
		</article>
		<%@include file="/template/footer.jsp"%>
	</div>
</body>
</html>