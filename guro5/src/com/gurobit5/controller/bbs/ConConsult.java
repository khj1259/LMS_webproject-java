package com.gurobit5.controller.bbs;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gurobit5.model.DaoLecInfo;
import com.gurobit5.model.bbs.DaoConsult;
import com.gurobit5.model.bbs.entity.DtoConsult;
import com.gurobit5.model.entity.DtoLecInfo;
import com.gurobit5.util.MyErrSender;

//2018-05-24 김형준 리스트불러오는 모델
@WebServlet("/bbs/consult.bit")
public class ConConsult extends HttpServlet {
	
	private String goRoot = "../";
	private String viewPath ="view/bbs/consult/";
	private String urlThis = "/bbs/consult.bit";


	private String[] modes = {"list", "detail", "form", "formlec","edit", "answer","del"};
	
	private HttpServletRequest reqThis;
	private HttpServletResponse respThis;
	private RequestDispatcher rd;
	
	private String modeP;
	private String method_type;
	private String web_id;
	private String web_name;
	private int web_mem_type;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		consultInit(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		consultInit(req, resp);
	}
		
	private void consultInit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			MyErrSender.sendErr("알수없는 서버문제.. 빠른시일내로 고치겠습니다.", req, resp, goRoot);
			e1.printStackTrace();
		}
		
		this.reqThis = req;
		this.respThis = resp;

		modeP = req.getParameter("mode");
		this.method_type = req.getMethod();
		
		// 경로넣어주기
		reqThis.setAttribute("rootPath", goRoot); // 파일경로에 씀
		reqThis.setAttribute("urlThis", urlThis); // a 태그등의 경로에 씀
		
		// ID 체크 필요하면 사용.
		HttpSession session = req.getSession();
		web_id=(String) session.getAttribute("web_id");
		web_name=(String) session.getAttribute("web_name");
		if(session.getAttribute("web_mem_type")!=null)web_mem_type= (int) session.getAttribute("web_mem_type");
		//임시용
		if(web_id==null) web_id="임시id"; web_mem_type=100;
		if(web_name==null) web_name="임시id";
		
		
		
		try {
			selectMode();
		} catch (ClassNotFoundException e) {
			MyErrSender.sendErr("알수없는 서버문제.. 빠른시일내로 고치겠습니다.", req, resp, goRoot);
			e.printStackTrace();
		} catch (SQLException e) {
			MyErrSender.sendErr("알수없는 서버문제.. 빠른시일내로 고치겠습니다.", req, resp, goRoot);
			e.printStackTrace();
		} catch (ServletException e) {
			MyErrSender.sendErr("알수없는 서버문제.. 빠른시일내로 고치겠습니다.", req, resp, goRoot);
			e.printStackTrace();
		} catch (IOException e) {
			MyErrSender.sendErr("알수없는 서버문제.. 빠른시일내로 고치겠습니다.", req, resp, goRoot);
			e.printStackTrace();
		}
	}// consultInit
	
	private void selectMode() throws ServletException, IOException, ClassNotFoundException, SQLException {
		if(modeP==null){ MyErrSender.sendErr(2, reqThis, respThis, goRoot); return;}
		if(comandCheck(modes)){
			// 제공하지 않는 기능 -- 2번
			MyErrSender.sendErr(2, reqThis, respThis, goRoot);;
		}else{
			System.out.println("comandCheck : false 다음 명령감");
			if("list".equals(modeP)){
				comandList();
			}else if("detail".equals(modeP)){
				comandDetail();
			}else if("form".equals(modeP)){
				comandForm();
			}else if("formlec".equals(modeP)){
				comandFormforLec();
			}else if("edit".equals(modeP)){
				comandEdit();
			}else if("answer".equals(modeP)){
				comandAnswer();
			}else if("del".equals(modeP)){
				comandDel();
			}else{
				MyErrSender.sendErr(0, reqThis, respThis, goRoot);
			}
		
		}// if end
	}// selectMode()
	
	private boolean comandCheck(String[] modes) throws ClassNotFoundException, SQLException, ServletException, IOException {
		boolean checker_mode = true;
		for(String msg : modes){
			if(modeP.equals(msg)) checker_mode=false;
		}
		return checker_mode;
	}// comandCheck()
	
	private void comandList() throws ServletException, IOException {
		//게시판 리스트를 보여줌
		if("GET".equals(method_type)){
			List<DtoConsult> list=null;
			DaoConsult daoConsult =new DaoConsult();
			list = daoConsult.selectAll(1,2);
			reqThis.setAttribute("alist", list);

			RequestDispatcher rd = reqThis.getRequestDispatcher(goRoot+viewPath+"consult_list.jsp");
			rd.forward(reqThis, respThis);
		}else{
			// 존재하지 않는 접근 : 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}
		
	}//comandList()
	
	private void comandDetail() throws ServletException, IOException, ClassNotFoundException, SQLException {
		//2018-05-24 김형준 상세페이지불러오는 모델
		if("GET".equals(method_type)){
			String idx = reqThis.getParameter("idx");
			int num =Integer.parseInt(idx);
			

			DtoConsult dtoConsult =null;
			DaoConsult daoConsult = new DaoConsult();
			dtoConsult = daoConsult.selectOne(num);
			
			DaoLecInfo daoLecInfo = new DaoLecInfo();
			if(dtoConsult.getLec_code()!=11111){
				DtoLecInfo dtoLecInfo = daoLecInfo.selectOne(dtoConsult.getLec_code());
				reqThis.setAttribute("dtoLecInfo", dtoConsult);
			}
				
			reqThis.setAttribute("dtoConsult", dtoConsult);
				
			RequestDispatcher rd = reqThis.getRequestDispatcher(goRoot+viewPath+"consult_detail.jsp");
			rd.forward(reqThis, respThis);
		}else{
			// 존재하지 않는 접근 : 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}
		
	}// comandDetail()
	
	private void comandFormforLec() throws ServletException, IOException, ClassNotFoundException, SQLException {
		//2018-05-24 김형준 상담신청 폼 모델
		if("GET".equals(method_type)){
			String lecCodeP = reqThis.getParameter("idx");
			int lecCode = 11111;
			if(lecCodeP!=null) lecCode = Integer.parseInt(lecCodeP);
			DaoLecInfo daoLecInfo = new DaoLecInfo();
			DtoLecInfo dtoLecInfo = daoLecInfo.selectOne(lecCode);
			
			reqThis.setAttribute("dtoLecInfo", dtoLecInfo);
			rd =reqThis.getRequestDispatcher(goRoot+viewPath+"consult_formlec.jsp");
			rd.forward(reqThis, respThis);
		}else if("POST".equals(method_type)){
			String title = reqThis.getParameter("title");
			String name = reqThis.getParameter("name");
			String gen = reqThis.getParameter("gen");
			String email = reqThis.getParameter("email");
			String purpose = reqThis.getParameter("purpose");
			String lecCodeP = reqThis.getParameter("lec_code");
			int lecCode = 11111;
			if(lecCodeP!=null) lecCode = Integer.parseInt(lecCodeP);
			String contents= reqThis.getParameter("contents");
			int phone = Integer.parseInt(reqThis.getParameter("phone"));
			
			DaoConsult daoConsult = new DaoConsult();
			daoConsult.insertOneforLec(title, name, gen, email, purpose, lecCode, contents,phone);
			respThis.sendRedirect(goRoot); //메인페이지로 보내야함.
		}else{
			// 존재하지 않는 접근 : 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}
		
	}// comandForm()
	private void comandForm() throws ServletException, IOException {
		//2018-05-24 김형준 상담신청 폼 모델
		if("GET".equals(method_type)){
			rd =reqThis.getRequestDispatcher(goRoot+viewPath+"consult_form.jsp");
			rd.forward(reqThis, respThis);
		}else if("POST".equals(method_type)){
			String title = reqThis.getParameter("title");
			String name = reqThis.getParameter("name");
			String gen = reqThis.getParameter("gen");
			String email = reqThis.getParameter("email");
			String purpose = reqThis.getParameter("purpose");
			String contents= reqThis.getParameter("contents");
			int phone = Integer.parseInt(reqThis.getParameter("phone"));
			
			DaoConsult daoConsult = new DaoConsult();
			daoConsult.insertOne(title, name, gen, email, purpose, contents, phone);
			respThis.sendRedirect(goRoot); //메인페이지로 보내야함.
		}else{
			// 존재하지 않는 접근 : 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}
			
	}// comandForm()

	private void comandEdit() throws ServletException, IOException {

		if("GET".equals(method_type)){
			
			String idxP = reqThis.getParameter("idx");
			DaoConsult daoConsult = new DaoConsult();
			String answer = daoConsult.getAnswer(Integer.parseInt(idxP));
			reqThis.setAttribute("answer", answer);
			RequestDispatcher rd =reqThis.getRequestDispatcher(goRoot+viewPath+"consult_edit.jsp");
			rd.forward(reqThis, respThis);
			
		}else if("POST".equals(method_type)){
			String answer =reqThis.getParameter("answer");
			int num = Integer.parseInt(reqThis.getParameter("idx"));
			DaoConsult daoConsult = new DaoConsult();
			int result=0;
			result=daoConsult.answer(answer, num);
		}else{
			// 존재하지 않는 접근 : 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}
	}// comandEdit()

	private void comandDel() throws IOException, ServletException {
		//2018-05-24 김형준 게시글삭제 모델
		
		if("POST".equals(method_type)){
			int num = Integer.parseInt(reqThis.getParameter("idx"));
			DaoConsult daoConsult = new DaoConsult();
			daoConsult.deleteOne(num);
			
			respThis.sendRedirect("?mode=list");
		}else{
			// 존재하지 않는 접근 : 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}
	}//comandDel()

	private void comandAnswer() throws ServletException, IOException {
		if("GET".equals(method_type)){
			String answer = reqThis.getParameter("answer");
			reqThis.setAttribute("answer", answer);
			RequestDispatcher rd =reqThis.getRequestDispatcher(goRoot+viewPath+"consult_answer.jsp");
			rd.forward(reqThis, respThis);
			
		}else if("POST".equals(method_type)){
			// 어자피 팝업에서 ajax로 처리해서 redirect 안함.
			String answer =reqThis.getParameter("answer");
			String idxP = reqThis.getParameter("idx");
			int num = 0;
			num = Integer.parseInt(idxP);
			System.out.println(num+":"+answer);
			DaoConsult daoConsult = new DaoConsult();
			int result = daoConsult.answer(answer, num);
			System.out.println(result);
			
		}else{
			// 존재하지 않는 접근 : 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}
	}// comandAnswer()

	
}//ConConsult

