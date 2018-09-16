package com.gurobit5.controller.bbs;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.gurobit5.model.DaoMember;
import com.gurobit5.model.bbs.DaoJobInfo;
import com.gurobit5.model.bbs.entity.DtoJobInfo;
import com.gurobit5.util.MyErrSender;

@WebServlet("/bbs/job_info.bit")
public class ConJobInfo extends HttpServlet {
	// try - sskim/2018-05-21
	// try - sskim / 2018-05-24 : 틀만 만듬....
	// try - sskim / 2018-05-29 : 80%


	private String goRoot = "../";
	private String viewPath ="view/bbs/job_info/";

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
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// lecNoticeInit

	private void selectMode() throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
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


	private void comandList() throws ServletException, IOException, ClassNotFoundException, SQLException{

		if("GET".equals(method_type)){
			String search= reqThis.getParameter("search");
			String category= reqThis.getParameter("cat");

			DaoJobInfo daoJobInfo = new DaoJobInfo();
			List<DtoJobInfo> dtoJobInfoList = new ArrayList<DtoJobInfo>();

			String pageP = reqThis.getParameter("pageNum");
			String rowP = reqThis.getParameter("rowNum");

			int pageNum = 1;
			int rowNum = 10;

			if( pageP!=null){pageNum = Integer.parseInt(pageP);	}
			if( rowP!=null){rowNum = Integer.parseInt(rowP); }

			int start = (pageNum-1)*rowNum+1; 

			if(search!=null){
				dtoJobInfoList = daoJobInfo.selectAll(start, start+rowNum-1, search, category);
			}else {
				dtoJobInfoList = daoJobInfo.selectAll(start, start+rowNum-1);
			}

			reqThis.setAttribute("dtoJobInfoList", dtoJobInfoList);
			Iterator<DtoJobInfo> ite = dtoJobInfoList.iterator();
			while(ite.hasNext()){
				DtoJobInfo item = ite.next();
				System.out.println(item.getTitle());
			}
			
			reqThis.setAttribute("totNum", daoJobInfo.countAll());
			reqThis.setAttribute("rowNum", rowNum);

			rd = reqThis.getRequestDispatcher(goRoot+viewPath+"list.jsp");
			rd.forward(reqThis, respThis);
		}else{
			// 허용하지 않는 method - 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}
	}// comandList()



	private void comandDetail() throws ServletException, IOException, ClassNotFoundException, SQLException{ 
		if("GET".equals(method_type)){

			int idx=Integer.parseInt(reqThis.getParameter("idx"));
			String click = reqThis.getParameter("click");
			if(click==null)click="none";
			
			DtoJobInfo dtoJobInfo = new DtoJobInfo();
			DaoJobInfo daoJobInfo = new DaoJobInfo();
			dtoJobInfo = daoJobInfo.selectOne(idx);
			if(click.equals("on")){	  //목록에서 게시물 클릭할때 조회수 증가
				daoJobInfo.viewNum(idx);
			}

			reqThis.setAttribute("dtoJobInfo", dtoJobInfo);
			RequestDispatcher rd = reqThis.getRequestDispatcher(goRoot+viewPath+"detail.jsp");
			rd.forward(reqThis, respThis);

		}else{
			// 잘못된 메소드 - 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}
	}

	private void comandAdd() throws ServletException, IOException, ParseException{
		if("GET".equals(method_type)){
			RequestDispatcher rd = reqThis.getRequestDispatcher(goRoot+viewPath+"add.jsp");
			rd.forward(reqThis, respThis);
		}else if("POST".equals(method_type)){

			int result = 0;
			DaoJobInfo daoJobInfo = new DaoJobInfo();
			// 파라미터에서 값 받기
			DtoJobInfo dtoJobInfo = getDtoJobIfoToParam();
			
			try {
				result = daoJobInfo.insertOne(dtoJobInfo);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}// try-catch
			System.out.println(result);
			if(result>0){
				respThis.setContentType("text/html");
				respThis.getWriter().print(goRoot+"bbs/job_info.bit?mode=list");
			}else{
				respThis.setContentType("text/html");
				respThis.getWriter().print("오류");
			}
		}else{
			// 잘못된 메소드 - 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}
	}// comandCreate()


	private void comandEdit() throws IOException, ServletException, ParseException {

		if("GET".equals(method_type)){
			int idx = Integer.parseInt(reqThis.getParameter("idx"));

			DtoJobInfo dtoJobInfo = null;
			DaoJobInfo daoJobInfo = new DaoJobInfo();
			try {
				dtoJobInfo = daoJobInfo.selectOne(idx);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			reqThis.setAttribute("dtoJobInfo", dtoJobInfo);
			RequestDispatcher rd = reqThis.getRequestDispatcher(goRoot+viewPath+"edit.jsp");
			rd.forward(reqThis, respThis);

		}else if("POST".equals(method_type)){
			int idx = Integer.parseInt(reqThis.getParameter("idx"));

			int result = 0;
			DaoJobInfo daoJobInfo = new DaoJobInfo();
			
			// 파라미터에서 값 받기
			DtoJobInfo dtoJobInfo = getDtoJobIfoToParam();
			dtoJobInfo.setNum(idx);
		
			try {
				result = daoJobInfo.updateOne(dtoJobInfo);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			System.out.println(result);
			if(result>0){
				respThis.setContentType("text/html");
				respThis.getWriter().print(goRoot+"bbs/job_info.bit?mode=list");
			}else{
				respThis.setContentType("text/html");
				respThis.getWriter().print("오류");
			}
			
		}else{
			// 잘못된 메소드 - 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}
	}// comandEdit()

	private void comandDel() throws ServletException, IOException {	
		int result = 0;
		if("POST".equals(method_type)){
			int idx = Integer.parseInt(reqThis.getParameter("idx"));
			DaoJobInfo daoJobInfo = new DaoJobInfo();
			try{
				result = daoJobInfo.deleteOne(idx);
			}catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			System.out.println();
			if(result>0)respThis.sendRedirect("?mode=list");
		}else{
			// 잘못된 메소드 - 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}

	}//comandDel()

	private DtoJobInfo getDtoJobIfoToParam() throws ParseException, UnsupportedEncodingException{

		DtoJobInfo dtoJobInfo = new DtoJobInfo();
		
		String title = reqThis.getParameter("title");
		String id = this.web_id;
		String company = reqThis.getParameter("company");
		
		String hirenum= reqThis.getParameter("hirenum");
		String hiretype = reqThis.getParameter("hiretype");
		String sal = reqThis.getParameter("sal");
		String region = reqThis.getParameter("region");
		String qualify = reqThis.getParameter("qualify");
		
		String duedate = reqThis.getParameter("duedate");
		String email = reqThis.getParameter("email");
		String contents = reqThis.getParameter("contents");
		// 유효성 검사해야됨.
		
		
		SimpleDateFormat shortDate = new SimpleDateFormat("yyyy-MM-dd");
		//SimpleDateFormat detailDate = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

		dtoJobInfo.setTitle(title);
		dtoJobInfo.setId(id);
		dtoJobInfo.setCompany(company);

		dtoJobInfo.setHireNum(Integer.parseInt(hirenum));
		dtoJobInfo.setHireType(hiretype);
		dtoJobInfo.setSal(Integer.parseInt(sal));
		dtoJobInfo.setRegion(region);
		dtoJobInfo.setQualify(qualify);

		dtoJobInfo.setDueDate( new Date(shortDate.parse(duedate).getTime()) );
		dtoJobInfo.setEmail(email);
		dtoJobInfo.setContents(contents);

		return dtoJobInfo;
	}// getDtoLecInfoToParam()

}// ConJobInfo
