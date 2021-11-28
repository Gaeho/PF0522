package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.BoardDao;
import dao.Product;
import dao.ProductDao;

public class MyContentAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		//세션확인 로그인 안되어 있으면 이후 과정 진행 안되도록!
		
		HttpSession session = request.getSession();
		String id =(String)session.getAttribute("id");
				
		System.out.println("세션으로 넘어온 id->"+id);
				
		//1. request에 담긴 아이디가 null이거나 0이면 시작페이지로 간다.
		if(id==null||id.equals("")){
			return "login.do";
		}
			
				
				
				

		BoardDao bd = BoardDao.getInstance();
		//dao 인스턴스를 생성함

		try {
			String pageNum = request.getParameter("pageNum");
			int code = Integer.parseInt(request.getParameter("code"));
			System.out.println("code안 내용물??->"+code);
			Board board = bd.select(code);
			
			ProductDao pd=ProductDao.getInstance();
			Product product=new Product();
			product = pd.select(board.getBrd_pd_code());
			System.out.println("board안 내용물?->"+board.getBrd_code());
			
			board.setBrd_context(board.getBrd_context().replace("\n", "<br>"));
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("board", board);
			request.setAttribute("pd_name", product.getPd_name());
					
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return "myContent.jsp";
	}

}
