<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	h1{
		text-align: center;
		margin-bottom: 30px;
	}
    .con{
        width: 100%;
        text-align: center;
    }
     .lab,.val{
     	display: inline-block;
     	width: 100px;
     	margin-right: 30px;
     	margin-left: 30px;
     	margin-bottom: 5px;
     	margin-top: 5px;
     	text-align: left;
     	font-size: 26px;
     }
    .lab{
    	width: 100px;
    	text-align:center;
     	border-right-color: gray;
     	border-right-width:2px;
     	border-right-style:solid;
     	background-color: lightgray;
    }
    .bt{
    	width: 10px;
    	margin: 0px auto;
    	margin-top: 30px;
    }
    
    .clearboth{
    clear: both;
    }
</style>
<script type="text/javascript" src="${rootPath }js/jquery-1.12.4.min.js"></script>
<title>비트캠프 구로점-마이페이지</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
</head>
<body>
	<div id="body">
		<%@include file="/template/header.jsp"%>
		<%@include file="/template/nav.jsp"%><!-- 이걸로 다 바꿔주세요 분기로 처리합니다. -->
		<br> <br> <br> <br> <br>
		<hr class="bar" />
		<span class="navigator deps">홈</span> 
		<span class="navigator deps">&nbsp;&rsaquo;&nbsp;</span>
		<span class="navigator final">마이페이지</span>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
		<hr class="bar" />
		<article id="article">
			<div id="container">
				<div>
					<!--  내용시작 -->
					<h1>마이페이지</h1>
					<div class="con">
						<div>
							<span class="lab">id</span><span class="val">${dtoMember.id}</span>
							</br> <span class="lab">이름</span><span class="val">${dtoMember.name}</span>
							</br> <span class="lab">휴대폰</span><span class="val">${dtoMember.phone}</span>
							</br><span class="lab">이메일</span><span class="val">${dtoMember.email}</span>
							</br> <span class="lab">계정유형</span><span class="val">${dtoMember.memTypeName}</span>
							</br> <span class="lab">강의코드</span><span class="val">${dtoMember.lecCode}</span>
							</br>
						</div>
						<div class="bt">
							<c:choose>
								<c:when test="${dtoMember.id eq web_id}">
									<button class="button edit">수정하기</button>
									<script type="text/javascript">
										$(function() {
											$('.edit').click(function() {
												window.location.href = '?pageid=id_con&mode=edit&idx=${dtoMember.id}';
											});
										});
									</script>
								</c:when>
							</c:choose>
						</div>
						<div class="clearboth"></div>
					</div>

					<!--  내용끝 -->
				</div>
				<!-- 빈 div  end -->
			</div>
			<!-- container end -->
		</article>
		<%@include file="/template/footer.jsp"%>
	</div>



</body>
</html>