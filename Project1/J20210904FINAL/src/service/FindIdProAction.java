package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UsersDao;

public class FindIdProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String returnStr = "";
		
		try {
			request.setCharacterEncoding("utf-8");
			System.out.println("FindIdProAction Start..");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			UsersDao ud = UsersDao.getInstance();
			System.out.println("FindIdProAction name->"+name);
			System.out.println("FindIdProAction email->"+email);
			
			String id = ud.idSelect(name, email);
			System.out.println("FindIdProAction id->"+id);
		
			request.setAttribute("name", name);
			request.setAttribute("id", id);
			
			if (id==null)  {
				System.out.println("FindIdProAction 아이디가 없습니다");
				returnStr = "findIdResult2.jsp";
			}  else {
				System.out.println("FindIdProAction 아이디를 찾았습니다");
				returnStr = "findIdResult.jsp";
			}
		} catch (Exception e) {
			System.out.println("FindIdProAction->"+e.getMessage());
		}
		
		System.out.println("FindIdProAction End ..");
	
		return returnStr;
	}

}
