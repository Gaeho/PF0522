package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDetailDao;

public class CartDeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id =(String)session.getAttribute("id");
		if(id==null||id.equals("")){
			return "loginForm.do";
		}
		
		String pd_code=request.getParameter("pd_code");
		CartDetailDao cdd = CartDetailDao.getInstance();
		int result=0;
		
		try {
			result = cdd.delete(id,pd_code);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		request.setAttribute("result", result);
		
		return "cartDeletePro.jsp";
	}

}
