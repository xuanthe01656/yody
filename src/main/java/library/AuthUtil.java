package library;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthUtil {
	public static boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		if(session.getAttribute("objUAd")!=null) {
			return true;
		}
		return false;
		
	}
	public static boolean checkLoginPublic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		if(session.getAttribute("objU")!=null) {
			return true;
		}
		return false;
		
	}
}
