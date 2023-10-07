package controller.ad;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.Menu;
import model.dao.MenuDAO;

import java.io.IOException;
import java.util.ArrayList;


public class HandelMenu1Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HandelMenu1Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("amenu1").equals("")==false&&request.getParameter("amenu1")!="") {
			int menu1 = Integer.parseInt(request.getParameter("amenu1"));
			MenuDAO objM = new MenuDAO();
			ArrayList<Menu> listChilMenu2 = objM.getItemChilMenu(menu1);
			response.getWriter().print("<option value=''>--Chọn Category--</option>\r\n");
			for (Menu menu3 : listChilMenu2) {
				response.getWriter().print("<option value="+menu3.getId()+">"+menu3.getName()+"</option>\r\n");
			}
		}else {
			response.getWriter().print("<option value=''>Không Có Dữ Liệu</option>\r\n");
		}
	}

}
