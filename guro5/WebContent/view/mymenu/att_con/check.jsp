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
        width: 100%;
        margin: 0px auto;
        margin-bottom: 30px;
    }
    .row {
        
    }
    .cell {
        display: inline-block;
        padding: 3px;
        border-bottom: 1px solid lightgray;
        overflow: hidden;
        text-overflow: ellipsis;
        /* white-space: nowrap; */
        text-align: center;
        width:19%;
    }
    .top{
        font-weight: bold;
        background-color: lightgray;
    }
   
    
	h1{text-align: center;}
	.clearboth{
    	clear: both;
    }
    h4{
    	display: inline-block;
    }
    a:link { color: black; text-decoration: none;}
 	a:visited { color: black; text-decoration: none;}
	a:hover { color: blue;}
	
</style>
<title>비트캠프 구로점-출석체크</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	//입실버튼 누를때 함수
	function inChange(data,target){
		$(target).replaceWith('<span class="inT">'+data+'</span>');
	}
	//퇴실버튼 누를때 함수
	function outChange(data,target){
		$(target).replaceWith('<span class="out">'+data+'</span>');
	}
	//결석버튼 누를때 함수
	function func02(target){
		$(target).parent().parent().find('.inT').replaceWith('<span class="inT cell">결석</span>');
		$(target).parent().parent().find('.out').replaceWith('<span class="out cell">결석</span>');
	}
	//수정모드로 바꾸는 함수
	function func03(data,target){
		$(target).find('.absent').hide();
		$(target).find('.out').replaceWith('<span class="cell"><input id="out" value="'+data.outTime+'"> </input></span>');
		$(target).find('.inT').replaceWith('<span class="cell"><input id="inT" value="'+data.inTime+'"> </input></span>');
	}
	
	$(function(){
		//입실버튼 누를 때
		var time;
		$('button.inT').click(function(){
			var id=$(this).val();
			var target = $(this);
			var date = $('h4').text();
			console.log(date);
			$.post('?pageid=attend_con&mode=in',{'id':id, 'today':date},function(data){
				inChange(data,target);
			});
		});
		//퇴실버튼 누를 때
		$('button.out').click(function(){
			var id=$(this).val();
			var target = $(this);
			var date = $('h4').text();
			if(target.parent().parent().find('span.inT').length==0){
				alert('입실시간을 입력하여야 합니다')
			}else{
				$.post('?pageid=attend_con&mode=out',{'id':id,'today':date},function(data){
					outChange(data,target);
				});
			}
		});
		//결석버튼 누를 때
		$('button.absent').click(function(){
			var id=$(this).val();
			var target = $(this);
			var date = $('h4').text();
			$.post('?pageid=attend_con&mode=absent',{'id':id,'today':date},function(){
				func02(target);
			});
			
		});
		//수정버튼 누를 때 
		$('button.edit').click(function(){
			
			var target = $(this).parent().parent();
			var id=$(this).val();
			var date = $('h4').text();
			if(target.find('span.out').length==0){
				alert('퇴실시간이 입력되어야 합니다');
			}else{
				$(this).replaceWith('<button id="confirm">완료</button><button id="cancel">취소</button>');
				$('.edit').hide();
				$.post('?pageid=attend_con&mode=edit',{'id':id,'today':date},function(data){
					console.log(data);
					func03(data,target);
			});
				
			}
		});
		//수정>완료 버튼 누를때
		$(document).on('click','button[id=confirm]',function(){
			var id=$(this).parent().parent().find('input[type=hidden]').val();
			var inT=$(this).parent().parent().find('input[id=inT]').val();
			var outT=$(this).parent().parent().find('input[id=out]').val();
			var date = $('h4').text();
			$.post('?pageid=attend_con&mode=edit2',{'id':id,'inT':inT,'outT':outT,'today':date},function(){
				window.location.href='?pageid=attend_con&mode=check&today='+date;
			});
		});
		//수정>취소 버튼 누를때
		$(document).on('click','button[id=cancel]',function(){
			var date = $('h4').text();
			window.location.href='?pageid=attend_con&mode=check&today='+date;
		});
		//조회 버튼 누를때
		$('button#search').click(function(){
			var date = $(this).parent().find('input[type=date]').val();
			window.location.href='?pageid=attend_con&mode=check&today='+date;
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
		<span class="navigator final">출결관리</span>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
		<hr class="bar" />
		<article id="article">
			<div id="container">

					<!-- 내용을 넣어주세요 -->
				<div id="table">
					<h1>출결 관리</h1>
					<div>
						<h4>${requestScope.today }</h4>
						<input type="date"></input>
						<button id="search">조회</button>
					</div>
					<div>
						<div class="row">
							<span class="cell top col1">이름</span> 
							<span class="cell top col2">IN</span> 
							<span class="cell top col3">OUT</span> 
							<span class="cell top col4">결석</span>
							<span class="cell top col5">수정</span>
						</div>
						<c:forEach items="${requestScope.listM }" var="dtoAttend">
							<div class="row">
								<input type="hidden" value="${dtoAttend.id}"></input> 
								<span class="cell"><a href="?pageid=attend_con&mode=detail&id=${dtoAttend.id}">${dtoAttend.name }</a></span>
								<c:choose>
									<c:when test="${dtoAttend.inDate eq null}">
										<span class="cell"><button class="inT" value="${dtoAttend.id}">입실</button></span>
									</c:when>
									<c:otherwise>
										<span class="inT cell">${dtoAttend.inDate }</span>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${dtoAttend.outDate eq null}">
										<span class="cell"><button class="out" value="${dtoAttend.id}">퇴실</button></span>
									</c:when>
									<c:otherwise>
										<span class="out cell">${dtoAttend.outDate }</span>
									</c:otherwise>
								</c:choose>
								<span class="cell"><button class="absent" value="${dtoAttend.id}">결석</button></span>
								<span class="cell"><button class="edit" value="${dtoAttend.id}">수정</button></span>
							</div>
						</c:forEach>
						<div class="clearboth"></div>
					</div>
				</div>


			</div>
		</article>
		<%@include file="/template/footer.jsp"%>
	</div>


</body>
</html>