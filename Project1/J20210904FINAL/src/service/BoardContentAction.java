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

public class BoardContentAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BoardDao bd=BoardDao.getInstance();
		Board board=new Board();
		Board board2=new Board();
		Product product=new Product();
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String adck=(String)session.getAttribute("adck");
		int bd_idck=0;
		System.out.println("adck > "+adck);
		int brd_code=Integer.parseInt(request.getParameter("brd_code"));
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		String searchContent = request.getParameter("searchContent");
		
		try {
			board=bd.select(brd_code);
			board2=bd.select(board.getRef());
			
			ProductDao pd=ProductDao.getInstance();
			System.out.println("test1 : "+product.getPd_name());
			product = pd.select(board.getBrd_pd_code());
			
			System.out.println("test3 : "+product.getPd_name());
			if(board.getUser_id().equals(id) || adck=="1" || board2.getUser_id().equals(id)) {
				bd_idck=1;
				System.out.println("test2");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
//		=======================================================
		

		
//		=======================================================
		board.setBrd_context(board.getBrd_context().replace("\n", "<br>"));
		
		request.setAttribute("brd_code", brd_code);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("board", board);
		request.setAttribute("bd_idck", bd_idck);
		request.setAttribute("searchContent", searchContent);
		request.setAttribute("pd_name", product.getPd_name());
		System.out.println("여기 BoardContentAction.java");
		return "boardContent.jsp";
	}

}
