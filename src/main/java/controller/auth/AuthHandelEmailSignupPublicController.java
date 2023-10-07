package controller.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.User;
import model.dao.UserDAO;

import java.io.IOException;
import java.util.ArrayList;

public class AuthHandelEmailSignupPublicController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AuthHandelEmailSignupPublicController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		UserDAO userDAO = new UserDAO();
		ArrayList<User> listUs = userDAO.getUserAc();
		AuthSignUpPublicController obj = new AuthSignUpPublicController();
		String aemail = request.getParameter("aemail");
		if(aemail!=null) {
			if(aemail.length()>=11&&aemail.length()<=150) {
				if(obj.equalEmail(listUs, aemail)) {
					response.getWriter().print("<div><span style=\"background-color: yellow; color: red;\">Email đã tồn tại!</span></div>");
				}else {
					response.getWriter().print("<div><span style=\"background-color: green; color: white;\">Email được phép dùng!</span></div>");
				}
			}
		}
		
	}

}
