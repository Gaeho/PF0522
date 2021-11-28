package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Users;
import dao.UsersDao;

public class JoinProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("utf-8");
			Users users=new Users();
			String grade = "U";
			
			users.setUser_id(request.getParameter("id"));
			users.setUser_pw(request.getParameter("pw"));
			users.setUser_name(request.getParameter("name"));
			users.setUser_email(request.getParameter("email"));
			users.setUser_addr(request.getParameter("addr"));
			users.setUser_tel(request.getParameter("tel"));
			users.setUser_grade(grade);
			
			UsersDao ud=UsersDao.getInstance();
			
			int result=0;
			if(ud.checkDuplicateUserId(request.getParameter("id")) == 0) {
				result=ud.insertOneUserByJoinForm(users);
			}else {
				result=-1;
			}
			request.setAttribute("result", result);
			
//			if(ud.checkDuplicateUserId(request.getParameter("id")) == 0) {
//				int result=ud.insertOneUserByJoinForm(users);
//				request.setAttribute("result", result);
//			}else {
//				return "joinForm.jsp";
//			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "joinPro.jsp";
	}

}
