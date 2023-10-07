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
import model.dao.AdminDAO;

import java.io.IOException;
import java.util.ArrayList;


public class EditUsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public EditUsersController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/admin/index");
			return;
		}
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		AdminDAO objAdDAO = new AdminDAO();
		Admin objAd = objAdDAO.getUsById(id);
		request.setAttribute("objAd", objAd);
		session.setAttribute("id", id);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/backend/page-edit-users.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AdminDAO objADDAO = new AdminDAO();
		int id = (int) session.getAttribute("id");
		ArrayList<Admin> listAd = objADDAO.getListAd(id);
		AddUsersController objAdd = new AddUsersController();
		if(request.getParameter("aemail")!=null) {
			String email = request.getParameter("aemail");
			if(objAdd.equalsEmail(email, listAd)) {
				response.getWriter().print("<p style=\"color: red\">Email đã tồn tại</p>");
			}else {
				response.getWriter().print("<p style=\"color: green\">Email được phép dùng</p>");
			}
			
		}
		if(request.getParameter("ausername")!=null&&request.getParameter("ausername").length()>=5) {
			String username = request.getParameter("ausername");
			if(objAdd.equalsUsername(username, listAd)) {
				response.getWriter().print("<p style=\"color: red\">Username đã tồn tại</p>");
			}else {
				response.getWriter().print("<p style=\"color: green\">Username được phép dùng</p>");
			}
		}
		if(request.getParameter("fullname")!=null&&request.getParameter("phone")!=null&&request.getParameter("email")!=null&&request.getParameter("username")!=null&&request.getParameter("password")!=null&&request.getParameter("position")!=null&&request.getParameter("position_detail")!=null) {
			
			String fullname = request.getParameter("fullname");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			int position_id = Integer.parseInt(request.getParameter("position"));
			String position = request.getParameter("position_detail");
			int result = objADDAO.editUser(id,fullname, phone, email, username, password, position_id, position);
			if(result>0) {
				response.sendRedirect(request.getContextPath()+"/admin/list-users?msg=1");
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/list-users?msg=2");
			}
		};
		
	}

}
