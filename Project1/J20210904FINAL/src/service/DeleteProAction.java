package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsersDao;

public class DeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//세션확인 로그인 안되어 있으면 이후 과정 진행 안되도록!
		
		HttpSession session = request.getSession();
		String id =(String)session.getAttribute("id");
				
		System.out.println("세션으로 넘어온 id->"+id);
				
		//1. request에 담긴 아이디가 null이거나 0이면 시작페이지로 간다.
		if(id==null||id.equals("")){
			return "loginForm.jsp";
		}
				
				
		try {
					
			String pw = request.getParameter("pw");
			System.out.println("입력화면에서 받은pw확인->"+pw);
					
			//아이디와 비밀번호를 이용하여 데이터베이스와 통신한다.
			//업데이트문이라 오는 답변은 인트일 것
					
			UsersDao bd = UsersDao.getInstance();
			int result = bd.idDelUpd(id, pw);
					
					
			request.setAttribute("result", result);
			
			System.out.println("result에 담긴것?->"+result);
					
			if(result ==1) {
						
				session = request.getSession();
				session.invalidate();
			};
					
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
				
				
		return "deletePro.jsp";
	}

}
