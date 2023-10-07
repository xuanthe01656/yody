package controller.pub;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.Menu;
import model.bean.ProductAdmin;
import model.dao.MenuDAO;
import model.dao.PublicDAO;

public class ProductByCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProductByCatController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PublicDAO objPDAO = new PublicDAO();
		MenuDAO objMDAO = new MenuDAO();
		int index = 0;
		if(request.getParameter("index")==null) {
			index=1;
		}else {
			index= Integer.parseInt(request.getParameter("index"));
		}
		if(request.getParameter("id")!=null&&request.getParameter("cat")==null) {
			int id = Integer.parseInt(request.getParameter("id"));
			Menu menuByID = objMDAO.getItemById(id);
			ArrayList<ProductAdmin> listPByCat = objPDAO.getProDetailByCat(id, index);
			int total = objPDAO.getNumProByCat(id);
			int numPage = objPDAO.getNumberPageByCat(id);
			request.setAttribute("total", total);
			request.setAttribute("listPro", listPByCat);
			request.setAttribute("index", index);
			request.setAttribute("numPage", numPage);
			request.setAttribute("id", id);
			request.setAttribute("menu", menuByID);
			RequestDispatcher rd = request.getRequestDispatcher("/public/product_by_cat_page.jsp");
			rd.forward(request, response);
			}else {
				if(request.getParameter("id")==null&&request.getParameter("cat")!=null) {
					String cat = request.getParameter("cat");
					Menu menuByCat = objMDAO.getItemByCat(cat);
					int id= menuByCat.getId();
					ArrayList<ProductAdmin> listPByCat = objPDAO.getProDetailByCat(id, index);
					int total = objPDAO.getNumProByCat(id);
					int numPage = objPDAO.getNumberPageByCat(id);
					request.setAttribute("total", total);
					request.setAttribute("listPro", listPByCat);
					request.setAttribute("index", index);
					request.setAttribute("numPage", numPage);
					request.setAttribute("id", id);
					request.setAttribute("menu", menuByCat);
					RequestDispatcher rd = request.getRequestDispatcher("/public/product_by_cat_page.jsp");
					rd.forward(request, response);
				}
			}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
