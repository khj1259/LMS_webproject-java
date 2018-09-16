package com.gurobit5.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.gurobit5.model.entity.DtoAttend;
import com.gurobit5.model.entity.DtoScore;
import com.gurobit5.util.MyOracle;

public class DaoScore {
	// try - hyunjung/2018-05-23
	// try - hyunjung/2018-05-28
	// try - hyunjung/2018-05-29	
	// try - hyunjung/2018-05-30	
	// try - hyunjung/2018-05-31	
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public DaoScore() throws ClassNotFoundException, SQLException {
	}
	//학생 전체의 성적 보기
	public ArrayList<DtoScore> selectAll(String id) throws ClassNotFoundException, SQLException{
		String sql="select * from score "
				+ "where lec_code=(select lec_code from member where id=?) "
				+ "order by name"; 
		ArrayList<DtoScore> list = new ArrayList<DtoScore>();
		try{
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				DtoScore dtoScore = new DtoScore();
				dtoScore.setId(rs.getString("id"));
				dtoScore.setName(rs.getString("name"));
				dtoScore.setJava(rs.getInt("java"));
				dtoScore.setWeb(rs.getInt("web"));
				dtoScore.setDb(rs.getInt("db"));
				dtoScore.setTot(rs.getInt("tot"));
				list.add(dtoScore);
			}
		}finally{
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		return list;
	}//selectAll


	


	//학생 전체의 성적 보기
	public List<DtoScore> selectAllStu() throws ClassNotFoundException, SQLException{
		String sql="select * from score order by name"; 
		List<DtoScore> list = new ArrayList<DtoScore>();
		try{
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				DtoScore dtoScore = new DtoScore();
				dtoScore.setName(rs.getString("name"));
				dtoScore.setJava(rs.getInt("java"));
				dtoScore.setWeb(rs.getInt("web"));
				dtoScore.setDb(rs.getInt("db"));
				dtoScore.setTot(rs.getInt("tot"));
				list.add(dtoScore);
			}
		}finally{
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		return list;
	}//selectAll


	// 강의배정 후 학생성적 리셋 김수성 추가
	public int initScoreAfArr(String id) throws  ClassNotFoundException{
		
		String sql1 = "select count(*) from score where id = ?";
		
		String sql2 = "INSERT INTO SCORE VALUES (?,"
				+ " (SELECT NAME FROM MEMBER WHERE ID = ?), 0, 0, 0, 0,"
				+ " (SELECT LEC_CODE FROM MEMBER WHERE ID = ?))";

		String sql3 = "UPDATE SCORE SET"
				+ " JAVA = 0, WEB= 0, DB=0, TOT=0,"
				+ " LEC_CODE=(SELECT LEC_CODE FROM MEMBER WHERE ID = ?)"
				+ " WHERE ID = ?";
		
		int result = 0;
		try{
			conn = MyOracle.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			if(result==1){
				pstmt = conn.prepareStatement(sql3);
				pstmt.setString(1, id);
				pstmt.setString(2, id);
				System.out.println(id);
				System.out.println(sql3);
			}else{
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, id);
				pstmt.setString(2, id);
				pstmt.setString(3, id);
				System.out.println(id);
				System.out.println(sql2);
			}
			result = pstmt.executeUpdate();
			conn.commit();
			System.out.println("2번됩니까?:"+result);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("배정후 성적초기화 도중 에러");
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null){
					conn.setAutoCommit(true);
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}



	//수정페이지를 위한 학생 성적리스트 
	public ArrayList<DtoScore> editList(String id, String[] arr) throws ClassNotFoundException, SQLException {
		String sql="select * from score "
				+ "where lec_code=(select lec_code from member where id=?) "
				+ "order by name"; 
		ArrayList<DtoScore> list = new ArrayList<DtoScore>();
		try{
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				DtoScore dtoScore = new DtoScore();
				dtoScore.setId(rs.getString("id"));
				dtoScore.setName(rs.getString("name"));
				dtoScore.setJava(rs.getInt("java"));
				dtoScore.setWeb(rs.getInt("web"));
				dtoScore.setDb(rs.getInt("db"));
				dtoScore.setTot(rs.getInt("tot"));
				for(String msg:arr){
					if((rs.getString("id")).equals(msg)){
						dtoScore.setCheck(true);
					}
				}
				list.add(dtoScore);
			}
		}finally{
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		return list;
	}

