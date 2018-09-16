package com.gurobit5.controller.bbs;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
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
import com.gurobit5.model.bbs.DaoJobAf;
import com.gurobit5.model.bbs.DaoLecNotice;
import com.gurobit5.model.bbs.entity.DtoJobAf;
import com.gurobit5.model.bbs.entity.DtoLecNotice;
import com.gurobit5.model.entity.DtoLecInfo;
import com.gurobit5.util.MyErrSender;

@WebServlet("/bbs/job_af.bit")
public class ConJobAf extends HttpServlet{
	//2018-05-28 김형준
	private String goRoot = "../";
	private String viewPath ="view/bbs/jobaf/";
	//private String urlThis = "/bbs/lec_notice.bit";

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
		//reqThis.setAttribute("urlThis", urlThis); // a 태그등의 경로에 씀

		// ID 체크 필요하면 사용.
		HttpSession session = req.getSession();
		web_id=(String) session.getAttribute("web_id");
		web_name=(String) session.getAttribute("web_name");
		if(session.getAttribute("web_mem_type")!=null)web_mem_type= (int) session.getAttribute("web_mem_type");
		//임시용
		if(web_id==null){web_id="임시id"; web_mem_type=100;}
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
			System.out.println("comnadCheck : false 다음 명령감");
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
		//2018-05-28 김형준
		if("GET".equals(method_type)){
			DaoJobAf daoJobAf =new DaoJobAf();
			List<DtoJobAf> list =null;
			list=daoJobAf.selectList(1,10);
			reqThis.setAttribute("alist", list);
			
			RequestDispatcher rd =reqThis.getRequestDispatcher(goRoot+viewPath+"jobaf_list.jsp");
			rd.forward(reqThis, respThis);
		}else{

		}
	}// comandList()
	
	private void comandDetail() throws ServletException, IOException {
		if("GET".equals(method_type)){

			int idx=Integer.parseInt(reqThis.getParameter("idx"));
			DtoJobAf dtoJobAf = new DtoJobAf();
			DaoJobAf daoJobAf = new DaoJobAf();
			dtoJobAf = daoJobAf.selectOne(idx);
			System.out.println(dtoJobAf.getClass_name());
			reqThis.setAttribute("dtoJobAf", dtoJobAf);
			RequestDispatcher rd = reqThis.getRequestDispatcher(goRoot+viewPath+"jobaf_detail.jsp");
			rd.forward(reqThis, respThis);
		}else{
			// 잘못된 메소드 - 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}

	}// comandDetail()
	
	private void comandAdd() throws ServletException, IOException, ClassNotFoundException, SQLException{
		int result = 0;
		DaoLecInfo daoLecInfo = new DaoLecInfo();
		if("GET".equals(method_type)){
			ArrayList<DtoLecInfo> list = daoLecInfo.selectClassNameAll();
			reqThis.setAttribute("lecinfo_list", list);
			rd = reqThis.getRequestDispatcher(goRoot+viewPath+"jobaf_add.jsp");
			rd.forward(reqThis, respThis);
		}else if("POST".equals(method_type)){
			//result = daoJobInfo.createRecord();
			String company=reqThis.getParameter("company");
			int lec_code=Integer.parseInt(reqThis.getParameter("lec_code"));
			String title=reqThis.getParameter("title");
			String name=reqThis.getParameter("name");
			String field=reqThis.getParameter("field");
			String contents=reqThis.getParameter("contents");
			
			DaoJobAf daoJobAf = new DaoJobAf();
			daoJobAf.insertOne(name, company, lec_code, title, field, contents, contents);
			respThis.sendRedirect("?mode=list");
		}
	}// comandCreate()

	
	private void comandEdit() throws IOException, ServletException {
		if("GET".equals(method_type)){
			int num=Integer.parseInt(reqThis.getParameter("idx"));

			DaoJobAf daoJobAf = new DaoJobAf();
			DtoJobAf dtoJobAf = new DtoJobAf();
			dtoJobAf = daoJobAf.selectOne(num);
			reqThis.setAttribute("dtoJobAf", dtoJobAf);
			RequestDispatcher rd = reqThis.getRequestDispatcher(goRoot+viewPath+"jobaf_edit.jsp");
			rd.forward(reqThis, respThis);
		}else if("POST".equals(method_type)){
			String title = reqThis.getParameter("title");
			String contents=reqThis.getParameter("contents");
			String company=reqThis.getParameter("company");
			int lec_code=Integer.parseInt(reqThis.getParameter("lec_code"));
			String field=reqThis.getParameter("field");
			String name=reqThis.getParameter("name");
			int num =Integer.parseInt(reqThis.getParameter("num"));

			DaoJobAf daoJobAf =new DaoJobAf();
			int result=0;
			result = daoJobAf.edit(title, contents, company, lec_code, field, name, num);
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
	private void comandDel() throws ServletException, IOException, SQLException{
		//int result = daoJobInfo.delRecord();
		if("POST".equals(method_type)){
			int num=Integer.parseInt(reqThis.getParameter("idx"));
			DaoJobAf daoJobAf = new DaoJobAf();
			daoJobAf.deleteOne(num);
		}else{
			// 잘못된 메소드 - 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}

	}//comandDel()
	}

	
