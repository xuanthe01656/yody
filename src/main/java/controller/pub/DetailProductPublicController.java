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

public class DetailProductPublicController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DetailProductPublicController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		PublicDAO objPlDAO = new PublicDAO();
		DisplayPublic objPl = objPlDAO.getObjPl(id);
		request.setAttribute("objPl", objPl);
		
		int index = 0;
		if(request.getParameter("index")==null) {
			index=1;
		}else {
			index= Integer.parseInt(request.getParameter("index"));
		}
		if(id==1) {
			
			ArrayList<ProductAdmin> listFS = objPlDAO.getItemFS(index);	
			int total = objPlDAO.getNumProFS();
			request.setAttribute("total", total);
			request.setAttribute("listPro", listFS);
			request.setAttribute("index", index);
			RequestDispatcher rd = request.getRequestDispatcher("/public/detailProductPublic.jsp");
			rd.forward(request, response);
			
		}else {
			if(id==2) {
				
				ArrayList<ProductAdmin> listProNew = objPlDAO.getProductNew(index);
				int total = objPlDAO.getNumProNew();
				request.setAttribute("total", total);
				request.setAttribute("listPro", listProNew);
				request.setAttribute("index", index);
				RequestDispatcher rd = request.getRequestDispatcher("/public/detailProductPublic.jsp");
				rd.forward(request, response);
				
			}else {
				if(id==3) {
					
					request.setAttribute("index", index);
					
				}else {
					if(id==4) {
						
						ArrayList<ProductAdmin> listPoloShirt = objPlDAO.getProPS(index);
						request.setAttribute("listPro", listPoloShirt);
						request.setAttribute("index", index);
						int total = objPlDAO.getNumProPS();
						request.setAttribute("total", total);
						RequestDispatcher rd = request.getRequestDispatcher("/public/detailProductPublic.jsp");
						rd.forward(request, response);
						
						
						
					}else {
						if(id==5) {
							
							ArrayList<ProductAdmin> listJean = objPlDAO.getProJean(index);
							request.setAttribute("listPro", listJean);
							request.setAttribute("index", index);
							int total = objPlDAO.getNumProJean();
							request.setAttribute("total", total);
							RequestDispatcher rd = request.getRequestDispatcher("/public/detailProductPublic.jsp");
							rd.forward(request, response);
							
							
						}else {
							if(id==6) {
								
								ArrayList<ProductAdmin> listOffice = objPlDAO.getProOffice(index);
								request.setAttribute("listPro", listOffice);
								request.setAttribute("index", index);
								int total = objPlDAO.getNumProOffice();
								request.setAttribute("total", total);
								RequestDispatcher rd = request.getRequestDispatcher("/public/detailProductPublic.jsp");
								rd.forward(request, response);
								
							}else {
								if(id==7) {
									
									ArrayList<ProductAdmin> listSport = objPlDAO.getProSport(index);
									request.setAttribute("listPro", listSport);
									request.setAttribute("index", index);
									int total = objPlDAO.getNumProSport();
									request.setAttribute("total", total);
									RequestDispatcher rd = request.getRequestDispatcher("/public/detailProductPublic.jsp");
									rd.forward(request, response);
									
								}else {
									if(id==8) {
										
										ArrayList<ProductAdmin> listAccessories = objPlDAO.getProAccessories(index);
										request.setAttribute("listPro", listAccessories);
										request.setAttribute("index", index);
										int total = objPlDAO.getNumProAccessories();
										request.setAttribute("total", total);
										RequestDispatcher rd = request.getRequestDispatcher("/public/detailProductPublic.jsp");
										rd.forward(request, response);
										
									}
								}
							}
						}
					}
				}
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
