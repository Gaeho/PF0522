package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Product;
import dao.ProductDao;


public class PListFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 일단 카테고리에 맞는 Product 리스트를 가져와야됨
		ProductDao pdao = ProductDao.getInstance();

		try {
			
			String pageNum = request.getParameter("pageNum");
			String sort = request.getParameter("sort");
			String cateCode =request.getParameter("cateCode");
			String searchContent = request.getParameter("searchContent");
			List<Product> plist = null;
			System.out.println("searchContent ->" + searchContent);
			int totCnt=0;
			

			
			//테스트용 기본 카테고리(상의먼저)
			if(cateCode==null || cateCode==("")) { 
				cateCode="1";
			}
			
			//기본 정렬기준 최신순
			if(sort==null || sort.equals("")) { 
				sort="pd_reg_date"; 
				if(cateCode.equals("hot"))	{
					sort="pd_count desc";
				}

				
			}
			
			if(pageNum==null || pageNum.equals("")) { 
				pageNum="1";
			}
			
			

				System.out.println(sort);
//				int cate_code = Integer.parseInt(request.getParameter("cate_code"));
				
				//게시판
				int currentPage = Integer.parseInt(pageNum); //현재 페이지
				int pageSize = 8, blockSize = 5;//페이지당 게시글갯수, 보여질 페이지갯수
				int startRow = (currentPage - 1) * pageSize + 1; // 시작 게시글 번호
				int endRow = startRow + pageSize - 1;  // 끝 게시글 번호
//				int startNum = totCnt - startRow + 1; //게시글번호
				if(searchContent == null || searchContent == "") {
					totCnt = pdao.getTotalCnt(cateCode);
					plist = pdao.list(startRow, endRow, sort, cateCode);
					System.out.println("searchContent null");
				}
				if(searchContent != null && searchContent != ""){
					totCnt = pdao.searchTotalCnt(searchContent);
					plist = pdao.searchProduct(startRow, endRow, sort, searchContent);
				}
				
				int pageCnt = (int)Math.ceil((double)totCnt/pageSize); // ceil 올림
				int startPage = (int)(currentPage-1)/blockSize*blockSize+1; // 보여질 시작 페이지번호
				int endPage = startPage + blockSize -1; //보여질 마지막 페이지번호
				if (endPage > pageCnt) endPage = pageCnt;
				
				request.setAttribute("plist", plist);
				request.setAttribute("totCnt", totCnt);
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("currentPage", currentPage);
//				request.setAttribute("startNum", startNum);
				request.setAttribute("blockSize", blockSize);
				request.setAttribute("pageCnt", pageCnt);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				request.setAttribute("cateCode", cateCode);
				request.setAttribute("searchContent", searchContent);
				
				request.setAttribute("sort", sort);
				
		}catch(SQLException e)	{
			System.out.println(e.getMessage());
		}

	return"PListForm.jsp";
	}

}
