package controller.ad;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import model.bean.OrderDetail;
import model.dao.OrdersDAO;

import java.io.IOException;
import java.util.ArrayList;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

public class ListOrderDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ListOrderDetailController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		OrdersDAO objOdDAO = new OrdersDAO();
		
		String code_order = request.getParameter("order_code");
		int index = Integer.parseInt(request.getParameter("index"));
		ArrayList<OrderDetail> listOrderByOrderCode = objOdDAO.getListOrderByOrderCode(code_order);
		request.setAttribute("listOrderDetail", listOrderByOrderCode);
		request.setAttribute("index", index);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/backend/page-list-order_detail.jsp");
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
