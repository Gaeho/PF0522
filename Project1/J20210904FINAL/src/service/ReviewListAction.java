package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Review;
import dao.ReviewDao;

public class ReviewListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ReviewDao rd = ReviewDao.getInstance();
		
		try {
			
			String pageNum = request.getParameter("pageNum");
			String pd_code = request.getParameter("pd_code");
			int totCnt = rd.getTotalCnt(pd_code);
			System.out.println("totCnt ->"+totCnt);
			
			if(pageNum == null || pageNum.equals("")){
				pageNum = "1";
			}
			
			int currentPage = Integer.parseInt(pageNum);
			int pageSize = 5, blockSize = 5;
			int startRow = (currentPage -1) * pageSize +1;
			int endRow = startRow + pageSize -1;
			int startNum = totCnt - startRow + 1; 
			
			List<Review> list = rd.list(startRow, endRow, pd_code);
			int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
			int startPage = (int)(currentPage-1)/blockSize * blockSize +1;
			int endPage = startPage + blockSize -1;
			if(endPage > pageCnt) endPage = pageCnt;
		
			
			request.setAttribute("totCnt", totCnt);
			System.out.println("totCnt ->"+totCnt);
	        request.setAttribute("pageNum", pageNum);
	        request.setAttribute("currentPage", currentPage);
	        request.setAttribute("startNum", startNum);
	        request.setAttribute("list", list);
	        request.setAttribute("blockSize", blockSize);
	        request.setAttribute("pageCnt", pageCnt);
	        request.setAttribute("startPage", startPage);
	        request.setAttribute("endPage", endPage);
			request.setAttribute("pd_code", pd_code);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
		return "reviewList.jsp";
	}

}
