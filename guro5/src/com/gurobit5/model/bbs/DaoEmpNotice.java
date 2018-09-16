package com.gurobit5.model.bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gurobit5.model.bbs.entity.DtoEmpNotice;
import com.gurobit5.util.MyOracle;

//김형준 2018-05-29 생성
public class DaoEmpNotice {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ArrayList<DtoEmpNotice> selectAll(int start,int end){
		ArrayList<DtoEmpNotice> list =new ArrayList<DtoEmpNotice>();
		String sql="SELECT ROWNUM AS RN, TITLE, ID, NALJA, VIEW_NUM, NUM"
				+ " FROM (SELECT ROWNUM AS RN, TITLE, ID, NALJA, VIEW_NUM, NUM"
				+ " FROM (SELECT * FROM EMP_NOTICE_BBS ORDER BY NUM ASC) WHERE ROWNUM <= ?)"
				+ " WHERE RN >= ? ORDER BY rn desc";
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			rs=pstmt.executeQuery();
			while(rs.next()){
				DtoEmpNotice dtoEmpNotice = new DtoEmpNotice();
				dtoEmpNotice.setRn(rs.getInt("rn"));
				dtoEmpNotice.setTitle(rs.getString("title"));
				dtoEmpNotice.setId(rs.getString("id"));
				dtoEmpNotice.setNalja(rs.getDate("nalja"));
				dtoEmpNotice.setView_num(rs.getInt("view_num"));
				dtoEmpNotice.setNum(rs.getInt("num"));
				list.add(dtoEmpNotice);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;		
	}//selectAll end
	
	public DtoEmpNotice selectOne(int num){
		DtoEmpNotice dtoEmpNotice =new DtoEmpNotice();
		String sql="select num,title,view_num,nalja,contents from EMP_NOTICE_BBS where num=?";
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			while(rs.next()){
				dtoEmpNotice.setNum(rs.getInt("num"));
				dtoEmpNotice.setTitle(rs.getString("title"));
				dtoEmpNotice.setView_num(rs.getInt("view_num"));
				dtoEmpNotice.setNalja(rs.getDate("nalja"));
				dtoEmpNotice.setContents(rs.getString("contents"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return dtoEmpNotice;
	}//selectOne end
	
	public int edit(String title, String contents, int num){
		String sql="update EMP_NOTICE_BBS set title=?,contents=? where num=?";
		int result=0;
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setInt(3, num);
			
			result=pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}//edit end
	
	public void deleteOne(int num){
		String sql="delete from EMP_NOTICE_BBS where num=?";
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}//deleteOne end
	
	public void insertOne(String title,String contents,String id){
		String sql = "insert into EMP_NOTICE_BBS values(EMP_NOTICE_BBS_SEQ.nextval,?,?,?,sysdate,0)";
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setString(3, id);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}//insertOne
	//조회수 증가
		public void viewNum(int num) throws ClassNotFoundException, SQLException{
			String sql="update EMP_NOTICE_BBS set view_num=view_num+1 where num=?";
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
}//DaoEmpNotice end
