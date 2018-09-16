<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>비트캠프 구로점-ID관리</title>
<!--  css -->
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link rel="stylesheet" type="text/css" href="${rootPath}template/button.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">

<!-- script -->
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">

	$(function(){
		
		$('form').submit(function(e){
				return false;
		});		
		
		$("#test").click( function(e){
			e.preventDefault();
			if($("input:checked").length){
				// 실제 보낼 데이터용			
				var list = new Array();
				// 보여줄 데이터용
				var showM ='### 변경될 내용 [아이디/이름] --> [변경유형] ###';
				var parent;
				$("input:checked").each(function(idx){
		            var data = new Object() ;
		            parent = $(this).parent().parent().attr("change","on")
		            console.log(parent);
					selv = parent.find('option:selected').attr("value");
					seln = parent.find('option:selected').text();
					id = parent.find('span.id').text();
					name = parent.find('span.name').text();
					idV = $(this).val();
					// 데이터 담기용
					data.id = id;
					data.memtype = selv;
					list.push(data);
					
					showM = showM
						+'\n'
						+'#'+(idx+1)+' ['+id+'/'+name+']'+' --> '+'['+seln+']';
				});
				showM = showM+'\n'+'#### 위의 내용으로 변경하시겠습니까? ####';
				// ajax
		        var jsonData = JSON.stringify(list) ;
		        /*
		            출력결과 : [{"number":1,"name":"Tester #1"},{"number":2,"name":"Tester #2"}]
		        */
		        if(confirm(showM)){
					$.post('${rootPath}ajax.bit?mode=ch_mem_t',{'data':jsonData},function(){
						var tmp = $("#list_label").attr("txt");
						var url = '?pageid=id_con&mode=list';
						if(tmp=='99') url = url +'&mem_t=99';
						window.location.href= url;
					});
				}
			}else{
				alert("선택된 항목이 없습니다.");
			}
		});
	});// 


</script>
<style type="text/css">

#table {
        width: 75%;
        margin: 0px auto;
        margin-bottom: 130px;
        margin-top: 90px;
    }
    .cell {
        display: inline-block;
        padding: 3px;
        border-bottom: 1px solid lightgray;
        overflow: hidden;
        text-overflow: ellipsis;
        /* white-space: nowrap; */
        text-align: center;
        /* margin-left: 10px; */
    }
    .top{
        font-weight: bold;
        background-color: lightgray;
    }
    .col1{width:20%;}
    .col2{width:15%;}
    .col3{width:30%;}
    .col4{width:20%;}
    .col5{width:8%;}
    
    select{
    	width: 80%;
    }
    
    input,select,textarea,button {vertical-align:middle;}
	h1{
		text-align: center;
	}
	.clearboth{
    	clear: both;
    }
    
    .score{
    	font-size: 25px;
    	margin-bottom: 10px;
    }
 a:link { color: black; text-decoration: none;}
 a:visited { color: black; text-decoration: none;}
 a:hover { color: blue;}

</style>
</head>
<% 

	String memT = (String) request.getParameter("mem_t");
	int memTypeForLabel = 99;
	if(memT==null)memTypeForLabel = 0;
	pageContext.setAttribute("memTypeLabel",memTypeForLabel);
		
	String conOn = (String) request.getAttribute("acceptOn");
	if("on".equals(conOn))pageContext.setAttribute("conOn", "OK");
%>
<body>

	<div id="body">
	<%@include file="/template/header.jsp" %>
	<%@include file="/template/nav.jsp" %><!-- 각 회원에 맞게 바꿔 주셔야 합니다. -->
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
		<span class="navigator final">ID 관리</span>
		<!-- 뎁스영역을 직접 적어 주셔야 합니다. "홈 > 학원소개 > 찾아오시는 길" 이런식으로 적어주세요
			> 표시는 &rsaquo; 입니다.
		 -->
	<hr class="bar"/>
	<article id="article">
		<div id="container">
		 	<!-- 내용을 넣어주세요 -->


	<div class ="search_bar ">
		<!-- 서치바 넣을거임 -->
	</div>
	<c:choose>
		<c:when test="${memTypeLabel eq 99}">
			<h2 id="list_label" style="text-align: center;" txt="${memTypeLabel}">승인요청</h2>
		</c:when>	
		<c:otherwise>
			<h2 id="list_label" style="text-align: center;" txt="${memTypeLabel}">회원관리</h2>
		</c:otherwise>
	</c:choose>
	<form>
	<div id="table" target="on">
	
	
			<div class="row">
				<span class="cell col1 top">ID</span>			
				<span class="cell col2 top">이름</span>			
				<span class="cell col3 top">연락처</span>			
				<span class="cell col4 top">계정유형</span>			
				<span class="cell col5 top">체크</span>			
			</div>
			
		<c:forEach var="item" items="${dtoMemberList }"> 
			<div class="row" change="no" >
			<!--  1로우 -->
				<span class="cell col1 id"><a href="?pageid=id_con&mode=detail&idx=${item.id }">${item.id }</a></span>			
				<span class="cell col2 name"><a href="?pageid=id_con&mode=detail&idx=${item.id }">${item.name }</a></span>
				<span class="cell col3 "><a href="?pageid=id_con&mode=detail&idx=${item.id }">${item.phone }</a></span>
				<span class="cell col4 "><select>
					<c:forEach var="itemN" items="${memTypeListN }" varStatus="statusN" >
					<c:forEach var="itemV" items="${memTypeListV }" varStatus="statusV">
						<c:choose>
						<c:when test="${statusN.index eq statusV.index }">
							<c:choose>
							<c:when test="${ item.memType eq itemV }">
								<option selected value="${itemV }">${itemN}</option>
							</c:when>
							<c:otherwise>
								<option value="${itemV }">${itemN}</option>
							</c:otherwise>
							</c:choose>
						</c:when>
						</c:choose>
					</c:forEach>
					</c:forEach>
				</select></span>
				<span class="cell col5 "><input type="checkbox" value="${item.id}"/></span>
			</div>
		</c:forEach>	
	<div class="">
		<button class="button" type="reset" style="float:right">리셋하기</button>
		<button id="test" class="button" type="button" style="float:right">확정하기</button>
	</div>
	</div>
	</form>


		
			<!--  내용끝 -->
		</div>
	<div style="clear:both;"></div>
</article>
	<%@include file="/template/footer.jsp" %>
    </div>
</body>
</html>

</body>
</html>