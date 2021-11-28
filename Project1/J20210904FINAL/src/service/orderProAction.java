package service;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDetailDao;
import dao.Orders;
import dao.OrdersDao;

public class orderProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//세션확인 로그인 안되어 있으면 이후 과정 진행 안되도록!
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id =(String)session.getAttribute("id");
						
		System.out.println("세션으로 넘어온 id->"+id);
						
				
		if(id==null||id.equals("")){
			return "loginForm.do";
		}
		
		Orders order=new Orders();	
		OrdersDao od=OrdersDao.getInstance();
		order.setUser_id(id);
		order.setUser_tel(request.getParameter("user_tel"));
		order.setUser_addr(request.getParameter("user_addr"));
		order.setOrd_receiver(request.getParameter("ord_receiver"));
		order.setOrd_memo(request.getParameter("ord_memo"));
	
		String pd_code_list=request.getParameter("pd_code_list");
		String pd_qty_list=request.getParameter("pd_qty_list");
		int result=0;
		int max_ord_code=0;
		
		String[] pd_code_array = pd_code_list.split(" ");
		String[] pd_qty_array = pd_qty_list.split(" ");
		
		for(int i=0; i<pd_code_array.length; i++) {
			System.out.println("pd_code_array : "+pd_code_array[i]);
			System.out.println("pd_qty_array : "+pd_qty_array[i]);
		}

		
		try {
			max_ord_code=od.insert(order);
			System.out.println("max_ord_code : "+max_ord_code);
			if(max_ord_code>0) {
				for(int i=0; i<pd_code_array.length; i++) {
					order.setPd_code(pd_code_array[i]);
					order.setPd_qty(Integer.parseInt(pd_qty_array[i]));
					result=od.insert_detail(order, max_ord_code);
					
				}
				if(result>0) {
					CartDetailDao cd=CartDetailDao.getInstance();
					result=cd.delete(id);
					System.out.println("결제 result : "+result);
				}
			}
	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		request.setAttribute("result", result);
		
		
		return "orderPro.jsp";
	}

}
