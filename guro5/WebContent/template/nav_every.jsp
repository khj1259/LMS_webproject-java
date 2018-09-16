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
		</ul>
		<div id="login">
			<span><a href="${rootPath}login.bit?mode=login" class="login in">로그인</a></span>
			<span class="login in">&nbsp;&#124;&nbsp;</span>
			<span><a href="${rootPath}login.bit?mode=join" class="join in">회원가입</a></span>
		</div>
	</nav>