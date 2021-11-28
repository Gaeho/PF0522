package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Users;
import dao.UsersDao;

public class PersonalInfoAction implements CommandProcess {

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
		
		
		/*
		1.세션확인 2. db와 통신하여 개인정보 가져오기 3. 뷰로 보내기
		dao는 userdao를 이용하여 통신한다!
		 */
		try {
			
			UsersDao bd = UsersDao.getInstance();
			//dao 인스턴스를 생성함
			Users users = bd.select(id);
			// 다오에 접근하여 정보를 가져온다. 무엇으로 db검색?->users의 pk이용
			//id는 위의 세션 과정에서 선언하고 받아놓은 것을 이용한다.
			System.out.println("usersInfo.getName->"+users.getUser_name());
//			request.setAttribute("id", id);
			request.setAttribute("pw", users.getUser_pw());
			System.out.println("pw->"+users.getUser_pw());
			request.setAttribute("name", users.getUser_name());
			request.setAttribute("tel", users.getUser_tel());
			request.setAttribute("email", users.getUser_email());
			request.setAttribute("addr", users.getUser_addr());
//			String a="A";
			String usersGrade = users.getUser_grade();
			System.out.println();
			if(usersGrade.equals("U")) {
				usersGrade = "회원";
				System.out.println("usersGrade2->"+usersGrade);
			}else if(usersGrade.equals("A")) {
				usersGrade = "관리자";
			    System.out.println("usersGrade2->"+usersGrade);
			}else if(usersGrade.equals("X")) {
				usersGrade = "탈퇴한 회원";
			}
			
	

			//알파벳에 따라 알아볼 수 있는 문자열로 바꿔서 저장
			
			System.out.println("usersGrade->"+usersGrade);
			request.setAttribute("grade", usersGrade);
			//request에 가져온 정보를 담는다.
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//뷰단으로 이동한다~
		return "personalInfo.jsp";
	}

}
