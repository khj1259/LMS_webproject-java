package com.gurobit5.model.bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gurobit5.model.bbs.entity.DtoJobInfo;
import com.gurobit5.util.MyOracle;


public class DaoJobInfo {
	// try - sskim/2018-05-21
	// try - sskim / 2018-05-24
	// done - sskim / 2018-05-28

	public Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public DaoJobInfo(){}	// 
	
	public int countAll() throws SQLException, ClassNotFoundException {
		String sql = "SELECT COUNT(*) AS TOT FROM JOB_INFO_BBS";
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

	public void closeAll() throws SQLException{
		if(rs!=null){ rs.close(); }
		if(pstmt!=null){ pstmt.close(); }
		if(conn!=null){ conn.close(); }
	}//closeAll()

	public List<DtoJobInfo> selectAll(int start, int end) throws ClassNotFoundException, SQLException {
		// 리스트 목록 : 
		// rn, num, title, COMPANY, HIRE_NUM, HIRE_TYPE, DUE_DATE, view_Num
		List<DtoJobInfo> dtoJobInfoList = new ArrayList<DtoJobInfo>();

		String sql =""
				+ "SELECT RN, NUM, TITLE, COMPANY, HIRE_NUM, HIRE_TYPE, DUE_DATE, VIEW_NUM"
				+ " FROM (SELECT ROWNUM AS RN, NUM, TITLE, COMPANY, HIRE_NUM, HIRE_TYPE, DUE_DATE, VIEW_NUM"
				+ " FROM (SELECT NUM, TITLE, COMPANY, HIRE_NUM, HIRE_TYPE, DUE_DATE, VIEW_NUM"
				+ " FROM JOB_INFO_BBS ORDER BY NUM ASC)"
				+ " WHERE ROWNUM <= ?)"
				+ " WHERE RN >= ? ORDER BY RN DESC";
		try{
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			rs=pstmt.executeQuery();
			while(rs.next()){
				DtoJobInfo dtoJobInfo = new DtoJobInfo();
				dtoJobInfo.setRn(rs.getInt("rn"));
				dtoJobInfo.setNum(rs.getInt("num"));
				dtoJobInfo.setTitle(rs.getString("title"));
				dtoJobInfo.setCompany(rs.getString("company"));
				dtoJobInfo.setHireNum(rs.getInt("hire_num"));
				dtoJobInfo.setHireType(rs.getString("hire_type"));
				dtoJobInfo.setDueDate(rs.getDate("due_date"));
				dtoJobInfo.setViewNum(rs.getInt("view_num"));//조회수
				dtoJobInfoList.add(dtoJobInfo);
			}
		}finally{
			closeAll();
		}

		return dtoJobInfoList;
	}// selectAll() 조건 없는거

	public List<DtoJobInfo> selectAll(int start, int end, String search, String category) throws ClassNotFoundException, SQLException{

		// rn, num, title, COMPANY, HIRE_NUM, HIRE_TYPE, DUE_DATE, view_Num
		DtoJobInfo dtoJobInfo = new DtoJobInfo();
		List<DtoJobInfo> dtoJobInfoList = new ArrayList<DtoJobInfo>();

		String sql =""
				+ "SELECT RN, NUM, TITLE, COMPANY, HIRE_NUM, HIRE_TYPE, DUE_DATE, VIEW_NUM"
				+ " FROM (SELECT ROWNUM AS RN, NUM, TITLE, COMPANY, HIRE_NUM, HIRE_TYPE, DUE_DATE, VIEW_NUM"
				+ " FROM (SELECT NUM, TITLE, COMPANY, HIRE_NUM, HIRE_TYPE, DUE_DATE, VIEW_NUM"
				+ " FROM JOB_INFO_BBS WHERE "+category+ " LIKE ? ORDER BY NUM DESC)"
				+ " WHERE ROWNUM <= ?)"
				+ " WHERE RN >= ?";		
		try{
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, end);
			pstmt.setInt(3, start);
			rs=pstmt.executeQuery();
			while(rs.next()){
				dtoJobInfo.setRn(rs.getInt("rn"));
				dtoJobInfo.setNum(rs.getInt("num"));
				dtoJobInfo.setTitle(rs.getString("title"));
				dtoJobInfo.setCompany(rs.getString("company"));
				dtoJobInfo.setHireNum(rs.getInt("hire_num"));
				dtoJobInfo.setHireType(rs.getString("hire_tyep"));
				dtoJobInfo.setDueDate(rs.getDate("due_date"));
				dtoJobInfo.setViewNum(rs.getInt("view_num"));//조회수
				dtoJobInfoList.add(dtoJobInfo);
			}
		}finally{
			closeAll();
		}
		return dtoJobInfoList;
	}// selectAll() 조건 있는거

