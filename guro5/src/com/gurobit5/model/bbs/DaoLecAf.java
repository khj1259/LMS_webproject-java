package com.gurobit5.model.bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gurobit5.model.bbs.entity.DtoLecAf;
import com.gurobit5.util.MyOracle;


public class DaoLecAf{
	// try - hyunjung/2018-05-23
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//수강후기 게시판 전체 리스트 보기
	public ArrayList<DtoLecAf> selectAll() throws ClassNotFoundException, SQLException{
		String sql="SELECT RN, NUM, TITLE, ID, NALJA, VIEW_NUM"
				+ " FROM (SELECT ROWNUM AS RN, A.NUM, A.TITLE, A.ID, A.NALJA, A.VIEW_NUM"
					+ " FROM (SELECT NUM, TITLE, ID, NALJA, VIEW_NUM FROM LEC_AF_BBS ORDER BY NUM ASC) A )"
				+ " ORDER BY RN DESC";
		ArrayList<DtoLecAf> list = new ArrayList<DtoLecAf>();
		try{
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				DtoLecAf dtoLecAf = new DtoLecAf();
				dtoLecAf.setNum(rs.getInt("num"));
				dtoLecAf.setTitle(rs.getString("title"));
				dtoLecAf.setId(rs.getString("id"));
				dtoLecAf.setNalja(rs.getDate("nalja"));
				dtoLecAf.setViewNum(rs.getInt("view_num"));//조회수?
				dtoLecAf.setRn(rs.getInt("rn"));
				list.add(dtoLecAf);
			}
		}finally{
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		return list;
	}//selectAll
	
	//수강후기 상세보기
	public DtoLecAf selectOne(int num) throws ClassNotFoundException, SQLException{
		String sql=""
				+ "SELECT A.NUM, A.TITLE, A.ID, A.NALJA, A.CONTENTS, A.VIEW_NUM, A.LEC_CODE LEC_CODE, B.CLASS_NAME AS CLASS_NAME "
				+ "FROM LEC_AF_BBS A, LEC_INFO B WHERE NUM=? AND A.LEC_CODE=B.LEC_CODE";
		DtoLecAf dtoLecAf = new DtoLecAf();
		try{
			viewNum(num);//조회수 증가
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			while(rs.next()){
				dtoLecAf.setNum(rs.getInt("num"));
				dtoLecAf.setTitle(rs.getString("title"));
				dtoLecAf.setId(rs.getString("id"));
				dtoLecAf.setNalja(rs.getDate("nalja"));
				dtoLecAf.setContents(rs.getString("contents"));
				dtoLecAf.setViewNum(rs.getInt("view_num"));//조회수?
				dtoLecAf.setLecCode(rs.getInt("lec_code"));
				dtoLecAf.setClassName(rs.getString("class_name"));
			}
		}finally{
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		return dtoLecAf;
		
	}//selectOne
	
	//게시글 작성
	public void insertOne(String title, String id,int lec_code,String contents){
		String sql="insert into Lec_af_bbs values(JOB_AF_BBS_SEQ.nextval,?,?,sysdate,?,?,0)";
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, id);
			pstmt.setInt(3,lec_code);
			pstmt.setString(4, contents);
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
	}//insert
	
	
	//조회수 증가
	public void viewNum(int num) throws ClassNotFoundException, SQLException{
		String sql="update lec_af_bbs set view_num=view_num+1 where num=?";
		try{
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		}finally{
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
	}//viewNum

	public int update(int num, int lec_code, String title, String contents) {
		// TODO Auto-generated method stub
		String sql="update lec_af_bbs set set lec_code=?, title=?, contents=? where num=?";
		int result=0;
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, lec_code);
			pstmt.setString(2, title);
			pstmt.setString(3, contents);
			pstmt.setInt(4, num);
			result=pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public void deleteOne(int num){
		String sql="delete from lec_af_bbs where num=?";
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(pstmt!=null)pstmt.close();
				if(pstmt!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
