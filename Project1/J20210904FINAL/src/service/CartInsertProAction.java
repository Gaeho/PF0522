package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDetailDao;


public class CartInsertProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		String id = (String)request.getSession().getAttribute("id");
		if(id==null||id.equals("")){
			return "loginForm.do";
		}
		
		try {
			int qty		  =Integer.parseInt(request.getParameter("quantity"));
			String pd_code =request.getParameter("pd_code");
			System.out.println(qty);
			
			/* 지울예정
			 * ProductDao pdao = ProductDao.getInstance(); Product product =
			 * pdao.productSelect(pdCode);
			 */
			
			
			CartDetailDao cdao = CartDetailDao.getInstance();
			
			int result = cdao.cartInsert(id, pd_code,qty);
			
			request.setAttribute("result", result);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		
		return "cartInsertPro.jsp";
	}

}