package controller.ad;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import model.dao.AdminDAO;

import java.io.IOException;

public class DeleteUsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteUsersController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/admin/index");
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		AdminDAO objAdDAO = new AdminDAO();
		int result = objAdDAO.deleteUser(id);
		
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/admin/list-users?msg=3");
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/list-users?msg=4");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/admin/index");
			return;
		}
		doGet(request, response);
	}

}
