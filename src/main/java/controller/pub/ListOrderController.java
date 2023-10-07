package controller.pub;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.OrderDetail;
import model.dao.OrdersDAO;

import java.io.IOException;
import java.util.ArrayList;

public class ListOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListOrderController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrdersDAO objOdDAO = new OrdersDAO();
		String code_order = request.getParameter("order_code");
		ArrayList<OrderDetail> listOrderByOrderCode = objOdDAO.getListOrderByOrderCode(code_order);
		request.setAttribute("listOrderDetail", listOrderByOrderCode);
		RequestDispatcher rd = request.getRequestDispatcher("/public/page-list-order.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
