package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDetailDao;
import dao.Product;

public class CartFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//세션확인 로그인 안되어 있으면 이후 과정 진행 안되도록!
		
		HttpSession session = request.getSession();
		String id =(String)session.getAttribute("id");
		
		System.out.println("세션으로 넘어온 id->"+id);
		
		//1. request에 담긴 아이디가 null이거나 0이면 시작페이지로 간다.
		if(id==null||id.equals("")){
			return "loginForm.do";
		}

		
		try {
			CartDetailDao cd = CartDetailDao.getInstance();
//			String cartCode = cd.getCartCode(id);
			
			List<Product> cartList = new ArrayList<Product>();
			cartList=cd.getCartList(id);
			System.out.println("cartListIsEmpty ->"+cartList.isEmpty());
			
			request.setAttribute("cartList", cartList);
			System.out.println("cartList.size() ; "+cartList.size());
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "cartForm.jsp";
	}

}
