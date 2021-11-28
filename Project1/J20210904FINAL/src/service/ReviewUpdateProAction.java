package service;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Review;
import dao.ReviewDao;

public class ReviewUpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ReviewDao rd=ReviewDao.getInstance();
		Review review=new Review();
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		String pd_code=request.getParameter("pd_code");
		String id=(String) session.getAttribute("id");
		String adck=(String)session.getAttribute("adck");
		String pageNum=request.getParameter("pageNum");
		String rev_code=request.getParameter("rev_code");
		
		if(id==null || id.equals("")) {
			return "loginForm.jsp";
		}
		
		int result;
		try {
			review=rd.select(rev_code);
			if(review.getUser_id().equals(id) || adck=="1") {
				review.setRev_code(Integer.parseInt(request.getParameter("rev_code")));
				review.setUser_id(request.getParameter("user_id"));
				review.setRev_avg(Integer.parseInt(request.getParameter("rev_avg")));
				review.setRev_context(request.getParameter("rev_context"));
//				review.setRev_reg_date(request.getParameter("rev_reg_date"));
				
				result=rd.update(review);
			}else {
				result=-1;
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("result", result);
			}
			
			request.setAttribute("rev_code", review.getRev_code());
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("result", result);
			request.setAttribute("pd_code", pd_code);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return "reviewUpdatePro.jsp";
	}

}
