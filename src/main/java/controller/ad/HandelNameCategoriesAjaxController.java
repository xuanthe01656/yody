package controller.ad;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import model.bean.Menu;
import model.dao.MenuDAO;

import java.io.IOException;
import java.util.ArrayList;

public class HandelNameCategoriesAjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HandelNameCategoriesAjaxController() {
        super();
    }
    public boolean existsName(ArrayList<Menu> listMenu, String name) {
    	for (Menu menu : listMenu) {
			if(menu.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
    	return false;
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
		
		String name = request.getParameter("aname");
		int parent_id = Integer.parseInt(request.getParameter("aparent_id")); 
		MenuDAO objMDAO = new MenuDAO();
		ArrayList<Menu> listMenu = objMDAO.getItemMByParent_id(parent_id);
		if(name.length()>=2 && existsName(listMenu, name)==true) {
			response.getWriter().print("<p style=\"color: red; background-color: yellow\">--Tên danh mục đã tồn tại. Hãy sử dụng một tên khác.--</p>");
		}else {
			if(name.length()>=2 && existsName(listMenu, name)==false) {
			response.getWriter().print("<p style=\"color: red; background-color: green\">--Tên danh mục được phép dùng--</p>");	
			}
		}
	}

}
