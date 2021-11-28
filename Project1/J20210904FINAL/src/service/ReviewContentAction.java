package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Review;
import dao.ReviewDao;

public class ReviewContentAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ReviewDao rd=ReviewDao.getInstance();
		Review review=new Review();
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String adck=(String)session.getAttribute("adck"); //이게 뭐지....
		int rd_idck=0;
		System.out.println("adck > "+adck);
		String rev_code = request.getParameter("rev_code");
		String pd_code = request.getParameter("pd_code");
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		
		
		try {
			review=rd.select(rev_code);
			if(review.getUser_id().equals(id) || adck=="1") {
				rd_idck=1;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
		request.setAttribute("rev_code", rev_code);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("review", review);
		request.setAttribute("rd_idck", rd_idck);
		request.setAttribute("pd_code", pd_code);
		
		return "reviewContent.jsp";
	}

}
