package com.gurobit5.util;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyErrSender {

	private static String[] errMsgs = {
			"잘못된 [접근] 입니다." // 0번 -- servlet에서 사용하지 않는 메소드 방식일때
			,"존재하지 않는 [글] 입니다." // 1번 -- 잘못된 idx 로 접근했을때
			,"제공하지 않는 [기능] 입니다." // 2번 -- 해당 servlet 에 없는 mode 일때
			,"존재하지 않는 [메뉴] 입니다." // 3번 -- mymenu에 없는 pageid 인 경우
			,"제공하지 않는 [페이지] 입니다." // 4번 -- index에서 제공하지 않는 페이지일때
			,"[ 로그인 ] 이 필요합니다 "// 5번 -- 로그인 필요합니다.
	};
	
	public static void sendErr(int errIndex, HttpServletRequest reqThis, HttpServletResponse respThis, String goRoot) throws ServletException, IOException{
		String errMsg = errMsgs[errIndex];
		reqThis.setAttribute("err", errMsg);
		RequestDispatcher rd = reqThis.getRequestDispatcher(goRoot+"view/err.jsp");
		rd.forward(reqThis, respThis);
	}// sendErr
	
	
	
	public static void sendErr(String msgM, HttpServletRequest reqThis, HttpServletResponse respThis, String goRoot) throws ServletException, IOException{
		reqThis.setAttribute("errM", msgM);
		RequestDispatcher rd = reqThis.getRequestDispatcher(goRoot+"view/err.jsp");
		rd.forward(reqThis, respThis);
	}// sendErr// msg로 뿌릴때
	
	
	
	public static boolean idTypeChecker(int[] compareType, int web_mem_type){
		if(web_mem_type==100){	return true; }
		boolean result = true;
		for(int compare : compareType){
			if(compare==web_mem_type){
				result = false;		break;	
			}
		}// foreach end		
		return result;
	}// idTypeChecker // 검사 유형에 안걸리면 true;
	
	public static String getRandom(int size){
		StringBuffer buf =new StringBuffer();
		Random r =new Random();
		for(int i=0;i<size;i++){
			// rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 소문자를, false 일 시 랜덤 한 숫자를 StringBuffer 에 append 한다.
			if(r.nextBoolean()){
				if(i%2==0){ buf.append((char)((int)(r.nextInt(26))+97)); }
				else{ buf.append((char)((int)(r.nextInt(26))+65)); }
			}else{
				buf.append((r.nextInt(10)));
			}
		}
		return new String(buf);
	}//GetRandom()
	
}// MyErrSender
