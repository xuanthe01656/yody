package controller.auth;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.User;
import model.dao.UserDAO;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AuthSignUpPublicController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AuthSignUpPublicController() {
        super();
    }
    public boolean equalEmail(ArrayList<User> listUs,String email) {
    	for (User user : listUs) {
			if(user.getEmail().equals(email)) {
				return true;
			}
		}
    	return false;
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/auth/auth-sign-up-pub.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		UserDAO userDAO = new UserDAO();
		ArrayList<User> listUs = userDAO.getUserAc();
		AuthSignUpPublicController obj = new AuthSignUpPublicController();
		if(obj.equalEmail(listUs, request.getParameter("email"))==false) {
			String password = request.getParameter("password");
			String fullname = request.getParameter("fullname");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String created_date = sdf.format(date);
			User objU = new User(0, fullname, password,email,phone,"","",created_date,"","",0);
			UserDAO objUDAO = new UserDAO();
			int result =  objUDAO.addUser(objU);
			if(result>0) {
				response.sendRedirect(request.getContextPath()+"/auth/public/sign-up?msg=1");
				
			}else {
				response.sendRedirect(request.getContextPath()+"/auth/public/sign-up?msg=2");
			}
		}else {
			response.sendRedirect(request.getContextPath()+"/auth/public/sign-up?msg=3");
		}
		
		
	}

}
