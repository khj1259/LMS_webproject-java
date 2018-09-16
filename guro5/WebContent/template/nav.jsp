<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String webIdP= (String) session.getAttribute("web_id");
	if(webIdP!=null){
	int webMemType= ((Integer)session.getAttribute("web_mem_type")).intValue();
		if("임시id".equals(webIdP)){ 
			%><%@ include file="/template/nav_every.jsp" %><%			
		}// 임시 ID 분기끝 - 모든 사용자 : 유효성에 임시ID 넣어야됨
		else if(webMemType==0){
			%><%@ include file="/template/nav_student.jsp" %><%
		}// 학생분기 끝
		else if(webMemType==1){
			%><%@ include file="/template/nav_teacher.jsp" %><%
		}// 교수 분기끝
 		else if(webMemType==2){
			%><%@ include file="/template/nav_job.jsp" %><%
		}// 취업 분기끝
 		else if(webMemType==3){
			%><%@ include file="/template/nav_sales.jsp" %><%
		}// 영업 분기끝
 		else if(webMemType==4){
			%><%@ include file="/template/nav_executive.jsp" %><%
		}// 행정 분기끝
 		else if(webMemType==5){
			%><%@ include file="/template/nav_admin.jsp" %><%
		}// 관리자 분기끝
 		else if(webMemType==99){
			%><%@ include file="/template/nav_every.jsp" %><%
		}// 승인대기 회원
	}else{
%><%@ include file="/template/nav_every.jsp" %><%
	}


%>