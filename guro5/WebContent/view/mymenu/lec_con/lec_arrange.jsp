<%@page import="jdk.nashorn.internal.runtime.RewriteException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%
	String idx = request.getParameter("idx");
	pageContext.setAttribute("idx", idx);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비트캠프 구로점-학생배정</title>
<!--  css -->
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>

<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>

<script type="text/javascript">


function remarker(){
	var remark = $("div[chck=yes]");

	if(remark.length!=0){
		remark.each(function(){
			$(this).parent().css('border', '1px solid red');
		});
	}
}// remarker();
function clearBorder(){
	$("#max_num").removeClass('err');
	$("div#nonelistForm").css("border","#ccc solid 1px");
	$("div#nowlistForm").css("border","#ccc solid 1px");
}
function minusArrange(){
	clearBorder();
	var orgin = $("div#nowlistForm");
	var target = $("div#nonelistForm");
	arrange(orgin,target,false);
}
function plusArrange(){
	clearBorder();
	var orgin = $("div#nonelistForm");
	var target = $("div#nowlistForm");
	arrange(orgin,target,true);
}
function arrange(orgin,target,flag){
	var lecCodeidx = $("#findidx").val();
	test = orgin.find("input:checked").parent().each(function(){
		if(flag){
			$(this).find("input").attr('afmt',lecCodeidx);
		}else{
			$(this).find("input").attr('afmt','11111');
		}
		$(target).append(this);
	});
	cnt = $('#nowlistForm').find('div.row').length;
	console.log(cnt);
	$('#now_num').text(cnt);
	
}

$(function(){
	$("input[type=checkbox]").on("click",function(){
		$(this).parent().attr("chck","yes");
	});
	
	$("#minus").on("click",minusArrange);
	$("#plus").on("click",plusArrange);	
	
	$("#confirm").click( function(){
			var list = new Array();
		
		var nowT = $('#now_num').text();
		var maxT = $('#max_num').text();
		var nowNtoM = Number(nowT);
		var maxNtoM = Number(maxT); 

		if(nowNtoM > maxNtoM){	
			alert("인원을 확인해주세요");
			$("#max_num").addClass('err');

			$("div#nowlistForm").css("border","#F2845C solid 3px");
		}else{
			if($("input:checked").length ){
				// 실제 보낼 데이터용			
				// 보여줄 데이터용
				var showM ='### 변경될 내용 [학생/연락처/이메일] : [ 변경사항(코드) ] ###';
					showM =showM+'\n 배정없음 : 11111 / 배정됨 : 11111 이외 숫자 ';
				var parent; var idx = 0;
				$("input:checked").each(function(idx){
		            var data = new Object();		parent = $(this).parent();
					id = $(this).val();				msg = parent.find('label').text();
					memt = $(this).attr("memt");	afmt = $(this).attr("afmt");
					// 데이터 담기용
					if(afmt=="no")afmt = memt;
					if(memt!=afmt){
						data.id = id;
						data.lec_code = afmt;
						list.push(data);
						showM = showM
							+'\n'
							+'#'+(++idx)+' '+msg +' : '+'[ '+memt+ ' --> '+afmt+' ]';
					}
				});
				showM = showM+'\n'+'#### 위의 내용으로 변경하시겠습니까? ####';
				// ajax
		        var jsonData = JSON.stringify(list) ;
		        if(list.length==0){	 
		        	alert("변경사항이 없습니다."); 
		        	$('input:checked').attr("checked", false).attr('afmt','no'); 
		        	$('div[chck=yes]').attr("chck","no");
		        	$('div[chck=no]').css('border', '2px solid red').css("margin","1px auto");
		        }
		        else{
		        	if(confirm(showM)){
		        		var lecidx = $("findidx").val();
		        		console.log(lecidx);
						$.post('${rootPath}ajax.bit?mode=ch_lec_code',{'data':jsonData},function(){
		        			console.log(lecidx);
						    window.location.href='?pageid=lec_con&mode=list&next=arrange';
						});
					}
		        }
			}else{
				alert("체크된 항목이 없습니다.");
				remarker();
			}
		}	
	});
});// 제이쿼리 로딩 끝
</script>
<style type="text/css">

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

.clr_both{
	clear: both;
}

.list{
	width: 350px;
	min-height : 100px;
	max-height: 300px;
	float: left;
    overflow: auto;
	margin: 2px auto;
	padding: 3px;
	border: #ccc solid 1px;
}

#container {
    width: 49%;
    margin: 30px auto;
}

	border-bottom: 1px #aaa solid;


div[chck="yes"]{
	border-bottom: 1px #aaa solid;
}
div[chck="no"]{
	border-bottom: 0px;
}
#max_num{
	
}
#now_num{
	color: blue;
}

.err{
	font-size : 20px;
	color: red;
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
		<span class="navigator deps">나의 메뉴</span>
		<span class="navigator deps">&nbsp;&rsaquo;&nbsp;</span>
		<span class="navigator final">학생배정[${lecName}]</span>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
	<hr class="bar"/>
	<article id="article">
		<div id="container">
		
		 	<!-- 내용을 넣어주세요 -->

	<div class="txt_center" style="width: 300px;margin: 0 auto;"><h1>[ ${lecName} ] 학생배정 </h1></div>
	<div style="width: 80%; margin: 0px auto;">
	<div class="txt_center" style="width: 250px;margin: 0 auto;">
		현재인원 : <span id="now_num">${lecRoomInfo.nowNum }</span> / 수용가능총원 : <span id="max_num">${lecRoomInfo.maxNum }</span></div>
	<div class="clr_both"></div>

	<label><h2>[현재] 학생목록</h2></label>
	<input id="findidx" type="hidden" value="${idx}"/>
	<div id="nowlistForm" class="list">
		<c:forEach items="${nowlist }" var="nowitem" varStatus="status">
		<div chck="no" class="row">
			<input class="" type="checkbox" id="now${status.index+1 }" memt="${idx}" afmt="no" value="${nowitem.id}"/>
			<label class="val" for="now${status.index+1 }">[ ${nowitem.name} / ${nowitem.phone} / ${nowitem.email } ]</label>
		</div>	
		</c:forEach>
	</div><button class="button" style="width: 40px; flaot:left; margin: 0 10px;" id="minus">-</button>
	<div class="clr_both"></div>
	<label><h2>[미배정] 학생목록</h2></label>
	<div id="nonelistForm" class="list">
		<c:forEach items="${nonelist }" var="noneitem" varStatus="status">
		<div chck="no" class="row">
			<input class="" type="checkbox" id="none${status.index+1 }" memt="11111" afmt="no" value="${noneitem.id}"/>
			<label class="val" for="none${status.index+1 }">[ ${noneitem.name} / ${noneitem.phone} / ${noneitem.email } ]</label>
		</div>	
		</c:forEach>
	</div>
	<button class="button" style="width: 40px; margin: 0 10px;" id="plus">+</button>
	<div class="clr_both"></div>
	<div style="margin-top: 10px;">
		<button class="button" id="confirm" style="margin: 0px;">배정완료</button>
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