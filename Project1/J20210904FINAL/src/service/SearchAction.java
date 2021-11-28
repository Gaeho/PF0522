package service;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String searchCate = request.getParameter("searchCate");
		String searchContent = request.getParameter("searchContent");

		System.out.println(searchCate);

		if (searchCate.equals("board")) {
			request.setAttribute("searchContent", searchContent);
			return "boardList.do";
		}

		if (searchCate.equals("product")) {
			String cateCode = "search";
			request.setAttribute("searchContent", searchContent);
			request.setAttribute("cateCode", cateCode);
			return "plist.do";
		}

		return "main.do";

	}

}
