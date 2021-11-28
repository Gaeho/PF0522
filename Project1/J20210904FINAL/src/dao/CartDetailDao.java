package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CartDetailDao {
	private static CartDetailDao instance;

	private CartDetailDao() {
	}

	public static CartDetailDao getInstance() {
		if (instance == null) {
			instance = new CartDetailDao();
		}
		return instance;
	}

	private Connection getConnection() {

		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}



	
	//상품정보 가져오는 것
	public List<Product> getCartList(String id) throws SQLException {
		List<Product> cartList = new ArrayList<Product>();
		Product product=null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select p.*,pd_qty,img_path from Product p, cart_detail c, product_image pi "
				+ "where p.pd_code=c.pd_code and p.pd_code=pi.pd_code and c.user_id= ? and pi.img_code='1'";
		
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				product = new Product();
				
				product.setImgPath(rs.getString("img_path"));
				product.setCate_code(rs.getInt("cate_code"));
				product.setPd_category(rs.getString("pd_category"));
				product.setPd_code(rs.getString("pd_code"));
				product.setPd_name(rs.getString("pd_name"));
				product.setPd_price(rs.getInt("pd_price"));
				product.setPd_reg_date(rs.getDate("pd_reg_date"));
				product.setQty(rs.getInt("pd_qty"));
				System.out.println("product.getCate_code() : "+product.getCate_code());
				cartList.add(product);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
		}

	
	
		return cartList;
	}

	
	//장바구니에서 삭제 
	public int delete(String id, String pd_code) throws SQLException {
		
		Connection conn = null; 
		PreparedStatement pstmt = null;
		int result = 0; 
		String sql = "delete from cart_detail where user_id=? and pd_code=?";
			
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pd_code);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally { 
			if(conn!=null) conn.close();
			if(pstmt!=null) pstmt.close();
		}
		return result;
	}
	
	public int delete(String id) throws SQLException {
		Connection conn = null; 
		PreparedStatement pstmt = null;
		int result = 0; 
		String sql = "delete from cart_detail where user_id=?";
			
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
			System.out.println("delete result : "+ result);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally { 
			if(conn!=null) conn.close();
			if(pstmt!=null) pstmt.close();
		}
		return result;
	}
	
	
	//장바구니 추가
	public int cartInsert(String user_id,String pd_code, int pd_qty) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "insert into cart_detail values(?,?,?)";
		String sql2= "select * from cart_detail where pd_code = ? and user_id = ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, pd_code);
			pstmt.setString(2, user_id);
			rs = pstmt.executeQuery();
			
			if((rs.next())) {
				pstmt.close();
				rs.close();
				conn.close();
				result=-1;
				System.out.println("result : "+result);
				return result;
			}else {
				pstmt.close();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user_id);
				pstmt.setString(2, pd_code);
				pstmt.setInt(3, pd_qty);
				result = pstmt.executeUpdate();
			}

			
		} catch (SQLException e) {
			System.out.println(e.getMessage());

			e.printStackTrace();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			if (rs != null)
				rs.close();
		}
		return result;

	}



}

