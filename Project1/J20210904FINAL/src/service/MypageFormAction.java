package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.Users;
import dao.UsersDao;

public class MypageFormAction implements CommandProcess {

	
	
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
			UsersDao db = UsersDao.getInstance();
			Users users=db.select(id);
			//넘겨 받은 아이디를 갖고서 db와 통신한다. 
			//해당 아이디의 이름을 받아온다. 
			String name =users.getUser_name();
//			request.setAttribute("id", id);
//			이미 위에 세션에서 String id를 넣었기때문에 필요없습니다
			request.setAttribute("name", name);
			//request객체에 아이디와 이름을 넣는다.
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		
		//마이페이지폼.jsp로 이동한다.
		return "mypageForm.jsp";	
		
	}

}
