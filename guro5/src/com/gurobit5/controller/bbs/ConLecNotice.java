package com.gurobit5.controller.bbs;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gurobit5.model.bbs.DaoLecNotice;
import com.gurobit5.model.bbs.entity.DtoLecNotice;
import com.gurobit5.util.MyErrSender;

@WebServlet("/bbs/lec_notice.bit")
public class ConLecNotice extends HttpServlet {

	// try - hyunjung/2018-05-24
	// modify - sskim/2018-05-27
	// try - hyunjung/2018-05-28

	
	
	private String goRoot = "../";
	private String viewPath ="view/bbs/lec_notice/";
	private String urlThis = "/bbs/lec_notice.bit";

	private String[] modes = {"list", "detail", "add", "edit", "del"};

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
		lecNoticeInit(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		lecNoticeInit(req, resp);
	}

	private void lecNoticeInit(HttpServletRequest req, HttpServletResponse resp) {

		try {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
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
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// lecNoticeInit

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
			}else if("add".equals(modeP)){
				comandAdd();
			}else if("edit".equals(modeP)){
				comandEdit();
			}else if("del".equals(modeP)){
				comandDel();
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
		// try - hyunjung/2018-05-24
		if("GET".equals(method_type)){
			ArrayList<DtoLecNotice> list=null;
			DaoLecNotice daoLecNotice = new DaoLecNotice();
			try {
				list = daoLecNotice.selectAll(web_id);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			reqThis.setAttribute("alist", list);
			RequestDispatcher rd = reqThis.getRequestDispatcher(goRoot+viewPath+"LecNoticeBBS.jsp");
			rd.forward(reqThis, respThis);
		}else{
			// 잘못된 메소드 - 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}
	}// comandList()


	private void comandDetail() throws ServletException, IOException {
		// try - hyunjung/2018-05-24
		// try - hyunjung/2018-05-28
		if("GET".equals(method_type)){

			int idx=Integer.parseInt(reqThis.getParameter("idx"));
			String click = reqThis.getParameter("click");
			if(click==null)click="none";
			
			DtoLecNotice dtoLecNotice = new DtoLecNotice();
			DaoLecNotice daoLecNotice = new DaoLecNotice();
			try {
				
				
				if(click.equals("on")){	  //목록에서 게시물 클릭할때 조회수 증가
					daoLecNotice.viewNum(idx);
				}
				dtoLecNotice = daoLecNotice.selectOne(idx);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

			reqThis.setAttribute("dtoLecNotice", dtoLecNotice);
			RequestDispatcher rd = reqThis.getRequestDispatcher(goRoot+viewPath+"LecNoticeDetail.jsp");
			rd.forward(reqThis, respThis);
		}else{
			// 잘못된 메소드 - 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}

	}// comandDetail()

	private void comandAdd() throws ServletException, IOException {
		// try - hyunjung/2018-05-24
		if("GET".equals(method_type)){
			RequestDispatcher rd = reqThis.getRequestDispatcher(goRoot+viewPath+"LecNoticeAdd.jsp");
			rd.forward(reqThis, respThis);
		}else if("POST".equals(method_type)){
			String title=reqThis.getParameter("title");
			String contents=reqThis.getParameter("contents");

			DaoLecNotice daoLecNotice = new DaoLecNotice();
			try {
				daoLecNotice.insertOne(web_id,title,contents);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}// try-catch

			respThis.sendRedirect("?mode=list");
		}else{
			// 잘못된 메소드 - 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}
		// ii -elseif
	}// comandAdd()

	private void comandEdit() throws IOException, ServletException {
		//try - hyunjung/2018-5-24
		//try - hyunjung/2018-5-28
		if("GET".equals(method_type)){
			int num=Integer.parseInt(reqThis.getParameter("idx"));

			DaoLecNotice daoLecNotice = new DaoLecNotice();
			DtoLecNotice dtoLecNotice = new DtoLecNotice();
			try {
				dtoLecNotice = daoLecNotice.selectOne(num);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

			reqThis.setAttribute("dtoLecNotice", dtoLecNotice);
			RequestDispatcher rd = reqThis.getRequestDispatcher(goRoot+viewPath+"LecNoticeEdit.jsp");
			rd.forward(reqThis, respThis);
		}else if("POST".equals(method_type)){
			int num=Integer.parseInt(reqThis.getParameter("num"));
			String title=reqThis.getParameter("title");
			String contents=reqThis.getParameter("contents");

			DaoLecNotice daoLecNotice = new DaoLecNotice();
			int result=0;
			try {
				result = daoLecNotice.update(num,title,contents);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			if(result>0){
				respThis.sendRedirect("?mode=list");
			}else{
				respThis.sendRedirect("?mode=edit&idx="+num);
			}
		}else{
			// 잘못된 메소드 - 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}
	}// comandEdit()

	
	private void comandDel() throws ServletException, IOException {
		//try - hyunjung/2018-05-28
		if("POST".equals(method_type)){
			int num=Integer.parseInt(reqThis.getParameter("idx"));

			DaoLecNotice daoLecNotice = new DaoLecNotice();
			//DtoLecNotice dtoLecNotice = new DtoLecNotice();
			try {
				daoLecNotice.delete(num);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}else{
			// 잘못된 메소드 - 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}

	}//comandDel()


}// ConLecNotice
