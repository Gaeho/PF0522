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

public class ReviewDao {

	private static ReviewDao instance;

	private ReviewDao() {
	}

	public static ReviewDao getInstance() {
		if (instance == null) {
			instance = new ReviewDao();
		}
		return instance;
	}

	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public int getTotalCnt(String pd_code) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		int tot = 0;
		String sql = "select count(*) as tot from Review where pd_code= '"+pd_code+"'";

		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				tot = rs.getInt("tot");
			}

		} catch (SQLException e) {
			System.out.println("리뷰 글 갯수 확인 : " + tot);
		} finally {
			if (conn != null)
				conn.close();
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
		}

		return tot;
	}

	public List<Review> list(int startRow, int endRow, String pd_code) throws SQLException {

		List<Review> list = new ArrayList<Review>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from (select rownum rn, r.* from (select * from review where pd_code= ? order by rev_code desc) r)"
					+ " where rn between ? and ? ";

		System.out.println("ReviewDao  select  startRow->" + startRow);
		System.out.println("ReviewDao  select  endRow->" + endRow);
		System.out.println("ReviewDao  select  sql->" + sql);

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,pd_code);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Review review = new Review();
				review.setPd_code(rs.getString("pd_code"));
				review.setRev_code(rs.getInt("rev_code"));
				review.setUser_id(rs.getString("user_id"));
				review.setRev_context(rs.getString("rev_context"));
				review.setRev_reg_date(rs.getDate("rev_reg_Date"));
				list.add(review);

				System.out.println("ReviewDao  select list ->" + list);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		}
		return list;

	}

	public Review select(String rev_code) throws SQLException {

		Review review = null;
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from review where rev_code=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rev_code);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				review = new Review();
				review.setRev_code(rs.getInt("rev_code"));
				review.setUser_id(rs.getString("user_id"));
				review.setRev_avg(rs.getInt("rev_avg"));
				review.setRev_context(rs.getString("rev_context"));
				review.setRev_reg_date(rs.getDate("rev_reg_date"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		}
		return review;

	}

	public int update(Review review) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = "update review set user_id=?, rev_avg=?, rev_context=? where rev_code=?";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, review.getUser_id());
			pstmt.setInt(2, review.getRev_avg());
			pstmt.setString(3, review.getRev_context());
			pstmt.setInt(4, review.getRev_code());
//			pstmt.setDate(4, (Date)review.getRev_reg_date());
			result = pstmt.executeUpdate();
			if (result > 0) {
				conn.commit();
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("dao update 에러");
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return result;
	}

	public int insert(Review review) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rs = null;
		int max_num = 0;
		String sql1 = "select nvl(max(rev_code),0) from review";
		String sql = "insert into review values (?,?,?,?,?, sysdate)";
		try {

			conn = getConnection();

			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				max_num = rs.getInt(1) + 1;
			}
			pstmt.close();
			rs.close();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, review.getPd_code());
			pstmt.setInt(2, max_num);
			pstmt.setString(3, review.getUser_id());
			pstmt.setInt(4, review.getRev_avg());
			pstmt.setString(5, review.getRev_context());
			result = pstmt.executeUpdate();

			if (result > 0) {
				conn.commit();
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("dao insert 에러");
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return result;
	}

	public int rvdelete(String revcode) throws SQLException {

		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from review where rev_code=?";

		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, revcode);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
		}
		return result;

	}

}
