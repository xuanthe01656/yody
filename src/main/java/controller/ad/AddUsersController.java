package controller.ad;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.AuthUtil;
import model.bean.Admin;
import model.dao.AdminDAO;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddUsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddUsersController() {
        super();
    }
    public boolean equalsEmail(String email, ArrayList<Admin> listUsAd) {
    	for (Admin admin : listUsAd) {
			if(email.equals(admin.getEmail())) {
				return true;
			}
		}
    	return false;
    	
    }
    public boolean equalsUsername(String username, ArrayList<Admin> listUsAd) {
    	for (Admin admin : listUsAd) {
			if(username.equals(admin.getUsername())) {
				return true;
			}
		}
    	return false;
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/admin/index");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/backend/page-add-users.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/admin/index");
			return;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		AdminDAO objADDAO = new AdminDAO();
		ArrayList<Admin> listAd = objADDAO.getListAd();
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
			String created_date = sdf.format(date);
			int result = objADDAO.addUser(fullname, phone, email, username, password, position_id, position, created_date);
			if(result>0) {
				response.sendRedirect(request.getContextPath()+"/admin/add-users?msg=1");
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/add-users?msg=2");
			}
		};
		
	}

}
