package controller.auth;

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

public class AuthLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDAO adminDAO;
       
    public AuthLoginController() {
        super();
        adminDAO = new AdminDAO();
    }
    public boolean equalsUser(ArrayList<Admin> list, String username) {
		for (Admin user : list) {
			if(user.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/admin/index");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/auth/auth-sign-in.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<Admin> listUser = new ArrayList<>();
		AuthLoginController obj = new AuthLoginController();
		Admin user = new Admin();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		ArrayList<Admin> lUser = adminDAO.exisUser(username, password);
		Admin userLogin = (Admin)session.getAttribute("objUAd");
		if(userLogin!=null) {
			response.sendRedirect(request.getContextPath()+"/admin/index");
			return;
		}
		if(lUser.size()>0) {
			for (int i = 0; i < lUser.size(); i++) {
				if(lUser.get(i).getUsername().equals(username)&&lUser.get(i).getPassword().equals(password)) {
					user = lUser.get(i);				
				}else {
					listUser.add(lUser.get(i));
				}
			}	
		}else {
			response.sendRedirect(request.getContextPath()+"/auth/login?msg=2");
			return;
		}
		if(user.getUsername()!=null&&user.getPassword()!=null) {
			session.setAttribute("objUAd", user);
			response.sendRedirect(request.getContextPath()+"/admin/index");
		}else {
			if(obj.equalsUser(listUser, username)) {
				response.sendRedirect(request.getContextPath()+"/auth/login?msg=1");
				return;
			}else {
				if(obj.equalsUser(listUser, username)==false) {
					response.sendRedirect(request.getContextPath()+"/auth/login?msg=2");
					return;
				}			
			}
		}
	}

}
