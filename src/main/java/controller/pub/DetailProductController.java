package controller.pub;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.ProductAdmin;
import model.bean.ProductAdminDetail;
import model.dao.ProductDao;

public class DetailProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DetailProductController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if("".equals(request.getParameter("sku"))==false) {
		String sku = request.getParameter("sku");
		ProductDao objPDAO = new ProductDao();
		ArrayList<ProductAdminDetail> listPBySKU = objPDAO.getProDetailBySUK(sku);
		int result = objPDAO.updateView(sku);
		if(listPBySKU.size()>0&&result>0) {
			ArrayList<ProductAdminDetail> listProBySKU = objPDAO.getItemBySUK(sku, listPBySKU.get(0).getCategory_menu_detail());
			request.setAttribute("listPro", listPBySKU);
			request.setAttribute("listPAD", listProBySKU);
			RequestDispatcher rd = request.getRequestDispatcher("/public/product_detail_page .jsp");
			rd.forward(request, response);
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
