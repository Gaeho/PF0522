package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProductImageDao {
	private static ProductImageDao instance;
	private ProductImageDao() {
	}
	public static ProductImageDao getInstance() {
		if(instance ==null) {
			instance=new ProductImageDao();
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

	
	public String selectDesc(String new_pd_code_value) throws SQLException {
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String result=null;
		
		String sql="select img_path from product_image where pd_code=? order by img_code desc";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, new_pd_code_value);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				result=rs.getString(1);
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
	
	public List<ProductImage> select(String pdCode) throws SQLException  {
		List<ProductImage> imglist = new ArrayList<ProductImage>();
		ProductImage pdImage = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from product_image where pd_code=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pdCode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pdImage=new ProductImage();
				
				pdImage.setPd_code(pdCode);
				pdImage.setImg_code(rs.getString("img_code"));
				pdImage.setImg_path(rs.getString("img_path"));
				
				imglist.add(pdImage);
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
		return imglist;
	}
	
	
}
