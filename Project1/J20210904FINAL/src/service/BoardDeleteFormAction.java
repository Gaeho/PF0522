package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.BoardDao;

public class BoardDeleteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BoardDao bd=BoardDao.getInstance();
		Board board=new Board();
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String adck=(String)session.getAttribute("adck");
		
		//id 세션없으면 로그인으로
		if(id==null || id.equals("")){              
			return "loginForm.jsp";
		}
		
		int bd_idck=0;
		
		int brd_code=Integer.parseInt(request.getParameter("brd_code"));
		String pageNum=request.getParameter("pageNum");
		try {
			board=bd.select(brd_code);
			if(board.getUser_id().equals(id) || adck=="1") {
				bd_idck=1;
			}
			
			request.setAttribute("brd_code", brd_code);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("bd_idck", bd_idck);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return "boardDeleteForm.jsp";
	}

}
