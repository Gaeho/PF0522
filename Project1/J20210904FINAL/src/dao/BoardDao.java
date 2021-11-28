package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDao {
	private static BoardDao instance;
	private BoardDao() {
	}
	public static BoardDao getInstance() {
		if(instance ==null) {
			instance=new BoardDao();
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
	
	public int getTotalCnt() throws SQLException {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		int tot=0;
		String sql="select count(*) from board";
		try {
			conn=getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()) tot=rs.getInt(1);
			System.out.println("BoardDao getTotalCnt tot-> "+tot);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
			
		}
		return tot;
	}
	
	//MyWritingAction에서 호출 (마이페이지-작성한글) 특정아이디의 총 글 개수 구하는 메소드
	public int getTotalCnt(String id) throws SQLException {
		Connection conn = null;	
		PreparedStatement pstmt= null; 
		ResultSet rs = null;    
		int tot = 0;
		String sql = "select count(*) from board where USER_ID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) System.out.println(rs.getInt(1));
			tot = rs.getInt(1);
					
		} catch(Exception e) {	
			System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return tot;
	}
	
	public List<Board> list(int startRow, int endRow) throws SQLException{
		List<Board> result=new ArrayList<Board>();
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql="Select * from (select rownum rn ,a.* \r\n"
				+ "	from   (select * from board order by ref desc,ref_step) a) \r\n"
				+ "	where rn between ? and ?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Board board=new Board();
				board.setBrd_code(rs.getInt("brd_code"));
				board.setUser_id(rs.getString("user_id"));
				board.setBrd_reg_date(rs.getDate("brd_reg_date"));
				board.setBrd_subject(rs.getString("brd_subject"));
				board.setBrd_context(rs.getString("Brd_context"));
				board.setRef(rs.getInt("ref"));
				board.setRef_level(rs.getInt("ref_level"));
				board.setRef_step(rs.getInt("ref_step"));
				result.add(board);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		
		return result;
	}
	
	//MyWritingAction에서 호출.  (마이페이지-작성한글)특정 아이디가 작성한 글의 정보를 모두 가져옴
	public List<Board> list(int startRow, int endRow, String id) throws SQLException	{
		List<Board> list = new ArrayList<Board>();		
		Connection conn = null;	
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql = "select * from (select rownum rn ,a.* from " + 
		                   	" (select * from board where user_id=? order by ref desc,ref_step) a ) "+
			         " where rn between ? and ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startRow); //시작 줄 1들어감
			pstmt.setInt(3, endRow); //끝줄 10 들어감
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board board = new Board();
				board.setNum(rs.getInt("RN"));
				board.setBrd_code(rs.getInt("BRD_CODE"));
				board.setUser_id(rs.getString("USER_ID"));
				board.setBrd_reg_date(rs.getDate("BRD_REG_DATE"));
				board.setBrd_context(rs.getString("BRD_CONTEXT"));		
				board.setBrd_subject(rs.getString("BRD_SUBJECT"));
				board.setRef(rs.getInt("REF"));	
				board.setRef_level(rs.getInt("REF_LEVEL"));
				board.setRef_step(rs.getInt("REF_STEP"));
				board.setBrd_pd_code(rs.getString("brd_pd_code"));
				
				list.add(board);
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		} 		
		return list;	
	}
	
	public Board select(int brd_code) throws SQLException {
		Board board=null;
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql="select * from board where brd_code=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, brd_code);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				board=new Board();
				board.setBrd_code(rs.getInt("brd_code"));
				board.setUser_id(rs.getString("user_id"));
				board.setBrd_reg_date(rs.getDate("brd_reg_date"));
				board.setBrd_subject(rs.getString("brd_subject"));
				board.setBrd_context(rs.getString("Brd_context"));
				board.setRef(rs.getInt("ref"));
				board.setRef_level(rs.getInt("ref_level"));
				board.setRef_step(rs.getInt("ref_step"));
				board.setBrd_pd_code(rs.getString("brd_pd_code"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		
		return board;
	}
	
	public int update(Board board) throws SQLException {
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		int result=0;
		
		String sql="update board set brd_subject=?, brd_context=? where brd_code=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, board.getBrd_subject());
			pstmt.setString(2, board.getBrd_context());
			pstmt.setInt(3, board.getBrd_code());
			result=pstmt.executeUpdate();
			System.out.println("boardgetbrdsubject - "+board.getBrd_subject());
			if(result>0) {
				conn.commit();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("dao update 에러");
		}finally {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		
		return result;
	}
	
	public int delete(int brd_code) throws SQLException {
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		Board board=new Board();
		int result=0;
		
		try {
			board=select(brd_code);
			if(brd_code==board.getBrd_code()) {
				String sql="delete board where brd_code=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, brd_code);
				result=pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		return result;
	}
	


	// insert board테이블에 입력한 게시글 정보 insert
	public int insert(Board board) throws SQLException {
		int brdCode = board.getBrd_code();
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int max_num=0;
		// NVL 함수는 값이 null인 경우 지정값을 출력한다.
		String sql1 = "select nvl(max(brd_code),0) from board";
		String sql2 = "update board set ref_step = ref_step+1 where ref = ? and ref_step > ?";
		String sql = "insert into board values(?,?,sysdate,?,?,?,?,?,?)";

		try {
			conn = getConnection();

			// 댓글
			if (brdCode != 0) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, board.getRef());
				pstmt.setInt(2, board.getRef_step());
				pstmt.executeUpdate();
				pstmt.close();
				board.setRef_step(board.getRef_step() + 1);
				board.setRef_level(board.getRef_level() + 1);
			}
			
			// 마지막 게시글 번호
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				max_num=rs.getInt(1)+1;
			}
			
			if(pstmt!=null) pstmt.close();
			rs.close();

			// 댓글 대비용
			if (brdCode == 0) board.setRef(max_num);

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, max_num);
			pstmt.setString(2, board.getUser_id());
			pstmt.setString(3, board.getBrd_subject());
			pstmt.setString(4, board.getBrd_context());
			pstmt.setInt(5, board.getRef());
			pstmt.setInt(6, board.getRef_level());
			pstmt.setInt(7, board.getRef_step());
			pstmt.setString(8, board.getBrd_pd_code());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
		}
		return result;
	}
	
	public int searchTotalCnt(String searchContent) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int tot = 0;
		String sql = "select count(*) from board where brd_context like '%"+searchContent+"%' or brd_subject like '%"+searchContent+"%'";
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
				tot = rs.getInt(1);
			System.out.println("BoardDao searchTotalCnt tot-> " + tot);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();

		}
		return tot;
	}
	
	// 검색 게시판 목록 제목 or 내용 포함시 출력
	public List<Board> searchList(int startRow, int endRow, String searchContent) throws SQLException {
		List<Board> result = new ArrayList<Board>();
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "Select * from (select rownum rn ,a.*from(select * from board where brd_context like '%"
				+ searchContent + "%' or brd_subject like '%" + searchContent + "%' order by ref desc,ref_step) a)"
				+ "	where rn between ? and ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board board = new Board();
				board.setBrd_code(rs.getInt("brd_code"));
				board.setUser_id(rs.getString("user_id"));
				board.setBrd_reg_date(rs.getDate("brd_reg_date"));
				board.setBrd_subject(rs.getString("brd_subject"));
				board.setBrd_context(rs.getString("Brd_context"));
				board.setRef(rs.getInt("ref"));
				board.setRef_level(rs.getInt("ref_level"));
				board.setRef_step(rs.getInt("ref_step"));
				result.add(board);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return result;
	}
}
