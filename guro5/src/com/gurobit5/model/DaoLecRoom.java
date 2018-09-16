package com.gurobit5.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gurobit5.model.entity.DtoLecRoom;
import com.gurobit5.util.MyOracle;

public class DaoLecRoom {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public DaoLecRoom(){}


	public void closeAll() throws SQLException{
		if(rs!=null){ rs.close(); }
		if(pstmt!=null){ pstmt.close(); }
		if(conn!=null){ conn.close(); }
	}
	
	
	public DtoLecRoom selectOneInfo(int lecCode) throws SQLException, ClassNotFoundException{
		String sql = "SELECT CLASSNO, CLASS_USING, MAX_NUM"
				+ ", (SELECT COUNT(*) FROM MEMBER WHERE LEC_CODE=? AND MEM_TYPE = 0) AS NOW_NUM"
				+ " FROM LEC_ROOM A WHERE A.CLASSNO = (SELECT CLASSNO FROM LEC_INFO WHERE LEC_CODE = ?)";
		
		DtoLecRoom bean = new DtoLecRoom();
		try{
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lecCode);
			pstmt.setInt(2, lecCode);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				bean.setClassno(rs.getInt("classno"));
				bean.setClassUsing(rs.getString("class_using"));
				bean.setMAX_NUM(rs.getInt("max_num"));
				bean.setNowNum(rs.getInt("now_num"));
			}
		}finally{
			closeAll();

		}
		return bean;
	}
	
	// 그냥 다 가지고 올때
	public List<DtoLecRoom> selectAll() throws ClassNotFoundException, SQLException {
		String sql = "SELECT CLASSNO, CLASS_USING, MAX_NUM FROM LEC_ROOM";

		List<DtoLecRoom> list  = null;
		try{
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs 	= pstmt.executeQuery();

			list = new ArrayList<DtoLecRoom>();
			while(rs.next()){
				DtoLecRoom bean = new DtoLecRoom();
				bean.setClassno(rs.getInt("classno"));
				bean.setClassUsing(rs.getString("class_using"));
				bean.setMAX_NUM(rs.getInt("max_num"));
				list.add(bean);
			}
		}finally{
			closeAll();

		}

		return list;
	}// selectAll()

	// 사용여부로 필터링할때
	public List<DtoLecRoom> selectAll(String string) throws SQLException, ClassNotFoundException {
		String sql = "SELECT CLASSNO, CLASS_USING, MAX_NUM FROM LEC_ROOM where class_using = ?";

		List<DtoLecRoom> list = null;
		try{
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,string);
			rs 	= pstmt.executeQuery();

			list = new ArrayList<DtoLecRoom>();
			while(rs.next()){
				DtoLecRoom bean = new DtoLecRoom();
				bean.setClassno(rs.getInt("classno"));
				bean.setClassUsing(rs.getString("class_using"));
				bean.setMAX_NUM(rs.getInt("max_num"));
				list.add(bean);
			}
		}finally{
			closeAll();
		}
		return list;
	}// selectAll(String string)

	public void changeUsing(int classno, String using) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		// "사용", "빔"
		
		String sql = "update lec_room set"
				+ " class_using = ? "
				+ " where classno = ? ";
		try{
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,using);
			pstmt.setInt(2,classno);
			pstmt.executeUpdate();
		}finally{
			closeAll();
		}
	}// changeUsing();
}//DaoLecRoom
