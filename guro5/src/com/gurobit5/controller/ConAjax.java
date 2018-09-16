package com.gurobit5.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.gurobit5.model.DaoAttend;
import com.gurobit5.model.DaoLecRoom;
import com.gurobit5.model.DaoMember;
import com.gurobit5.model.DaoScore;
import com.gurobit5.model.entity.DtoAttend;
import com.gurobit5.util.MyErrSender;

@WebServlet("/ajax.bit")
public class ConAjax extends HttpServlet{
	// try - sskim / 2018-05-24

	private BufferedReader bfr;

	private HttpServletResponse respThis;
	private HttpServletRequest reqThis;
	private String modeP;
	private String jsonP;

	private Object goRoot;
	private Object urlThis;
	private String web_id;

	private JSONArray jsonD;

	private String jsonSingleP;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ajaxInit(req,resp);
	}

	private void ajaxInit(HttpServletRequest req, HttpServletResponse resp){
		// 혹시 모를 사태를 대비합시다.
		try {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		reqThis = req;
		respThis = resp;
		// ID 체커도 필요함
		HttpSession session = req.getSession();
		web_id=(String) session.getAttribute("web_id");
		//임시용
		if(web_id==null) web_id="임시id";
		reqThis.setAttribute("rootPath", goRoot); // 파일경로에 씀
		modeP = req.getParameter("mode");
		jsonSingleP = req.getParameter("single");
		jsonP = req.getParameter("data");
		JSONParser jsonp = new JSONParser();
		try {
			if(jsonP!=null)jsonD = (JSONArray) jsonp.parse(jsonP);
			selectMode();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch ( Exception e ){
			e.printStackTrace();
			return;
		}
		
	}

	private void selectMode() throws ClassNotFoundException, SQLException, IOException{
		if("ch_mem_t".equals(modeP)){
			this.comandChMemType();
			// 없는 ajax 요청
		}else if("ch_lec_code".equals(modeP)){
			this.comandChLecCodeForStu();
		// 없는 ajax 요청
		}else if("check_att".equals(modeP)){
			this.comandCheckAtt();
			// 없는 ajax 요청
		}else{
			
		}

	}// command()

	private void comandCheckAtt() throws ClassNotFoundException, SQLException, IOException {
		String id = jsonSingleP;
		DaoAttend daoAttend = new DaoAttend();
		ArrayList<DtoAttend> list = null; 
		list = daoAttend.selectOne(id);
		String msg = "없음@ccc";
		if(list.size()!=0){
			DtoAttend dtoAttend = list.get(0);
			int absDay = dtoAttend.getAbsent();
			int lateDay = dtoAttend.getLate();
			int cnt = (int) (absDay+(lateDay*0.5));
			int maxDay = dtoAttend.getMax_ab();
			if(cnt < maxDay*0.2 ) msg = "아주좋음@3C989E";
			else if(cnt < maxDay*0.4 ) msg = "좋음@5DB5A4";
			else if(cnt < maxDay*0.6 ) msg = "보통@F4CDA5";
			else if(cnt < maxDay*0.8 ) msg = "위험@F57A82";
			else msg = "아주위험@ED5276";
		}
		respThis.getWriter().print(msg);
	}

	private void comandChLecCodeForStu() throws ClassNotFoundException, SQLException, NumberFormatException {
		// TODO Auto-generated method stub
		Iterator ite = jsonD.iterator();
		DaoScore daoScore = new DaoScore();
		DaoMember daoMember = new DaoMember();
		DaoScore score = new DaoScore();
		while(ite.hasNext()){
			JSONObject json = null;
			json = (JSONObject) ite.next();
			String id = (String) json.get("id");
			int lec_code = Integer.parseInt((String) json.get("lec_code"));
			daoMember.changeLecCode(id, lec_code);
			daoScore.initScoreAfArr(id);
		}
	}

	private void comandChMemType() throws ClassNotFoundException, SQLException {
		// sys
		Iterator ite = jsonD.iterator();
		DaoMember daoMember = new DaoMember();
		while(ite.hasNext()){
			JSONObject json = null;
			json = (JSONObject) ite.next();
			String id = (String) json.get("id");
			int mem_type = Integer.parseInt((String) json.get("memtype"));
			daoMember.changeMemTypeById(id, mem_type);
		}
	}

}// ConAjax
