package controller.ad;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import model.bean.Suppliers;
import model.dao.SuppliersDAO;

import java.io.IOException;
import java.util.ArrayList;

public class ListSuppliersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListSuppliersController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		SuppliersDAO objSupDAO = new SuppliersDAO();
		ArrayList<Suppliers> listSup = objSupDAO.getSup();
		request.setAttribute("list-suppliers", listSup);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/backend/page-list-suppliers.jsp");
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
