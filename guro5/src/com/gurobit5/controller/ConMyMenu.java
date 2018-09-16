package com.gurobit5.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gurobit5.model.DaoAttend;
import com.gurobit5.model.DaoLecInfo;
import com.gurobit5.model.DaoLecRoom;
import com.gurobit5.model.DaoMember;
import com.gurobit5.model.DaoScore;
import com.gurobit5.model.entity.DtoAttend;
import com.gurobit5.model.entity.DtoLecInfo;
import com.gurobit5.model.entity.DtoLecRoom;
import com.gurobit5.model.entity.DtoMember;
import com.gurobit5.model.entity.DtoScore;
import com.gurobit5.util.MyErrSender;

@WebServlet("/mymenu.bit")
public class ConMyMenu extends HttpServlet {
	// try - sskim / 2018-05-24
	// try - sskim / 2018-05-25 : 리팩토링 

	// menu(pageid) : 
	// 강의관리 : lec_con  try - 05-28 sskim
	// 출결확인 : attend_con { add mode 05-28 hjkim }
	// 성적관리 : score_con definition - 05-28 sskim  not create in selectpage()
	// 계정관리 : id_con = { try - 05-29 sskim - modify } 05-30 끝
	// 관리자메뉴 : master

	// ===== Command(modes) =========
	private String[] lecModes = {"list", "detail", "add", "edit", "arrange"}; // del 삭제
	private String[] attendModes = { "list","detail", "add", "edit","edit2", "del","check","in","out","absent"};
	private String[] scoreModes = {"list","detail", "edit"};
	private String[] idModes = {"list","detail", "edit"};// 계정유형 변경은 ajax

	// Path 관련
	private String goRoot = "./";
	// my메뉴의 경우 메소드가 곧 클래스 같은놈이라(사실 빼도 좋은데) view 경로가 다를수 있음
	// 그래서 각 comandLecCon 등 안에서 viewPath를 꼭 설정해야됨.

	// 여기서 쓸 것들
	private HttpServletRequest reqThis;
	private HttpServletResponse respThis;
	private RequestDispatcher rd;

	private String method_type;
	private String pageid;
	private String modeP;

