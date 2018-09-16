<%@page import="com.gurobit5.model.entity.DtoLecRoom"%>
<%@page import="com.gurobit5.model.entity.DtoMember"%>
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

<title>비트캠프 구로점-강의개설</title>
<!--  css -->
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">

<!-- script -->
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<style>
	h1{
		text-align: center;
	}
	
    .detail{
        width: 80%;
        margin: 0px auto;
        
    }
	.column2_1{
		width: 50%;
		float: left;
	}
	.column2_2{
		width: 100%;
		clear: both;
	}
    .row{
		height : 30px;
        width: 100%;
        margin: 1px auto;
		vertical-align : middle;
        display: inline-block;
    }
	.lab{
		background-color : #D3D3D3;

        height: 25px;
		line-height : 25px;
		
		width: 25%;
		
		display: inline-block;
		text-align: center;
		margin-right: 1%;
	}
	
	.val{
        height: 25px;
		line-height : 25px;
		width: 70%;
		display: inline-block;
		padding-left : 1%;
		text-align: left; 
	}
	select.val{
        height: 22px;
		padding:0px;
		vertical-align: bottom;
	}

	input.val{
        height: 22px;
		padding:0px;
		vertical-align: bottom;
	}

	textarea.val{
	margin-top: 5px;
    height: 100%;
    min-height: 50px;
	}
	
	.contents{
	margin-top: 5px;
    height: 100%;
    min-height: 100px;
	}
	
    .clearboth{
    clear: both;
    }
    
    .bar_u{
    	height :1px;
    	border-top: 2px solid black; 
    	margin-bottom : 2px;
    	clear:both
    }
    
    .bar_b{
    	height :1px;
    	margin-top : 2px;
    	border-bottom: 2px solid black; 
    	clear:both
    }
    
</style>
<script>
	function calcDate(start, end) {
		var date1 = new Date(start);
		var date2 = new Date(end);
		var count = 0;
		while (true) {
			var temp_date = date1;
			if (temp_date.getTime() > date2.getTime()) {
				console.log("count : " + count);
				break;
			}else{
				var tmp = temp_date.getDay();
				if (tmp == 0 || tmp == 6) {
					// console.log("주말");
				}else{
					// console.log("평일");
					count++;
				}
				temp_date.setDate(date1.getDate() + 1);
			}// if- else end
		}// while end */
		return count;
	}// calcDate();
	
	function checkBeforeSubmit() {
		// 유효성검사에 성공하면 true / 실패하면 false
		// 강의제목
		var className = $('#className').val();
		var teacher;
		$('#teacher>option').each(function() {
			if ($(this).prop('selected'))
				teacher = $(this).val();
		});
		var classno;
		$('#classno>option').each(function() {
			if ($(this).prop('selected'))
				classno = $(this).val();
		});
		var openDate = $('#openDate').val();
		var endDate = $('#endDate').val();
		var maxAB = calcDate(openDate, endDate);
		var keyword;
		$('#keyword>option').each(function() {
			if ($(this).prop('selected'))
				keyword = $(this).val();
		});
		console.log(maxAB);
		// 일단 값만 받았음 유효성 검사해야됨
		// 검사해야될것 : className 
		if(maxAB<=1){
			alert('개강일-종강일을 다시 한번 확인해주세요.');
			// 에러 넣기
			return false
		}else{
			$('#maxAB').val( Math.ceil(maxAB*0.2) );
			return true;
		}
	}//

	$('select#status').ready(function(){
		var number = $(this).attr("number");
		$(this).find("option").each(function(){
			var tmp = $(this).val();	
			if(tmp==1)$(this).text("비공개");
			else if(tmp==2)$(this).text("공개");
			else if(tmp==3)$(this).text("진행중");
			else if(tmp==4)$(this).text("종강");
			
			if(number==tmp)$(this).prop("selected", true);
			
		});	
	});
	
	$(function() {
		form = document.querySelector('form');
		$('form').submit(function(e){
			e.preventDefault();
			if(checkBeforeSubmit()){
				// 참일때 submit
				form.submit();
			}else{
				
			}
		});
	});
</script>
<style>
 .column2_1 {
 	witdh: 200px;
 	float : left;
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
		<span class="navigator deps">내 메뉴</span>
		<span class="navigator deps">&nbsp;&rsaquo;&nbsp;</span>
		<span class="navigator final">강의개설</span>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
	<hr class="bar"/>
	<article id="article">
		<div id="container">
		 	<div>
		 	<!-- 내용을 넣어주세요 -->


		<h1>강의 개설</h1>
		<form method="post">
		<div class="bar_u"></div>
		<div class="column2_1">
			<div class="row"><label for="className" class="lab">강의명</label><input id="className" name="className" type="text" class="val" value="${dtoLecInfo.className }"/></div>
			<div class="row"><label  class="lab" for="teacher" >강사명</label><select id="teacher" class="val" name="teacher"> 
			<c:set var="beanTea" value="<%=new DtoMember() %>"></c:set>
			<c:forEach items="${requestScope.tealist }" var="beanTea">
				<c:if test="${beanTea.name eq dtoLecInfo.teacher}" >
					<option selected="selected" >${beanTea.name}</option> 
				</c:if>
				<c:if test="${beanTea.name ne dtoLecInfo.teacher}" >
					<option>${beanTea.name}</option> 
				</c:if>
			</c:forEach>
			</select></div>
			<div class="row"><label for="classno" class="lab">강의실</label><select id="classno" name="classno" class="val" >  
			<c:set var="beanRoom" value="<%=new DtoLecRoom() %>"></c:set>
			<c:forEach items="${requestScope.roomlist }" var="beanRoom">
					<option value="${beanRoom.classno}">${beanRoom.classno}실/${beanRoom.maxNum}명</option> 
			</c:forEach>
			</select></div>
			<div class="row"><label for="lecCode" class="lab">강의코드</label><span class="val auto">자동계산</span></div>
		</div>
		<div class="column2_1">
			<div class="row"><label for="openDate" class="lab">개강일</label><input id="openDate" type="date" name="openDate" class="val" /></div>
			<div class="row"><label for="endDate" class="lab">종강일</label><input id="endDate" type="date" name="endDate" class="val" /></div>
			<div class="row"><label for="maxAB" class="lab">최대결석일</label><span class="val auto">자동계산</span><input id="maxAB" type="hidden" name="maxAB" value="null"></div>
			<div class="row"><label for="keyword" class="lab">키워드</label><select id="keyword" name="keyword" class="val">
				<option selected="selected">java</option>
				<option>web</option>
				<option>db</option>
			</select></div>
		</div>
		<div class="clearboth"></div>
		<div class="column2_1"> 
			<div class="row"><label for="preview" class="lab">강의미리보기 링크</label><input type="text" id="preview" class="val long" name="preview" /></div>
		</div>
		<div class="column2_1">
			<div class="row"><label for="status" class="lab">공개여부</label><select id="status" class="val" name="status" >
			<c:forEach begin="1" end="4" var="indexX" >
				<option value="${indexX }"></option> 
			</c:forEach>
			</select></div>
		</div>
		<div class="bar_b"></div>
		<div class="contents"> 
			<textarea class="val" name="contents"></textarea>
		</div>
		<div class="bar_b"></div>
		
		
		<div class="btnline" style="clear:both; margin: 10px 0px; float : right; padding:0px;">
			<button class="button" type="submit">전송</button>
			<!-- <button type="reset">리셋</button>  -->
		</div>
		</form>


			<!--  내용끝 -->
		 	</div>
		</div>
</article>
	<%@include file="/template/footer.jsp" %>
    </div>
</body>
</html>