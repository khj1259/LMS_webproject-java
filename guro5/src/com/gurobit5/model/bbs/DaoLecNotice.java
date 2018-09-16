package com.gurobit5.model.bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gurobit5.model.bbs.entity.DtoLecNotice;
import com.gurobit5.model.entity.DtoScore;
import com.gurobit5.util.MyOracle;


public class DaoLecNotice {
	// try - hyunjung/2018-05-23
		// try - hyunjung/2018-05-24
		// try - hyunjung/2018-05-25
		// try - hyunjung/2018-05-28
		private Connection conn;
		private PreparedStatement pstmt;
		private ResultSet rs;
		
		public DaoLecNotice() {	}
		
		//강의공지사항 전체 리스트 보기
		// num, title, id, nalja, view_num
		public ArrayList<DtoLecNotice> selectAll(String id) throws ClassNotFoundException, SQLException{
			String sql="select rownum as rn, A.num, A.title, A.id, A.nalja, A.view_num from "
					+ "(select num, title, id, nalja, view_num, lec_code from lec_notice_bbs order by num) A "
					+ "where A.lec_code=(select lec_code from MEMBER where id=?)"
					+ " order by rn desc";
			ArrayList<DtoLecNotice> list = new ArrayList<DtoLecNotice>();
			try{
				conn=MyOracle.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs=pstmt.executeQuery();
				while(rs.next()){
					DtoLecNotice dtoLecNotice = new DtoLecNotice();
					dtoLecNotice.setNum(rs.getInt("num"));
					dtoLecNotice.setRn(rs.getInt("rn"));
					dtoLecNotice.setTitle(rs.getString("title"));
					dtoLecNotice.setId(rs.getString("id"));
					dtoLecNotice.setNalja(rs.getDate("nalja"));
					dtoLecNotice.setViewNum(rs.getInt("view_num"));//조회수
					list.add(dtoLecNotice);
				}
			}finally{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}
			return list;
		}//selectAll
		
		//강의공지사항 상세보기
		public DtoLecNotice selectOne(int num) throws SQLException, ClassNotFoundException{
			String sql="select * from lec_notice_bbs where num=?";
			DtoLecNotice dtoLecNotice = new DtoLecNotice();
			try{
				conn=MyOracle.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs=pstmt.executeQuery();
				while(rs.next()){
					dtoLecNotice.setNum(rs.getInt("num"));
					dtoLecNotice.setTitle(rs.getString("title"));
					dtoLecNotice.setId(rs.getString("id"));
					dtoLecNotice.setNalja(rs.getDate("nalja"));
					dtoLecNotice.setContents(rs.getString("contents"));
					dtoLecNotice.setViewNum(rs.getInt("view_num"));//조회수
				}
			}finally{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}
			return dtoLecNotice;
					
		}//selectOne
		
		
		//조회수 증가
		public void viewNum(int num) throws ClassNotFoundException, SQLException{
			String sql="update lec_notice_bbs set view_num=view_num+1 where num=?";
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

		//강의공지사항 게시물 작성
		public void insertOne(String id, String title, String contents) throws ClassNotFoundException, SQLException {
			String sql="insert into lec_notice_bbs values "
					+ "(LEC_NOTICE_BBS_SEQ.nextval,?,?,sysdate,"
					+ "(select lec_code from MEMBER where id=?), ?, 0)";
			try{
				conn=MyOracle.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, id);
				pstmt.setString(3, id);
				pstmt.setString(4, contents);
				pstmt.executeUpdate();
			}finally{
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}
		}//insertOne
		
		//강의 공지사항 수정
		public int update(int num, String title, String contents) throws ClassNotFoundException, SQLException{
			String sql="update Lec_NOTICE_BBS set title=?, contents=? where num=?";
			int result = 0;
			try{
				conn=MyOracle.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, contents);
				pstmt.setInt(3, num);
				result= pstmt.executeUpdate();
			}finally{
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}
			return result;
			
		}//update
		
		//강의공지사항 게시물 삭제
		public void delete(int num) throws ClassNotFoundException, SQLException{
			String sql="delete from LEC_NOTICE_BBS where num=?";
			try{
				conn=MyOracle.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
			}finally{
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}
		}//delete
		
	
}
