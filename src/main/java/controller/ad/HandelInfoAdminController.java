package controller.ad;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.Admin;
import model.dao.AdminDAO;

import java.io.IOException;
import java.util.ArrayList;


public class HandelInfoAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public HandelInfoAdminController() {
        super();
    }
    public boolean equalsEmail(String email, ArrayList<Admin> listUs) {
    	for (Admin objUs : listUs) {
			if(email.equals(objUs.getEmail())) {
				return true;
			}
		}
    	return false;
    	
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=0;
		if(request.getParameter("aid")!=null) {
			id= Integer.parseInt(request.getParameter("aid"));
		}
		HandelInfoAdminController objProfileAdmin = new HandelInfoAdminController();
		AdminDAO objAdDAO = new AdminDAO();
		ArrayList<Admin> listAdUnById = objAdDAO.getListAd(id);
		Admin objAd = objAdDAO.getUsById(id);
		if(request.getParameter("check-email")!=null) {
			String email = request.getParameter("check-email");
			if(objProfileAdmin.equalsEmail(email, listAdUnById)) {
				response.getWriter().print("<p style=\"color: red\">Email đã tồn tại</p>");
			}
		}
		if(request.getParameter("acpass")!=null) {
			String password = request.getParameter("acpass");
			if(password.equals(objAd.getPassword())==false) {
				response.getWriter().print("<p style=\"color: red\">Password chưa chính xác. Kiểm tra lại.</p>");
			}
		}
		if(request.getParameter("bnpass")!=null&&request.getParameter("bvpass")!=null) {
			if(request.getParameter("bnpass").equals(request.getParameter("bvpass"))==false) {
				response.getWriter().print("<p style=\"color: red\">Mật khẩu không khớp.</p>");
			}
			
		}
		if(request.getParameter("ccpass")!=null&&request.getParameter("cnpass")!=null) {
			if(request.getParameter("ccpass").equals(request.getParameter("cnpass"))) {
				response.getWriter().print("<p style=\"color: red\">Mật khẩu mới không được trùng với mật khẩu cũ.</p>");
			}
		}
		if(request.getParameter("cpass")!=null&&request.getParameter("npass")!=null) {
			String cpass = request.getParameter("cpass");
			String npass = request.getParameter("npass");
			if(cpass.equals(npass)==false) {
				if(cpass.equals(objAd.getPassword())==true) {
					int result = objAdDAO.updatePassword(id, npass);
					if(result>0) {
						response.getWriter().print("<script>alert('Thành công!');</script>");
					}else {
						response.getWriter().print("<p style=\"color: red\">Cập nhật password thất bại.</p>");
					}
				}else {
					response.getWriter().print("<p style=\"color: red\">Cập nhật password thất bại do mật khẩu cũ không chính xác.</p>");
				}
			}else {
				response.getWriter().print("<p style=\"color: red\">Cập nhật password thất bại do mật khẩu cũ với mật khẩu mới trùng nhau.</p>");
			}
		}
	}

}
