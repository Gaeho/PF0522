package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Review;
import dao.ReviewDao;

public class ReviewWriteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		if(id==null || id.equals("")){              
			return "loginForm.jsp";
		}
		
		Review review = new Review();

//			review.setPd_code(request.getParameter("pd_code"));
		review.setUser_id(id);
		review.setRev_avg(Integer.parseInt(request.getParameter("rev_avg")));
		review.setRev_context(request.getParameter("rev_context"));
		review.setPd_code(request.getParameter("pd_code"));
		
		int result = 0;
		ReviewDao rd = ReviewDao.getInstance();
		try {
			result = rd.insert(review);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		request.setAttribute("result", result);
		request.setAttribute("pd_code", review.getPd_code());
		
		return "reviewWritePro.jsp";
	}

}
