package controller.ad;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import library.ExportExcel;
import model.bean.OrderDetail;
import model.bean.Orders;
import model.dao.OrdersDAO;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

public class ListOrderContronller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListOrderContronller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		int index = 1;
		if(request.getParameter("index")!=null) {
			index = Integer.parseInt(request.getParameter("index"));
		}else {
			index = 1;
		}
		OrdersDAO objOrderDAO = new OrdersDAO();
		ArrayList<Orders> listOrder = objOrderDAO.getPaging(index);
		
		
		request.setAttribute("listOrder", listOrder);
		request.setAttribute("index", index);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/backend/page-list-order.jsp");
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
