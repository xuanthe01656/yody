package controller.ad;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import library.DefineLb;
import model.bean.Product;
import model.dao.ProductDao;

public class ListProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListProductController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		ProductDao objPrDAO = new ProductDao();
		int index = 0;
		if(request.getParameter("index")==null) {
			index=1;
		}else {
			index= Integer.parseInt(request.getParameter("index"));
		}
		int dfNum = DefineLb.NUMBER_PER_PAGE;
		ArrayList<Product> listPro = objPrDAO.getPagingAd(index);
		request.setAttribute("listPro", listPro);
		request.setAttribute("index", index);
		request.setAttribute("Dfnum", dfNum);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/backend/page-list-product.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		doGet(request, response);
	}

}
