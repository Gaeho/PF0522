package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Product;
import dao.ProductDao;
import dao.ProductImage;
import dao.ProductImageDao;

public class PDetailFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
		
		String pdCode = request.getParameter("pdCode");	
		
		ProductDao pdao = ProductDao.getInstance();
		Product product = pdao.select(pdCode);
		ProductImageDao imgdao = ProductImageDao.getInstance();
		List<ProductImage> imglist = imgdao.select(pdCode);
		pdao.pd_count(pdCode);
		
		request.setAttribute("product", product);
		request.setAttribute("imglist", imglist);
		
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		return "pdetailForm.jsp";
	}

}
