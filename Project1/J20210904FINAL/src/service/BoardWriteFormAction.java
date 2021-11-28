package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.BoardDao;
import dao.OrdersDao;
import dao.Product;
import dao.ProductDao;

public class BoardWriteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 같은댓글 댓글 내 level 댓글 내 순서
		try {
			
//			수정:정원교---------------------------------
//			id세션 확인해서 없으면 로그인 페이지로
//			=============================================
			HttpSession session=request.getSession();
			String id=(String)session.getAttribute("id");
			if(id==null || id.equals("")){              
				return "loginForm.jsp";
			}
//			=============================================
			
			int brd_code = 0, ref = 0, ref_level = 0, ref_step = 0;
			String pageNum = request.getParameter("pageNum");
			BoardDao bd = BoardDao.getInstance();
			OrdersDao od = OrdersDao.getInstance();
			if (pageNum == null)
				pageNum = "1";
			// 댓글 처리
			if (request.getParameter("brd_code") != null) {
				brd_code = Integer.parseInt(request.getParameter("brd_code"));
				
				Board board = bd.select(brd_code);
				ProductDao pd=ProductDao.getInstance();
				Product product=new Product();
				product = pd.select(board.getBrd_pd_code());
				// 댓글 관련 필드;
				ref = board.getRef();
				ref_level = board.getRef_level();
				ref_step = board.getRef_step();
				request.setAttribute("pd_name", product.getPd_name());
				request.setAttribute("pd_code", product.getPd_code());
			}
			List<Product> myOrderList = od.myOrderList(id);
			
			request.setAttribute("myOrderList", myOrderList);
			request.setAttribute("brd_code", brd_code);
			request.setAttribute("ref", ref);
			request.setAttribute("ref_level", ref_level);
			request.setAttribute("ref_step", ref_step);
			request.setAttribute("pageNum", pageNum);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "boardWriteForm.jsp";
	}

}
