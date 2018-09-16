<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비트캠프 구로점</title>
<link rel="stylesheet" type="text/css" href="${rootPath}template/main.css"/>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${rootPath}js/css/jquery.bxslider.min.css"/>
<script type="text/javascript" src="${rootPath}js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${rootPath}js/bxslider/jquery.bxslider.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('.slider').bxSlider({
		    auto: true,
		    captions: false,
		    slideWidth: 960,
		    speed: 400,
		    minSlides:1,
		    maxSlides:1,
		    moveSlides:1,
		    controls: false,
		    autoControls: true
		  });
	});
</script>
<style type="text/css">
    .topImg{
    	width: 1150px;
    }
    .maincard{
    	width: 380px;
    	height: 300px;
    	float: left;
    	border-top: 4px solid lightgray;
    	align-content:center;
    	vertical-align: center;
    	display: inline-block;
    	position: relative;
    	margin-top: 10px;
    }
    .mainimg{
    	margin-top: 50px;
    	width: 100%;
    }
    #space{
    	clear: both;
    	margin-top: 20px;
    }
</style>
</head>
<body>
	<div id="body">
	<%@include file="/template/header.jsp" %>
	<%@include file="/template/nav.jsp" %>
	<br>
	<br>
	<br>
	<br>
	<br>
	<hr class="bar"/>
		<span class="navigator deps"></span>
	<hr class="bar"/>
	<article id="article">
		<div id="container">
		   	<%@include file="/template/main_bxslider.jsp" %>
		   	<div class="topImg">
			 	<div class="maincard">
			 		<a href="${rootPath}?pageid=leclist"><img alt="" src="img/main/lecture_main.png" class="mainimg lecture"></a>
			 	</div>
			 	<div class="maincard">
			 		<a href="${rootPath}bbs/consult.bit?mode=form"><img alt="" src="img/main/consulting_main.png" class="mainimg consulting"></a>	 	
			 	</div>
			 	<div class="maincard">
			 		<a href="${rootPath}bbs/lec_af.bit?mode=list"><img alt="" src="img/main/jobafter_main.png" class="mainimg jobafter"></a>	 	
			 	</div>
		 	</div>
		</div>
		<div id="space"></div>
</article>
	<%@include file="/template/footer.jsp" %>
    </div>
</body>
</html>