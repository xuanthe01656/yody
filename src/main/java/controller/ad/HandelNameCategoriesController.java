package controller.ad;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import model.dao.MenuDAO;

import java.io.IOException;

public class HandelNameCategoriesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HandelNameCategoriesController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		int id = Integer.parseInt(request.getParameter("aid"));
		String name = request.getParameter("aname");
		MenuDAO objMDAO = new MenuDAO();
		int result = objMDAO.editNameCategory(id, name);
		if(result>0) {
			response.getWriter().print("<p style=\"color: red; background-color: yellow\">Cập nhật tên danh mục thành công</p>");
		}else {
			response.getWriter().print("<p style=\"color: red; background-color: yellow\">Cập nhật tên danh mục không thành công</p>");
		}
	}

}
