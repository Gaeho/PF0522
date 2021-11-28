package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
//		수정:정원교---------------------------------
		// 코드추가
		// id 세션이 있는지 확인해서 있으면 메인화면으로(로그인 된 사람이 다시 로그인페이지로 가지 않기하기위해)
		HttpSession session=request.getSession();
		String id=(String) session.getAttribute("id");
		if(id==null || id.equals("")){  
			return "loginForm.jsp";
		}
		return "main.jsp";
//		------------------------------------------
	}

}
