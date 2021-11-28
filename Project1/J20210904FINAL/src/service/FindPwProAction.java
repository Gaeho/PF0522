package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UsersDao;

public class FindPwProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		String returnStr = "";
		
		try {
			request.setCharacterEncoding("utf-8");
			System.out.println("FindPwProAction Start..");
			String name = request.getParameter("name");
			String id = request.getParameter("id");
			String email = request.getParameter("email");
			
			UsersDao ud = UsersDao.getInstance();
			System.out.println("FindPwProAction name->"+name);
			System.out.println("FindPwProAction id->"+id);
			System.out.println("FindPwProAction email->"+email);
			
			String pw = ud.pwselect(name, id, email);
			System.out.println("FindPwProAction pw->"+pw);
			
			request.setAttribute("name", name);
			request.setAttribute("id", id);
			request.setAttribute("pw", pw);
			
//			if(pw==null) {
//				System.out.println("FindIdProAction 찾는 비밀번호가 없습니다.");
//				returnStr = "findPwForm.jsp";
//			}else {
//				System.out.println("FindIdProAction 비밀번호를 찾았습니다.");
//				returnStr = "findPwResult.jsp";
//			}
			
		} catch (Exception e) {
			System.out.println("FindPwProAction->"+e.getMessage());
		}
		System.out.println("FindIdProAction End ..");
		
		return "findPwResult.jsp";
	}

}
