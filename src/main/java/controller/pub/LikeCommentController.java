package controller.pub;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.CommentRate;
import model.bean.User;
import model.dao.CommentRateDAO;

import java.io.IOException;
import java.util.ArrayList;


public class LikeCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LikeCommentController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	public boolean equalsIpAddress(String ip_address, String[] list_ip_adress) {
		for (int i=0; i<list_ip_adress.length; i++) {
			if(list_ip_adress[i].equals(ip_address)) {
				return true;
			}
		}
		return false;
	}
	public boolean equalsUserId(String id, String[] list_user_id) {
		for(int i =0; i<list_user_id.length; i++) {
			if(list_user_id[i].equals(id)) {
				return true;
			}
		}
		return false;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		LikeCommentController objLikeComment = new LikeCommentController();
		User objU = new User();
		int id_user = 0;
		
		if(session.getAttribute("objU")!=null) {
			objU = (User) session.getAttribute("objU");
			id_user = objU.getId();
		}else {
			id_user = 0;
		}
		String id_user_str = String.valueOf(id_user);
		int id_comment = Integer.parseInt(request.getParameter("aid"));
		String ip_address = request.getParameter("aip_address");
		CommentRateDAO objCmtRDAO = new CommentRateDAO();
		CommentRate objCmtR = new CommentRate();
		objCmtR = objCmtRDAO.getCmtById(id_comment);
		int like_comment=0;
		if("".equals(objCmtR.getId_user_like())==false&&"".equals(objCmtR.getIp_like())==false) {
			String user_like = objCmtR.getId_user_like();
			String [] list_user_like = user_like.split(",");
			String ip_like = objCmtR.getIp_like();
			String[] list_ip_like = ip_like.split(",");
			if(id_user!=0) {
				if(objLikeComment.equalsUserId(id_user_str, list_user_like)&&objLikeComment.equalsIpAddress(ip_address, list_ip_like)==false) {
					ip_like = ip_like+","+ip_address;
					int result = objCmtRDAO.addLikeByIpLike(id_comment, ip_like);
					if(result>0) {
						objCmtR = objCmtRDAO.getCmtById(id_comment);
						like_comment = objCmtR.getLike_coment();
						response.getWriter().print("<svg class=\"icon-useful\" version=\"1.1\" viewBox=\"0 0 30 30\" xml:space=\"preserve\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">\r\n"
								+ "									                    		<path d=\"M4,25h2l0,0c1.281,1.281,3.017,2,4.828,2H21l2-2v-4l0.5-10H14c0,0,1-3.266,1-4c0-2.251,0-5-3-5c-1,0-1,0-1,0l-0.501,3.491  L8.132,9.894C7.435,11.191,6.082,12,4.609,12H4V25z\"></path></svg>\r\n");
						if(like_comment>0) {
																	response.getWriter().print(""+like_comment+" Hữu ích");
						}else {
																	response.getWriter().print("Hữu ích");
						}
					}
				}
					if(objLikeComment.equalsUserId(id_user_str, list_user_like)==false&&objLikeComment.equalsIpAddress(ip_address, list_ip_like)) {
						user_like = user_like+","+id_user_str;
						int result = objCmtRDAO.addLikeByIdUser(id_comment, user_like);
						if(result>0) {
							objCmtR = objCmtRDAO.getCmtById(id_comment);
							like_comment = objCmtR.getLike_coment();
							response.getWriter().print("<svg class=\"icon-useful\" version=\"1.1\" viewBox=\"0 0 30 30\" xml:space=\"preserve\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">\r\n"
									+ "									                    		<path d=\"M4,25h2l0,0c1.281,1.281,3.017,2,4.828,2H21l2-2v-4l0.5-10H14c0,0,1-3.266,1-4c0-2.251,0-5-3-5c-1,0-1,0-1,0l-0.501,3.491  L8.132,9.894C7.435,11.191,6.082,12,4.609,12H4V25z\"></path></svg>\r\n");
							if(like_comment>0) {
																		response.getWriter().print(""+like_comment+" Hữu ích");
							}else {
																		response.getWriter().print("Hữu ích");
							}
						}
					}
					if(objLikeComment.equalsUserId(id_user_str, list_user_like)==false&&objLikeComment.equalsIpAddress(ip_address, list_ip_like)==false) {
						ip_like = ip_like+","+ip_address;
						user_like = user_like+","+id_user_str;
						int result = objCmtRDAO.addLikeByIdUserIp(id_comment, user_like, ip_like);
						if(result>0) {
							objCmtR = objCmtRDAO.getCmtById(id_comment);
							like_comment = objCmtR.getLike_coment();
							response.getWriter().print("<svg class=\"icon-useful\" version=\"1.1\" viewBox=\"0 0 30 30\" xml:space=\"preserve\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">\r\n"
									+ "									                    		<path d=\"M4,25h2l0,0c1.281,1.281,3.017,2,4.828,2H21l2-2v-4l0.5-10H14c0,0,1-3.266,1-4c0-2.251,0-5-3-5c-1,0-1,0-1,0l-0.501,3.491  L8.132,9.894C7.435,11.191,6.082,12,4.609,12H4V25z\"></path></svg>\r\n");
							if(like_comment>0) {
																		response.getWriter().print(""+like_comment+" Hữu ích");
							}else {
																		response.getWriter().print("Hữu ích");
							}
						}
					}
						
						if(objLikeComment.equalsUserId(id_user_str, list_user_like)&&objLikeComment.equalsIpAddress(ip_address, list_ip_like)) {
							objCmtR = objCmtRDAO.getCmtById(id_comment);
							like_comment = objCmtR.getLike_coment();
							response.getWriter().print("<svg class=\"icon-useful\" version=\"1.1\" viewBox=\"0 0 30 30\" xml:space=\"preserve\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">\r\n"
									+ "									                    		<path d=\"M4,25h2l0,0c1.281,1.281,3.017,2,4.828,2H21l2-2v-4l0.5-10H14c0,0,1-3.266,1-4c0-2.251,0-5-3-5c-1,0-1,0-1,0l-0.501,3.491  L8.132,9.894C7.435,11.191,6.082,12,4.609,12H4V25z\"></path></svg>\r\n");
							if(like_comment>0) {
																		response.getWriter().print(""+like_comment+" Hữu ích");
							}else {
																		response.getWriter().print("Hữu ích");
							}
						}
			}
		}else {
			if("".equals(objCmtR.getId_user_like())&&"".equals(objCmtR.getIp_like())) {
				String ip_like = objCmtR.getIp_like()+","+ip_address;
				String user_like = objCmtR.getId_user_like()+","+id_user_str;
				int result = objCmtRDAO.addLikeByIdUserIp(id_comment, user_like, ip_like);
				if(result>0) {
					objCmtR = objCmtRDAO.getCmtById(id_comment);
					like_comment = objCmtR.getLike_coment();
					response.getWriter().print("<svg class=\"icon-useful\" version=\"1.1\" viewBox=\"0 0 30 30\" xml:space=\"preserve\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">\r\n"
							+ "									                    		<path d=\"M4,25h2l0,0c1.281,1.281,3.017,2,4.828,2H21l2-2v-4l0.5-10H14c0,0,1-3.266,1-4c0-2.251,0-5-3-5c-1,0-1,0-1,0l-0.501,3.491  L8.132,9.894C7.435,11.191,6.082,12,4.609,12H4V25z\"></path></svg>\r\n");
					if(like_comment>0) {
																response.getWriter().print(""+like_comment+" Hữu ích");
					}else {
																response.getWriter().print("Hữu ích");
					}
				}
			}
		}
		 
	}

}
