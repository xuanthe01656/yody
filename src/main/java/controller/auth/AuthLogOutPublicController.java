package controller.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthLogOutPublicController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AuthLogOutPublicController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		//session.removeAttribute("ObjU");
		response.sendRedirect(request.getContextPath()+"/auth/public/login");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {// TODO Auto-generated method stub
		doGet(request, response);
	}

}
