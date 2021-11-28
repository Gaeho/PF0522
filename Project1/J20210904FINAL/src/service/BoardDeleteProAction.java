package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.BoardDao;

public class BoardDeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BoardDao bd=BoardDao.getInstance();
		int brd_code=Integer.parseInt(request.getParameter("brd_code"));
		String pageNum=request.getParameter("pageNum");
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String adck=(String)session.getAttribute("adck");
		int result=0;
		Board board=null;
		
		//id 세션없으면 로그인으로
		if(id==null || id.equals("")){              
			return "loginForm.jsp";
		}
		
		try {
			board=bd.select(brd_code);
			if(board.getUser_id().equals(id) || adck=="1") {
				result=bd.delete(brd_code);
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("result", result);
			}else {
				result=-1;
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("result", result);
			}
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		//brd_code삭제성공 result=1(물리적으로 table row delete)
		//brd_code없음, 삭제실패 			result=0
		
		
		
		return "boardDeletePro.jsp";
	}

}
