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

import com.gurobit5.model.bbs.DaoEmpNotice;
import com.gurobit5.model.bbs.DaoJobAf;
import com.gurobit5.model.bbs.DaoLecNotice;
import com.gurobit5.model.bbs.entity.DtoEmpNotice;
import com.gurobit5.model.bbs.entity.DtoJobAf;
import com.gurobit5.model.bbs.entity.DtoLecNotice;
import com.gurobit5.util.MyErrSender;

@WebServlet("/bbs/emp_notice.bit")
public class ConEmpNotice extends HttpServlet {
	//김형준 2018-05-29
	private String goRoot = "../";
	private String viewPath ="view/bbs/empnotice/";
	private String urlThis = "/bbs/emp_notice.bit";


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
		lecAfInit(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		lecAfInit(req, resp);
	}
		
	private void lecAfInit(HttpServletRequest req, HttpServletResponse resp) {

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
		if(web_id==null) {web_id="임시id"; web_mem_type=100;}
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
	}// lecAfInit()
	
	private void selectMode() throws ServletException, IOException, ClassNotFoundException, SQLException {
		if(modeP==null){ MyErrSender.sendErr(2, reqThis, respThis, goRoot); return;}

		if(comandCheck(modes)){
			// 제공하지 않는 기능 -- 2번
			MyErrSender.sendErr(2, reqThis, respThis, goRoot);
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
	
	
	private void comandList() throws ServletException, IOException{
		if("GET".equals(method_type)){
			ArrayList<DtoEmpNotice> list=null;
			DaoEmpNotice daoEmpNotice = new DaoEmpNotice();
			list = daoEmpNotice.selectAll(1,10);
			reqThis.setAttribute("alist", list);
			
			RequestDispatcher rd = reqThis.getRequestDispatcher(goRoot+viewPath+"emp_notice_list.jsp");
			rd.forward(reqThis, respThis);
		}else{

		}
	}// comandList()

	private void comandDetail() throws ServletException, IOException{ 
		if("GET".equals(method_type)){

			int idx=Integer.parseInt(reqThis.getParameter("idx"));
			String click = reqThis.getParameter("click");
			if(click==null)click="none";
			
			DtoEmpNotice dtoEmpNotice = new DtoEmpNotice();
			DaoEmpNotice daoEmpNotice = new DaoEmpNotice();
			try {
				
				
				if(click.equals("on")){	  //목록에서 게시물 클릭할때 조회수 증가
					daoEmpNotice.viewNum(idx);
				}
				dtoEmpNotice = daoEmpNotice.selectOne(idx);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

			reqThis.setAttribute("dtoEmpNotice", dtoEmpNotice);
			RequestDispatcher rd = reqThis.getRequestDispatcher(goRoot+viewPath+"emp_notice_detail.jsp");
			rd.forward(reqThis, respThis);
		}else{
			// 잘못된 메소드 - 0번
		}
		
	}
	
	private void comandAdd() throws ServletException, IOException{
		if("GET".equals(method_type)){
			RequestDispatcher rd = reqThis.getRequestDispatcher(goRoot+viewPath+"emp_notice_add.jsp");
			rd.forward(reqThis, respThis);
		}else if("POST".equals(method_type)){
			String title=reqThis.getParameter("title");
			String contents=reqThis.getParameter("contents");
			DaoEmpNotice daoEmpNotice = new DaoEmpNotice();
			daoEmpNotice.insertOne(title,contents,web_id);

			respThis.sendRedirect("?mode=list");
		}else{
			// 잘못된 메소드 - 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}
		// ii -elseif
	}// comandAdd()

	
	private void comandEdit() throws IOException, ServletException{
		if("GET".equals(method_type)){
			int num=Integer.parseInt(reqThis.getParameter("idx"));
			System.out.println("여기들어왔니");
			DaoEmpNotice daoEmpNotice = new DaoEmpNotice();
			DtoEmpNotice dtoEmpNotice = new DtoEmpNotice();
			dtoEmpNotice = daoEmpNotice.selectOne(num);
			
			reqThis.setAttribute("dtoEmpNotice", dtoEmpNotice);
			RequestDispatcher rd = reqThis.getRequestDispatcher(goRoot+viewPath+"emp_notice_edit.jsp");
			rd.forward(reqThis, respThis);
		}else if("POST".equals(method_type)){
			String title = reqThis.getParameter("title");
			String contents=reqThis.getParameter("contents");
			int num =Integer.parseInt(reqThis.getParameter("num"));

			DaoEmpNotice daoEmpNotice =new DaoEmpNotice();
			
			int result=0;
			result = daoEmpNotice.edit(title, contents, num);
			if(result>0){
				respThis.sendRedirect("?mode=list");
			}else{
				respThis.sendRedirect("?mode=edit&idx="+num);
			}
		}else{
		
		}
	}
	private void comandDel() throws ServletException, IOException{
		//int result = daoJobInfo.delRecord();
		if("POST".equals(method_type)){
			int num=Integer.parseInt(reqThis.getParameter("idx"));
			DaoEmpNotice daoEmpNotice = new DaoEmpNotice();
			daoEmpNotice.deleteOne(num);
		}else{
			// 잘못된 메소드 - 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}

	}//comandDel()
	
}
