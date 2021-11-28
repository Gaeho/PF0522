package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ProductDao;
import dao.ProductImageDao;



public class MainAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String id=request.getParameter("id");
//		HttpSession session=request.getSession();
//		if(id==null||id.equals("")){
//			session.setAttribute("id", null);
//		}else{
//			session.setAttribute("id", id);
//		}
		
		ProductDao pd=ProductDao.getInstance();
		ProductImageDao pdi=ProductImageDao.getInstance();
		HashMap<String, String> new_pd_code=new HashMap<String, String>();
		HashMap<String, String> new_pd_img=new HashMap<String, String>();
		HashMap<String, String> best_pd_code=new HashMap<String, String>();
		HashMap<String, String> best_pd_img=new HashMap<String, String>();
		
//		String new_pd_code=null;
		
		try {
			new_pd_code=pd.new_select();
			best_pd_code=pd.best_select(new_pd_code);
			for(String key:new_pd_code.keySet()) {
				System.out.println("key : "+key +"/ value : "+new_pd_code.get(key));
				System.out.println("key : "+key +"/ value : "+best_pd_code.get(key));
				new_pd_img.put(key, pdi.selectDesc(new_pd_code.get(key)));
				best_pd_img.put(key, pdi.selectDesc(best_pd_code.get(key)));
			}
			
			for(String key:new_pd_img.keySet()) {
			System.out.println("key : "+key +"/ value : "+new_pd_img.get(key));
			}
			
			
			request.setAttribute("new_pd_img", new_pd_img);
			request.setAttribute("new_pd_code", new_pd_code);
			request.setAttribute("best_pd_img", best_pd_img);
			request.setAttribute("best_pd_code", best_pd_code);
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		

		

		
		// id 세션이 있는지 확인
//		HttpSession session=request.getSession();
//		String id=(String) session.getAttribute("id");
		
  

        	
		

		
		
		System.out.println("이거 mainaction.java");
		return "main.jsp";
	}

}
