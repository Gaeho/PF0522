package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.BoardDao;
import dao.Product;
import dao.ProductDao;

public class BoardUpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BoardDao bd=BoardDao.getInstance();
		Product product=new Product();
		int brd_code=Integer.parseInt(request.getParameter("brd_code"));
		String pageNum=request.getParameter("pageNum");
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String adck=(String)session.getAttribute("adck");
		
		//id 세션없으면 로그인으로
		if(id==null || id.equals("")){              
			return "loginForm.jsp";
		}
		
		
		int bd_idck=0;
		
		Board board=null;
		try {
			
			board = bd.select(brd_code);
			ProductDao pd=ProductDao.getInstance();
			product = pd.select(board.getBrd_pd_code());
			
			if(board.getUser_id().equals(id) || adck=="1") {
				bd_idck=1;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		request.setAttribute("brd_code", brd_code);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("board", board);
		request.setAttribute("bd_idck", bd_idck);
		request.setAttribute("pd_name", product.getPd_name());
		return "boardUpdateForm.jsp";
	}

}
