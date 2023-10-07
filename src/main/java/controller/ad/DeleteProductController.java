package controller.ad;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import model.bean.Product;
import model.bean.productDetail;
import model.dao.ProductDao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DeleteProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteProductController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		int index = Integer.parseInt(request.getParameter("index"));
		int id = Integer.parseInt(request.getParameter("id"));
		ProductDao objProDAO = new ProductDao();
		productDetail objPro = objProDAO.getObjPro(id);
		String images = objPro.getImages();
		if(images!=null) {
			String[] picture = images.split(",");
			String path = request.getServletContext().getRealPath("/templates/admin/assets/images/table/product/");
			int result = objProDAO.delProductById(id);
			if(result>0) {
				for (String string : picture) {
					File image = new File(path+string);
						if(image.exists()) {
							image.delete();
						}
					}
				response.sendRedirect(request.getContextPath()+"/admin/product-detail?product_code="+objPro.getProductSKU()+"&index="+index+"&msg=3");
				}else {
					response.sendRedirect(request.getContextPath()+"/admin/product-detail?product_code="+objPro.getProductSKU()+"&index="+index+"&msg=4");
				}		
		
		}else {
			int result = objProDAO.delProductById(id);
			if(result>0) {
					response.sendRedirect(request.getContextPath()+"/admin/product-detail?product_code="+objPro.getProductSKU()+"&index="+index+"&msg=3");
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/product-detail?product_code="+objPro.getProductSKU()+"&index="+index+"&msg=4");
			}
		}
		
	}

}
