package controller.ad;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import library.AuthUtil;
import model.bean.Admin;
import model.bean.User;
import model.dao.UserDAO;

import java.io.IOException;
import java.util.ArrayList;

public class EditCustomersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditCustomersController() {
        super();
    }
    public boolean equalsEmail(String email, ArrayList<User> listUs) {
    	for (User admin : listUs) {
			if(email.equals(admin.getEmail())) {
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
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		UserDAO objUsDAO = new UserDAO();
		User objUs = objUsDAO.getUserByID(id);
		request.setAttribute("objUs", objUs);
		session.setAttribute("id", id);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/backend/page-edit-customers.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("id");
		UserDAO objUsDAO = new UserDAO();
		ArrayList<User> listUs = objUsDAO.getListUs(id);
		EditCustomersController objEdit = new EditCustomersController();
		if(request.getParameter("aemail")!=null) {
			String email = request.getParameter("aemail");
			if(objEdit.equalsEmail(email, listUs)) {
				response.getWriter().print("<p style=\"color: red\">Email đã tồn tại</p>");
			}else {
				response.getWriter().print("<p style=\"color: green\">Email được phép dùng</p>");
			}
			
		}
	}

}
