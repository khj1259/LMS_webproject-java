package com.gurobit5.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.gurobit5.model.DaoMember;
import com.gurobit5.util.MyErrSender;

@WebServlet("/login.bit")
public class ConLogin extends HttpServlet{
	

	private String goRoot = "./";
	private String viewPath ="view/login/";

	private String[] modes = {"login", "joinChk","idFind", "pwFind", "join", "logout"};
	
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
		loginInit(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		loginInit(req, resp);
	}
		
	private void loginInit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		try {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		this.reqThis = req;
		this.respThis = resp;

		modeP = req.getParameter("mode");
		if(modeP==null)modeP="none";

		this.method_type = req.getMethod();
		
		// 경로넣어주기
		reqThis.setAttribute("rootPath", goRoot); // 파일경로에 씀
		
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
		} catch (ServletException e) {
			MyErrSender.sendErr("알수없는 서버문제.. 빠른시일내로 고치겠습니다.", req, resp, goRoot);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			MyErrSender.sendErr("알수없는 서버문제.. 빠른시일내로 고치겠습니다.", req, resp, goRoot);
			e.printStackTrace();
		} catch (SQLException e) {
			MyErrSender.sendErr("알수없는 서버문제.. 빠른시일내로 고치겠습니다.", req, resp, goRoot);
			e.printStackTrace();
		} catch (IOException e) {
			MyErrSender.sendErr("알수없는 서버문제.. 빠른시일내로 고치겠습니다.", req, resp, goRoot);
			e.printStackTrace();
		}catch ( Exception e ){
			MyErrSender.sendErr("알수없는 서버문제.. 빠른시일내로 고치겠습니다.", req, resp, goRoot);
			e.printStackTrace();
			return;
		}
		
	}// consultInit
	
	private void selectMode() throws ServletException, IOException, ClassNotFoundException, SQLException {
		if(modeP==null){ MyErrSender.sendErr(2, reqThis, respThis, goRoot); return;}
		
		if(comandCheck(modes)){
			// 제공하지 않는 기능 -- 2번
			MyErrSender.sendErr(2, reqThis, respThis, goRoot);
		}else{
			System.out.println("comandCheck : false 다음 명령감");
			if("login".equals(modeP)){
				comandLogin();
			}else if("joinChk".equals(modeP)){
				comandJoinChk();
			}else if("idFind".equals(modeP)){
				comandIdFind();
			}else if("pwFind".equals(modeP)){
				comandPwFind();
			}else if("join".equals(modeP)){
				comandJoin();
			}else if("logout".equals(modeP)){
				comandLogout();
			}
		}// if end
	}// selectMode()
	
	private boolean comandCheck(String[] modes) throws ClassNotFoundException, SQLException, ServletException, IOException {
		boolean checker_mode = true; // 없으면 true 를 리턴해서 조건을 발동 시킴.
		for(String msg : modes){
			if(modeP.equals(msg)){ 
				checker_mode=false; // 명령리스트에 있으면 false
				return checker_mode;
			}
		}// foreach 끝
		return checker_mode;
	}// comandCheck()
	
	
	private void comandLogout() throws ServletException, IOException {
		if("GET".equals(method_type)){
			HttpSession session = reqThis.getSession();
			session.removeAttribute("web_id");
			session.removeAttribute("web_name");
			session.removeAttribute("web_mem_type");
			rd = reqThis.getRequestDispatcher(goRoot+viewPath+"logout.jsp");
			rd.forward(reqThis, respThis);
		}else if("POST".equals(method_type)){
			HttpSession session = reqThis.getSession();
			session.removeAttribute("web_id");
			session.removeAttribute("web_name");
			session.removeAttribute("web_mem_type");
			rd = reqThis.getRequestDispatcher(goRoot+viewPath+"logout.jsp");
			rd.forward(reqThis, respThis);
		}else{
			// 허용하지 않는 method - 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}		
	}

	private void comandJoin() throws IOException, ClassNotFoundException, SQLException, ServletException {
		//2018-05-30 재환 회원 가입 확인
		if("GET".equals(method_type)){

			rd = reqThis.getRequestDispatcher(goRoot+viewPath+"joinForm.jsp");
			rd.forward(reqThis, respThis);
					
		}else if("POST".equals(method_type)){
			// AJAX로 할꺼임
			String id=reqThis.getParameter("id");		
			String password=reqThis.getParameter("password");
			String pwchk=reqThis.getParameter("pwchk");
			String name=reqThis.getParameter("name");
			String email=reqThis.getParameter("email");
			String phoneP = reqThis.getParameter("phone");
			int phone=Integer.parseInt(phoneP);
			
			if(password.equals(pwchk)){
				DaoMember myjoin = new DaoMember();
				int result = myjoin.insertJoin(id, password, name, email, phone);
				if(result ==1)respThis.getWriter().print(goRoot+"login.bit?mode=login");
				else respThis.getWriter().print("있음");
			}else{
				respThis.getWriter().print("오류");
			}
			
		}else{
			// 허용하지 않는 method - 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}
	}// comandJoin()

	private void comandJoinChk() throws IOException, ServletException, ClassNotFoundException, SQLException {
		//2018-05-30 재환 비밀번호 확인
		if("GET".equals(method_type)){
			rd = reqThis.getRequestDispatcher(goRoot+viewPath+"joinchk.jsp");
			rd.forward(reqThis, respThis);
		}else if("POST".equals(method_type)){
			rd = reqThis.getRequestDispatcher(goRoot+viewPath+"joinchk.jsp");
			rd.forward(reqThis, respThis);
		}else{
			// 허용하지 않는 method - 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}
	}// comandPwChk
	
	private void comandPwFind() throws IOException, ServletException, ClassNotFoundException, SQLException {
		//2018-05-30 재환 비밀번호 찾기
		if("GET".equals(method_type)){
			
			rd = reqThis.getRequestDispatcher(goRoot+viewPath+"pwFind.jsp");
			rd.forward(reqThis, respThis);
			
		}else if("POST".equals(method_type)){
			// AJAX로 할꺼임
			String id = reqThis.getParameter("id");
			String name = reqThis.getParameter("name");
			int phone = Integer.parseInt(reqThis.getParameter("phone"));
			
			DaoMember daoMember = new DaoMember();
			int result = daoMember.pwFind(id,name,phone);
			String findM ="없음";
			if(result==1){ 
				String newPw = MyErrSender.getRandom(10);
				daoMember.pwInit(id,newPw);
				findM = daoMember.getPW(id);
			}
			reqThis.setAttribute("findM",findM);
			
			rd  = reqThis.getRequestDispatcher(goRoot+viewPath+"pwChk.jsp");
			rd.forward(reqThis, respThis);
			
		}else{
			// 허용하지 않는 method - 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}
	}// comandPwChk

	private void comandIdFind() throws ServletException, IOException, ClassNotFoundException, SQLException {
		//2018-05-30 재환 아이디 찾기
		if("GET".equals(method_type)){
			
			rd = reqThis.getRequestDispatcher(goRoot+viewPath+"idFind.jsp");
			rd.forward(reqThis, respThis);
			
		}else if("POST".equals(method_type)){
				String name = reqThis.getParameter("name");
				int phone = Integer.parseInt(reqThis.getParameter("phone"));
				DaoMember daoMember  = new DaoMember();
				String result= daoMember.idFindByName(name, phone);
				if("".equals(result)){ 
					reqThis.setAttribute("findM","없음");
				}else{
					reqThis.setAttribute("findM",result);
				}
				rd = reqThis.getRequestDispatcher(goRoot+viewPath+"idChk.jsp");
				rd.forward(reqThis, respThis);
		}else{
			// 허용하지 않는 method - 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}
			
	}// comandIdChk

	@SuppressWarnings("unchecked")
	private void comandLogin() throws ServletException, IOException, ClassNotFoundException, SQLException {
		if("GET".equals(method_type)){
			
			rd = reqThis.getRequestDispatcher(goRoot+viewPath+"login.jsp");
			rd.forward(reqThis, respThis);
			
		}else if("POST".equals(method_type)){
			String id = reqThis.getParameter("id");
			String password = reqThis.getParameter("password");
			if(id==null || "임시id".equals(id)){
				respThis.setContentType("application/x-json");
				respThis.getWriter().print("{\"result\":0}");
				return;
			}
			
			DaoMember daoMember  = new DaoMember();
			int result = daoMember.login(id, password);
			
			JSONObject obj = new org.json.simple.JSONObject();
			obj.put("result", result);
			
			
			if(result == 1){
					
				try {
					String name = new DaoMember().getName(id);
					int mem_type = new DaoMember().getType(id);
					obj.put("name", name);

					if(mem_type!=99){
					HttpSession session = reqThis.getSession();
					// id성공시 필요한 값들을 셋팅함.
					session.setAttribute("web_id", id);
					session.setAttribute("web_name", name);
					session.setAttribute("web_mem_type", mem_type);
					obj.put("confirm","yes");
					}else{
						obj.put("confirm","no");
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			respThis.setContentType("application/x-json");
			respThis.getWriter().print(obj);

		}else{
			// 허용하지 않는 method - 0번
			MyErrSender.sendErr(0, reqThis, respThis, goRoot);
		}
	}// comandLogin()


	
}// Conlogin
