package controller.ad;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import model.bean.ProductAdmin;
import model.bean.ProductAdminDetail;
import model.dao.ProductDao;

public class ListProductDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListProductDetailController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		if("".equals(request.getParameter("product_code"))==false) {
			String productSKU = request.getParameter("product_code");
			int index =Integer.parseInt(request.getParameter("index"));
			ProductDao objProDAO = new ProductDao();
			ArrayList<ProductAdminDetail> listPro = objProDAO.getProDetailBySUK(productSKU);
			request.setAttribute("listPr", listPro);
			request.setAttribute("index", index);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/backend/page-list-product_detail.jsp");
			rd.forward(request, response);
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		doGet(request, response);
		
	}

}
