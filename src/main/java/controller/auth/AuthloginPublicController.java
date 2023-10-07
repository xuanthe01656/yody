package controller.auth;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.User;
import model.dao.UserDAO;

import java.io.IOException;
import java.util.ArrayList;

public class AuthloginPublicController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final UserDAO userDAO = new UserDAO();
    public AuthloginPublicController() {
        super();
    }
    public boolean equalsEmail(ArrayList<User> list, String email) {
		for (User user : list) {
			if(user.getEmail().equals(email)) {
				return true;
			}
		}
	return false;
}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/auth/auth-sign-in-pub.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<User> listUser = new ArrayList<>();
		AuthloginPublicController obj = new AuthloginPublicController();
		User user = new User();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		ArrayList<User> lUser = userDAO.exisUser(email, password);
		User userLogin = (User)session.getAttribute("objU");
		if(userLogin!=null) {
			response.sendRedirect(request.getContextPath()+"/public/index");
			return;
		}
		if(lUser.size()>0) {
			for (int i = 0; i < lUser.size(); i++) {
				if(lUser.get(i).getEmail().equals(email)&&lUser.get(i).getPassword().equals(password)) {
					user = lUser.get(i);				
				}else {
					listUser.add(lUser.get(i));
				}
			}	
		}else {
			response.sendRedirect(request.getContextPath()+"/auth/public/login?msg=2");
			return;
		}
		if(user.getEmail()!=null&&user.getPassword()!=null) {
			session.setAttribute("objU", user);
			response.sendRedirect(request.getContextPath()+"/public/index");
		}else {
			if(obj.equalsEmail(listUser, email)) {
				response.sendRedirect(request.getContextPath()+"/auth/public/login?msg=1");
				return;
			}else {
				if(obj.equalsEmail(listUser, email)==false) {
					response.sendRedirect(request.getContextPath()+"/auth/public/login?msg=2");
					return;
				}			
			}
		}
	}

}
