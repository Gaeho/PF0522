package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Users;
import dao.UsersDao;

public class UpdateProAction implements CommandProcess {

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
			request.setCharacterEncoding("utf-8"); 
			//리퀘스트 깨지지 않도록 인코딩
	
			Users users = new Users();
			//dto객체 준비
			users.setUser_id(id);
			users.setUser_pw(request.getParameter("pw"));
			users.setUser_tel(request.getParameter("tel"));
			users.setUser_email(request.getParameter("email"));
			users.setUser_addr(request.getParameter("addr"));
			//업데이트할 정보를 모두 담았다.
			//실수한 사항: UpdateForm.jsp에서 객체 담을 때의 name과 
			//여기에서 넣는 파라미터의 이름을 다르게 하여 users에 자료가 제대로 담기지 않음
			//뷰단에서 다시 Pro액션 넘길 때 name 변수명을 제대로 확인할 것
			
			
			//전번 이메일 주소
			UsersDao bd = UsersDao.getInstance();
			//dao의 메서드를 사용하기 위해서 객체 선언 후에 인스턴스를 생성하여 저장함 
			int UdResult = bd.idUpdatePro(users); 
			//세션에서 가져온 아이디를 파라미터로 조회한 정보를 dto에 담는다. 
		
			request.setAttribute("UdResult", UdResult);
			//가져온 정보를 request에 담아 다음 페이지로 넘어갈 준비 완료
			System.out.println("유저정보변경db에서 잘 받아옴?숫자?->"+UdResult);
			
		}catch(Exception e) {	
			System.out.println(e.getMessage());	
		}
		return "updatePro.jsp";
	}

}
