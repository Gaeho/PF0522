package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.BoardDao;

public class BoardUpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BoardDao bd=BoardDao.getInstance();
		Board board=new Board();
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String adck=(String)session.getAttribute("adck");
		String pageNum=request.getParameter("pageNum");
		
		int brd_code=Integer.parseInt(request.getParameter("brd_code"));

		
		//id 세션없으면 로그인으로
		if(id==null || id.equals("")){              
			return "loginForm.jsp";
		}

		int result;
		try {
			board=bd.select(brd_code);
			if(board.getUser_id().equals(id) || adck=="1") {
				board.setBrd_code(Integer.parseInt(request.getParameter("brd_code")));
				board.setUser_id(request.getParameter("user_id"));
				board.setBrd_subject(request.getParameter("brd_subject"));
				board.setBrd_context(request.getParameter("brd_context"));
				
				result = bd.update(board);
			}else {
				result=-1;
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("result", result);
			}
			

			
			
			request.setAttribute("brd_code", board.getBrd_code());
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("result", result);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return "boardUpdatePro.jsp";
	}

}
