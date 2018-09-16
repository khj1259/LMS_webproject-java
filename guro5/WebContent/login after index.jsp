<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
    *{
        font-family: 'Jua', sans-serif;
    }
    #body{
    	width: 100%;
    }
    /*↓메뉴 관련*/
    #header{
        width: 20%;
        height: 50px;
    }
    .logo{
        width: 100%;
    }
	#header,#nav{
		float: left;
	}

	#nav{
		height: 50px;
		width: 75%;
        margin-left: 25px;
		margin-top: 25px;
	}
	#nav ul{
		list-style-type: none;
		margin: 0px;
		padding: 0px;
	}
	#menu{
        width: 70%;
        position: relative;
        z-index: 1;
    }
    /*↓로그인 메뉴쪽*/
    #login{
        width: 25%;
        height: 50px;
        display: inline-block;
        text-align: center;
    }
    .in{
        text-decoration: none;
        color: black;
        line-height: 50px;
        vertical-align: middle;
        font-size: 20px;
    }
    .identity{
        height: 100%;
    }
    .temp1{
        width: 100%;
        height: 25px;
    }
    .temp2{
        width: 33%;
        height: 25px;
        float: left;
    }
    .temp3{
        width: 33%;
        height: 25px;
        float: left;
    }
    .temp4{
        width: 33%;
        height: 25px;
        float: left;
    }
    /*↑로그인 메뉴쪽*/
	#nav ul li{
		float: left;
		line-height: 50px;
		vertical-align: middle;
		text-align: center;
		position: relative;
	}
	.submenuLink, .topmenuLink{
		text-decoration: none;
		display: block;
		width: 160px;
		font-size: 20px;
		font-weight: bold;
	}
	.topmenuLink{
		color: black;
		font-size: 26px;
	}

	.topmenu:hover .topmenuLink{
		padding-bottom: 28px;
		border-bottom:3px solid black;
	}


	.longlink{
		width: 200px;
	}

	.submenuLink{
        color: black;
		background-color: white;
		border: 2px solid lightgray;
		border-radius: 3px;
	}
	.submenu{
		position: absolute;
		height: 0px;
		overflow: hidden;
		width: 800px;
	}
	.submenu li{
		display: inline-block;
	}
	.topmenu:hover .submenu{
		height: 70px;
	} 
	.submenuLink:hover {
		color: lightgray;
	}
    /*↑메뉴 관련*/
	.bar{
		border: 1px solid lightgray;
		margin: 0px;
		padding: 0px;
	}
    /*↓풋터 관련*/
    #footer{
        width: 100%;
        height: 100px;
        display: block;
    }
    .footer1{
        width: 15%;
        display: inline-block;
    }
    .footer2{
        width: 10%;
       display: inline-block;
    }
    .footer3{
        width: 55%;
         display: inline-block;
    }
    .footer4{
        width: 15%;
         display: inline-block;
    }
    .footerlogo{
        width:100%;
        float:left;
        padding-bottom: 10px;
    }
    /*↑풋터 관련*/
    /*↓article(내용) 관련*/
    #article{
    	width: 100%;
    }
    #container{
    	width: 1080px;
    	margin: 0px auto;
    }
    /*↑article(내용) 관련*/
    /*↓navigator(뎁스) 관련*/
    .navigator{
    	font-size: 20px;
    }
    /*navigator(뎁스) 관련*/
</style>
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="js/css/jquery.bxslider.min.css"/>
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="js/bxslider/jquery.bxslider.min.js"></script>
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
</head>
<body>
	<div id="body">
	<header id="header">
		<a href="#"><img src="img/main/logo.png" class="logo"></a>
	</header>
	<nav id="nav">
		<ul id="menu">
			<li class="topmenu"><a href="#" class="topmenuLink">학원소개</a>
				<ul class="submenu">
					<li><a href="#" class="submenuLink">개요</a></li>
					<li><a href="#" class="submenuLink">인사말</a></li>
					<li><a href="#" class="submenuLink longlink">찾아오시는길</a></li>
					<li><a href="#" class="submenuLink">교수진소개</a></li>
				</ul>
			</li>
			<li class="topmenu"><a href="#" class="topmenuLink">강의소개</a>
				<ul class="submenu">
					<li><a href="#" class="submenuLink">강의목록</a></li>
					<li><a href="#" class="submenuLink">수강후기</a></li>
				</ul>
			</li>
			<li class="topmenu"><a href="#" class="topmenuLink">상담신청</a></li>
			<li class="topmenu"><a href="#" class="topmenuLink">취업센터</a>
				<ul class="submenu">
					<li><a href="#" class="submenuLink longlink">취업프로세스</a></li>
					<li><a href="#" class="submenuLink">취업정보</a></li>
					<li><a href="#" class="submenuLink">취업현황</a></li>
				</ul>
			</li>
			<li id="mymenu" class="topmenu"><a href="#" class="topmenuLink">내 메뉴</a></li>
		</ul>
		<!--로그인 시작-->
		<div id="login">
		    <div class="temp1">
		        <span>
		            <img src="img/login/student.png" class="identity"><a href="#" class="in">학생이름</a>
		        </span>
		    </div>
		    <div class="temp2">
		        <span><a href="#" class="in">로그아웃</a></span>
		    </div>
		    <div class="temp3">
		        <span class="in">&nbsp;&#124;&nbsp;</span>
		    </div>
		    <div class="temp4">
		        <span><a href="#" class="in">My Page</a></span>
		    </div>
		</div>
		<!--로그인 끝-->
	</nav>
	<!--탭 네비게이션-->
	<br>
	<br>
	<br>
	<br>
	<br>
	<hr class="bar"/>
		<span class="navigator"></span>
	<hr class="bar"/>
	<!--탭 네비게이션 끝-->
	<!--내용시작-->
	<article id="article">
		<div id="container">
		   	<div class="slider">
			    <div><img alt="thumbnail01" src="img/main/mainimg1.png" title="1"></div>
			    <div><img alt="thumbnail02" src="img/main/mainimg2.png" title="2"></div>
			    <div><img alt="thumbnail03" src="img/main/mainimg3.jpg" title="3"></div>
			    <div><img alt="thumbnail04" src="img/main/mainimg4.png" title="4"></div>
			    <div><img alt="thumbnail05" src="img/main/mainimg5.png" title="5"></div>
			    <div><img alt="thumbnail06" src="img/main/mainimg6.png" title="6"></div>
		 	</div>
		 	<div>
		 	<!-- 내용을 넣어주세요 -->
		 	</div>
		</div>
    </article>
    <!--내용끝-->
    <footer id="footer">
      <div class="footer1">
      </div>
      <div class="footer2">
          <img src="img/main/logo.png" class="footerlogo">
      </div>
      <div id="address" class="footer3">
          <address id="address">
          비트캠프 서울시 서초구 서초동 1327-15 비트아카데미빌딩사업자등록번호 : 214-85-24928 ┃ (주)비트컴퓨터 서초지점 대표이사 : 조현정
            <br>문의 : 02-3486-9600 / 팩스 : 02-6007-1245 ┃통신판매업 신고번호 : 제 서초-00098호 / 개인정보보호관리책임자 : 권도혁
            <br>Copyright (c) 비트캠프 All rights reserved.
         </address>
      </div>
      <div class="footer4">
      </div>
    </footer>
    </div>
</body>
</html>