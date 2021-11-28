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

public class OrdersDao {

	private static OrdersDao instance;

	private OrdersDao() {
	}

	public static OrdersDao getInstance() {
		if (instance == null) {
			instance = new OrdersDao();
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
	
	public Orders select(String ord_code) throws SQLException {
		Orders order=null;
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql="select * from orders where ord_code=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, ord_code);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				order=new Orders();
				order.setOrd_code(rs.getInt("ord_code"));
				order.setUser_id(rs.getString("user_id"));
				order.setOrd_date(rs.getDate("ord_date"));
				order.setOrd_status(rs.getString("ord_status"));
				order.setUser_tel(rs.getString("user_tel"));
				order.setUser_addr(rs.getString("user_addr"));
				order.setOrd_receiver(rs.getString("ord_receiver"));
				order.setOrd_memo(rs.getString("ord_memo"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		
		return order;
	}


	//자기가 주문한 상품들 이름과 상품코드 가져오기
	public List<Product> myOrderList(String id) throws SQLException {
		List<Product> myOrderList = new ArrayList<Product>();
		Product product = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select distinct(pd_name),p.pd_code from product p, orders o,order_detail od "
				+ "where od.ord_code = o.ord_code and p.pd_code = od.pd_code and o.user_id = ?"
				+ "order by 1";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product = new Product();
				product.setPd_name(rs.getString(1));
				product.setPd_code(rs.getString(2));
				myOrderList.add(product);
				System.out.println("getPd_name->"+product.getPd_name());
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
		return myOrderList;
	}
	
	//OrderConfirmAction에서 호출. (마이페이지-주문확인) 특정 아이디와 주문번호에 따라 목록 생성
	public List<Orders> OrderCf(int startRow, int endRow, String id) throws SQLException	{
		List<Orders> list = new ArrayList<Orders>();		
		Connection conn = null;	
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql = "select * from (select rownum rn ,a.* from " + 
		                   	" (select * from orders where user_id=? order by ord_date desc) a ) "+
			         " where rn between ? and ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startRow); //시작 줄 1들어감
			pstmt.setInt(3, endRow); //끝줄 10 들어감
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Orders orders = new Orders();
				orders.setOrd_code(rs.getInt("ord_code"));
				orders.setOrd_date(rs.getDate("ord_date"));
				String ordstatus = rs.getString("ord_status");
					if(ordstatus.equals("P")) ordstatus="배송완료";
					else if (ordstatus.equals("U")) ordstatus="미배송";
				orders.setOrd_status(ordstatus);
				list.add(orders);
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		} 		
		return list;
	}

	
	//OrderConfirmAction에서 호출. (마이페이지-주문확인) 특정 아이디로 작성한 주문의 수 
	public int getTotalCntId(String id) throws SQLException {
		Connection conn = null;	
		PreparedStatement pstmt= null; 
		ResultSet rs = null;    
		int tot = 0;
		String sql = "select count(*) from orders where USER_ID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				tot = rs.getInt(1);
				System.out.println(tot);
					
			}
				
		} catch(Exception e) {	
			System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return tot;
	}
		

	
	//OrderDetailAction에서 호출 (마이페이지-주문번호-상세) 특정 주문번호의 아이템 종류 수
	public int getTotalCntOrdcode(String ordcode) throws SQLException {
		Connection conn = null;	
		PreparedStatement pstmt= null; 
		ResultSet rs = null;    
		int tot = 0;
		String sql = "select count(*) from order_detail where ord_code=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ordcode);
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
		
		
	//orderDetailAction에서 호출. (마이페이지-주문번호-상세)상품명/수량/가격/배송상태 표시 
		public List<Orders> OrderDt(String ordcode) throws SQLException {
			List<Orders> list = new ArrayList<Orders>();
			Connection conn = null;	
			PreparedStatement pstmt= null; 
			ResultSet rs = null;   
			Orders orders = null;
			String sql =   
							  "SELECT rownum, orders.ord_code,product.pd_name, order_detail.pd_price, " 
					
							 +"order_detail.pd_qty, orders.ord_status, "
							 					 
							 + "substr(product_image.img_path,14) a " 
							 
							 +"FROM orders " + "LEFT JOIN order_detail "
							 
							 +"ON orders.ord_code = order_detail.ord_code " 
							 
							 + "join product " 
							 
							 +"on order_detail.pd_code = product.pd_code " 
							 
							 + "join product_image " 
							 
							 + "on product.pd_code = product_image.pd_code " 
							 
							 + "where orders.ord_code = ? "
							 
							 + "and product_image.img_code='1' " + "order by orders.ord_code";
							
		
						
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, ordcode);
				rs = pstmt.executeQuery();
					
				while (rs.next()) {
					orders = new Orders();
				
					orders.setPd_name(rs.getString("pd_name"));
					
					orders.setPd_price(Integer.parseInt(rs.getString("pd_price")));
					
					orders.setPd_qty(Integer.parseInt(rs.getString("pd_qty")));
			
					String ordstatus = rs.getString("ord_status");
						if(ordstatus.equals("P")) ordstatus="배송완료";
						else if (ordstatus.equals("U")) ordstatus="미배송";
					orders.setOrd_status(ordstatus);
					
					
					orders.setImg_path(rs.getString("a"));
					 
					list.add(orders);
				}
					
			} catch(Exception e) {	
				System.out.println("OrderDt 여기가 출력되는거?"+e.getMessage()); 
			} finally {
				if (rs !=null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn !=null) conn.close();
			}
			return list;
		}

		
		
	public int insert(Orders order) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		int result=0;
		int max_ord_code=0;
		
		String sql1="select max(ord_code) from orders";
		String sql2="insert into ORDERS(ORD_CODE,USER_ID,ORD_DATE,ORD_STATUS,USER_TEL,USER_ADDR,ORD_RECEIVER,ORD_MEMO)"
				+ " values(?,?,sysdate,?,?,?,?,?)";

		
		try {
			pstmt=conn.prepareStatement(sql1);
			rs=pstmt.executeQuery();
			if(rs.next())	max_ord_code=rs.getInt(1)+100;
			rs.close();
			pstmt.close();
			
			pstmt=conn.prepareStatement(sql2);
			pstmt.setInt(1, max_ord_code);
			pstmt.setString(2, order.getUser_id());
			pstmt.setString(3, "U");
			pstmt.setString(4, order.getUser_tel());
			pstmt.setString(5, order.getUser_addr());
			pstmt.setString(6, order.getOrd_receiver());
			pstmt.setString(7, order.getOrd_memo());
			result=pstmt.executeUpdate();
			
			
			if(result>0)	result=max_ord_code;
			
		} catch(Exception e) {	
			System.out.println(e.getMessage()); 
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result;
	}

	public int insert_detail(Orders order, int max_ord_code) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		int result=0;
		int pd_price=0;
		
		String sql1="select pd_price from product where pd_code=?";
		String sql2="insert into ORDER_DETAIL(ORD_CODE,PD_CODE,PD_QTY,PD_PRICE) values(?,?,?,?)";
		
		try {
			pstmt=conn.prepareStatement(sql1);
			pstmt.setString(1, order.getPd_code());
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				pd_price=rs.getInt(1);
				rs.close();
				pstmt.close();
				
				pstmt=conn.prepareStatement(sql2);
				pstmt.setInt(1, max_ord_code);
				pstmt.setString(2, order.getPd_code());
				pstmt.setInt(3, order.getPd_qty());
				pstmt.setInt(4, pd_price);
				
				result=pstmt.executeUpdate();
			}
		} catch(Exception e) {	
			System.out.println(e.getMessage()); 
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		
		return result;
	}	
}	
	
