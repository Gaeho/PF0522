package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReviewWriteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String pageNum=request.getParameter("pageNum");
		String pd_code= request.getParameter("pd_code");
		
		if(id==null || id.equals("")) {
			return "loginForm.jsp";
		}
		
		request.setAttribute("pd_code", pd_code);
		request.setAttribute("pageNum", pageNum);
		
		return "reviewWriteForm.jsp";
	}

}

