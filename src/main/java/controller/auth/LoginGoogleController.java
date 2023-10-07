package controller.auth;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.GoogleLb;
import model.bean.GoogleUser;

public class LoginGoogleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginGoogleController() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		System.out.println("Code: "+code+",,");
	    if (code == null || code.isEmpty()) {
	      RequestDispatcher rd = request.getRequestDispatcher("/auth/auth-sign-in-pub.jsp");
	      rd.forward(request, response);
	    } else {
	      String accessToken = GoogleLb.getToken(code);
	      System.out.println("asscess: "+accessToken);
	      GoogleUser objU = GoogleLb.getUserInfo(accessToken);
	      request.setAttribute("objU", objU);
	      RequestDispatcher rd = request.getRequestDispatcher("public/index.jsp");
	      rd.forward(request, response);
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
