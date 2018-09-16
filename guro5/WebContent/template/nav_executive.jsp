<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<nav id="nav">
		<ul id="menu">
			<li class="topmenu"><a href="${rootPath}" class="topmenuLink">학원소개</a>
				<ul class="submenu">
					<li><a href="${rootPath}?pageid=summary" class="submenuLink">개요</a></li>
					<li><a href="${rootPath}?pageid=greeting" class="submenuLink">인사말</a></li>
					<li><a href="${rootPath}?pageid=map" class="submenuLink longlink">찾아오시는길</a></li>
					<li><a href="${rootPath}?pageid=teacher" class="submenuLink">교수진소개</a></li>
				</ul>
			</li>
			<li class="topmenu"><a href="${rootPath}" class="topmenuLink">강의소개</a>
				<ul class="submenu">
					<li><a href="${rootPath}?pageid=leclist" class="submenuLink">강의목록</a></li>
					<li><a href="${rootPath}bbs/lec_af.bit?mode=list" class="submenuLink">수강후기</a></li>
				</ul>
			</li>
			<li class="topmenu"><a href="${rootPath}bbs/consult.bit?mode=form" class="topmenuLink">상담신청</a></li>
			<li class="topmenu"><a href="${rootPath}" class="topmenuLink">취업센터</a>
				<ul class="submenu">
					<li><a href="${rootPath}?pageid=jobpro" class="submenuLink longlink">취업프로세스</a></li>
					<li><a href="${rootPath}bbs/job_info.bit?mode=list" class="submenuLink">취업정보</a></li>
					<li><a href="${rootPath}bbs/job_af.bit?mode=list" class="submenuLink">취업현황</a></li>
				</ul>
			</li>
			<li id="mymenu" class="topmenu"><a href="#" class="topmenuLink">내 메뉴</a>
				<ul class="submenu">
					<li><a href="${rootPath }mymenu.bit?pageid=lec_con&mode=list" class="submenuLink">강의개설</a></li>
					<li><a href="${rootPath }mymenu.bit?pageid=lec_con&mode=list&next=arrange" class="submenuLink">학생배정</a></li>
					<li><a href="${rootPath }mymenu.bit?pageid=attend_con&mode=list" class="submenuLink">출결확인</a></li>
					<li><a href="${rootPath }bbs/emp_notice.bit?mode=list" class="submenuLink longlink">직원 공지사항</a></li>
				</ul>
			</li>
		</ul>
		<!--로그인 시작-->
		<div id="login">
		    <div class="temp1">
		        <span>
		            <img src="${rootPath}img/login/executive.png" class="identity"><a href="#" class="in">${web_name }님(행정)</a>
		        </span>
		    </div>
		    <div class="temp2">
		        <span><a href="${rootPath }login.bit?mode=logout" class="in">로그아웃</a></span>
		    </div>
		    <div class="temp3">
		        <span class="in">&nbsp;&#124;&nbsp;</span>
		    </div>
		    <div class="temp4">
		        <span><a href="${rootPath }mymenu.bit?pageid=id_con&mode=detail&idx=${web_id}" class="in">My Page</a></span>
		    </div>
		</div>
		<!--로그인 끝-->
	</nav>