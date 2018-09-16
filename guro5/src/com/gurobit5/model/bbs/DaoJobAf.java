package com.gurobit5.model.bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gurobit5.model.bbs.entity.DtoConsult;
import com.gurobit5.model.bbs.entity.DtoJobAf;
import com.gurobit5.util.MyOracle;

public class DaoJobAf {
	//2018-05-24 취업현황게시판 DAO
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public List<DtoJobAf> selectList(int start,int end){
		List<DtoJobAf> list =new ArrayList<DtoJobAf>();
		String sql ="SELECT rn, name, title, lec_code, company, field, nalja, num"
				+ " FROM (SELECT ROWNUM as rn, name, title, lec_code, company, field, nalja, num"
				+ " FROM (SELECT * FROM JOB_AF_BBS ORDER BY num ASC) WHERE rownum <= ?)"
				+ " WHERE RN >= ? ORDER BY RN DESC";
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			rs=pstmt.executeQuery();
			while(rs.next()){
				DtoJobAf dtoJobAf= new DtoJobAf();
				dtoJobAf.setRn(rs.getInt("rn"));
				dtoJobAf.setName(rs.getString("name"));
				dtoJobAf.setTitle(rs.getString("title"));
				dtoJobAf.setLec_code(rs.getInt("lec_code"));
				dtoJobAf.setCompany(rs.getString("company"));
				dtoJobAf.setField(rs.getString("field"));
				dtoJobAf.setNalja(rs.getDate("nalja"));
				dtoJobAf.setNum(rs.getInt("num"));
				list.add(dtoJobAf);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
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
	}
	
	public DtoJobAf selectOne(int num){
		DtoJobAf dtoJobAf =new DtoJobAf();
		String sql="SELECT A.NUM, A.TITLE, A.contents, A.company, A.id, A.field, A.name, A.LEC_CODE LEC_CODE, B.CLASS_NAME CLASS_NAME"
				+ " FROM JOB_AF_BBS A, LEC_INFO B WHERE NUM=? AND A.LEC_CODE=B.LEC_CODE";
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			while(rs.next()){
				dtoJobAf.setNum(rs.getInt("num"));
				dtoJobAf.setTitle(rs.getString("title"));
				dtoJobAf.setContents(rs.getString("contents"));
				dtoJobAf.setCompany(rs.getString("company"));
				dtoJobAf.setId(rs.getString("id"));
				dtoJobAf.setField(rs.getString("field"));
				dtoJobAf.setName(rs.getString("name"));
				dtoJobAf.setLec_code(rs.getInt("lec_code"));
				dtoJobAf.setClass_name(rs.getString("class_name"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dtoJobAf;
	}
	
	public void insertOne(String name, String company, int lec_code, String title, String field, String contents, String id){
		String sql="insert into JOB_AF_BBS"
				+ " values(JOB_AF_BBS_seq.nextval,?,?,sysdate,?,?,?,?,?,?)";
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setString(3, company);
			pstmt.setString(4, id);
			pstmt.setInt(5, 0);
			pstmt.setInt(6, lec_code);
			pstmt.setString(7, field);
			pstmt.setString(8, name);
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
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void deleteOne(int num){
		String sql="delete from JOB_AF_BBS where num=?";
		
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
	
	public int edit(String title, String contents,String company,int lec_code,String field,String name,int num){
		String sql="update JOB_AF_BBS set title=?,contents=?,company=?,lec_code=?,field=?,name=? where num=?";
		int result=0;
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setString(3, company);
			pstmt.setInt(4, lec_code);
			pstmt.setString(5, field);
			pstmt.setString(6, name);
			pstmt.setInt(7, num);
			result=pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
		return result;
	}
}
