package service;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.BoardDao;

public class BoardWriteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();

		try {
			
//			수정:정원교---------------------------------
//			id세션 확인해서 없으면 로그인 페이지로
//			=============================================
			String id=(String)session.getAttribute("id");
			if(id==null || id.equals("")){              
				return "loginForm.jsp";
			}
//			=============================================
			
			request.setCharacterEncoding("utf-8");
			Board board = new Board();
			BoardDao bdao = BoardDao.getInstance();
			String pageNum = request.getParameter("pageNum");
			
			

			String brd_subject	=request.getParameter("brd_subject");
			String brd_context	=request.getParameter("brd_context");
//			String brdRegDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			int brd_code 		=Integer.parseInt(request.getParameter("brd_code"));
			String brd_pd_code = request.getParameter("pd_code");
			System.out.println("brd_pd_code : "+brd_pd_code);
			
			board.setBrd_code(brd_code);
			board.setBrd_context(brd_context);
			board.setBrd_subject(brd_subject);
			board.setUser_id(id);
			board.setRef(Integer.parseInt(request.getParameter("ref")));
			board.setRef_level(Integer.parseInt(request.getParameter("ref_level")));
			board.setRef_step(Integer.parseInt(request.getParameter("ref_step")));
			board.setBrd_pd_code(brd_pd_code);
			
			int result = bdao.insert(board);
			
			request.setAttribute("result", result);
			request.setAttribute("brd_code", board.getBrd_code());
			request.setAttribute("pageNum", pageNum);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "boardWritePro.jsp";
	}

}
