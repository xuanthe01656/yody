package controller.ad;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import model.bean.User;
import model.dao.UserDAO;

import java.io.IOException;
import java.util.ArrayList;


public class ListCustomersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ListCustomersController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		int index = 0;
		if(request.getParameter("index")!=null) {
			index = Integer.parseInt(request.getParameter("index"));
		}else {
			index=1;
		}
		request.setAttribute("index", index);
		UserDAO objUDAO = new UserDAO();
		ArrayList<User> listUs = objUDAO.getPaging(index);
		request.setAttribute("listUs", listUs);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/backend/page-list-customers.jsp");
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
