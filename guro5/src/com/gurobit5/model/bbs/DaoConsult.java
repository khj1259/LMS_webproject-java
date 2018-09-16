package com.gurobit5.model.bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gurobit5.model.bbs.entity.DtoConsult;
import com.gurobit5.util.MyOracle;

//2018-05-24 김형준 상담게시판 DAO
public class DaoConsult {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public List<DtoConsult> selectAll(int start,int end){
		//리스트 불러오는 메소드
		List<DtoConsult> list = new ArrayList<DtoConsult>();
		String sql ="SELECT rn, title, name, nalja, num"
				+ " FROM (SELECT ROWNUM as rn, title, name, nalja, num"
				+ " FROM (SELECT * FROM Consult_bbs ORDER BY num DESC) WHERE rownum <= ?)"
				+ " WHERE RN >= ? ORDER BY RN DESC";
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			rs=pstmt.executeQuery();
			while(rs.next()){
				DtoConsult dtoConsult = new DtoConsult();
				dtoConsult.setRn(rs.getInt("rn"));
				dtoConsult.setTitle(rs.getString("title"));
				dtoConsult.setName(rs.getString("name"));
				dtoConsult.setNalja(rs.getDate("nalja"));
				dtoConsult.setNum(rs.getInt("num"));
				list.add(dtoConsult);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
	
	public DtoConsult selectOne(int num){
		//리스트중 게시글 한개만 불러오는 메소드(상세페이지)
		String sql ="select * from CONSULT_BBS where num=?";
		DtoConsult dtoConsult = new DtoConsult();
		
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()){
				dtoConsult.setNum(rs.getInt("num"));
				dtoConsult.setName(rs.getString("name"));
				dtoConsult.setTitle(rs.getString("title"));
				dtoConsult.setNalja(rs.getDate("nalja"));
				dtoConsult.setId(rs.getString("id"));
				dtoConsult.setGen(rs.getString("gen"));
				dtoConsult.setEmail(rs.getString("email"));
				dtoConsult.setPhone(rs.getInt("phone"));
				dtoConsult.setPurpose(rs.getString("purpose"));
				dtoConsult.setLec_code(rs.getInt("lec_code"));
				dtoConsult.setContents(rs.getString("contents"));
				dtoConsult.setAnswer(rs.getString("answer"));
				dtoConsult.setView_num(rs.getInt("view_num"));
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
	
		return dtoConsult;
	}
	
	public int answer(String answer,int num){
		//상담답변 메소드
		String sql ="update CONSULT_BBS set answer=? where num=?";
		int result=0;
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, answer);
			pstmt.setInt(2, num);
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
	
	public void insertOneforLec(String title, String name, String gen, String email, String purpose, int leccode,String contents, int phone) throws SQLException, ClassNotFoundException{
		//수강신청 폼 메소드
		String sql="insert into CONSULT_BBS"
				+" values(CONSULT_BBS_seq.nextval,?,?,sysdate,?,?,?,?,?,?,?,?,?)";
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, title);
			pstmt.setString(3, " ");
			pstmt.setString(4, gen);
			pstmt.setString(5, email);
			pstmt.setInt(6, phone);
			pstmt.setString(7, purpose);
			pstmt.setInt(8, leccode);
			pstmt.setString(9, contents);
			pstmt.setString(10, " ");
			pstmt.setInt(11, 0);
			pstmt.executeUpdate();
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
	public void insertOne(String title, String name, String gen, String email, String purpose, String contents, int phone){
		//상담신청 폼 메소드
		String sql="insert into CONSULT_BBS"
				+" values(CONSULT_BBS_seq.nextval,?,?,sysdate,?,?,?,?,?,?,?,?,?)";
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, title);
			pstmt.setString(3, " ");
			pstmt.setString(4, gen);
			pstmt.setString(5, email);
			pstmt.setInt(6, phone);
			pstmt.setString(7, purpose);
			pstmt.setInt(8, 11111);
			pstmt.setString(9, contents);
			pstmt.setString(10, " ");
			pstmt.setInt(11, 0);
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
		//상담게시판 삭제 메소드
		String sql="delete from CONSULT_BBS where num=?";
		
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
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public String getAnswer(int parseInt) {
		String sql="select answer from CONSULT_BBS where num=?";
		String result = null;
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, parseInt);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getString("answer");
			}
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
}//DaoConsult end
