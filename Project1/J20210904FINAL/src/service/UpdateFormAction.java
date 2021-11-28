package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Users;
import dao.UsersDao;

public class UpdateFormAction implements CommandProcess {

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
		
		
		try {
			
			UsersDao bd = UsersDao.getInstance();
			//dao의 메서드를 사용하기 위해서 객체 선언 후에 인스턴스를 생성하여 저장함 
			Users idUpdateForm = bd.select(id);
			//세션에서 가져온 아이디를 파라미터로 조회한 정보를 dto에 담는다. 
		
			request.setAttribute("idUpdateForm", idUpdateForm);
			//가져온 정보를 request에 담아 다음 페이지로 넘어갈 준비 완료
			System.out.println("유저정보변경db에서 잘 받아옴?전화번호?->"+idUpdateForm.getUser_tel());
			
		}catch(Exception e) {	
			System.out.println(e.getMessage());	
		}
		
		
		
		return "updateForm.jsp";
	}

}
