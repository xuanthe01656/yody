package controller.pub;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.DisplayPublic;
import model.bean.ProductAdmin;
import model.dao.PublicDAO;

public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public IndexController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PublicDAO objPlDAO = new PublicDAO();
		int index = 0;
		if(request.getParameter("index")==null) {
			index=1;
		}else {
			index= Integer.parseInt(request.getParameter("index"));
		}
		ArrayList<DisplayPublic> listPl = objPlDAO.getItemPl();
		ArrayList<ProductAdmin> listFS = objPlDAO.getItemFS(index);
		ArrayList<ProductAdmin> listProNew = objPlDAO.getProductNew(index);
		ArrayList<ProductAdmin> listPoloShirt = objPlDAO.getProPS(index);
		ArrayList<ProductAdmin> listJean = objPlDAO.getProJean(index);
		ArrayList<ProductAdmin> listOffice = objPlDAO.getProOffice(index);
		ArrayList<ProductAdmin> listSport = objPlDAO.getProSport(index);
		ArrayList<ProductAdmin> listAccessories = objPlDAO.getProAccessories(index);
		
		request.setAttribute("listFS", listFS);
		request.setAttribute("listPl", listPl);
		request.setAttribute("listProNew", listProNew);
		request.setAttribute("listPoloShirt", listPoloShirt);
		request.setAttribute("listJean", listJean);
		request.setAttribute("listOffice", listOffice);
		request.setAttribute("listSport", listSport);
		request.setAttribute("listAccessories", listAccessories);
		
		RequestDispatcher rd = request.getRequestDispatcher("/public/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
