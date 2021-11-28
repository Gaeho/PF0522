package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Orders;
import dao.OrdersDao;

public class OrderDetailAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//세션확인 로그인 안되어 있으면 이후 과정 진행 안되도록!
		
		HttpSession session = request.getSession();
		String id =(String)session.getAttribute("id");
		
		System.out.println("세션으로 넘어온 id->"+id);
		
		//1. request에 담긴 아이디가 null이거나 0이면 시작페이지로 간다.
		if(id==null||id.equals("")){
			return "loginForm.do";
		}	
		//갖고 와야할 정보? 세션 아이디를 통해서 유저 정보를 모두 끌고온다.->다음페이지에 입력해놈
		
		
		
		//이전페이지에서 넘긴 오더코드를 받는다.
		
		try {
		OrdersDao od = OrdersDao.getInstance();
		String ord_code = request.getParameter("ord_code");
		System.out.println("오더코드 넘어왔나?->"+ord_code);
		
		//ord_code의 주문자와 세션id가 일치하는지 확인
//		==============================================
		Orders order=new Orders();
		int ord_id_check=0;
		order=od.select(ord_code);
		if(order.getUser_id().equals(id)) {
//		==============================================
			ord_id_check=1;
			int totCnt = od.getTotalCntOrdcode(ord_code);
			System.out.println("dao에서 넘어온 totCnt->"+totCnt); //정상적으로 넘어 온 것 확인 됨 dao이상 없음
			//해당 id를 가지고 db와 통신하여 해당 아이디로 작성한 글 개수 가져옴
			
			String pageNum = request.getParameter("pageNum");	
			//페이지넘버 = 넘겨받은 페이지넘버 mypageForm에서 pageNum정보를 따로 저장한 것이 없어서 해당 자료가 없다.
			if (pageNum==null || pageNum.equals("")) {	
				pageNum = "1";	//넘겨받은 페이지 정보가 널이거나 비어있다면? 페이지넘버는 1이된다.
				//->마이페이지에서 넘어왔다면 1이 셋팅되고 이후 페이지에서 뒤로 넘어 왔다면 페이지번호를 가져왔을테니 여기에서 그 번호를 바탕으로 목록을 보여준다.  
			}
			int currentPage = Integer.parseInt(pageNum);	//현재페이지에 페이지번호를 인트로 파싱하여 저장한다.(현재페이지=페이지번호)
			
			
			int pageSize  = 10, blockSize = 10;
			//페이지 표시하는 글의 개수=10, 페이지의 번호 = 10
			
			int startRow = (currentPage - 1) * pageSize + 1;   
			// 시작줄 = 현재페이지에서 1을 뺀 뒤에 페이지사이즈를 곱하고 1을 더함 
			// 현재 페이지가 1인 경우 (1-1)*10+1=1 시작하는 줄 1
			// 현재 페이지가 2인 경우 (2-1)*10+1=1 시작하는 줄 11
			// 현재 페이지가 3인 경우 (3-1)*10+1=1 시작하는 줄 21
			
			int endRow   = startRow + pageSize - 1;     
			// 끝 줄 = 시작줄번호 + 페이지에 총 표시할 글 개수 -1
			// 시작줄이 1번인경우 -> 1+10-1=10 
			// 시작줄이 1이면 그 페이지의 마지막 줄은 10번 글이 온다/시작줄이 11이면 마지막 줄은 20번 글이 온다.
			
			int startNum = totCnt - startRow + 1; 
			//시작번호 = 총 글의 개수 - 시작줄번호 + 1
			// 5-시작줄 번호1+1 = 5 -> 5번부터 번호가 시작된다. startNum은 게시글 앞에 붙는 글의 번호이다 
			//admin이라는 아이디의 경우에는 글의 개수가 총 5개이므로 제일 끝 숫자 부터 표시 5->4->3->2->1
			
			
			
			
			
			
			List<Orders> list = od.OrderDt(ord_code);
			
			request.setAttribute("list", list);
			request.setAttribute("startNum", startNum);
			request.setAttribute("totCnt", totCnt);
		}else {
			ord_id_check=0;
		}
		request.setAttribute("ord_id_check", ord_id_check);

		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return "orderDetail.jsp";
	}

}
