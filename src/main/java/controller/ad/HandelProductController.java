package controller.ad;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.Menu;
import model.bean.productDetail;
import model.dao.MenuDAO;
import model.dao.ProductDao;

import java.io.IOException;
import java.util.ArrayList;

public class HandelProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HandelProductController() {
        super();
    }
    public boolean existName(ArrayList<productDetail> listPro, String name) {
    	for (productDetail productDetail : listPro) {
			if(productDetail.getName().equals(name)) {
				return true;
			}
		}
    	return false;
    }
    public boolean existSKU(ArrayList<productDetail> listPro, String sku) {
    	for (productDetail productDetail : listPro) {
			if(productDetail.getProductSKU().equals(sku)) {
				return true;
			}
		}
    	return false;
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HandelProductController objHD = new HandelProductController();
		if(request.getParameter("asuk")!=null&&request.getParameter("aname")!=null) {
			String id_product = request.getParameter("asuk");
			String name = request.getParameter("aname");
			ProductDao objPrDAO = new ProductDao();
			ArrayList<productDetail> listProD = objPrDAO.getItem();
			if(listProD.size()>0) {
				if(objHD.existName(listProD, name)==false&&objHD.existSKU(listProD, id_product)) {
					response.getWriter().print("<p style=\"color: red; background-color: yellow\">--SUK đã được dùng cho sản phẩm khác rồi. Hãy sử dụng một SUK khác.--</p>");
				}else {
					if(objHD.existName(listProD, name)&&objHD.existSKU(listProD, id_product)==false) {
						response.getWriter().print("<p style=\"color: red; background-color: yellow\">--Tên sản phẩm đã được dùng rồi. Hãy sử dụng một tên sản phẩm khác.--</p>");
					}
				}	
			}
		}
		
	}

}
