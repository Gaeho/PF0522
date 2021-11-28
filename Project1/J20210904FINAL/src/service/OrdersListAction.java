package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDetailDao;
import dao.Product;
import dao.ProductDao;
import dao.Users;
import dao.UsersDao;

public class OrdersListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		//세션확인 로그인 안되어 있으면 이후 과정 진행 안되도록!
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id =(String)session.getAttribute("id");
				
		System.out.println("세션으로 넘어온 id->"+id);
				
		//1. request에 담긴 아이디가 null이거나 0이면 시작페이지로 간다.
		if(id==null||id.equals("")){
			return "loginForm.do";
		}	
		
		
		String select_check = request.getParameter("select_check");
		String pd_qty_list = request.getParameter("pd_qty_list");
		String pd_code_list = request.getParameter("pd_code_list");
		Users user = new Users();
		List<Product> product_list = new ArrayList<Product>();
		
		System.out.println("select_check : "+select_check);
		int result=0;
		
		
		try {
			UsersDao ud = UsersDao.getInstance();
			user = ud.select(id);
			Product product= new Product();
			
			
			//선택상품버튼클릭시
			if(select_check==null) {
				
				
				// 선택한 상품이 없을때
				if(pd_code_list.equals("pd_code_list") || pd_code_list==null || pd_code_list.equals("")) {
					result=-1;
					request.setAttribute("result", result);
					return "ordersList.jsp";
				}
				
				
				String[] pd_code_array = pd_code_list.split(" ");
				for(int i=0; i<pd_code_array.length; i++) {
					System.out.println(pd_code_array[i]);
				}
				
				
				
				System.out.println("pd_qty_list : "+pd_qty_list);
				String[] pd_qty_array = pd_qty_list.split(" ");
				for(int i=0; i<pd_qty_array.length; i++) {
					System.out.println(pd_qty_array[i]);
				}
				
				
				
				
				ProductDao pd=ProductDao.getInstance();

				// OrderList 조회하여 가져옴 
				
				
				for(int i=0; i<pd_code_array.length; i++) {
					product=pd.select(pd_code_array[i]);
					product.setQty(Integer.parseInt(pd_qty_array[i]));
					product_list.add(i, product);
				}
				
				for(int i=0; i<pd_qty_array.length; i++) {
					System.out.println(product_list.get(i).getPd_name());
				}
			}else if (select_check.equals("SC")) {	//전체 선택 클릭시
				
				pd_qty_list = request.getParameter("pd_qty_listAll");
				
				pd_code_list="";
				CartDetailDao cd=CartDetailDao.getInstance();
				product_list = cd.getCartList(id);
				
				if(pd_qty_list.equals("pd_qty_listAll") || pd_qty_list==null || pd_qty_list.equals("")) {
					pd_qty_list="";
					for(int i=0; i<product_list.size(); i++ ) {
						pd_code_list+=product_list.get(i).getPd_code()+" ";
						pd_qty_list+=product_list.get(i).getQty()+" ";
						System.out.println("pd_code_list : "+pd_code_list);
						System.out.println("pd_qty_list : "+pd_qty_list);
					}
					
					

					
				}else {
					String[] pd_qty_array = pd_qty_list.split(" ");
					
					for(int i=0; i<product_list.size(); i++ ) {
						pd_code_list+=product_list.get(i).getPd_code()+" ";
						product=product_list.get(i);
						product.setQty(Integer.parseInt(pd_qty_array[i]));
						product_list.set(i, product);
						
					}
				}
				
			}else {
				result=-2;
				request.setAttribute("result", result);
				return "ordersList.jsp";
			}


			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			result=-2;
		}
		
		request.setAttribute("result", result);
		request.setAttribute("user", user);
		request.setAttribute("product_list", product_list);
		request.setAttribute("pd_code_list", pd_code_list);
		request.setAttribute("pd_qty_list", pd_qty_list);
	
		
		
		return "ordersList.jsp";
	}

}