	public DtoJobInfo selectOne(int num) throws SQLException, ClassNotFoundException{
		String sql="select * from job_info_bbs where num=?";
		DtoJobInfo dtoJobInfo = new DtoJobInfo();
		try{
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()){
				dtoJobInfo.setNum(rs.getInt("num"));
				dtoJobInfo.setTitle(rs.getString("title"));
				dtoJobInfo.setId(rs.getString("id"));
				dtoJobInfo.setNalja(rs.getDate("nalja"));
				dtoJobInfo.setCompany(rs.getString("company"));

				dtoJobInfo.setHireNum(rs.getInt("hire_num"));
				dtoJobInfo.setHireType(rs.getString("hire_type"));
				dtoJobInfo.setSal(rs.getInt("sal"));
				dtoJobInfo.setRegion(rs.getString("region"));
				dtoJobInfo.setQualify(rs.getString("qualify"));
				
				dtoJobInfo.setDueDate(rs.getDate("due_date"));
				dtoJobInfo.setEmail(rs.getString("email"));
				dtoJobInfo.setContents(rs.getString("contents"));
				dtoJobInfo.setViewNum(rs.getInt("view_num"));//조회수
			}
		}finally{
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		return dtoJobInfo;

	}//selectOne()


	//
	public int insertOne(DtoJobInfo bean) throws ClassNotFoundException, SQLException {
		String sql="insert into job_info_bbs values "
				+ "(job_info_bbs_SEQ.nextval,?,?,sysdate,?,"
				+ "?,?,?,?,?,"
				+ "?,?,?,0)";
		int result = 0;

		try{
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, bean.getTitle());//dtoJobInfo.setTitle(rs.getString("title"));
			pstmt.setString(2,bean.getId());//dtoJobInfo.setId(rs.getString("id"));
			pstmt.setString(3, bean.getCompany());//dtoJobInfo.setCompany(rs.getString("company"));

			pstmt.setInt(4, bean.getHireNum());//dtoJobInfo.setHireNum(rs.getInt("hire_num"));
			pstmt.setString(5, bean.getHireType());//dtoJobInfo.setHireType(rs.getString("hire_type"));
			pstmt.setInt(6, bean.getSal());//dtoJobInfo.setSal(rs.getInt("sal"));
			pstmt.setString(7, bean.getRegion());//dtoJobInfo.setRegion(rs.getString("region"));
			pstmt.setString(8, bean.getQualify());//dtoJobInfo.setQualify(rs.getString("qualify"));

			pstmt.setDate(9, bean.getDueDate());//dtoJobInfo.setDueDate(rs.getDate("due_date"));
			pstmt.setString(10, bean.getEmail());//dtoJobInfo.setEmail(rs.getString("email"));
			pstmt.setString(11, bean.getContents());//dtoJobInfo.setContents(rs.getString("contents"));

			result = pstmt.executeUpdate();
		}finally{
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		return result;

	}//insertOne

	//수정
	public int updateOne(DtoJobInfo bean) throws ClassNotFoundException, SQLException{
		String sql="update job_info_bbs set"
				+ " title = ?" + ", company = ?"
				+ ", hire_Num = ?"+ ", hire_type = ?"+ ", sal = ?"+ ", region = ?"+ ", qualify = ?"
				+ ", due_date = ?"+ ", email = ?"+ ", contents = ?"
				+ " where num=?";
		int result = 0;
		try{
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, bean.getTitle());//dtoJobInfo.setTitle(rs.getString("title"));
			pstmt.setString(2, bean.getCompany());//dtoJobInfo.setCompany(rs.getString("company"));

			pstmt.setInt(3, bean.getHireNum());//dtoJobInfo.setHireNum(rs.getInt("hire_num"));
			pstmt.setString(4, bean.getHireType());//dtoJobInfo.setHireType(rs.getString("hire_type"));
			pstmt.setInt(5, bean.getSal());//dtoJobInfo.setSal(rs.getInt("sal"));
			pstmt.setString(6, bean.getRegion());//dtoJobInfo.setRegion(rs.getString("region"));
			pstmt.setString(7, bean.getQualify());//dtoJobInfo.setQualify(rs.getString("qualify"));

			pstmt.setDate(8, bean.getDueDate());//dtoJobInfo.setDueDate(rs.getDate("due_date"));
			pstmt.setString(9, bean.getEmail());//dtoJobInfo.setEmail(rs.getString("email"));
			pstmt.setString(10, bean.getContents());//dtoJobInfo.setContents(rs.getString("contents"));
			
			pstmt.setInt(11, bean.getNum());
			
			result= pstmt.executeUpdate();
		}finally{
			closeAll();
		}
		return result;

	}//updateOne()
	
	
	public int deleteOne(int idx) throws ClassNotFoundException, SQLException{
		String sql="DELETE FROM JOB_INFO_BBS WHERE NUM= ?";
		int result = 0;
		try{
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			result = pstmt.executeUpdate();
		}finally{
			closeAll();
		}
		return result;
	}// deleteOne();
	
	
	//조회수 증가
	public void viewNum(int num) throws ClassNotFoundException, SQLException{
		String sql="update job_info_bbs set view_num=view_num+1 where num=?";
		try{
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		}finally{
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
	}//viewNum()


}// DaoJobInfo()