	private String web_id; // id값
	private String web_name;
	private int web_mem_type;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		mymenuInit(req, resp);
	}// doGet()

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		mymenuInit(req, resp);
	}// doPost()


	private void mymenuInit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		// 혹시 모를 사태를 대비합시다.
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		// 필드 셋팅
		this.reqThis = req;
		this.respThis = resp;

		pageid = req.getParameter("pageid");
		modeP = req.getParameter("mode"); // modeP : 파라미터로 부터 받은 mode 라는 뜻
		if(pageid==null)pageid="none";
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
		if(web_id==null){web_id="임시id"; web_mem_type=100;}
		if(web_name==null) web_name="임시id";

		
		
		// 셋팅 끝나고 pageid를 구분하는 메소드 호출합니다.
		// 여기서 대부분의 catch를 실행합니다.		
		try {
			selectPage();
		} catch (ClassNotFoundException e) {
			MyErrSender.sendErr("알수없는 서버문제.. 빠른시일내로 고치겠습니다.", req, resp, goRoot);
			e.printStackTrace();
			return;
		} catch (SQLException e) {
			MyErrSender.sendErr("알수없는 서버문제.. 빠른시일내로 고치겠습니다.", req, resp, goRoot);
			e.printStackTrace();
			return;
		} catch (ParseException e) {
			MyErrSender.sendErr("알수없는 서버문제.. 빠른시일내로 고치겠습니다.", req, resp, goRoot);
			e.printStackTrace();
			return;
		} catch ( Exception e ){
			MyErrSender.sendErr("알수없는 서버문제.. 빠른시일내로 고치겠습니다.", req, resp, goRoot);
			e.printStackTrace();
			return;
		}
		
		
	}// mymenuInit()

	private void selectPage() throws ClassNotFoundException, SQLException, ServletException, IOException, ParseException {

		// 여기서 체크합니다.
		// comandCheck는 각각의 modes 배열을 확인해서 커맨드가 없으면 true를 리턴합니다.
		if("lec_con".equals(pageid)){
			if(comandCheck(lecModes)){
				// 제공하지 않는 기능 -- 2번
				MyErrSender.sendErr(2, reqThis, respThis,goRoot);
			}else{
				System.out.println("Lec_con test : false >> comandLecCon 명령감");
				comandLecCon();
			}
		}else if("attend_con".equals(pageid)){
			if(	comandCheck(attendModes) ){
				// 제공하지 않는 기능 -- 2번
				MyErrSender.sendErr(2,reqThis,respThis,goRoot);
			}else{
				System.out.println("comand-check test : false  >> comandAttendCon 명령감");
				comandAttendCon();
			}
		}else if("score_con".equals(pageid)){
			if(	comandCheck(scoreModes) ){
				// 제공하지 않는 기능 -- 2번
				MyErrSender.sendErr(2,reqThis,respThis,goRoot);
			}else{
				System.out.println("comand-check test : false 다음 명령감");
				comandScoreCon();
			}
		}else if("id_con".equals(pageid)){
			if(	comandCheck(idModes) ){
				// 제공하지 않는 기능 -- 2번
				MyErrSender.sendErr(2,reqThis,respThis,goRoot);
			}else{
				System.out.println("comand-check test : false >> comandIdCon  명령감");
				comandIdCon();
			}
		}else if("master".equals(pageid)){
			int[] compareType={5};
			if(MyErrSender.idTypeChecker(compareType, web_mem_type)){
				//"[ 로그인 ] 이 필요합니다 "// 5번 -- 로그인 필요합니다.
				MyErrSender.sendErr(5,reqThis,respThis,goRoot);
			}else{
				RequestDispatcher rd = reqThis.getRequestDispatcher(goRoot+"/view/master.jsp");
				rd.forward(reqThis, respThis);
			}
		}else{
			// 제공하지 않는 메뉴 -- 3번
			MyErrSender.sendErr(3,reqThis,respThis,goRoot);
		}
	}// selectPage()


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

	
	private void comandScoreCon() throws ServletException, IOException, ClassNotFoundException, SQLException {
		// try - hyunjung/2018-05-29
		String viewPath = "view/mymenu/sco_con/";
		
		if("list".equals(modeP)){
			if("GET".equals(method_type)){
				DaoScore daoScore = new DaoScore();
				ArrayList<DtoScore> list = new ArrayList<DtoScore>();
				//로그인한 강사의 lec_code와 같은 lec_code를 갖는 성적만 출력해야 하므로
				//session의 id를 넘겨준다
				list = daoScore.selectAll(web_id);
				
				reqThis.setAttribute("list", list);
				RequestDispatcher rd = reqThis.getRequestDispatcher(goRoot+viewPath+"list.jsp");
				rd.forward(reqThis, respThis);
			}else{
				// 허용하지 않는 method - 0번
				MyErrSender.sendErr(0, reqThis, respThis, goRoot);
			}
			
		}else if("detail".equals(modeP)){
			if("GET".equals(method_type)){
				DaoScore daoScore = new DaoScore();
				DtoScore dtoScore = new DtoScore();
				//성적detail 페이지는 학생만 사용하기때문에 session의 id를 사용
				dtoScore = daoScore.selectOne(web_id);
				
				reqThis.setAttribute("dtoScore", dtoScore);
				RequestDispatcher rd = reqThis.getRequestDispatcher(goRoot+viewPath+"detail.jsp");
				rd.forward(reqThis, respThis);
			}else{
				// 허용하지 않는 method - 0번
				MyErrSender.sendErr(0, reqThis, respThis, goRoot);
			}
			
		}else if("edit".equals(modeP)){
			if("GET".equals(method_type)){
				//수정페이지로 이동할때 GET
				String ids = reqThis.getParameter("ids"); //체크된 id들 문자열 "id,id,..."
				String arr[] = ids.split(",");//ids 문자열을 , 로 쪼개서 배열로 만듦
				
				DaoScore daoScore = new DaoScore();
				ArrayList<DtoScore> list = new ArrayList<DtoScore>();
				//
				list = daoScore.editList(web_id,arr);
				
				reqThis.setAttribute("list", list);
				RequestDispatcher rd = reqThis.getRequestDispatcher(goRoot+viewPath+"edit.jsp");
				rd.forward(reqThis, respThis);
				
			}if("POST".equals(method_type)){
				//내용을 수정해서 완료 버튼을 누를때 POST
				
				String str = reqThis.getParameter("str");// "id,90,90,90,270/id2,80,80,80,240/..."
				//System.out.println(str);
				String arr1[] = str.split("/");
				String arr2[][]= new String[arr1.length][];
				for(int i=0; i<arr1.length; i++){
					arr2[i]=arr1[i].split(",");
				}
				String id="";
				int java=0;
				int web=0;
				int db=0;
				int tot=0;
				DaoScore daoScore = new DaoScore();
				for(int i=0; i<arr2.length; i++){
					String tmp[] = arr2[i];
					id=tmp[0];
					java=Integer.parseInt(tmp[1]);
					web=Integer.parseInt(tmp[2]);
					db=Integer.parseInt(tmp[3]);
					tot=Integer.parseInt(tmp[4]);
					daoScore.update(id,java,web,db,tot);
				}
				
				
			}else{
				// 허용하지 않는 method - 0번
				MyErrSender.sendErr(0, reqThis, respThis, goRoot);
			}
			
		}else{
			// 제공하지 않는 기능 - 2번
			MyErrSender.sendErr(2, reqThis, respThis, goRoot);
		}
		
	}//comandScoreCon()

	// 0529 - sskim
	// 0530 - sskim
	private void comandIdCon() throws ServletException, IOException, ClassNotFoundException, SQLException {
		// {"list", "typemod" ,"detail", "update"};

		String viewPath = "view/mymenu/id_con/";
		// 계속 쓸놈들
		String[] memTypeNs = {"학생","강사","취업","영업","행정","관리자","예비"};
		int[] memTypeVs = { 0, 1, 2, 3, 4, 5, 99};
		DaoMember daoMember = new DaoMember();
		
		if("list".equals(modeP)){
			if("GET".equals(method_type)){
				// 페이징 처리
				int pageNum = 1; // 초기값
				int rowNum = 100; // 초기값.. 한번에 보여줄

				String pageP = reqThis.getParameter("pageNum");
				String rowP = reqThis.getParameter("rowNum");
				if( pageP!=null){pageNum = Integer.parseInt(pageP);	}
				if( rowP!=null){rowNum = Integer.parseInt(rowP); }

				int start = (pageNum-1)*rowNum+1; // 시작 번호 계산
				// 조건체크
				// 1. 유형별, 나중에 이름, id 추가해야됨
				String memType = reqThis.getParameter("mem_t");
				//String idS = reqThis.getParameter("web_s");
				//String namsS = reqThis.getParameter("name_s");

				List<DtoMember> list = null ;
				if(memType!=null){
					// 승인요청(바로 memType 99  나머지 경우도 계산해서 넣어줍시다)
					list = daoMember.selectAllbyType(start, start+rowNum-1, Integer.parseInt(memType));
					reqThis.setAttribute("acceptOn", "on");
				}else{
					// 조건 없는 거
					list = daoMember.selectAll(start, start+rowNum-1);
				}
				// 리스트 
				reqThis.setAttribute("dtoMemberList", list);
				// 항목 유형 추가
				reqThis.setAttribute("memTypeListN", memTypeNs);
				reqThis.setAttribute("memTypeListV", memTypeVs);
				// 페이징 처리 기본
				int totNum = daoMember.countAllByLecCode(); // lec_code가 null이 아닌것들을 불러온다.
				reqThis.setAttribute("totNum", totNum);
				reqThis.setAttribute("rowNum", rowNum);

				rd = reqThis.getRequestDispatcher(goRoot+viewPath+"id_list.jsp");
				rd.forward(reqThis, respThis);
			}else{
				// 제공하지 않는 method - 0
				MyErrSender.sendErr(0, reqThis, respThis, goRoot);
			}

		}else if("detail".equals(modeP)){
			//
			if("GET".equals(method_type)){
				String id= reqThis.getParameter("idx");
				DtoMember dtoMember = null;
				dtoMember	= daoMember.selectOne(id);
				int tmp = dtoMember.getMemType(); 
				for(int i=0; i<memTypeNs.length; i++){
					if(tmp==memTypeVs[i]){
						dtoMember.setMemTypeName(memTypeNs[i]);
					}
				}
				reqThis.setAttribute("dtoMember", dtoMember);
				
				rd = reqThis.getRequestDispatcher(goRoot+viewPath+"mypage_detail.jsp");
				rd.forward(reqThis, respThis);
			}else{
				// 제공하지 않는 method - 0
				MyErrSender.sendErr(0, reqThis, respThis, goRoot);
			}

		}else if("edit".equals(modeP)){
			if("GET".equals(method_type)){
				// 하나보기 공통
				String id= reqThis.getParameter("idx");
				DtoMember dtoMember = daoMember.selectOne(id);

				/*int tmp = dtoMember.getMemType(); 
				for(int i=0; i<memTypeNs.length; i++){
					if(tmp==memTypeVs[i]){
						dtoMember.setMemTypeName(memTypeNs[i]);
					}
				}*/
				reqThis.setAttribute("dtoMember", dtoMember);

				// 항목 유형 추가
				//reqThis.setAttribute("memTypeListN", memTypeNs);
				//reqThis.setAttribute("memTypeListV", memTypeVs);

				rd = reqThis.getRequestDispatcher(goRoot+viewPath+"mypage_edit.jsp");
				rd.forward(reqThis, respThis);

			}else if("POST".equals(method_type)){

				String id = reqThis.getParameter("id");
				int phone = Integer.parseInt(reqThis.getParameter("phone"));
				String email = reqThis.getParameter("email");
				String pw1 = reqThis.getParameter("pw1");
				String pw2 = reqThis.getParameter("pw2");
				if(pw1.equals(pw2)){//비밀번호 두개가 일치할 때
					
					int result = daoMember.updateMypage(id,phone,email,pw2);
					if(result>0){
						respThis.sendRedirect("?pageid=id_con&mode=detail&idx="+id);
					}else{
						// 업데이트 실패경우..
						
					}
				}else{
					//비밀번호 불일치할때 에러페이지..
					//프론트에서 미리 처리하기때문에 페이지 안만들었음
					System.out.println("비번 불일치..");
				}
			}else{
				// 제공하지 않는 method - 0
				MyErrSender.sendErr(0, reqThis, respThis, goRoot);
			}
		
		}else {
			// 제공하지 않는 mode - 2번
			MyErrSender.sendErr(2, reqThis, respThis, goRoot);
		}
	}// comandIdCon()


	// try - 0528 - sskim
	private void comandAttendCon() throws ClassNotFoundException, SQLException, ServletException, IOException, ParseException {
		//
		String viewPath = "view/mymenu/att_con/";
		
		if("list".equals(modeP)){
			if("GET".equals(method_type)){
				// int pageNum = 1;
				// int rowNum = 20; List<String[]> list
				// int totNum = daoMember.countByLecCode();
				DaoMember daoMember = null;
				daoMember = new DaoMember();

				int pageNum = 1;
				int rowNum = 20;

				String pageP = reqThis.getParameter("pageNum");
				String rowP = reqThis.getParameter("rowNum");

				if( pageP!=null){pageNum = Integer.parseInt(pageP);	}
				if( rowP!=null){rowNum = Integer.parseInt(rowP); }

				int start = (pageNum-1)*rowNum+1;
				List<DtoMember> list = daoMember.selectStuAllAddLecName(start,start+rowNum-1);
				int totNum = daoMember.countAllByLecCode(11111);// not null 에다가 11111 이 아닌것까지 불러오기
				// 조심 쓸때 다 String[] 임

				reqThis.setAttribute("daoMemberlist", list);
				reqThis.setAttribute("totNum", totNum);
				reqThis.setAttribute("rowNum", rowNum);

				rd = reqThis.getRequestDispatcher(goRoot+viewPath+"att_con_list.jsp");
				rd.forward(reqThis, respThis);
			}else{
				// 허용하지 않는 method - 0번
				MyErrSender.sendErr(0, reqThis, respThis, goRoot);
			}

		}else if("detail".equals(modeP)) {
			//try - hyunjung/2018-05-28
			//학생 한명의 출결 정보를 불러오는 페이지
			if("GET".equals(method_type)){
				ArrayList<DtoAttend> list=null;
				DaoAttend daoAttend = new DaoAttend();
				//학생-본인출결확인, 직원-클릭한 학생 출결확인
				// ->id를 파라미터로 받아서 사용해야 함
				String id=reqThis.getParameter("id");
				
				if(web_mem_type==0){//학생
					list = daoAttend.selectOne(web_id);
				}else{
					list = daoAttend.selectOne(id);
				}
				
				
				reqThis.setAttribute("list", list);
				RequestDispatcher rd = reqThis.getRequestDispatcher(goRoot+viewPath+"detail.jsp");
				rd.forward(reqThis, respThis);
			}else{
				// 허용하지 않는 method - 0번
				MyErrSender.sendErr(0, reqThis, respThis, goRoot);
			}
		}else if("check".equals(modeP)){
			//try - hyunjung/2018-05-30
			//modify - hyunjung/2018-05-31
			//강사 - 출석체크 페이지
			if("GET".equals(method_type)){
				String param = reqThis.getParameter("today");
				java.util.Date date = new java.util.Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String msg=null;
				if(param==null){
					msg = sdf.format(date);
				}else{
					msg=param;
				}
				ArrayList<DtoAttend> listM=null; //출석체크할 학생 리스트
				ArrayList<DtoAttend> listA=null; //학생들의 입실,퇴실시간 리스트
				DaoAttend daoAttend = new DaoAttend();
				if(msg!=null){
					java.sql.Date sql_date = new java.sql.Date(sdf.parse(msg).getTime()); 
					listM = daoAttend.selectName(web_id);
					listA = daoAttend.selectTime(web_id,sql_date); 
				}
				//listM과 listA를 비교하여 id가 같으면 listM에 listA의 입실퇴실시간을 넣어준다
				for(int i=0; i<listM.size(); i++){
					if(listA.size()!=0){
						for(int j=0; j<listA.size(); j++){
							if((listM.get(i).getId()).equals(listA.get(j).getId())){//id가 같으면
								listM.get(i).setInDate(listA.get(j).getInDate());
								listM.get(i).setOutDate(listA.get(j).getOutDate());
							}
						}
					}
				}
				reqThis.setAttribute("today", msg);
				reqThis.setAttribute("listM", listM);
				RequestDispatcher rd = reqThis.getRequestDispatcher(goRoot+viewPath+"check.jsp");
				rd.forward(reqThis, respThis);
				
			}else{
				// 허용하지 않는 method - 0번
				MyErrSender.sendErr(0, reqThis, respThis, goRoot);
			}
		}else if("in".equals(modeP)){
			//try - hyunjung/2018-05-30
			//출석체크 - 입실 버튼 눌렀을때
			if("POST".equals(method_type)){
				String id = reqThis.getParameter("id");
				String msg = reqThis.getParameter("today");
				java.util.Date date = new java.util.Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf2 = new SimpleDateFormat("kk:mm");
				String msg2 = sdf2.format(date);
				if(msg!=null){
					if(msg2!=null){
						java.sql.Time sql_time = new java.sql.Time(sdf2.parse(msg2).getTime()); 
						java.sql.Date sql_date = new java.sql.Date(sdf.parse(msg).getTime()); 
						DaoAttend daoAttend = new DaoAttend();
						daoAttend.inCheck(id,sql_time,sql_date);
					}
				}
				
				respThis.getWriter().print(msg2);//입실시간을 다시 문자열로 보내기
			}else{
				// 허용하지 않는 method - 0번
				MyErrSender.sendErr(0, reqThis, respThis, goRoot);
			}
		}else if("out".equals(modeP)){
			//try - hyunjung/2018-05-30
			//출석체크 - 퇴실 버튼 눌렀을때
			if("POST".equals(method_type)){
				String id = reqThis.getParameter("id");
				String msg = reqThis.getParameter("today");
				java.util.Date date = new java.util.Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf2 = new SimpleDateFormat("kk:mm");
				String msg2 = sdf2.format(date);
				if(msg!=null){
					if(msg2!=null){
						java.sql.Time sql_time = new java.sql.Time(sdf2.parse(msg2).getTime()); 
						java.sql.Date sql_date = new java.sql.Date(sdf.parse(msg).getTime()); 
						DaoAttend daoAttend = new DaoAttend();
						daoAttend.outCheck(id, sql_time, sql_date);
					}
				}
				respThis.getWriter().print(msg2);//퇴실시간을 다시 문자열로 보내기
			}else{
				// 허용하지 않는 method - 0번
				MyErrSender.sendErr(0, reqThis, respThis, goRoot);
			}
		}else if("absent".equals(modeP)){
			//try - hyunjung/2018-05-30
			//출석체크 - 결석 버튼 눌렀을때
			if("POST".equals(method_type)){
				String id = reqThis.getParameter("id");
				String msg = reqThis.getParameter("today");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if(msg!=null){
						java.sql.Date sql_date = new java.sql.Date(sdf.parse(msg).getTime()); 
						DaoAttend daoAttend = new DaoAttend();
						daoAttend.abCheck(id, sql_date);
				}
			}else{
				// 허용하지 않는 method - 0번
				MyErrSender.sendErr(0, reqThis, respThis, goRoot);
			}
		}else if("edit".equals(modeP)){
			//try - hyunjung/2018-05-31
			//출석체크 - 수정 버튼 눌렀을때
			if("POST".equals(method_type)){
				String id = reqThis.getParameter("id");
				String msg = reqThis.getParameter("today");
				java.util.Date date = new java.util.Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf2 = new SimpleDateFormat("kk:mm");
				DtoAttend dtoAttend = new DtoAttend();
				if(msg!=null){
					java.sql.Date sql_date = new java.sql.Date(sdf.parse(msg).getTime()); 
					DaoAttend daoAttend = new DaoAttend();
					//기존의 입력되었던 시간값을 가져옴
					dtoAttend=daoAttend.editClick(id,sql_date);
				}
				String inTime = sdf2.format(dtoAttend.getInDate());
				String outTime = sdf2.format(dtoAttend.getOutDate());
				String time = "{\"inTime\":\""+inTime+"\",\"outTime\":\""+outTime+"\"}";
				respThis.setContentType("application/json; charset=UTF-8");
				respThis.getWriter().print(time);//json 형태로 입실,퇴실 시간을 보냄
			}else{
				// 허용하지 않는 method - 0번
				MyErrSender.sendErr(0, reqThis, respThis, goRoot);
			}
		}else if("edit2".equals(modeP)){
			//try - hyunjung/2018-05-31
			//출석체크 - 수정>완료 버튼 눌렀을때
			if("POST".equals(method_type)){
				String id = reqThis.getParameter("id");
				String inT = reqThis.getParameter("inT");
				String outT = reqThis.getParameter("outT");
				String msg = reqThis.getParameter("today");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf2 = new SimpleDateFormat("kk:mm");
				
				if(inT!=null){
				if(outT!=null){
				if(msg!=null){
					java.sql.Time in_date = new java.sql.Time(sdf2.parse(inT).getTime()); 
					java.sql.Time out_date = new java.sql.Time(sdf2.parse(outT).getTime()); 
					java.sql.Date sql_date = new java.sql.Date(sdf.parse(msg).getTime()); 
					DaoAttend daoAttend = new DaoAttend();
					daoAttend.ed_Complete(id,in_date,out_date,sql_date);
				}}}
				
			}else{
				// 허용하지 않는 method - 0번
				MyErrSender.sendErr(0, reqThis, respThis, goRoot);
			}
		}else{
			// 제공하지 않는 기능 - 2번
			MyErrSender.sendErr(2, reqThis, respThis, goRoot);
		}

	}// comandAttend()

	// 	try - 0528 - sskim
	// 	try - 0530 - sskim
	private void comandLecCon() throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
		DaoLecInfo daoLecInfo = null;
		daoLecInfo = new DaoLecInfo();

		// 이거 꼭해주세요
		String viewPath = "view/mymenu/lec_con/";

		String[] nextUrlN = {"arrange"
							,"scorelist"}; // 05-30
		
		String[] nextUrlV = {"?pageid=lec_con&mode=arrange&idx="
							,"?pageid=sc_con&mode=arrange&idx="}; // 05-30
		
		if("list".equals(modeP)){
			// doGet 일때만 실행하게 할거임.
			if("GET".equals(method_type)){
				
				int pageNum = 1;		int rowNum = 5;
				String pageP = reqThis.getParameter("pageNum");
				String rowP = reqThis.getParameter("rowNum");

				if( pageP!=null){pageNum = Integer.parseInt(pageP);	}
				if( rowP!=null){rowNum = Integer.parseInt(rowP);	}

				int start = (pageNum-1)*rowNum+1;
				List<DtoLecInfo> list = daoLecInfo.selectAllforList(start,start+rowNum-1);
				int totNum = daoLecInfo.countAll();

				String nextMode = reqThis.getParameter("next");
				// 다른모드로 이동할때 > 강의리스트에서 강의 배정으로
				if(nextMode!=null){
					// 아래경우는 강의배정
					if(nextUrlN[0].equals(nextMode))reqThis.setAttribute("nextUrl", nextUrlV[0]);
				}
				
				reqThis.setAttribute("lecbeans", list);
				reqThis.setAttribute("totNum", totNum);
				reqThis.setAttribute("rowNum", rowNum);
				
				rd = reqThis.getRequestDispatcher(goRoot+viewPath+"lec_list.jsp");
				rd.forward(reqThis, respThis);

			}else{
				// 다른 메소드 허용되지 않습니다. 0번
				MyErrSender.sendErr(0, reqThis, respThis, viewPath);
			}

		}else if("detail".equals(modeP)) {
			
			
			int[] compareType = {3,4,5};
			if(MyErrSender.idTypeChecker(compareType, web_mem_type)){
				// 로그인 권한
				MyErrSender.sendErr(5, reqThis, respThis, goRoot);
			}else{
				// doGet 일때만 실행하게 할거임.
				if("GET".equals(method_type)){
					int idx = -1;
					String idxP = reqThis.getParameter("idx");
					if(idxP!=null)idx = Integer.parseInt(idxP);
					
					DaoLecRoom daoLecRoom = new DaoLecRoom();
					
					DtoLecInfo dtoLecInfo = daoLecInfo.selectOne(idx);
					DtoLecRoom dtoLecRoom = daoLecRoom.selectOneInfo(idx);
					
					if(dtoLecInfo==null){
						// 존재하지 않는 글 -- 1번
						MyErrSender.sendErr(1, reqThis, respThis, goRoot);
					}else{
						reqThis.setAttribute("dtoLecInfo", dtoLecInfo);
						reqThis.setAttribute("dtoLecRoom", dtoLecRoom);

						rd = reqThis.getRequestDispatcher(goRoot+viewPath+"lec_detail.jsp");
						rd.forward(reqThis, respThis);
					}

				}else{
					// 잘못된 접근 -- 0번
					MyErrSender.sendErr(0, reqThis, respThis, goRoot);
				}
			}// 권한 싸기
		}else if("add".equals(modeP)){
			
 			// 권한싸기 - 행정/ 관리자

			if("GET".equals(method_type)){
				// doGet 일때는 입력폼
				DaoMember daoMember = new DaoMember();
				DaoLecRoom daoLecRoom = new DaoLecRoom();

				List<DtoMember> tealist = daoMember.selectNameAllByLecCodeAndMemTye(11111 ,1);
				List<DtoLecRoom> roomlist = daoLecRoom.selectAll("빔");

				reqThis.setAttribute("tealist", tealist);
				reqThis.setAttribute("roomlist", roomlist);

				rd = reqThis.getRequestDispatcher(goRoot+viewPath+"lec_add.jsp");
				rd.forward(reqThis, respThis);

			}else if("POST".equals(method_type)){
				// doPost일때는 db 갔다 입력하기
				// 성공시
				DtoLecInfo dtoLecInfo = getDtoLecInfoToParam();
				
				
				int result = daoLecInfo.addRecord(dtoLecInfo);
				
				
				System.out.println(dtoLecInfo.getLecCode());
				if(result==1){
					String teacher3 = reqThis.getParameter("teacher");
					DtoLecInfo dtoLecInfo2 = daoLecInfo.selectOnebyName(teacher3);
					DaoMember daoMember = new DaoMember();
					DaoLecRoom daoLecRoom = new DaoLecRoom();

					daoMember.changeLecCode(dtoLecInfo2.getTeacher(), 1, 11111, dtoLecInfo2.getLecCode() );
					daoLecRoom.changeUsing(dtoLecInfo2.getClassno(),"사용");
					
					respThis.sendRedirect(goRoot+"mymenu.bit?pageid=lec_con&mode=list");
				}
			
			
			}else{
				// 잘못된 접근 -- 0번
				MyErrSender.sendErr(0, reqThis, respThis, goRoot);
			}
		}else if("edit".equals(modeP)){
			
			// 권한싸기 - 행정/ 관리자
					
			if("GET".equals(method_type)){
				// doGet 일때는 수정폼
				int idx = -1;
				// idx == 강의코드
				String idxP = reqThis.getParameter("idx");
				if(idxP!=null)idx = Integer.parseInt(idxP);

				DaoMember daoMember = new DaoMember();
				DaoLecRoom daoLecRoom = new DaoLecRoom();

				DtoLecInfo dtoLecInfo = daoLecInfo.selectOne(idx);
				// 가져온걸 바탕으로... 강사와 강의실을 빈걸로 바꿔준다...
				daoMember.changeLecCode(dtoLecInfo.getTeacher(), 1, dtoLecInfo.getLecCode(), 11111 );
				daoLecRoom.changeUsing(dtoLecInfo.getClassno(),"빔");
				
				List<DtoMember> tealist = daoMember.selectNameAllByLecCodeAndMemTye(11111,1);
				List<DtoLecRoom> roomlist = daoLecRoom.selectAll("빔");

				reqThis.setAttribute("tealist", tealist);
				reqThis.setAttribute("roomlist", roomlist);
				reqThis.setAttribute("dtoLecInfo", dtoLecInfo);

				rd = reqThis.getRequestDispatcher(goRoot+viewPath+"lec_edit.jsp");
				rd.forward(reqThis, respThis);

			}else if("POST".equals(method_type)){
				// doPost일때는 db 갔다 입력하기
				reqThis.setCharacterEncoding("UTF-8");

				DtoLecInfo dtoLecInfo = getDtoLecInfoToParam();

				String lecCode = reqThis.getParameter("lecCode"); // edit 때 추가
				dtoLecInfo.setLecCode(Integer.parseInt(lecCode)); // edit때 추가

				DaoMember daoMember = new DaoMember();
				DaoLecRoom daoLecRoom = new DaoLecRoom();
				
				int result = daoLecInfo.updateRecord(dtoLecInfo);
				if(result==1){
					String teacher3 = reqThis.getParameter("teacher");
					DtoLecInfo dtoLecInfo2 = daoLecInfo.selectOnebyName(teacher3);

					daoMember.changeLecCode(dtoLecInfo2.getTeacher(), 1, 11111, dtoLecInfo2.getLecCode() );
					daoLecRoom.changeUsing(dtoLecInfo2.getClassno(),"사용");				
					respThis.sendRedirect(goRoot+"mymenu.bit?pageid=lec_con&mode=list");
				}
				else{}// 수정실패
			}else{
				// 잘못된 접근 -- 0번
				MyErrSender.sendErr(0, reqThis, respThis, goRoot);
			}

		}else if("arrange".equals(modeP)){
			if("GET".equals(method_type)){
				int[]compareType ={3,4,5};
				if(	MyErrSender.idTypeChecker(compareType, web_mem_type)){
					MyErrSender.sendErr(5, reqThis, respThis, viewPath);
					return;
				}
				
				DaoMember daoMember = new DaoMember();
				DaoLecInfo daoLec = new DaoLecInfo();
				String lecCodeP = reqThis.getParameter("idx");
				if(lecCodeP==null){
					MyErrSender.sendErr("강의코드가 없습니다.", reqThis, respThis, goRoot);
					return;
				}
				int lecCode = Integer.parseInt(lecCodeP);
				
				
				// 유효성검사해야되긴함

				List<DtoMember> nowStulist = null;
				List<DtoMember> noneStulist = null;
				try{
					nowStulist = daoMember.selectAllStu(lecCode);
					noneStulist = daoMember.selectAllStu(11111); // 초기값
				}finally{
					// 뭐할거 생각나면 넣으셈
				}
				DtoLecInfo dtoLecInfo = daoLec.selectOne(lecCode);
				
				DaoLecRoom daoLecRoom = new DaoLecRoom();
				DtoLecRoom lecRommInfo = daoLecRoom.selectOneInfo(lecCode);
				
				
				reqThis.setAttribute("lecRoomInfo",lecRommInfo );
				reqThis.setAttribute("lecName", dtoLecInfo.getClassName());
				reqThis.setAttribute("nowlist", nowStulist);
				reqThis.setAttribute("nonelist", noneStulist);
				rd = reqThis.getRequestDispatcher(goRoot+viewPath+"lec_arrange.jsp");
				rd.forward(reqThis, respThis);
				
			}else{
				// 잘못된것들 - -0번 에러 메소드 에러
				MyErrSender.sendErr(0, reqThis, respThis, viewPath);
			}

		}else{
			// 잘못된 접근 -- 2번
			MyErrSender.sendErr(2, reqThis, respThis, goRoot);
		}

	}// comandLecCon() end


	// 유틸용
	// 넣을때 중복 넣기 싫어서.. sskim
	private DtoLecInfo getDtoLecInfoToParam() throws ParseException, UnsupportedEncodingException{

		DtoLecInfo dtoLecInfo = new DtoLecInfo();

		String className = reqThis.getParameter("className");
		String teacher = reqThis.getParameter("teacher");
		String openDate = reqThis.getParameter("openDate");
		String endDate = reqThis.getParameter("endDate");
		String maxAB = reqThis.getParameter("maxAB");
		String keyword = reqThis.getParameter("keyword");
		String preView = reqThis.getParameter("preview");
		String contents = reqThis.getParameter("contents");
		String status = reqThis.getParameter("status");
		String classno = reqThis.getParameter("classno");
		
		// 유효성 검사해야됨.
		
		if(status==null)status="1";
		SimpleDateFormat shortDate = new SimpleDateFormat("yyyy-MM-dd");
		//SimpleDateFormat detailDate = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

		dtoLecInfo.setClassName(className);
		dtoLecInfo.setTeacher(teacher);
		dtoLecInfo.setOpenDate( new Date(shortDate.parse(openDate).getTime() ));
		dtoLecInfo.setEndDate( new Date(shortDate.parse(endDate).getTime() ));
		dtoLecInfo.setMaxAB(Integer.parseInt(maxAB));
		dtoLecInfo.setKeyword(keyword);
		dtoLecInfo.setPreView(preView);
		dtoLecInfo.setContents(contents);
		dtoLecInfo.setStatus(Integer.parseInt(status));
		dtoLecInfo.setClassno(Integer.parseInt(classno));

		return dtoLecInfo;
	}// getDtoLecInfoToParam()


	
	
	
}// ConDoc
