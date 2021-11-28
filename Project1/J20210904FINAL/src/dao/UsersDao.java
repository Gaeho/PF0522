package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UsersDao {
	private static UsersDao instance;
	private UsersDao() {
	}
	public static UsersDao getInstance() {
		if(instance ==null) {
			instance=new UsersDao();
		}
		return instance;
	}
	
	private Connection getConnection() {
		Connection conn=null;
		try {
			Context ctx=new InitialContext();
			DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn=ds.getConnection();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public Users select(String id) throws SQLException {
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Users users=null;
		
		String sql="select * from users where user_id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				users=new Users();
				users.setUser_id(rs.getString("user_id"));
				users.setUser_pw(rs.getString("user_pw"));
				users.setUser_name(rs.getString("user_name"));
				users.setUser_tel(rs.getString("user_tel"));
				users.setUser_email(rs.getString("user_email"));
				users.setUser_addr(rs.getString("user_addr"));
				users.setUser_grade(rs.getString("user_grade"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
			if(rs!=null) rs.close();
		}
		
		
		return users;
	}
	
	//adminck - 유저가 관리자계정인지 확인 1, null
	public String adminck(String id) throws SQLException {
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String adck=null;
		
		String sql="select user_grade from users where user_id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("user_grade").equals("A")) {
					adck="1";
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
			if(rs!=null) rs.close();
		}
		
		return adck;
	}
	
	// check - 입력한 아이디와 비밀번호 일치확인 1, 0, -1
	public int check (String id, String pw) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;
		
		
		String sql = "select user_pw, user_grade from Users where user_id=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
			String dbgrade = rs.getString("user_grade");
			String dbpasswd = rs.getString("user_pw");
				if(dbpasswd.equals(pw)) {
					if(dbgrade.equals("X")) {
						result = 3;
						//등급이 X인 경우 결과에 3 저장 아니라면 밑으로 빠짐
					}else{
						result = 1; // 로그인 성공
					}
				}else { 
					result = 0; // 비밀번호 틀림
				}
			}else {
				result = -1; // 아이디가 없음
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if(conn!=null) conn.close();
			if(pstmt!=null) pstmt.close();
			if(rs!=null) rs.close();
		}
		return result;
		
	}
	
	// idSearch - 입력한 이름과 이메일에 일치하는 아이디가 있는지 확인 user_id or null
	public String idSelect (String name, String email) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String id = null;
		String sql = "select user_id from Users where user_name=? and user_email=?";
		
		System.out.println("UsersDao  select  name->"+name);
		System.out.println("UsersDao  select  email->"+email);
		System.out.println("UsersDao  select  sql->"+sql);
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getString("user_id"); // 아이디 찾기 성공
			}else {
//				id = "Fail"; // 아이디 찾기 실패
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		}finally {
			if(conn!=null) conn.close();
			if(pstmt!=null) pstmt.close();
			if(rs!=null) rs.close();
		}
		
		return id;
	}
	
	// pwselect - 입력한 이름 아이디 이메일에 일치하는 비밀번호가 있는지 확인 user_pw, null
	public String pwselect (String name, String id, String email) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String pw = null;
		String sql = "select user_pw from Users where user_name=? and user_id=? and user_email=?";
		
		System.out.println("UsersDao  select  name->"+name);
		System.out.println("UsersDao  select  id->"+id);
		System.out.println("UsersDao  select  email->"+email);
		System.out.println("UsersDao  select  sql->"+sql);
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pw = rs.getString("user_pw"); // 아이디 찾기 성공
				System.out.println("pw찾기성공 : "+pw);
			}else {
//				pw = "Fail"; // 아이디 찾기 실패
				System.out.println("pw찾기실패 : "+pw);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		}finally {
			if(conn!=null) conn.close();
			if(pstmt!=null) pstmt.close();
			if(rs!=null) rs.close();
		}
		
		return pw;
	}
	
	// USERS 테이블에 회원가입 입력한 정보 insert
	public int insertOneUserByJoinForm(Users users) throws SQLException {
		int result = 0;
		Connection conn = null;
		String sql = "insert into Users values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, users.getUser_id());
			pstmt.setString(2, users.getUser_pw());
			pstmt.setString(3, users.getUser_name());
			pstmt.setString(4, users.getUser_tel());
			pstmt.setString(5, users.getUser_email());
			pstmt.setString(6, users.getUser_addr());
			pstmt.setString(7, users.getUser_grade());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return result;
	}
	
	//checkDuplicateUserId	중복된아이디가 있으면 1이상 없으면 0
	public int checkDuplicateUserId(String id) throws SQLException {
		int result =-1;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="SELECT COUNT(*) AS counts FROM Users WHERE user_id = ? ";
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt("counts");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();

		}
		
		return result;
	}
	
	//UpdateProAction에서 호출. (마이-개인정보-정보수정)변경입력된 정보를 db에 입력하는 용도.
	public int idUpdatePro(Users users) throws SQLException {
		Connection conn = null;	
		PreparedStatement pstmt= null; 
		ResultSet rs = null;
		String sql = "update Users set user_pw=?, user_tel=?, user_email=?, user_addr=? where user_id=?";
		//users에서 아이디가 같은 열을 찾아서 전부 가져와
		int UdResult = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,users.getUser_pw());
			pstmt.setString(2,users.getUser_tel());
			pstmt.setString(3,users.getUser_email());
			pstmt.setString(4,users.getUser_addr());
			pstmt.setString(5,users.getUser_id());
			
			UdResult = pstmt.executeUpdate();
		
		} catch(Exception e) {	
			System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return UdResult;
		//정보를 담은 usersInfo를 메서드 호출한 곳으로 리턴
	}
	
	//DeleteProAction에서 호출. (마이-개인정보-정보수정)올바른 비번 입력 시 - 회원 등급 x로 변경
	public int idDelUpd(String id, String pw) throws SQLException {
		Connection conn = null;	
		PreparedStatement pstmt= null; 
		ResultSet rs = null;
		int result=0;
		String sql1 = "select USER_PW from users where user_id=?";
		String sql = "update Users set user_grade='X' where user_id=?";
		//users에서 아이디가 같은 열을 찾아서 전부 가져와

		try {
			String dbPasswd="";
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
				if(rs.next()) {
					dbPasswd = rs.getString(1);
					if(dbPasswd.equals(pw)) {
						rs.close();
						pstmt.close();
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, id);
						result = pstmt.executeUpdate();					
					}else result = 0;			
				}else result = -1;
		
		} catch(Exception e) {	
			System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result;
		
	}
}
