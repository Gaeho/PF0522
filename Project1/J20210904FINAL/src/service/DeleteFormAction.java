package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteFormAction implements CommandProcess {

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
	
		//갖고 와야할 정보? 세션 아이디를 통해서 유저 정보를 모두 끌고온다.->다음페이지에 입력해놈
		
		
		
		return "deleteForm.jsp";
	}

}