	//학생 성적 수정(입력)
	public void update(String id, int java, int web, int db, int tot) throws ClassNotFoundException, SQLException {
		//성적은 기본값이 0으로 입력되어 있음, 그래서 처음 입력할때도 update로
		String sql="update score set java=?, web=?, db=?, tot=? where id=?";

		try{
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, java);
			pstmt.setInt(2, web);
			pstmt.setInt(3, db);
			pstmt.setInt(4, tot);
			pstmt.setString(5, id);
			pstmt.executeUpdate();
		}finally{
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}

	}//update()

	//학생 한명의 성적 보기
		public DtoScore selectOne(String id) throws ClassNotFoundException, SQLException{	
			String sql="select A.name, A.java, A.web, A.db, A.tot,"
					+ "(select B.class_name from lec_info B where A.lec_code=B.lec_code ) as class_name "
					+ "from score A where id=?";
			DtoScore dtoScore = new DtoScore();
			try{
				conn=MyOracle.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs=pstmt.executeQuery();
				while(rs.next()){
					dtoScore.setClass_name(rs.getString("class_name"));
					dtoScore.setName(rs.getString("name"));
					dtoScore.setJava(rs.getInt("java"));
					dtoScore.setWeb(rs.getInt("web"));
					dtoScore.setDb(rs.getInt("db"));
					dtoScore.setTot(rs.getInt("tot"));
				}
			}finally{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}
			return dtoScore;
		}//selectOne
	
	
	//학생 한명의 출석 현황 보기
		public ArrayList<DtoAttend> selectOneList(String id) throws SQLException, ClassNotFoundException{	
			//아이디,이름,날짜,입실시간,퇴실시간,출석상태, 최대결석일, 지각횟수,결석횟수 
			String sql="select A.id, A.name, A.nalja, A.in_date, A.out_date,A.state,"
					+ "(select B.max_ab from lec_info B where B.lec_code=A.lec_code) as max_ab," 
					+"(select count(decode(state,'지각','1')) from ATTEND group by id having id=?) as late ,"
					+"(select count(decode(state,'결석','1')) from Attend group by id having id=?) as absent"  
					+" from attend A where A.id=? order by nalja desc";
			ArrayList<DtoAttend> list = new ArrayList<DtoAttend>();
			try {
				conn=MyOracle.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, id);
				pstmt.setString(3, id);
				rs=pstmt.executeQuery();
				while(rs.next()){
					DtoAttend dtoAttend = new DtoAttend();
					dtoAttend.setId(rs.getString("id"));
					dtoAttend.setName(rs.getString("name"));
					dtoAttend.setNalja(rs.getDate("nalja"));
					dtoAttend.setInDate(rs.getTime("in_date"));
					dtoAttend.setOutDate(rs.getTime("out_date"));
					dtoAttend.setState(rs.getString("state"));
					dtoAttend.setLate(rs.getInt("late"));
					dtoAttend.setAbsent(rs.getInt("absent"));
					dtoAttend.setMax_ab(rs.getInt("max_ab"));
					list.add(dtoAttend);
				}
			}finally{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}
			return list;

		}//selectOne()

		
		//내 강의를 듣는 학생들의 이름(id)가져오기
		public ArrayList<DtoAttend> selectName(String id) throws ClassNotFoundException, SQLException {
			String sql="select name, id from member "
					+ "where lec_code=(select lec_code from member where id=?) and  mem_type=0  "
					+ "order by name";
			
			ArrayList<DtoAttend> listM = new ArrayList<DtoAttend>();
			try{
				conn=MyOracle.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs=pstmt.executeQuery();
				while(rs.next()){
					DtoAttend dtoAttend= new DtoAttend();
					dtoAttend.setName(rs.getString("name"));
					dtoAttend.setId(rs.getString("id"));
					listM.add(dtoAttend);
				}
			}finally{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}
			return listM;
		}//selectName()

		//입실 버튼 누를때
		public void inCheck(String id, Time sql_time, Date sql_date) throws ClassNotFoundException, SQLException {
			String sql="insert into attend values (?,?,"
					+ "(select name from member where id=?),"
					+ "?,null,null,"
					+ "(select lec_code from member where id=?))";
			try{
				conn=MyOracle.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setDate(2, sql_date);
				pstmt.setString(3, id);
				pstmt.setTime(4, sql_time);
				pstmt.setString(5, id);
				pstmt.executeUpdate();
			}finally{
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}
			
		}//inCheck()

		//퇴실 버튼 누를때
		public void outCheck(String id, Time sql_time, Date sql_date) throws ClassNotFoundException, SQLException {
			String sql1="select in_date from ATTEND where id=? and nalja=?";
			String sql2="update ATTEND set out_date=?, state=? where nalja=? and id=?";
			String state="";
			int result=0;
			try{
				conn=MyOracle.getConnection();
				pstmt=conn.prepareStatement(sql1);
				pstmt.setString(1, id);
				pstmt.setDate(2, sql_date);
				rs=pstmt.executeQuery();
				if(rs.next()){
					Time in = rs.getTime("in_date");//입실시간
					Time out = sql_time;//퇴실시간
					long min = (out.getTime()-in.getTime())/60000;
					//하루 수업시간 510분
					if(min<510){
						state="지각";
					}else if(min>=510){
						state="출석";
					}
					result=1;
				}
				if(result==1){
					pstmt=conn.prepareStatement(sql2);
					pstmt.setTime(1, sql_time);
					pstmt.setString(2, state);
					pstmt.setDate(3, sql_date);
					pstmt.setString(4, id);
					pstmt.executeUpdate();
				}
			}finally{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}
			
		}//outCheck()

		//결석 버튼 누를때
		public void abCheck(String id, Date sql_date) throws ClassNotFoundException, SQLException {
			String sql1="select * from attend where id=? and nalja=?";
			String sql2="insert into attend values (?,?,"
					+ "(select name from member where id=?),"
					+ "TO_DATE('00:00', 'HH24:MI'),TO_DATE('00:00', 'HH24:MI'),'결석',"
					+ "(select lec_code from member where id=?))";
			String sql3="update ATTEND set in_date=TO_DATE('00:00', 'HH24:MI'), "
					+ "out_date=TO_DATE('00:00', 'HH24:MI'), state='결석' where nalja=? and id=? ";
			try{
				conn=MyOracle.getConnection();
				pstmt=conn.prepareStatement(sql1);
				pstmt.setString(1, id);
				pstmt.setDate(2, sql_date);
				rs=pstmt.executeQuery();
				if(rs.next()){//attend 테이블에 데이터가 이미 있으면 update한다
					pstmt=conn.prepareStatement(sql3);
					pstmt.setDate(1, sql_date);
					pstmt.setString(2, id);
					pstmt.executeUpdate();
				}else{//attend 테이블에 데이터가 없으면 insert 한다
					pstmt=conn.prepareStatement(sql2);
					pstmt.setString(1, id);
					pstmt.setDate(2, sql_date);
					pstmt.setString(3, id);
					pstmt.setString(4, id);
					pstmt.executeUpdate();
				}
			}finally{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}
			
		}//abCheck()

		//수정 버튼을 누르면 입력된 입실,퇴실시간을 불러온다
		public DtoAttend editClick(String id, Date sql_date) throws ClassNotFoundException, SQLException {
			String sql="select in_date, out_date from attend where id=? and nalja=?";
			DtoAttend dtoAttend = new DtoAttend();
			try{
				conn=MyOracle.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setDate(2, sql_date);
				rs=pstmt.executeQuery();
				while(rs.next()){
					dtoAttend.setInDate(rs.getTime("in_date"));
					dtoAttend.setOutDate(rs.getTime("out_date"));
				}
			}finally{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}
			return dtoAttend;
		}//editClick()

		//입력되어있는 오늘 입실시간,퇴실시간 불러오기
		public ArrayList<DtoAttend> selectTime(String id, Date sql_date) throws ClassNotFoundException, SQLException {
			String sql = "select id, in_date, out_date from attend "
					+ "where lec_code=(select lec_code from member where id=?) and nalja=?";
			ArrayList<DtoAttend> listA = new ArrayList<DtoAttend>();
			try{
				conn=MyOracle.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setDate(2, sql_date);
				rs=pstmt.executeQuery();
				while(rs.next()){
					DtoAttend dtoAttend= new DtoAttend();
					dtoAttend.setId(rs.getString("id"));
					dtoAttend.setInDate(rs.getTime("in_date"));
					dtoAttend.setOutDate(rs.getTime("out_date"));
					listA.add(dtoAttend);
				}
			}finally{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}
			return listA;
		}//selectTime()

		//수정> 완료 버튼 > 입력받은 시간으로 수정
		public void ed_Complete(String id, Time in_date, Time out_date, Date sql_date) throws ClassNotFoundException, SQLException {
			String sql="update ATTEND set in_date=?, out_date=?, state=? where nalja=? and id=?";
			
			String state="";
			Time in = in_date;//입실시간
			Time out = out_date;//퇴실시간
			long min = (out.getTime()-in.getTime())/60000;
			//하루 수업시간 510분
			if(min<510){
				state="지각";
			}else if(min>=510){
				state="출석";
			}
			try{
				conn=MyOracle.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setTime(1, in_date);
				pstmt.setTime(2, out_date);
				pstmt.setString(3, state);
				pstmt.setDate(4, sql_date);
				pstmt.setString(5, id);
				pstmt.executeUpdate();
			}finally{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}
		}//ed_Complete()


}
