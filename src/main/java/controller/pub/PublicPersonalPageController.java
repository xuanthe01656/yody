package controller.pub;

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

public class PublicPersonalPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PublicPersonalPageController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLoginPublic(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/public/login");
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		UserDAO objUDAO = new UserDAO();
		User objU = objUDAO.getUserByID(id);
		request.setAttribute("objU", objU);
		RequestDispatcher rd = request.getRequestDispatcher("/public/personal_customer_page.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLoginPublic(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/public/login");
			return;
		}
		doGet(request, response);
	}

}
