package controller.ad;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import model.bean.OrderDetail;
import model.bean.Orders;
import model.dao.OrdersDAO;

import java.io.IOException;
import java.util.ArrayList;

public class ListSaleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ListSaleController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		int index = 1;
		if(request.getParameter("index")!=null) {
			index = Integer.parseInt(request.getParameter("index"));
		}else {
			index = 1;
		}
		int status = 5;
		OrdersDAO objOrderDAO = new OrdersDAO();
		ArrayList<OrderDetail> listOrder = objOrderDAO.getListOrderByStatus(status);
		request.setAttribute("listOrder", listOrder);
		request.setAttribute("index", index);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/backend/page-list-sale.jsp");
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
