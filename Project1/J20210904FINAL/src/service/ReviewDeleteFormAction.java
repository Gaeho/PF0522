package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Review;
import dao.ReviewDao;

public class ReviewDeleteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//String id="test143";
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String adck=(String)session.getAttribute("adck");
		
		//id 세션없으면 로그인으로
		if(id==null || id.equals("")){              
			return "loginForm.jsp";
		}
		
		int rd_idck=0;
		
		
		try {
			String rev_code = request.getParameter("rev_code");
			String pageNum = request.getParameter("pageNum");
			String pd_code = request.getParameter("pd_code");
			
			ReviewDao rd = ReviewDao.getInstance();
			Review review = rd.select(rev_code);
			if(review.getUser_id().equals(id) || adck=="1") {
				rd_idck=1;
			}
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("rev_code", rev_code);
			request.setAttribute("pd_code", pd_code);
			request.setAttribute("rd_idck", rd_idck);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "reviewDeleteForm.jsp";
	}

}
