package com.gurobit5.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gurobit5.model.entity.DtoMember;
import com.gurobit5.util.MyOracle;

public class DaoMember {
	// try - sskim / 2018-05-24
	// try - sskim / 2018-05-29

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public DaoMember() throws ClassNotFoundException, SQLException {
	}

	public void closeAll() throws SQLException{
		if(rs!=null){ rs.close(); }
		if(pstmt!=null){ pstmt.close(); }
		if(conn!=null){ conn.close(); }
	}//

	public int confirmId(String id) throws ClassNotFoundException, SQLException {
		// 재환 추가 2018-05-29
		int cnt=-1;
		String sql = "select count(*) as cnt from member where id=?";
		try {
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next())cnt = rs.getInt(1);
		} finally {
			closeAll();
		}
		return cnt;
	}
	
	public int pwFind(String id, String name, int phone) {
		String sql = "select count(*) as cnt from member where id=? and name=? and phone=?";
		int result = 0;
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setInt(3, phone);
			rs=pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt("cnt");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public String getPW(String id){
		String sql = "select password from member where id= ? ";
		String result = "";
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				result = rs.getString("password");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	
	public String idFindByName(String name) {
		String sql = "SELECT id FROM MEMBER WHERE name=?";
		String result = "";
		try {
			conn = MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getString("id");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
		
	} // idFind();
	
	public String idFindByName(String name, int phone) {
		String sql = "SELECT id FROM MEMBER WHERE name=? AND PHONE=?";
		String result = "";
		try {
			conn = MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, phone);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getString("id");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
		
	} // idFind();
	
	public int login(String id, String password){
		String sql = "SELECT count(*) as cnt FROM MEMBER WHERE ID=? and PASSWORD=?";
		int result = 0;
		try {
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt("cnt");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try {
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
					if(conn!=null)conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return result;
		
	}// login()

	public int insertJoin(String id, String password, String name, String email, int phone){
		String sql = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?)";
		int result = 0;
		try {
			conn=MyOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setInt(4, phone);
			pstmt.setString(5, email);
			pstmt.setInt(6, 99);
			pstmt.setInt(7, 11111);
			result = pstmt.executeUpdate();
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
	}//insertJoin
	
	
	public String getName(String id) throws ClassNotFoundException, SQLException{
		// 재환 추가 2018-05-28
		String sql = "select name from member where id= ?";
		String name = null;
		try{
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				name = rs.getString("name");
			}
		}finally{
			closeAll();
		}
		return name;
	}
	
	public int getType(String id) throws SQLException, ClassNotFoundException {
		// 재환 추가 2018-05-29
		String sql = "select mem_type from member where id=?";
		int type=0;
		try{
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				type = rs.getInt("mem_type");
			}
		} finally{
			closeAll();
		}
		return type;
	}
	
	public int countAllByLecCode(int lec_code ) throws SQLException, ClassNotFoundException{
		String sql = "SELECT COUNT(*) AS TOT FROM MEMBER WHERE LEC_CODE IS NOT NULL AND LEC_CODE != ?";
		
		int tot = 0; 
		try{
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,lec_code);
			rs 	= pstmt.executeQuery();
			if(rs.next()){
				tot = rs.getInt(1);
			}
		}finally{
			closeAll();
		}
		return tot;
	}// countByLecCode();

	public int countAllByLecCode() throws SQLException, ClassNotFoundException{
		String sql = "SELECT COUNT(*) AS TOT FROM MEMBER WHERE LEC_CODE IS NOT NULL";

		int tot = 0; 
		try{
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs 	= pstmt.executeQuery();
			if(rs.next()){
				tot = rs.getInt(1);
			}
		}finally{
			closeAll();
		}
		return tot;
	}// countByLecCode();


	public List<DtoMember> selectNameAllByLecCodeAndMemTye( int lecCode, int memType) throws ClassNotFoundException, SQLException{
		// try - sskim / 2018-05-29
		
		String sql = ""
				+ "SELECT NAME FROM MEMBER WHERE MEM_TYPE = ? and lec_code = ? ORDER BY NAME DESC";
		List<DtoMember> list = null;
		try{
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memType);
			pstmt.setInt(2, lecCode);
			rs 	= pstmt.executeQuery();
			
			list = new ArrayList<DtoMember>();
			while(rs.next()){
				DtoMember bean = new DtoMember();
				bean.setName(rs.getString("name"));
				list.add(bean);
			}
		}finally{
			closeAll();
		}
		return list;
	}// selectNameAll(int memType)
	

	/*public List<DtoMember> selectNameAll(int memType) throws ClassNotFoundException, SQLException{
		// try - sskim / 2018-05-29

		String sql = ""
				+ "SELECT NAME FROM MEMBER WHERE MEM_TYPE = ? ORDER BY NAME DESC";
		List<DtoMember> list = null;
		try{
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memType);
			rs 	= pstmt.executeQuery();

			list = new ArrayList<DtoMember>();
			while(rs.next()){
				DtoMember bean = new DtoMember();
				bean.setName(rs.getString("name"));
				list.add(bean);
			}
		}finally{
			closeAll();
		}
		return list;
	}// selectNameAll(int memType)
*/

	public List<DtoMember> selectStuAllAddLecName(int start, int end) throws SQLException, ClassNotFoundException {
		// try - sskim / 2018-05-29

		String sql = ""
				+ "SELECT X.RN, X.ID, X.NAME, X.CLASS_NAME, X.PHONE"
				+ " FROM (SELECT ROWNUM AS RN, T.ID ID, T.NAME NAME, T.CLASS_NAME CLASS_NAME, T.PHONE PHONE"
				+ " FROM (SELECT A.ID ID, A.NAME NAME, A.PHONE PHONE,LI.CLASS_NAME CLASS_NAME"
				+ " FROM MEMBER A LEFT OUTER JOIN LEC_INFO LI"
				+ " ON (A.LEC_CODE = LI.LEC_CODE)"
				+ " WHERE (A.MEM_TYPE=0 AND A.LEC_CODE <> 11111)"
				+ " ORDER BY A.NAME DESC) T"
				+ " WHERE ROWNUM <= ?) X"
				+ " WHERE X.RN >= ?";

		ArrayList<DtoMember> list = null;
		try{
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			rs = pstmt.executeQuery();

			list = new ArrayList<DtoMember>();
			while(rs.next()){
				DtoMember dtoMember = new DtoMember();
				dtoMember.setRn(rs.getInt("rn") );
				dtoMember.setId(rs.getString("id") );
				dtoMember.setName(rs.getString("name") );
				dtoMember.setPhone( rs.getInt("phone") );
				dtoMember.setLecCodeName( rs.getString("class_name") );
				list.add(dtoMember);
			}
		}finally{
			closeAll();
		}


		return list;
	}// selectStuAllbyLecCode(int start, int end) 

	// sskim 추가 : 2018-05-29
	public List<DtoMember> selectAllbyType(int start, int end, int type) throws ClassNotFoundException, SQLException {
		String sql = ""
				+ "SELECT RN, ID, NAME, phone, email, MEM_TYPE"
				+ " FROM (SELECT ROWNUM AS RN, ID, NAME, phone, email, MEM_TYPE"
				+ " FROM (SELECT ID, NAME, phone, email, MEM_TYPE FROM MEMBER where mem_type = ? ORDER BY ID)"
				+ " WHERE ROWNUM <= ?)"
				+ " WHERE RN >= ?";
		List<DtoMember> list = null;
		try{
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			pstmt.setInt(2, end);
			pstmt.setInt(3, start);
			rs = pstmt.executeQuery();
			list = new ArrayList<DtoMember>();
			while(rs.next()){
				DtoMember bean = new DtoMember();
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
				bean.setPhone(rs.getInt("phone"));
				bean.setEmail(rs.getString("email"));
				bean.setMemType(rs.getInt("mem_type"));
				list.add(bean);
			}
		}finally{
			closeAll();
		}

		return list;

	}// selectAllbyType(int start, int end, int type) 

	public List<DtoMember> selectAll(int start, int end) throws ClassNotFoundException, SQLException {
		// try - sskim / 2018-05-29
		String sql = ""
				+ "SELECT RN, ID, NAME, phone, email, MEM_TYPE"
				+ " FROM (SELECT ROWNUM AS RN, ID, NAME, phone, email, MEM_TYPE"
				+ " FROM (SELECT ID, NAME, phone, email, MEM_TYPE FROM MEMBER ORDER BY ID)"
				+ " WHERE ROWNUM <= ?)"
				+ " WHERE RN >= ?";
		List<DtoMember> list = null;
		try{
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			rs = pstmt.executeQuery();
			list = new ArrayList<DtoMember>();
			while(rs.next()){
				DtoMember bean = new DtoMember();
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
				bean.setPhone(rs.getInt("phone"));
				bean.setEmail(rs.getString("email"));
				bean.setMemType(rs.getInt("mem_type"));
				list.add(bean);
			}
			
		}finally{
			closeAll();
		}
		return list;
	}// selectAll();

	public DtoMember selectOne(String id) throws SQLException, ClassNotFoundException {
		// try - sskim / 2018-05-29
		DtoMember dtoMember = null;

		String sql = "select * from member where id = ?";
		try{
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				dtoMember = new DtoMember();
				dtoMember.setId(rs.getString("id"));
				dtoMember.setName(rs.getString("name"));
				dtoMember.setPhone(rs.getInt("phone"));
				dtoMember.setEmail(rs.getString("email"));
				dtoMember.setMemType(rs.getInt("mem_type"));
				dtoMember.setLecCode(rs.getInt("lec_code"));
			}
		}finally{
			closeAll();
		}
		return dtoMember;
	}// selectOne();

	public int updateOne(DtoMember dtoMember) throws SQLException, ClassNotFoundException {
		// try - sskim / 2018-05-29
		String sql = "update member set"
				+ " name = ?"	+ " phone = ?"
				+ " email = ?"	+ " mem_type = ?"
				+ " where id = ?";
		int result = 0;
		
		try{
			if(conn==null)conn = MyOracle.getConnection(); // pwInit 때문에
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dtoMember.getName());
			pstmt.setInt(2, dtoMember.getPhone());
			pstmt.setString(3, dtoMember.getEmail());
			pstmt.setInt(4, dtoMember.getMemType());
			pstmt.setString(5, dtoMember.getId());
			result = pstmt.executeUpdate();
		}finally{
			if(conn.getAutoCommit()==false){
				conn.setAutoCommit(true);
			}
			closeAll();
		}
		return result;
	}// updateOne();

	@SuppressWarnings("finally")
	public int pwInit(String id, String newPw) throws SQLException {
		// try - sskim / 2018-05-29

		// 이건 catch 써서 에러나면 rollback 하고 commit은 하지 않습니다.
		String sql = ""
				+ "update member set password = ? where id = ?";
		int result = 0;
		try{
			conn = MyOracle.getConnection(); // pwInit 때문에
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPw);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			System.out.println("["+id+"] 비밀번호 초기화시 오류");
			conn.rollback();
			//e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("["+id+"] 비밀번호 초기화시 오류");
			conn.rollback();
			//e.printStackTrace();
		}finally{
			closeAll();
		}
		return result;
	}// pwInit

	public List<DtoMember> selectAllStu(int lecCode) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from member where lec_code= ? and mem_type = 0";
		
		List<DtoMember> list = null;
		try{
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lecCode);
			rs = pstmt.executeQuery();
			list = new ArrayList<DtoMember>();
			while(rs.next()){
				DtoMember dtoMember = new DtoMember();
				dtoMember.setId(rs.getString("id"));
				dtoMember.setName(rs.getString("name"));
				dtoMember.setPhone(rs.getInt("phone"));
				dtoMember.setEmail(rs.getString("email"));
				dtoMember.setMemType(rs.getInt("mem_type"));
				dtoMember.setLecCode(rs.getInt("lec_code"));
				list.add(dtoMember);
			}
			
		}finally{
			closeAll();
		}
		return list;
	}// selectAllStu(lecCode)

	public int changeMemTypeById(String id, int mem_type) throws SQLException, ClassNotFoundException {
		
		int result = 0;
		String sql = "update member set"
				+ " mem_type = ?"
				+ " where id = ?";
		try{
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,mem_type);
			pstmt.setString(2,id);
			result = pstmt.executeUpdate();
		}finally{
			closeAll();
		}
		return result;
	}//changeLecCode // simple
	
	public void changeLecCode(String name, int mem_type, int nowLecCode, int afLecCode) throws SQLException, ClassNotFoundException {

		String sql = "update member set"
				+ " lec_code = ?"
				+ " where name = ? and mem_type = ? and lec_code = ? ";
		try{
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,afLecCode);
			pstmt.setString(2,name);
			pstmt.setInt(3,mem_type);
			pstmt.setInt(4,nowLecCode);
			pstmt.executeUpdate();
		}finally{
			closeAll();
		}
	}//changeLecCode

	public void changeLecCode(String id, int lec_code) throws ClassNotFoundException, SQLException {
		String sql = "update member set"
				+ " lec_code = ? where id = ?";
		try{
			conn = MyOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,lec_code);
			pstmt.setString(2,id);
			pstmt.executeUpdate();
		}finally{
			closeAll();
		}		
	}

	public int updateMypage(String id, int phone, String email, String pw2) throws SQLException, ClassNotFoundException {
		// try - hyunjung / 2018-06-03
		String sql = "update member set phone=?, email=?, password=? where id=?";
				
		int result = 0;
		
		try{
			conn = MyOracle.getConnection(); 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, phone);
			pstmt.setString(2, email);
			pstmt.setString(3, pw2);
			pstmt.setString(4, id);
			result = pstmt.executeUpdate();
		}finally{
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
			
		}
		return result;
	}// updateMypage();



}//DaoMember
