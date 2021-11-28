package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsersDao;

public class LoginProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		try {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
		
			UsersDao ud = UsersDao.getInstance();
			int result = ud.check(id,pw);
			
			if(result == 1) {
				HttpSession session = request.getSession();
				session.setAttribute("id", id);
				//			수정:정원교---------------------------------
				// 코드추가
				// 로그인한 계정이 관리자 계정인지 일반회원인지 확인 
				// 관리자 계정이면 세션에 adck값을 1로 아니면 0으로
				String adck=null;
				adck=ud.adminck(id);
				session.setAttribute("adck", adck);
//				----------------------------------------------
				System.out.println("LoginProAction id : "+id);
			}

			request.setAttribute("result", result);
			

		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
	
		
		}
		return "loginPro.jsp";
	}
}

