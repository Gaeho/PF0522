package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProductDao {
	private static ProductDao instance;
	private ProductDao() {
	}
	public static ProductDao getInstance() {
		if(instance ==null) {
			instance=new ProductDao();
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
	
	// 선택된 상품정보 가져오기
	public Product select(String pdCode) throws SQLException {
		Product pd = new Product();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from product where pd_code=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pdCode);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pd = new Product();
				pd.setPd_code(rs.getString("pd_code"));
				pd.setPd_price(rs.getInt("pd_price"));
				pd.setPd_name(rs.getString("pd_name"));
				pd.setPd_category(rs.getString("pd_category"));
				pd.setPd_reg_date(rs.getDate("pd_reg_date"));
				pd.setCate_code(rs.getInt("cate_code"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
		}
		return pd;
	}
	
//	카테고리를 key로하고 가장 최신 상품코드를 value에 넣어서 map으로 리턴
	public HashMap<String, String> new_select() throws SQLException {
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		HashMap<String, String> new_pd_code=new HashMap<String, String>();
		
		String sql="select cate_code, pd_code from product "
				+"where (cate_code, pd_reg_date) in(select cate_code, max(pd_reg_date) "
				+"from product group by cate_code) "
				+"order by pd_code";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				new_pd_code.put(Integer.toString(rs.getInt("cate_code")), rs.getString("pd_code"));
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		return new_pd_code;
	}
	
//	카테고리를 key로하고 리뷰평점평균이 가장높은 상품코드를 value에 넣어서 map으로 리턴
//	new_pd_code에서 나온 상품코드를 제외함
	public HashMap<String, String> best_select(HashMap<String, String> new_pd_code) throws SQLException {
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		HashMap<String, String> best_pd_code=new HashMap<String, String>();
		
		String sql="select pcc, rpc, avg_rra"
					+ " from (select p.cate_code pcc, r.pd_code rpc, avg(r.rev_avg) avg_rra"
					+ "		 from review r, product p"
					+ "		 where r.pd_code=p.pd_code"
					+ "		 group by p.cate_code, r.pd_code)"
					+ "where (pcc, avg_rra)in(select pcc2, max(avg_rra2)"
					+ "                        from (select p.cate_code pcc2, r.pd_code rpc2, avg(r.rev_avg) avg_rra2"
					+ "                                from review r, product p"
					+ "                                where r.pd_code=p.pd_code"
					+ "                                group by p.cate_code, r.pd_code"
					+ "                                having r.pd_code!=?"
					+ "                                and r.pd_code!=?"
					+ "                                and r.pd_code!=?)"
					+ "                        group by pcc2)";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, new_pd_code.get("1"));
			pstmt.setString(2, new_pd_code.get("2"));
			pstmt.setString(3, new_pd_code.get("3"));
			rs=pstmt.executeQuery();
			while(rs.next()){
				best_pd_code.put(Integer.toString(rs.getInt("pcc")), rs.getString("rpc"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		return best_pd_code;
	}
	

	// 카테고리별 상품 총 갯수
	public int getTotalCnt(String cateCode) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		//인기상품 구분
		String sql = "select count(*) from product where cate_code =" + cateCode;
		
		if(cateCode.equals("hot")) {
			sql="select count(*) from product where pd_count>=30";
		}
		
		int tot = 0;

		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				tot = rs.getInt(1);
				System.out.println(tot);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			conn.close();
			stmt.close();
			rs.close();
		}

		return tot;
	}
	
	// 뿌려주자 목록
	public List<Product> list(int startRow, int endRow, String sort, String cateCode) throws SQLException {
		List<Product> list = new ArrayList<Product>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Product pd = null;

		String sql = "select a.*,b.img_path from (select rownum rn, a.* from (select * from product where cate_code=? order by "+sort+") a) a, product_image b"
				+ " where a.pd_code =b.pd_code and instr(b.img_path,'d')=0 and rn between ? and ? order by rn" ;
		//인기상품 구분
		if (cateCode.equals("hot")) {
			/*
			 * sql =
			 * "select a.*,b.img_path from (select rownum rn, a.* from (select * from product where pd_count>=30) a) a, product_image b"
			 * +
			 * " where a.pd_code =b.pd_code and instr(b.img_path,'d')=0 and rn between ? and ? order by "
			 * +sort;
			 */
			sql = "select a.*,b.img_path from (select rownum rn, a.* from (select * from product where pd_count>=30 order by "+sort+") a) a, product_image b "
					+"where a.pd_code =b.pd_code and instr(b.img_path,'d')=0 and rn between "+startRow+" and "+endRow +" order by rn";
			System.out.println(sql);
		}

		// String sql = "select * from product";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			if (!(cateCode.equals("hot"))) {
				pstmt.setString(1, cateCode);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			}


			rs = pstmt.executeQuery();
			System.out.println(rs);

			while (rs.next()) {
				pd = new Product();

				pd.setPd_code(rs.getString("pd_code"));
				pd.setPd_price(rs.getInt("pd_price"));
				pd.setPd_name(rs.getString("pd_name"));
				pd.setPd_category(rs.getString("pd_category"));
				pd.setPd_reg_date(rs.getDate("pd_reg_date"));
				pd.setCate_code(rs.getInt("cate_code"));
				pd.setImgPath(rs.getString("img_path"));
				pd.setPd_count(rs.getInt("pd_count"));
				
				list.add(pd);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
		}
		return list;
	}
	
    public int searchTotalCnt(String searchContent) throws SQLException {
    	Connection conn = null;
    	Statement stmt = null;
    	ResultSet rs = null;

    	// 인기상품 구분
    	String sql = "select count(*) from product where pd_name like '%" + searchContent + "%'";

    	int tot = 0;

    	try {
    		conn = getConnection();
    		stmt = conn.createStatement();
    		rs = stmt.executeQuery(sql);

    		if (rs.next()) {
    			tot = rs.getInt(1);
    			System.out.println(tot);
    		}

    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} finally {
    		conn.close();
    		stmt.close();
    		rs.close();
    	}

    	return tot;
    }
    
    
    public List<Product> searchProduct(int startRow, int endRow, String sort, String searchContent) throws SQLException {
    	List<Product> list = new ArrayList<Product>();
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	Product pd = null;

    	String sql = "select a.*,b.img_path from (select rownum rn, a.* from (select * from product where pd_name like '%"+searchContent+"%') a) a, product_image b"
    			+ " where a.pd_code =b.pd_code and instr(b.img_path,'d')=0 and rn between ? and ?  order by " + sort;
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, startRow);
    		pstmt.setInt(2, endRow);

    		rs = pstmt.executeQuery();
    		System.out.println(rs);

    		while (rs.next()) {
    			pd = new Product();
    			pd.setPd_code(rs.getString("pd_code"));
    			pd.setPd_price(rs.getInt("pd_price"));
    			pd.setPd_name(rs.getString("pd_name"));
    			pd.setPd_category(rs.getString("pd_category"));
    			pd.setPd_reg_date(rs.getDate("pd_reg_date"));
    			pd.setCate_code(rs.getInt("cate_code"));
    			pd.setImgPath(rs.getString("img_path"));
    			pd.setPd_count(rs.getInt("pd_count"));
    			
    			list.add(pd);
    		}
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	} finally {
    		if (rs != null)
    			rs.close();
    		if (conn != null)
    			conn.close();
    		if (pstmt != null)
    			pstmt.close();
    	}
    	return list;

    }
	public void pd_count(String pdCode) throws SQLException {
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		
		String sql="update product set pd_count=pd_count+1 where pd_code=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, pdCode);
			int n=pstmt.executeUpdate();
			if(n>0) {
				conn.commit();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
	}
}
