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
     	margin-right: 30px;
     	margin-left: 30px;
     	margin-bottom: 5px;
     	margin-top: 5px;
     	text-align: left;
     	font-size: 24px;
     }
    .lab{
    	width: 100px;
    	text-align:center;
     	border-right-color: gray;
     	border-right-width:2px;
     	border-right-style:solid;
     	background-color: lightgray;
    }
    .val{
    	width: 250px;
    }
    .bt{
    	width: 300px;
    	margin: 0px auto;
    	margin-top: 30px;
    }
    
    .clearboth{
    	clear: both;
    }
</style>
<script type="text/javascript" src="${rootPath }js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#cancel').click(function(){
			window.location.href='?pageid=id_con&mode=detail&idx=${dtoMember.id}';
		});
		
		$('button[type=submit]').click(function(e){
			var pw1=$('#pw1').val();
			var pw2=$('#pw2').val();
			console.log(pw1);
			console.log(pw2);
			if(pw1!=pw2){
				e.preventDefault();
				alert('비밀번호 불일치');
			}
		});
	});
</script>

<title>비트캠프 구로점-마이페이지 수정</title>
<!--  css -->
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
					<h1>마이페이지 수정</h1>
					<div  class="con">
						<form method="post">
							<div>
								<span class="lab">id</span><span class="val">${dtoMember.id}</span>
								</br><span class="lab">이름</span><span class="val">${dtoMember.name}</span>
								</br> <span class="lab">휴대폰</span>
								<input class="val" name="phone" value="${dtoMember.phone}" /> 
								</br><span class="lab">이메일</span>
								<input class="val" name="email" value="${dtoMember.email}" /> 
								</br> <span class="lab" for=" memType">계정유형</span>
								<c:if test="${dtoMember.memType eq 0}"><span class="val">학생</span></c:if>
								<c:if test="${dtoMember.memType eq 1}"><span class="val">강사</span></c:if>
								<c:if test="${dtoMember.memType eq 2}"><span class="val">취업</span></c:if>
								<c:if test="${dtoMember.memType eq 3}"><span class="val">영업</span></c:if>
								<c:if test="${dtoMember.memType eq 4}"><span class="val">행정</span></c:if>
								<c:if test="${dtoMember.memType eq 5}"><span class="val">관리자</span></c:if>
								<c:if test="${dtoMember.memType eq 99}"><span class="val">임시</span></c:if>

								</br><span class="lab">강의코드</span><span class="val">${dtoMember.lecCode}</span>
								</br> <input type="hidden" name="id" value="${dtoMember.id }" /> 
								<label class="lab" for="pw1">비밀번호</label> <input class="val" type="password" id="pw1" name="pw1" /></br>
								<label class="lab" for="pw2">비밀번호 확인</label> <input class="val" type="password" id="pw2" name="pw2" />
							</div>
							<div class="bt">
								<button type="submit" class="edit button">완료</button>
								<button type="reset" id="cancel" class="button">취소</button>
							</div>
						</form>
					</div>
					<div class="clearboth"></div>
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