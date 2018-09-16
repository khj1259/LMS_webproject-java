<%@page import="com.gurobit5.model.entity.DtoLecInfo"%>
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
        width:18%;
        margin-left: 6px;
    }
    .top{
        font-weight: bold;
        background-color: lightgray;
    }
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
<title>비트캠프 구로점-성적수정</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	
	$(function(){
		//완료 버튼을 누를때 
		$('#confirm').click(function(){
			var str='';
			//입력값을 문자열로 받아내기
			$('input').each(function(idx,ele){
				if(idx==0){
					str=$(this).val();
				}else{
					var id = $(this).attr('id');
					if(id=='id'){
						str=str+'/'+$(this).val();
					}else{
						str=str+','+$(this).val();	
					}
				}
			});
			
			$.post('?pageid=score_con&mode=edit',{'str':str},function(){
				window.location.href='?pageid=score_con&mode=list';
			});
		});

		//취소버튼 누를때
		$('#cancel').click(function(){
			window.location.href='?pageid=score_con&mode=list';
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
					<h1>성적 관리(수정)</h1>
						<div>
							<span class="cell top">학생목록</span> 
							<span class="cell top">JAVA</span> 
							<span class="cell top">WEB</span> 
							<span class="cell top">DB</span>
							<span class="cell top">총점</span>
						</div>
						<div>
							<c:forEach items="${requestScope.list }" var="dtoScore">
								<c:choose>
									<c:when test="${dtoScore.check eq true }">
										<div target="on">
											<input type="hidden" value="${dtoScore.id }" name="id" id="id" /> 
											<span class="cell col">${dtoScore.name }</span> 
											<span class="cell col"><input type="text" value="${dtoScore.java }" name="java" id="java" /></span>
											<span class="cell col"><input type="text" value="${dtoScore.web }" name="web" id="web" /> </span>
											<span class="cell col"><input type="text" value="${dtoScore.db }" name="db" id="db" /> </span>
											<span class="cell col"><input type="text" value="${dtoScore.tot }" name="tot" id="tot" /></span>
										</div>
									</c:when>
									<c:otherwise>
										<div>
											<span class="cell col">${dtoScore.name }</span> 
											<span class="cell col">${dtoScore.java }</span>
											<span class="cell col">${dtoScore.web}</span> 
											<span class="cell col">${dtoScore.db }</span> 
											<span class="cell col">${dtoScore.tot }</span>
										</div>
									</c:otherwise>
								</c:choose>

							</c:forEach>
						</div>
					<div class="bt">
						<button class="button" id="confirm">완료</button>
						<button class="button" id="cancel">취소</button>
					</div>
					<div class="clearboth"></div>
				</div>


			</div>
		</article>
		<%@include file="/template/footer.jsp"%>
	</div>


</body>
</html>