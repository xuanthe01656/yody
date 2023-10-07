package controller.ad;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import model.bean.Menu;
import model.dao.MenuDAO;
import model.dao.ProductDao;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteCategoriesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteCategoriesController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		MenuDAO objMDAO = new MenuDAO();
		ProductDao objPDAO = new ProductDao();
		Menu objM = objMDAO.getItemById(id);
		if(objM.getParentID()==0) {
			ArrayList<Menu> listobjMById = objMDAO.getItemChilMenu(objM.getId());
			if(listobjMById.size()>0) {
				ArrayList<Menu> listobjMbyId1 = objMDAO.getItemChilMenu(listobjMById.get(0).getId());
				if(listobjMbyId1.size()>0) {
					int result = objMDAO.delMenuById(id, listobjMById.get(0).getParentID(), listobjMbyId1.get(0).getParentID());
					int result2 = objPDAO.delProductByCat(id);
					if(result>0||result2>0) {
						response.sendRedirect(request.getContextPath()+"/admin/list-categories?msg=3");
					}else {
						response.sendRedirect(request.getContextPath()+"/admin/list-categories?msg=4");
					}
				}else {
					
					int result = objMDAO.delMenuById(id, listobjMById.get(0).getParentID());
					int result2 = objPDAO.delProductByCat(id);
					if(result>0||result2>0) {
						response.sendRedirect(request.getContextPath()+"/admin/list-categories?msg=3");
					}else {
						response.sendRedirect(request.getContextPath()+"/admin/list-categories?msg=4");
					}
				}
				
			}else {
				int result = objMDAO.delMenuById(id);
				int result2 = objPDAO.delProductByCat(id);
				if(result>0||result2>0) {
					response.sendRedirect(request.getContextPath()+"/admin/list-categories?msg=3");
				}else {
					response.sendRedirect(request.getContextPath()+"/admin/list-categories?msg=4");
				}
			}
			
		}else {
			ArrayList<Menu> listobjMById = objMDAO.getItemChilMenu(objM.getId());
			if(listobjMById.size()>0) {
				int result = objMDAO.delMenuById(id, listobjMById.get(0).getParentID());
				int result2 = objPDAO.delProductByCat(id);
				if(result>0||result2>0) {
					response.sendRedirect(request.getContextPath()+"/admin/list-categories?msg=3");
				}else {
					response.sendRedirect(request.getContextPath()+"/admin/list-categories?msg=4");
				}
			}else {
				int result = objMDAO.delMenuById(id);
				int result2 = objPDAO.delProductByCat(id);
				if(result>0||result2>0) {
					response.sendRedirect(request.getContextPath()+"/admin/list-categories?msg=3");
				}else {
					response.sendRedirect(request.getContextPath()+"/admin/list-categories?msg=4");
				}
			}	
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		doGet(request, response);
	}

}
