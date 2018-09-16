package com.gurobit5.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gurobit5.model.entity.DtoLecInfo;
import com.gurobit5.util.MyOracle;

public class DaoLecInfo {
	// try - sskim / 2018-05-24
	// try - hyunjung/2018-05-24
	// 80% - sskim / 2018-05-30
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public DaoLecInfo() throws ClassNotFoundException, SQLException {
	}
	
	//수강후기 게시판 - 강의명 불러오기
		public ArrayList<DtoLecInfo> selectClassNameAll() throws ClassNotFoundException, SQLException{
			String sql="select class_name,lec_code from lec_info";
			ArrayList<DtoLecInfo> list = new ArrayList<DtoLecInfo>();
			try{
				conn=MyOracle.getConnection();
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next()){
					DtoLecInfo dtoLecInfo = new DtoLecInfo();
					if(rs.getInt("lec_code")!=11111){
						dtoLecInfo.setClassName(rs.getString("class_name"));
						dtoLecInfo.setLecCode(rs.getInt("lec_code"));
						list.add(dtoLecInfo);
					}
				}
			}finally{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}
			return list;
		}
	

		public List<DtoLecInfo> selectAllforListByStatus(int start, int end, int status) throws SQLException, ClassNotFoundException {
			String sql =""
					+ "SELECT RN, lec_code, CLASS_NAME, OPEN_DATE, END_DATE, MAX_AB, KEYWORD, PREVIEW, STATUS"
					+ " FROM (SELECT ROWNUM AS RN, lec_code, CLASS_NAME, OPEN_DATE, END_DATE, MAX_AB, KEYWORD, PREVIEW, STATUS"
					+ " FROM (SELECT lec_code, CLASS_NAME, OPEN_DATE, END_DATE, MAX_AB, KEYWORD, PREVIEW, STATUS"
					+ " FROM LEC_INFO where STATUS = ? ORDER BY LEC_CODE DESC)"
					+ " WHERE ROWNUM <= ?)"
					+ " WHERE RN >= ?";
			List<DtoLecInfo> list = new ArrayList<DtoLecInfo>();
			
			try{
				conn = MyOracle.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, status);
				pstmt.setInt(2, end);
				pstmt.setInt(3, start);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					DtoLecInfo dtoLecInfo = new DtoLecInfo();
					if(rs.getInt("lec_code")!=11111){

					dtoLecInfo.setRn(rs.getInt("rn"));
					dtoLecInfo.setLecCode(rs.getInt("lec_code"));
					dtoLecInfo.setClassName(rs.getString("class_name"));
					dtoLecInfo.setOpenDate(rs.getDate("open_date"));
					dtoLecInfo.setEndDate( rs.getDate("end_date"));
					dtoLecInfo.setKeyword(rs.getString("keyword"));
					dtoLecInfo.setMaxAB(rs.getInt("MAX_AB"));
					dtoLecInfo.setKeyword(rs.getString("KEYWORD"));
					dtoLecInfo.setPreView(rs.getString("PREVIEW"));
					dtoLecInfo.setStatus(rs.getInt("STATUS"));
					list.add(dtoLecInfo);
					}
				}
			}finally{
				closeAll();
			}
			
			return list;
		}// selectAllforList()
	public List<DtoLecInfo> selectAllforList(int start, int end) throws SQLException, ClassNotFoundException {
		// 리스트의 형태는 강의명, 개강일-종강일. 키워드->이미지 로 보여줌
		// list 이기 때문에 list page 작업을 위해서 필요한 정보도 심어줌
		// int pageNum = 1;
		// int rowNum = 5; List<DtoLecInfo> list
		// int totNum = daoMember.countByLecCode();
		String sql =""
				+ "SELECT RN, LEC_CODE, CLASS_NAME, OPEN_DATE, END_DATE, MAX_AB, KEYWORD, PREVIEW, STATUS"
				+ " FROM (SELECT ROWNUM AS RN, LEC_CODE, CLASS_NAME, OPEN_DATE, END_DATE, MAX_AB, KEYWORD, PREVIEW, STATUS"
					+ " FROM (SELECT LEC_CODE, CLASS_NAME, OPEN_DATE, END_DATE, MAX_AB, KEYWORD, PREVIEW, STATUS"
						+ " FROM LEC_INFO ORDER BY LEC_CODE DESC)"
					+ " WHERE ROWNUM <= ?)"
				+ " WHERE RN >= ?";
		List<DtoLecInfo> list = new ArrayList<DtoLecInfo>();
		
		try{
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			rs = pstmt.executeQuery();

			while(rs.next()){
				DtoLecInfo dtoLecInfo = new DtoLecInfo();
				if(rs.getInt("lec_code")!=11111){

				dtoLecInfo.setRn(rs.getInt("rn"));
				dtoLecInfo.setLecCode(rs.getInt("lec_code"));
				dtoLecInfo.setClassName(rs.getString("class_name"));
				dtoLecInfo.setOpenDate(rs.getDate("open_date"));
				dtoLecInfo.setEndDate( rs.getDate("end_date"));
				dtoLecInfo.setKeyword(rs.getString("keyword"));
				dtoLecInfo.setMaxAB(rs.getInt("MAX_AB"));
				dtoLecInfo.setKeyword(rs.getString("KEYWORD"));
				dtoLecInfo.setPreView(rs.getString("PREVIEW"));
				dtoLecInfo.setStatus(rs.getInt("STATUS"));
				list.add(dtoLecInfo);
				}
			}
		}finally{
			closeAll();
		}
		
		return list;
	}// selectAllforList()

	public DtoLecInfo selectOnebyName(String teacher) throws SQLException, ClassNotFoundException {
		DtoLecInfo dtoLecInfo = new DtoLecInfo();
		String sql =""
				+ "SELECT LEC_CODE, CLASS_NAME, TEACHER, OPEN_DATE, END_DATE, MAX_AB, KEYWORD, PREVIEW, CONTENTS, STATUS, CLASSNO"
				+ " FROM LEC_INFO WHERE teacher= ?";
		try{
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, teacher);
			rs = pstmt.executeQuery();
			if(rs.next()){
				dtoLecInfo.setLecCode(rs.getInt("LEC_CODE"));
				dtoLecInfo.setClassName(rs.getString("CLASS_NAME"));
				dtoLecInfo.setTeacher(rs.getString("TEACHER"));
				dtoLecInfo.setOpenDate(rs.getDate("OPEN_DATE"));
				dtoLecInfo.setEndDate(rs.getDate("END_DATE"));
				dtoLecInfo.setMaxAB(rs.getInt("MAX_AB"));
				dtoLecInfo.setKeyword(rs.getString("KEYWORD"));
				dtoLecInfo.setPreView(rs.getString("PREVIEW"));
				dtoLecInfo.setContents(rs.getString("CONTENTS"));
				dtoLecInfo.setStatus(rs.getInt("STATUS"));
				dtoLecInfo.setClassno(rs.getInt("CLASSNO"));
			}else{
				dtoLecInfo = null;
			}
		} finally{
			closeAll();
		}
		return dtoLecInfo;
	}
	
	public DtoLecInfo selectOne(int lecCode) throws SQLException, ClassNotFoundException {
		DtoLecInfo dtoLecInfo = new DtoLecInfo();
		String sql =""
				+ "SELECT LEC_CODE, CLASS_NAME, TEACHER, OPEN_DATE, END_DATE, MAX_AB, KEYWORD, PREVIEW, CONTENTS, STATUS, CLASSNO"
				+ " FROM LEC_INFO WHERE LEC_CODE= ?";
		try{
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lecCode);
			rs = pstmt.executeQuery();
			if(rs.next()){
				dtoLecInfo.setLecCode(rs.getInt("LEC_CODE"));
				dtoLecInfo.setClassName(rs.getString("CLASS_NAME"));
				dtoLecInfo.setTeacher(rs.getString("TEACHER"));
				dtoLecInfo.setOpenDate(rs.getDate("OPEN_DATE"));
				dtoLecInfo.setEndDate(rs.getDate("END_DATE"));
				dtoLecInfo.setMaxAB(rs.getInt("MAX_AB"));
				dtoLecInfo.setKeyword(rs.getString("KEYWORD"));
				dtoLecInfo.setPreView(rs.getString("PREVIEW"));
				dtoLecInfo.setContents(rs.getString("CONTENTS"));
				dtoLecInfo.setStatus(rs.getInt("STATUS"));
				dtoLecInfo.setClassno(rs.getInt("CLASSNO"));
			}else{
				dtoLecInfo = null;
			}
		} finally{
			closeAll();
		}
		return dtoLecInfo;
	}
	

	public int countAll(int status) throws SQLException, ClassNotFoundException {
		String sql = "SELECT COUNT(*) AS TOT FROM LEC_INFO where lec_code <> 11111 and status = ?";
		int tot = 0; 
		try{
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, status);
			rs 	= pstmt.executeQuery();
			if(rs.next()){
				tot = rs.getInt(1);
			}
		}finally{
			closeAll();
		}
		return tot;
	}// countAll()
	
	
	public int countAll() throws SQLException, ClassNotFoundException {
		String sql = "SELECT COUNT(*) AS TOT FROM LEC_INFO where lec_code <> 11111";
		int tot = 0; 
		try{
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs 	= pstmt.executeQuery(sql);
			if(rs.next()){
				tot = rs.getInt(1);
			}
		}finally{
			closeAll();
		}
		return tot;
	}// countAll()
	
	
	// 손봐야됨
	public int addRecord(DtoLecInfo daoLecInfo) throws SQLException, ClassNotFoundException{
		// 순서 강의값 넣기 > member 강사에 넣기 > 강의실 끝내기>
		
		int result = 0;
		String sql =""
				+ "insert into LEC_INFO values (LEC_CODE_SEQ.nextval,?,?,?,?,"
				+ "?,?,?,?,?,"
				+ "?)";
		try{
			if(conn==null)conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, daoLecInfo.getClassName());
			pstmt.setString(2, daoLecInfo.getTeacher());
			pstmt.setDate(3, daoLecInfo.getOpenDate());
			pstmt.setDate(4, daoLecInfo.getEndDate());
			
			pstmt.setInt(5, daoLecInfo.getMaxAB());
			pstmt.setString(6, daoLecInfo.getKeyword());
			pstmt.setString(7, daoLecInfo.getPreView());
			pstmt.setString(8, daoLecInfo.getContents());
			pstmt.setInt(9, daoLecInfo.getStatus());
			
			pstmt.setInt(10, daoLecInfo.getClassno());
			
			result = pstmt.executeUpdate();
			// 이다음에 강의실 값 수정해야됨
			// 강사에 값 넣어주어야됨
			
		} finally{
			closeAll();
		}
		return result;
	}

	public int delRecord(int idx){
		return idx;
	}

	public int updateRecord(DtoLecInfo dtoLecInfo) throws SQLException, ClassNotFoundException{
		// 순서 강의값 넣기 > member 강사에 넣기 > 강의실 끝내기>
		
		int result = 0;
		String sql =""
				+ "UPDATE LEC_INFO SET "
									+"CLASS_NAME= ?"
									+", TEACHER = ?"
									+", OPEN_DATE= ?" 	
									+", END_DATE= ?"	
									+", MAX_AB= ?"		
									+", KEYWORD= ?"
									+", PREVIEW= ?"
									+", CONTENTS= ?"
									+", STATUS= ?"
									+ ", CLASSNO= ?"
									+ " WHERE LEC_CODE= ?";
		try{
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dtoLecInfo.getClassName());
			pstmt.setString(2, dtoLecInfo.getTeacher());
			pstmt.setDate(3, dtoLecInfo.getOpenDate());
			pstmt.setDate(4, dtoLecInfo.getEndDate());
			pstmt.setInt(5, dtoLecInfo.getMaxAB());
			
			pstmt.setString(6, dtoLecInfo.getKeyword());
			pstmt.setString(7, dtoLecInfo.getPreView());
			pstmt.setString(8, dtoLecInfo.getContents());
			pstmt.setInt(9, dtoLecInfo.getStatus());
			
			pstmt.setInt(10, dtoLecInfo.getClassno());
			
			pstmt.setInt(11, dtoLecInfo.getLecCode());
			result = pstmt.executeUpdate();
			
			// 이다음에 강의실 값 수정해야됨
			// 강사에 값 넣어주어야됨
			
			
		} finally{
			closeAll();
		}
		return result;
	}
	
	
	
	
	public void closeAll() throws SQLException{
		if(rs!=null){ rs.close(); }
		if(pstmt!=null){ pstmt.close(); }
		if(conn!=null){ conn.close(); }
	}//closeAll()

	

}// DaoLecInfo
