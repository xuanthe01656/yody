package controller.ad;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.UploadFileLB;
import model.bean.Menu;
import model.dao.MenuDAO;

import java.io.IOException;
import java.util.ArrayList;

public class handelMenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public handelMenuController() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("amenu").equals("")==false&&request.getParameter("amenu")!="") {
			int menu = Integer.parseInt(request.getParameter("amenu"));
			MenuDAO objM = new MenuDAO();
			ArrayList<Menu> listChilMenu1 = objM.getItemChilMenu(menu);
			response.getWriter().print("<option value=''>--Chọn Menu Detail--</option>\r\n");
			for (Menu menu2 : listChilMenu1) {
				response.getWriter().print("<option value="+menu2.getId()+">"+menu2.getName()+"</option>\r\n");
			}
		}else {
			response.getWriter().print("<option value=''>Không Có Dữ Liệu</option>\r\n");
		};
		
	}
}
