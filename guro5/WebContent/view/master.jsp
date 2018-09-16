<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비트캠프 구로점 - 관리자메뉴</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">

	$(function(){
		$("button#confirm_id").click(function(e){
			e.preventDefault();
			window.opener.location="${rootPath}mymenu.bit?pageid=id_con&mode=list&mem_t=99"
			window.close();
		});
		$("button#con_id").click(function(e){
			e.preventDefault();
			window.opener.location="${rootPath}mymenu.bit?pageid=id_con&mode=list";
			window.close();
		});
		$("button#con_lec_list").click(function(e){
			e.preventDefault();
			window.opener.location="${rootPath}mymenu.bit?pageid=lec_con&mode=list";
			window.close();
		});
		$("button#con_lec_arrange").click(function(e){
			e.preventDefault();
			window.opener.location="${rootPath}mymenu.bit?pageid=lec_con&mode=list&next=arrange";
			window.close();
		});
		$("button#con_attend_list").click(function(e){
			e.preventDefault();
			window.opener.location="${rootPath}mymenu.bit?pageid=attend_con&mode=list";
			window.close();
		});
		$("button#empbbs").click(function(e){
			e.preventDefault();
			window.opener.location="${rootPath}bbs/emp_notice.bit?mode=list";
			window.close();
		});
	});
</script>
	
<body>
<div style="margin: 2px auto; width:90%; height: 40px; line-height: 30px;">
<button id="confirm_id" class="button"style="margin:0px; width:100%; height: 30px;"">승인요청</button>
</div>
<div style="margin: 2px auto; width:90%; height: 40px; line-height: 30px;">
<button id="con_id" class="button" style="margin:0px; width:100%; height: 30px;">계정관리</button>
</div>
<div style="margin: 2px auto; width:90%; height: 40px; line-height: 30px;">
<button id="con_lec_list" class="button" style="margin:0px; width:100%; height: 30px;">강의개설</button>
</div>
<div style="margin: 2px auto; width:90%; height: 40px; line-height: 30px;">
<button id="con_lec_arrange" class="button" style="margin:0px; width:100%; height: 30px;">학생배정</button>
</div>
<div style="margin: 2px auto; width:90%; height: 40px; line-height: 30px;">
<button id="con_attend_list" class="button" style="margin:0px; width:100%; height: 30px;">출석조회</button>
</div>
<div style="margin: 2px auto; width:90%; height: 40px; line-height: 30px;">
<button id="empbbs" class="button" style="margin:0px; width:100%; height: 30px;">직원게시판</button>
</div>
</body>
</html>